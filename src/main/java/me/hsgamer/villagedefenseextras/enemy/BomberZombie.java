package me.hsgamer.villagedefenseextras.enemy;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.enemy.RunnableEnemySpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class BomberZombie implements RunnableEnemySpawner {
    @Override
    public Creature createBaseEnemy(Location location) {
        Creature creature = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        creature.getEquipment().setHelmet(XMaterial.REDSTONE_BLOCK.parseItem());
        creature.getEquipment().setHelmetDropChance(0.0F);
        VersionUtils.setItemInHand(creature, XMaterial.TNT.parseItem());
        VersionUtils.setItemInHandDropChance(creature, 0.0F);
        return creature;
    }

    @Override
    public void onTick(Creature creature) {
        Entity target = creature.getTarget();
        if (target == null) {
            return;
        }
        Location location = creature.getLocation();
        double distance = location.distance(target.getLocation());
        double y = MainConfig.ZOMBIE_BOMBER_THROW_OFFSET_Y.getValue();
        double power = Math.sqrt(Math.pow(distance / 2, 2) + Math.pow(y, 2)) / MainConfig.ZOMBIE_BOMBER_THROW_POWER_DIVIDER.getValue();
        Location eyeLocation = creature.getEyeLocation();
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
        return "BomberZombie";
    }

    @Override
    public int getMinWave() {
        return MainConfig.ZOMBIE_BOMBER_WAVE.getValue();
    }

    @Override
    public double getSpawnRate(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_BOMBER_RATE.getValue();
    }

    @Override
    public int getFinalAmount(Arena arena, int wave, int phase, int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_BOMBER_AMOUNT.getValue());
    }

    @Override
    public boolean checkPhase(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_BOMBER_PHASE.getValue().contains(phase);
    }
}
