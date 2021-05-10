package me.hsgamer.villagedefenseextras.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.*;
import me.hsgamer.villagedefenseextras.config.path.StringListConfigPath;
import org.bukkit.plugin.Plugin;

import java.util.Collections;

public class MainConfig extends PathableConfig {
    public static final BooleanConfigPath POWER_UP_LIGHTNING_STRIKE_ENABLED = new BooleanConfigPath("power-up.lightning-strike.enabled", true);

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

    public static final IntegerConfigPath ZOMBIE_GHOST_WAVE = new IntegerConfigPath("zombie.ghost.wave", 8);
    public static final IntegerConfigPath ZOMBIE_GHOST_AMOUNT = new IntegerConfigPath("zombie.ghost.amount", 10);
    public static final DoubleConfigPath ZOMBIE_GHOST_RATE = new DoubleConfigPath("zombie.ghost.rate", 0.2);
    public static final IntegerConfigPath ZOMBIE_BOMBER_WAVE = new IntegerConfigPath("zombie.bomber.wave", 10);
    public static final IntegerConfigPath ZOMBIE_BOMBER_AMOUNT = new IntegerConfigPath("zombie.bomber.amount", 10);
    public static final DoubleConfigPath ZOMBIE_BOMBER_RATE = new DoubleConfigPath("zombie.bomber.rate", 0.2);
    public static final DoubleConfigPath ZOMBIE_BOMBER_THROW_LENGTH = new DoubleConfigPath("zombie.bomber.throw-length", 2D);
    public static final DoubleConfigPath ZOMBIE_BOMBER_THROW_OFFSET_Y = new DoubleConfigPath("zombie.bomber.throw-offset-y", 1.5D);
    public static final LongConfigPath ZOMBIE_BOMBER_THROW_DELAY = new LongConfigPath("zombie.bomber.throw-delay", 40L);

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
