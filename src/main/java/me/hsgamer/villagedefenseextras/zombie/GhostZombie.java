package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.entity.Zombie;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugily.projects.villagedefense.creatures.CreatureUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.VersionUtils;

import java.util.List;

public class GhostZombie implements RunnableZombieSpawner {

    @Override
    public String getName() {
        return "GHOST_ZOMBIE";
    }

    @Override
    public int getMinWave() {
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
