package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.villagedefenseextras.Utils;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaState;
import plugily.projects.villagedefense.creatures.CreatureUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyTargetOnSpawnEnhance implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!(CreatureUtils.isEnemy(entity))) {
            return;
        }
        if (((Creature) entity).getTarget() != null) {
            return;
        }
        Optional<Arena> optionalArena = Utils.getArena(entity.getLocation());
        if (!optionalArena.isPresent()) {
            return;
        }
        Arena arena = optionalArena.get();
        if (arena.getArenaState() != ArenaState.IN_GAME) {
            return;
        }
        LivingEntity target = null;
        if (ThreadLocalRandom.current().nextDouble() < MainConfig.ENHANCE_ENEMY_TARGET_ON_PLAYER.getValue()) {
            List<Player> players = arena.getPlayersLeft();
            if (!players.isEmpty()) {
                target = players.get(ThreadLocalRandom.current().nextInt(players.size()));
            }
        } else if (ThreadLocalRandom.current().nextDouble() < MainConfig.ENHANCE_ENEMY_TARGET_ON_VILLAGER.getValue()) {
            List<Villager> villagerList = arena.getVillagers();
            if (!villagerList.isEmpty()) {
                target = villagerList.get(ThreadLocalRandom.current().nextInt(villagerList.size()));
            }
        }
        ((Creature) entity).setTarget(target);
    }
}
