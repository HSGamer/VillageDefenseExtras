package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.CraftingInventory;

public class CraftPlayerInventoryFix implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getClickedInventory() instanceof CraftingInventory)) {
            return;
        }

        HumanEntity humanEntity = event.getWhoClicked();
        if (Utils.isInArena(humanEntity.getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (!(event.getInventory() instanceof CraftingInventory)) {
            return;
        }

        HumanEntity humanEntity = event.getWhoClicked();
        if (Utils.isInArena(humanEntity.getLocation())) {
            event.setCancelled(true);
        }
    }
}
