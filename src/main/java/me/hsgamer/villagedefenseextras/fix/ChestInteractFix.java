package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;

public class ChestInteractFix implements Listener {

    @EventHandler()
    public void onInteract(CBPlayerInteractEvent event) {
        if (!Utils.isInArena(event.getPlayer())) {
            return;
        }
        if (event.getMaterial() != Material.CHEST) {
            return;
        }
        event.setCancelled(true);
    }
}
