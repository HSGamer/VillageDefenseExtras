package me.hsgamer.villagedefenseextras.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaRegistry;

import java.util.Optional;

public interface ArenaListener extends Listener {
    default boolean isInArena(Location location) {
        return ArenaRegistry.getArenas().parallelStream().anyMatch(arena -> location.getWorld().equals(arena.getStartLocation().getWorld()));
    }

    default Optional<Arena> getArena(Location location) {
        return ArenaRegistry.getArenas().parallelStream().filter(arena -> location.getWorld().equals(arena.getStartLocation().getWorld())).findAny();
    }

    default boolean isInArena(Player player) {
        return ArenaRegistry.isInArena(player);
    }

    default Optional<Arena> getArena(Player player) {
        return Optional.ofNullable(ArenaRegistry.getArena(player));
    }
}
