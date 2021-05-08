package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.api.listener.ArenaListener;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.CraftingInventory;

public class CraftPlayerInventoryFix implements ArenaListener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getClickedInventory() instanceof CraftingInventory)) {
            return;
        }

        HumanEntity humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player player = (Player) humanEntity;

        if (isInArena(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (!(event.getInventory() instanceof CraftingInventory)) {
            return;
        }

        HumanEntity humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player player = (Player) humanEntity;

        if (isInArena(player)) {
            event.setCancelled(true);
        }
    }
}
