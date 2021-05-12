package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;
import plugily.projects.villagedefense.creatures.CreatureUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;

import java.util.List;

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
        Vector direction = location.getDirection().multiply(2.2);
        Vector power = location.getDirection().multiply(MainConfig.ZOMBIE_WITHER_SHOOT_POWER.getValue());
        Bukkit.getScheduler().runTask(VillageDefenseExtras.getInstance(), () -> {
            Entity entity = location.getWorld().spawnEntity(location.setDirection(direction), EntityType.WITHER_SKULL);
            entity.setVelocity(power);
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
        return "WITHER_ZOMBIE";
    }

    @Override
    public int getSpawnWave() {
        return MainConfig.ZOMBIE_WITHER_WAVE.getValue();
    }

    @Override
    public double getSpawnRate() {
        return MainConfig.ZOMBIE_WITHER_RATE.getValue();
    }

    @Override
    public List<Integer> getSpawnPhases() {
        return MainConfig.ZOMBIE_WITHER_PHASE.getValue();
    }

    @Override
    public int getFinalAmount(int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_WITHER_AMOUNT.getValue());
    }
}
