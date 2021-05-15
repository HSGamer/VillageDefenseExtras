package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import plugily.projects.villagedefense.creatures.CreatureUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;

import java.util.List;

public class TeleporterZombie implements RunnableZombieSpawner {
    @Override
    public Zombie createBaseZombie(Location location) {
        Zombie zombie = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        VersionUtils.setItemInHand(zombie, XMaterial.ENDER_PEARL.parseItem());
        VersionUtils.setItemInHandDropChance(zombie, 0.0F);
        return zombie;
    }

    @Override
    public void onTick(Zombie zombie) {
        LivingEntity target = zombie.getTarget();
        if (target == null) {
            return;
        }
        Location location = target.getLocation();
        double y = location.getY();
        location = location.subtract(target.getEyeLocation().getDirection().multiply(MainConfig.ZOMBIE_TELEPORTER_DISTANCE.getValue()));
        location.setY(y + 0.25);
        Location finalLocation = location;
        zombie.teleport(finalLocation);
    }

    @Override
    public long getPeriod() {
        return MainConfig.ZOMBIE_TELEPORTER_TELEPORT_DELAY.getValue();
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public String getName() {
        return "TELEPORTER_ZOMBIE";
    }

    @Override
    public int getMinWave() {
        return MainConfig.ZOMBIE_TELEPORTER_WAVE.getValue();
    }

    @Override
    public double getSpawnRate() {
        return MainConfig.ZOMBIE_TELEPORTER_RATE.getValue();
    }

    @Override
    public List<Integer> getSpawnPhases() {
        return MainConfig.ZOMBIE_TELEPORTER_PHASE.getValue();
    }

    @Override
    public int getFinalAmount(int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_TELEPORTER_AMOUNT.getValue());
    }
}
