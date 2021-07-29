package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class EnemyTargetEnhance implements Listener {
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        LivingEntity target = event.getTarget();

        if (!CreatureUtils.isEnemy(entity) || !CreatureUtils.isEnemy(target)) {
            return;
        }

        if (Utils.isInArena(entity.getLocation())) {
            event.setTarget(null);
            event.setCancelled(true);
        }
    }
}
