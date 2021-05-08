package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.ZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import plugily.projects.villagedefense.creatures.CreatureUtils;

import java.util.List;

public class GhostZombie implements ZombieSpawner {
    @Override
    public String getName() {
        return "GHOST_ZOMBIE";
    }

    @Override
    public int getSpawnWave() {
        return MainConfig.ZOMBIE_GHOST_WAVE.getValue();
    }

    @Override
    public int getFinalAmount(int spawnAmount) {
        return spawnAmount - 5;
    }

    @Override
    public double getSpawnRate() {
        return MainConfig.ZOMBIE_GHOST_RATE.getValue();
    }

    @Override
    public List<Integer> getSpawnPhases() {
        return ALL_PHASES;
    }

    @Override
    public Zombie spawnZombie(Location location) {
        Zombie zombie = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        applyParticles(zombie);
        return zombie;
    }

    private void applyParticles(Zombie zombie) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (zombie.isDead()) {
                    cancel();
                    return;
                }
                Location location = zombie.getEyeLocation();
                location.getWorld().spawnParticle(Particle.FALLING_DUST, location, 1, 0.2, 0.1, 0.2, Material.STONE.createBlockData());
            }
        }.runTaskTimerAsynchronously(VillageDefenseExtras.getInstance(), 0, 2);
    }
}
