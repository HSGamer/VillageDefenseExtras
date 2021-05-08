package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.villagedefenseextras.api.listener.ArenaListener;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AutoLapisEnchantingTableEnhance implements ArenaListener {
    private final Map<Inventory, ItemStack> inventoryMap = new ConcurrentHashMap<>();

    private Optional<ItemStack> getLapisLevel(Arena arena) {
        ItemStack lapis = XMaterial.LAPIS_LAZULI.parseItem();
        if (lapis == null) {
            throw new IllegalStateException("Lapis is null");
        }
        int wave = arena.getWave();
        if (wave >= MainConfig.ENHANCE_AUTO_LAPIS_WAVE_LEVEL_THREE.getValue()) {
            lapis.setAmount(3);
            return Optional.of(lapis);
        } else if (wave >= MainConfig.ENHANCE_AUTO_LAPIS_WAVE_LEVEL_TWO.getValue()) {
            lapis.setAmount(2);
            return Optional.of(lapis);
        } else if (wave >= MainConfig.ENHANCE_AUTO_LAPIS_WAVE_LEVEL_ONE.getValue()) {
            lapis.setAmount(1);
            return Optional.of(lapis);
        }
        return Optional.empty();
    }

    @EventHandler
    public void openInventoryEvent(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        Location location = inventory.getLocation();
        if (location == null) {
            return;
        }
        Optional<Arena> optionalArena = getArena(location);
        if (!optionalArena.isPresent()) {
            return;
        }
        World world = location.getWorld();
        if (world == null || MainConfig.ENHANCE_AUTO_LAPIS_BLACKLISTED_WORLDS.getValue().contains(world.getName())) {
            return;
        }
        if (!(inventory instanceof EnchantingInventory)) {
            return;
        }
        if (inventoryMap.containsKey(inventory)) {
            inventory.setItem(1, inventoryMap.get(inventory));
            return;
        }
        Optional<ItemStack> optionalItemStack = getLapisLevel(optionalArena.get());
        if (!optionalItemStack.isPresent()) {
            return;
        }
        ItemStack itemStack = optionalItemStack.get();
        inventory.setItem(1, itemStack);
        inventoryMap.put(inventory, itemStack);
    }

    @EventHandler
    public void closeInventoryEvent(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        if (inventoryMap.containsKey(inventory)) {
            inventoryMap.remove(inventory);
            inventory.setItem(1, null);
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) {
            return;
        }
        if (inventoryMap.containsKey(inventory) && event.getSlot() == 1) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void enchantItemEvent(EnchantItemEvent event) {
        Inventory inventory = event.getInventory();
        if (inventoryMap.containsKey(inventory)) {
            inventory.setItem(1, inventoryMap.get(inventory));
        }
    }
}
