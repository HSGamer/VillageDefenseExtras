package me.hsgamer.villagedefenseextras.config;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.config.PathableConfig;
import me.hsgamer.hscore.config.path.StringConfigPath;
import me.hsgamer.villagedefenseextras.config.path.ColorizedStringConfigPath;
import org.bukkit.plugin.Plugin;

public class MessageConfig extends PathableConfig {
    public static final ColorizedStringConfigPath POWER_UP_LIGHTNING_STRIKE_NAME = new ColorizedStringConfigPath("power-up.lightning-strike.name", "&b&lLightning Strike");
    public static final ColorizedStringConfigPath POWER_UP_LIGHTNING_STRIKE_DESCRIPTION = new ColorizedStringConfigPath("power-up.lightning-strike.description", "&fGod is furious! The zombies are now weaker!");

    public static final StringConfigPath FIX_REQUIRED_KIT_ITEM_CANNOT_USE_ITEM = new StringConfigPath("fix.required-kit-item.cannot-use-item", "&cYou are not using the proper kit to use this item");
    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "messages.yml"));
    }
}
