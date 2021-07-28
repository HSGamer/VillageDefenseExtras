package me.hsgamer.villagedefenseextras.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.BooleanConfigPath;
import me.hsgamer.hscore.config.path.DoubleConfigPath;
import me.hsgamer.hscore.config.path.IntegerConfigPath;
import me.hsgamer.hscore.config.path.StringConfigPath;
import me.hsgamer.villagedefenseextras.config.path.StringListConfigPath;
import org.bukkit.plugin.Plugin;

import java.util.Collections;

public class MainConfig extends PathableConfig {
    public static final StringListConfigPath ENHANCE_AUTO_LAPIS_BLACKLISTED_WORLDS = new StringListConfigPath("enhance.auto-lapis.blacklisted-worlds", Collections.emptyList());
    public static final IntegerConfigPath ENHANCE_AUTO_LAPIS_WAVE_LEVEL_ONE = new IntegerConfigPath("enhance.auto-lapis.wave-level.one", 0);
    public static final IntegerConfigPath ENHANCE_AUTO_LAPIS_WAVE_LEVEL_TWO = new IntegerConfigPath("enhance.auto-lapis.wave-level.two", 0);
    public static final IntegerConfigPath ENHANCE_AUTO_LAPIS_WAVE_LEVEL_THREE = new IntegerConfigPath("enhance.auto-lapis.wave-level.three", 0);

    public static final StringConfigPath ENHANCE_SOUND_JOIN = new StringConfigPath("enhance.sound.join", "");
    public static final StringConfigPath ENHANCE_SOUND_QUIT = new StringConfigPath("enhance.sound.quit", "");
    public static final StringConfigPath ENHANCE_SOUND_GAME_START = new StringConfigPath("enhance.sound.game-start", "");
    public static final StringConfigPath ENHANCE_SOUND_GAME_END = new StringConfigPath("enhance.sound.game-end", "");
    public static final StringConfigPath ENHANCE_SOUND_WAVE_START = new StringConfigPath("enhance.sound.wave-start", "");
    public static final StringConfigPath ENHANCE_SOUND_WAVE_END = new StringConfigPath("enhance.sound.wave-end", "");

    public static final DoubleConfigPath ENHANCE_ENEMY_TARGET_ON_PLAYER = new DoubleConfigPath("enhance.enemy-target.on-player", 0.35);
    public static final DoubleConfigPath ENHANCE_ENEMY_TARGET_ON_VILLAGER = new DoubleConfigPath("enhance.enemy-target.on-villager", 0.50);

    public static final BooleanConfigPath ENHANCE_RESET_COUNTER_ON_WAVE = new BooleanConfigPath("enhance.reset-counter-on-wave", true);

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
