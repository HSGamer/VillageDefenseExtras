package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class WitherZombie implements RunnableZombieSpawner {
    @Override
    public Zombie createBaseZombie(Location location) {
        Zombie zombie = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        zombie.getEquipment().setHelmet(XMaterial.WITHER_SKELETON_SKULL.parseItem());
        zombie.getEquipment().setHelmetDropChance(0.0F);
        return zombie;
    }

    @Override
    public void onTick(Zombie zombie) {
        if (zombie.getTarget() == null) {
            return;
        }
        Location location = zombie.getLocation();
        Vector power = location.getDirection().multiply(MainConfig.ZOMBIE_WITHER_SHOOT_POWER.getValue());
        boolean charged = MainConfig.ZOMBIE_WITHER_CHARGED.getValue();
        Bukkit.getScheduler().runTask(VillageDefenseExtras.getInstance(), () -> {
            WitherSkull skull = zombie.launchProjectile(WitherSkull.class, power);
            skull.setCharged(charged);
        });
    }

    @Override
    public long getPeriod() {
        return MainConfig.ZOMBIE_WITHER_SHOOT_DELAY.getValue();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getName() {
        return "WitherZombie";
    }

    @Override
    public int getMinWave() {
        return MainConfig.ZOMBIE_WITHER_WAVE.getValue();
    }

    @Override
    public double getSpawnRate(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_WITHER_RATE.getValue();
    }

    @Override
    public boolean checkPhase(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_WITHER_PHASE.getValue().contains(phase);
    }

    @Override
    public int getFinalAmount(Arena arena, int wave, int phase, int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_WITHER_AMOUNT.getValue());
    }
}
