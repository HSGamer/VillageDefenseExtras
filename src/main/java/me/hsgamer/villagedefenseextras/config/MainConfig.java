package me.hsgamer.villagedefenseextras.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.*;
import me.hsgamer.villagedefenseextras.config.path.StringListConfigPath;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public static final SimpleConfigPath<List<Integer>> ZOMBIE_GHOST_PHASE = new SimpleConfigPath<>("zombie.ghost.phase", IntStream.range(1, 7).boxed().collect(Collectors.toList()));
    public static final IntegerConfigPath ZOMBIE_BOMBER_WAVE = new IntegerConfigPath("zombie.bomber.wave", 10);
    public static final IntegerConfigPath ZOMBIE_BOMBER_AMOUNT = new IntegerConfigPath("zombie.bomber.amount", 10);
    public static final DoubleConfigPath ZOMBIE_BOMBER_RATE = new DoubleConfigPath("zombie.bomber.rate", 0.2);
    public static final DoubleConfigPath ZOMBIE_BOMBER_THROW_POWER_DIVIDER = new DoubleConfigPath("zombie.bomber.throw-power-divider", 5D);
    public static final DoubleConfigPath ZOMBIE_BOMBER_THROW_LENGTH_RATE = new DoubleConfigPath("zombie.bomber.throw-length-rate", 0.7);
    public static final DoubleConfigPath ZOMBIE_BOMBER_THROW_OFFSET_Y = new DoubleConfigPath("zombie.bomber.throw-offset-y", 1.5D);
    public static final IntegerConfigPath ZOMBIE_BOMBER_FUSE_TICKS = new IntegerConfigPath("zombie.bomber.fuse-ticks", 5);
    public static final LongConfigPath ZOMBIE_BOMBER_THROW_DELAY = new LongConfigPath("zombie.bomber.throw-delay", 40L);
    public static final SimpleConfigPath<List<Integer>> ZOMBIE_BOMBER_PHASE = new SimpleConfigPath<>("zombie.bomber.phase", IntStream.range(3, 5).boxed().collect(Collectors.toList()));
    public static final IntegerConfigPath ZOMBIE_WITHER_WAVE = new IntegerConfigPath("zombie.wither.wave", 10);
    public static final IntegerConfigPath ZOMBIE_WITHER_AMOUNT = new IntegerConfigPath("zombie.wither.amount", 10);
    public static final DoubleConfigPath ZOMBIE_WITHER_RATE = new DoubleConfigPath("zombie.wither.rate", 0.2);
    public static final SimpleConfigPath<List<Integer>> ZOMBIE_WITHER_PHASE = new SimpleConfigPath<>("zombie.wither.phase", IntStream.range(3, 5).boxed().collect(Collectors.toList()));
    public static final LongConfigPath ZOMBIE_WITHER_SHOOT_DELAY = new LongConfigPath("zombie.wither.shoot-delay", 40L);
    public static final DoubleConfigPath ZOMBIE_WITHER_SHOOT_POWER = new DoubleConfigPath("zombie.wither.shoot-power", 1.5D);

    public static final BooleanConfigPath KIT_DEFUSER_ENABLED = new BooleanConfigPath("kit.defuser.enabled", true);
    public static final IntegerConfigPath KIT_DEFUSER_COOLDOWN = new IntegerConfigPath("kit.defuser.cooldown", 10);
    public static final FloatConfigPath KIT_DEFUSER_FAIL_YIELD_MULTIPLY = new FloatConfigPath("kit.defuser.fail-yield-multiply", 1F);
    public static final BooleanConfigPath KIT_ANGEL_ENABLED = new BooleanConfigPath("kit.angel.enabled", true);
    public static final IntegerConfigPath KIT_ANGEL_COOLDOWN = new IntegerConfigPath("kit.angel.cooldown", 180);

    public MainConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "config.yml"));
    }
}
