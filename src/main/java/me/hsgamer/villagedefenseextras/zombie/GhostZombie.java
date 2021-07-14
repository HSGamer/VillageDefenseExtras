package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugily.projects.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class GhostZombie implements RunnableZombieSpawner {

    @Override
    public String getName() {
        return "GhostZombie";
    }

    @Override
    public int getMinWave() {
        return MainConfig.ZOMBIE_GHOST_WAVE.getValue();
    }

    @Override
    public double getSpawnRate(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_GHOST_RATE.getValue();
    }

    @Override
    public int getFinalAmount(Arena arena, int wave, int phase, int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_GHOST_AMOUNT.getValue());
    }

    @Override
    public boolean checkPhase(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_GHOST_PHASE.getValue().contains(phase);
    }

    @Override
    public Zombie createBaseZombie(Location location) {
        Zombie zombie = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        return zombie;
    }

    @Override
    public void onTick(Zombie zombie) {
        VersionUtils.sendParticles("SMOKE_NORMAL", null, zombie.getEyeLocation(), 1, 0.2, 0.1, 0.2);
        VersionUtils.sendParticles("SMOKE_NORMAL", null, zombie.getLocation(), 1, 0.2, 0.1, 0.2);
        VersionUtils.sendParticles("DRIP_LAVA", null, zombie.getLocation(), 0, 0.0D, 0.0D, 0.0D);
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
