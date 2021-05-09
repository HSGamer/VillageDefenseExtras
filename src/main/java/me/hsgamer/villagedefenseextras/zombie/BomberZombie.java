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
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Location eyeLocation = zombie.getEyeLocation();
        Vector vector = eyeLocation.getDirection().multiply(MainConfig.ZOMBIE_BOMBER_THROW_LENGTH.getValue());
        Bukkit.getScheduler().runTask(VillageDefenseExtras.getInstance(), () -> {
            Entity entity = eyeLocation.getWorld().spawnEntity(eyeLocation, EntityType.PRIMED_TNT);
            entity.setVelocity(vector);
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
        return IntStream.range(3, 5).boxed().collect(Collectors.toList());
    }
}
