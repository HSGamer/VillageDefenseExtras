package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.hscore.common.Validate;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.listener.ArenaListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import plugily.projects.villagedefense.api.event.game.VillageGameJoinAttemptEvent;
import plugily.projects.villagedefense.api.event.game.VillageGameLeaveAttemptEvent;
import plugily.projects.villagedefense.api.event.game.VillageGameStartEvent;
import plugily.projects.villagedefense.api.event.game.VillageGameStopEvent;
import plugily.projects.villagedefense.api.event.wave.VillageWaveEndEvent;
import plugily.projects.villagedefense.api.event.wave.VillageWaveStartEvent;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XSound;

import java.util.Collection;
import java.util.Optional;

public class SoundEnhance implements ArenaListener {
    @EventHandler
    public void onGameJoin(VillageGameJoinAttemptEvent event) {
        sendSound(MainConfig.ENHANCE_SOUND_JOIN.getValue(), event.getPlayer());
    }

    @EventHandler
    public void onGameLeave(VillageGameLeaveAttemptEvent event) {
        sendSound(MainConfig.ENHANCE_SOUND_QUIT.getValue(), event.getPlayer());
    }

    @EventHandler
    public void onGameStart(VillageGameStartEvent event) {
        sendSound(MainConfig.ENHANCE_SOUND_GAME_START.getValue(), event.getArena().getPlayers());
    }

    @EventHandler
    public void onGameEnd(VillageGameStopEvent event) {
        sendSound(MainConfig.ENHANCE_SOUND_GAME_END.getValue(), event.getArena().getPlayers());
    }

    @EventHandler
    public void onWaveStart(VillageWaveStartEvent event) {
        sendSound(MainConfig.ENHANCE_SOUND_WAVE_START.getValue(), event.getArena().getPlayers());
    }

    @EventHandler
    public void onWaveEnd(VillageWaveEndEvent event) {
        sendSound(MainConfig.ENHANCE_SOUND_WAVE_END.getValue(), event.getArena().getPlayers());
    }

    private Optional<XSound> getXSound(String sound) {
        if (Validate.isNullOrEmpty(sound)) {
            return Optional.empty();
        } else {
            return XSound.matchXSound(sound);
        }
    }

    private void sendSound(String sound, Player player) {
        getXSound(sound).ifPresent(xSound -> xSound.play(player));
    }

    private void sendSound(String sound, Collection<Player> players) {
        getXSound(sound).ifPresent(xSound -> players.forEach(xSound::play));
    }
}
