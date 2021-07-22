package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import plugily.projects.villagedefense.arena.ArenaState;

public class BlockListenerFix implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlace(BlockPlaceEvent event) {
        Utils.getArena(event.getPlayer()).ifPresent(arena -> {
            if (arena.getArenaState() != ArenaState.IN_GAME) {
                event.setCancelled(true);
            }
        });
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent event) {
        Utils.getArena(event.getPlayer()).ifPresent(arena -> {
            if (arena.getArenaState() != ArenaState.IN_GAME) {
                event.setCancelled(true);
            }
        });
    }
}
