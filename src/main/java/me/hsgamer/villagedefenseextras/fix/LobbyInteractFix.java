package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import plugily.projects.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;
import plugily.projects.villagedefense.arena.ArenaState;

public class LobbyInteractFix implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(CBPlayerInteractEvent event) {
        Utils.getArena(event.getPlayer()).ifPresent(arena -> {
            if (arena.getArenaState() == ArenaState.IN_GAME) {
                return;
            }
            event.setCancelled(true);
        });
    }
}
