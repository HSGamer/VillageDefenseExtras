package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugily.projects.villagedefense.creatures.CreatureUtils;

import java.util.List;

public class GhostZombie implements RunnableZombieSpawner {
    private final BlockData data = Material.COBBLESTONE.createBlockData();

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
        return Math.min(spawnAmount, MainConfig.ZOMBIE_GHOST_AMOUNT.getValue());
    }

    @Override
    public double getSpawnRate() {
        return MainConfig.ZOMBIE_GHOST_RATE.getValue();
    }

    @Override
    public List<Integer> getSpawnPhases() {
        return MainConfig.ZOMBIE_GHOST_PHASE.getValue();
    }

    @Override
    public Zombie createBaseZombie(Location location) {
        Zombie zombie = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        return zombie;
    }

    @Override
    public void onTick(Zombie zombie) {
        Location eyeLocation = zombie.getEyeLocation();
        eyeLocation.getWorld().spawnParticle(Particle.FALLING_DUST, eyeLocation, 1, 0.2, 0.1, 0.2, data);
        Location location = zombie.getLocation();
        location.getWorld().spawnParticle(Particle.DRIP_LAVA, location, 0);
    }

    @Override
    public long getPeriod() {
        return 2;
    }

    @Override
    public boolean isAsync() {
        return true;
    }
}
