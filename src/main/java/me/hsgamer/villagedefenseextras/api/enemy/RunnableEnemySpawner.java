package me.hsgamer.villagedefenseextras.api.enemy;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.scheduler.BukkitRunnable;
import plugily.projects.villagedefense.arena.managers.spawner.SimpleEnemySpawner;

import static me.hsgamer.villagedefenseextras.VillageDefenseExtras.getInstance;

public interface RunnableEnemySpawner extends SimpleEnemySpawner {
    Creature createBaseEnemy(Location location);

    void onTick(Creature creature);

    long getPeriod();

    boolean isAsync();

    default long getDelay() {
        return 0;
    }

    @Override
    default Creature spawn(Location location) {
        Creature creature = createBaseEnemy(location);
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (creature.isDead()) {
                    cancel();
                    return;
                }
                onTick(creature);
            }
        };
        if (isAsync()) {
            runnable.runTaskTimerAsynchronously(getInstance(), getDelay(), getPeriod());
        } else {
            runnable.runTaskTimer(getInstance(), getDelay(), getPeriod());
        }
        return creature;
    }
}
