package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;
import plugily.projects.villagedefense.creatures.CreatureUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;

import java.util.List;

public class BomberZombie implements RunnableZombieSpawner {
    @Override
    public Zombie createBaseZombie(Location location) {
        Zombie zombie = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        zombie.getEquipment().setHelmet(XMaterial.REDSTONE_BLOCK.parseItem());
        zombie.getEquipment().setHelmetDropChance(0.0F);
        VersionUtils.setItemInHand(zombie, XMaterial.TNT.parseItem());
        VersionUtils.setItemInHandDropChance(zombie, 0.0F);
        return zombie;
    }

    @Override
    public void onTick(Zombie zombie) {
        Entity target = zombie.getTarget();
        if (target == null) {
            return;
        }
        Location location = zombie.getLocation();
        double distance = location.distance(target.getLocation());
        double y = MainConfig.ZOMBIE_BOMBER_THROW_OFFSET_Y.getValue();
        double power = Math.sqrt(Math.pow(distance / 2, 2) + Math.pow(y, 2)) / MainConfig.ZOMBIE_BOMBER_THROW_POWER_DIVIDER.getValue();
        Location eyeLocation = zombie.getEyeLocation();
        Vector vector = eyeLocation.getDirection()
                .add(new Vector(0, y, 0))
                .normalize()
                .multiply(power * MainConfig.ZOMBIE_BOMBER_THROW_LENGTH_RATE.getValue());
        int fuseTicks = MainConfig.ZOMBIE_BOMBER_FUSE_TICKS.getValue();
        Bukkit.getScheduler().runTask(VillageDefenseExtras.getInstance(), () -> {
            Entity entity = eyeLocation.getWorld().spawnEntity(eyeLocation, EntityType.PRIMED_TNT);
            entity.setVelocity(vector);
            ((TNTPrimed) entity).setFuseTicks(fuseTicks);
        });
    }

    @Override
    public long getPeriod() {
        return MainConfig.ZOMBIE_BOMBER_THROW_DELAY.getValue();
    }

    @Override
    public long getDelay() {
        return MainConfig.ZOMBIE_BOMBER_THROW_DELAY.getValue();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getName() {
        return "BOMBER_ZOMBIE";
    }

    @Override
    public int getFinalAmount(int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_BOMBER_AMOUNT.getValue());
    }

    @Override
    public int getSpawnWave() {
        return MainConfig.ZOMBIE_BOMBER_WAVE.getValue();
    }

    @Override
    public double getSpawnRate() {
        return MainConfig.ZOMBIE_BOMBER_RATE.getValue();
    }

    @Override
    public List<Integer> getSpawnPhases() {
        return MainConfig.ZOMBIE_BOMBER_PHASE.getValue();
    }
}
