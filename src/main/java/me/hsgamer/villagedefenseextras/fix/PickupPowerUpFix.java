package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.listener.InstanceListener;
import org.bukkit.event.EventHandler;
import plugily.projects.villagedefense.api.event.player.VillagePlayerPowerupPickupEvent;

public class PickupPowerUpFix extends InstanceListener {

    public PickupPowerUpFix(VillageDefenseExtras instance) {
        super(instance);
    }

    @EventHandler
    public void onPickup(VillagePlayerPowerupPickupEvent event) {
        if (getUser(event.getPlayer()).isSpectator()) {
            event.setCancelled(true);
        }
    }
}
