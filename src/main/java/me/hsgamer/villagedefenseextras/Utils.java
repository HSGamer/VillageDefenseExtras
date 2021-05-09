package me.hsgamer.villagedefenseextras;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaRegistry;
import plugily.projects.villagedefense.user.User;

import java.util.Optional;

public class Utils {
    private Utils() {
        // EMPTY
    }

    public static boolean isInArena(Location location) {
        return ArenaRegistry.getArenas().parallelStream().anyMatch(arena -> location.getWorld().equals(arena.getStartLocation().getWorld()));
    }

    public static Optional<Arena> getArena(Location location) {
        return ArenaRegistry.getArenas().parallelStream().filter(arena -> location.getWorld().equals(arena.getStartLocation().getWorld())).findAny();
    }

    public static boolean isInArena(Player player) {
        return ArenaRegistry.isInArena(player);
    }

    public static Optional<Arena> getArena(Player player) {
        return Optional.ofNullable(ArenaRegistry.getArena(player));
    }

    public static User getUser(Player player) {
        return VillageDefenseExtras.getInstance().getParentPlugin().getUserManager().getUser(player);
    }
}
