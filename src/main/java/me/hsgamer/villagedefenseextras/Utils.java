package me.hsgamer.villagedefenseextras;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaRegistry;
import plugily.projects.villagedefense.commonsbox.minecraft.misc.stuff.ComplementAccessor;
import plugily.projects.villagedefense.handlers.language.Messages;
import plugily.projects.villagedefense.user.User;

import java.util.Optional;

public class Utils {
    private Utils() {
        // EMPTY
    }

    public static boolean isInArena(Location location) {
        return ArenaRegistry.getArenas().stream().anyMatch(arena -> location.getWorld().equals(arena.getStartLocation().getWorld()));
    }

    public static Optional<Arena> getArena(Location location) {
        return ArenaRegistry.getArenas().stream().filter(arena -> location.getWorld().equals(arena.getStartLocation().getWorld())).findFirst();
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

    public static boolean checkDisplayName(ItemStack itemStack, String name) {
        return ComplementAccessor.getComplement().getDisplayName(itemStack.getItemMeta()).equalsIgnoreCase(name);
    }

    public static boolean checkDisplayName(ItemStack itemStack, Messages message) {
        return checkDisplayName(itemStack, VillageDefenseExtras.getInstance().getParentPlugin().getChatManager().colorMessage(message));
    }
}
