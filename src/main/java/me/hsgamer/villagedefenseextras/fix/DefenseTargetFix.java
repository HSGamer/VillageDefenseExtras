package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.creatures.CreatureUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefenseTargetFix implements Listener {
    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();
        LivingEntity target = event.getTarget();

        if (CreatureUtils.isEnemy(entity)) {
            return;
        }

        Optional<Arena> optionalArena = Utils.getArena(entity.getLocation());
        if (optionalArena.isPresent() && !CreatureUtils.isEnemy(target)) {
            List<Creature> list = optionalArena.get().getEnemies();
            Location location = entity.getLocation();
            Optional<Creature> optionalTarget = list.parallelStream()
                    .filter(c -> Objects.equals(c.getLocation().getWorld(), location.getWorld()))
                    .min(Comparator.comparingDouble(c -> c.getLocation().distance(location)));
            if (optionalTarget.isPresent()) {
                event.setTarget(optionalTarget.get());
            } else {
                event.setCancelled(true);
            }
        }
    }
}
