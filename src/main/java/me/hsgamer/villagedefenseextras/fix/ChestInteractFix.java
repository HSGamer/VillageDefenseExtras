package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;

public class ChestInteractFix implements Listener {

    @EventHandler
    public void onInteract(CBPlayerInteractEvent event) {
        if (!Utils.isInArena(event.getPlayer().getLocation())) {
            return;
        }
        if (event.getMaterial() != Material.CHEST) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (!Utils.isInArena(event.getPlayer().getLocation())) {
            return;
        }
        InventoryHolder holder = event.getInventory().getHolder();
        if (!(holder instanceof Chest) && !(holder instanceof DoubleChest)) {
            return;
        }
        event.setCancelled(true);
    }
}
