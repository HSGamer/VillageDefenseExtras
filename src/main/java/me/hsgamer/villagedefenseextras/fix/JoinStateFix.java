package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.api.event.game.VillageGameJoinAttemptEvent;
import plugily.projects.villagedefense.arena.ArenaState;

public class JoinStateFix implements Listener {
    @EventHandler
    public void onGameJoin(VillageGameJoinAttemptEvent event) {
        ArenaState state = event.getArena().getArenaState();
        if (state == ArenaState.ENDING || state == ArenaState.RESTARTING) {
            MessageUtils.sendMessage(event.getPlayer(), MessageConfig.FIX_JOIN_STATE_CANNOT_JOIN.getValue());
            event.setCancelled(true);
        }
    }
}
