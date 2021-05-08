package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.api.listener.ArenaListener;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class DefenseTargetFix implements ArenaListener {
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        LivingEntity target = event.getTarget();

        if (!(entity instanceof Wolf) && !(entity instanceof IronGolem)) {
            return;
        }

        if (isInArena(entity.getLocation()) && target instanceof Villager) {
            event.setTarget(null);
            event.setCancelled(true);
        }
    }
}
