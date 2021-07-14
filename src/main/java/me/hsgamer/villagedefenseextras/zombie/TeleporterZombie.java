package me.hsgamer.villagedefenseextras.zombie;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.zombie.RunnableZombieSpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import plugily.projects.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.commonsbox.minecraft.compat.xseries.XSound;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.creatures.CreatureUtils;

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
        Block block = finalLocation.getBlock();
        Block block2 = finalLocation.add(0, 1, 0).getBlock();
        if (block.getType() != Material.AIR || block2.getType() != Material.AIR) {
            return;
        }
        XSound.ENTITY_ENDERMAN_TELEPORT.play(zombie.getLocation());
        VersionUtils.sendParticles("PORTAL", null, zombie.getLocation(), 20, 0.5D, 0.5D, 0.5D);
        VersionUtils.sendParticles("PORTAL", null, finalLocation, 20, 0.5D, 0.5D, 0.5D);
        Bukkit.getScheduler().runTask(VillageDefenseExtras.getInstance(), () -> zombie.teleport(finalLocation));
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
        return "TeleporterZombie";
    }

    @Override
    public int getMinWave() {
        return MainConfig.ZOMBIE_TELEPORTER_WAVE.getValue();
    }

    @Override
    public double getSpawnRate(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_TELEPORTER_RATE.getValue();
    }

    @Override
    public int getFinalAmount(Arena arena, int wave, int phase, int spawnAmount) {
        return Math.min(spawnAmount, MainConfig.ZOMBIE_TELEPORTER_AMOUNT.getValue());
    }

    @Override
    public boolean checkPhase(Arena arena, int wave, int phase, int spawnAmount) {
        return MainConfig.ZOMBIE_TELEPORTER_PHASE.getValue().contains(phase);
    }
}
