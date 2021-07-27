package me.hsgamer.villagedefenseextras.enemy;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import me.hsgamer.villagedefenseextras.api.enemy.RunnableEnemySpawner;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.xseries.XSound;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class TeleporterZombie implements RunnableEnemySpawner {
    @Override
    public Creature createBaseEnemy(Location location) {
        Creature creature = CreatureUtils.getCreatureInitializer().spawnFastZombie(location);
        VersionUtils.setItemInHand(creature, XMaterial.ENDER_PEARL.parseItem());
        VersionUtils.setItemInHandDropChance(creature, 0.0F);
        return creature;
    }

    @Override
    public void onTick(Creature creature) {
        LivingEntity target = creature.getTarget();
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
        XSound.ENTITY_ENDERMAN_TELEPORT.play(creature.getLocation());
        VersionUtils.sendParticles("PORTAL", null, creature.getLocation(), 20, 0.5D, 0.5D, 0.5D);
        VersionUtils.sendParticles("PORTAL", null, finalLocation, 20, 0.5D, 0.5D, 0.5D);
        Bukkit.getScheduler().runTask(VillageDefenseExtras.getInstance(), () -> creature.teleport(finalLocation));
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
