package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.villagedefenseextras.config.MainConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.api.event.wave.VillageWaveStartEvent;
import plugily.projects.villagedefense.arena.options.ArenaOption;

public class ResetCounterEnhance implements Listener {
    @EventHandler
    public void onWave(VillageWaveStartEvent event) {
        if (MainConfig.ENHANCE_RESET_COUNTER_ON_WAVE.getValue()) {
            event.getArena().setOptionValue(ArenaOption.ZOMBIE_SPAWN_COUNTER, 0);
        }
    }
}
