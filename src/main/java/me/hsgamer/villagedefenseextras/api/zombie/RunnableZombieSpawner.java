package me.hsgamer.villagedefenseextras.api.zombie;

import org.bukkit.Location;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import plugily.projects.villagedefense.arena.managers.spawner.SimpleZombieSpawner;

import static me.hsgamer.villagedefenseextras.VillageDefenseExtras.getInstance;

public interface RunnableZombieSpawner extends SimpleZombieSpawner {
    Zombie createBaseZombie(Location location);

    void onTick(Zombie zombie);

    long getPeriod();

    boolean isAsync();

    default long getDelay() {
        return 0;
    }

    @Override
    default Zombie spawnZombie(Location location) {
        Zombie zombie = createBaseZombie(location);
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (zombie.isDead()) {
                    cancel();
                    return;
                }
                onTick(zombie);
            }
        };
        if (isAsync()) {
            runnable.runTaskTimerAsynchronously(getInstance(), getDelay(), getPeriod());
        } else {
            runnable.runTaskTimer(getInstance(), getDelay(), getPeriod());
        }
        return zombie;
    }
}
