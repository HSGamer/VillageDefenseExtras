package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.arena.ArenaState;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.events.api.CBPlayerInteractEvent;

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
