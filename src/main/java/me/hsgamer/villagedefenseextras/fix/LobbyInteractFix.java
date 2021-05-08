package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.api.listener.ArenaListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import plugily.projects.villagedefense.arena.ArenaState;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;

public class LobbyInteractFix implements ArenaListener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(CBPlayerInteractEvent event) {
        getArena(event.getPlayer()).ifPresent(arena -> {
            if (arena.getArenaState() == ArenaState.IN_GAME) {
                return;
            }
            event.setCancelled(true);
        });
    }
}
