package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.villagedefenseextras.Utils;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaState;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class ZombieTargetOnSpawnEnhance implements Listener {
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Zombie)) {
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
        List<Villager> villagerList = arena.getVillagers();
        List<Player> players = arena.getPlayersLeft();
        LivingEntity target = ThreadLocalRandom.current().nextDouble() < MainConfig.ENHANCE_ZOMBIE_TARGET_ON_PLAYER.getValue()
                ? players.get(ThreadLocalRandom.current().nextInt(players.size()))
                : villagerList.get(ThreadLocalRandom.current().nextInt(villagerList.size()));
        ((Zombie) entity).setTarget(target);
    }
}
