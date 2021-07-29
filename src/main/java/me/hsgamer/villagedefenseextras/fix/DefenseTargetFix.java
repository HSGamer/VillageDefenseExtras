package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class DefenseTargetFix implements Listener {
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        LivingEntity target = event.getTarget();

        if (CreatureUtils.isEnemy(entity)) {
            return;
        }

        if (Utils.isInArena(entity.getLocation()) && !CreatureUtils.isEnemy(target)) {
            event.setTarget(null);
            event.setCancelled(true);
        }
    }
}
