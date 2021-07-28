package me.hsgamer.villagedefenseextras.fix;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.api.event.game.VillageGameJoinAttemptEvent;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaState;

public class IngameBossbarFix implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(VillageGameJoinAttemptEvent event) {
        Arena arena = event.getArena();
        if (arena.getArenaState() == ArenaState.IN_GAME && arena.getGameBar() != null) {
            arena.doBarAction(Arena.BarAction.ADD, event.getPlayer());
        }
    }
}
