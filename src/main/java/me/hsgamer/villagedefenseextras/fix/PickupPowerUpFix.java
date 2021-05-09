package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.api.event.player.VillagePlayerPowerupPickupEvent;

public class PickupPowerUpFix implements Listener {

    @EventHandler
    public void onPickup(VillagePlayerPowerupPickupEvent event) {
        if (Utils.getUser(event.getPlayer()).isSpectator()) {
            event.setCancelled(true);
        }
    }
}
