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

    public static final ColorizedStringConfigPath KIT_DEFUSER_NAME = new ColorizedStringConfigPath("kit.defuser.name", "&cDefuser");
    public static final ColorizedStringConfigPath KIT_DEFUSER_DESCRIPTION = new ColorizedStringConfigPath("kit.defuser.description", "&7You defuse any bomb you interact");
    public static final ColorizedStringConfigPath KIT_DEFUSER_ITEM_NAME = new ColorizedStringConfigPath("kit.defuser.item-name", "&eDefuser");
    public static final ColorizedStringConfigPath KIT_DEFUSER_ITEM_LORE = new ColorizedStringConfigPath("kit.defuser.item-lore", "&7Right-click on a bomb to defuse it");
    public static final ColorizedStringConfigPath KIT_ANGEL_NAME = new ColorizedStringConfigPath("kit.angel.name", "&aAngel");
    public static final ColorizedStringConfigPath KIT_ANGEL_DESCRIPTION = new ColorizedStringConfigPath("kit.angel.description", "&7Other lives are on your hand");
    public static final ColorizedStringConfigPath KIT_ANGEL_ITEM_NAME = new ColorizedStringConfigPath("kit.angel.item-name", "&aFeather Of Angel");
    public static final ColorizedStringConfigPath KIT_ANGEL_ITEM_LORE = new ColorizedStringConfigPath("kit.angel.item-lore", "&7Right click to reborn dead people");
    public static final ColorizedStringConfigPath KIT_ANGEL_REBORN_MESSAGE = new ColorizedStringConfigPath("kit.angel.reborn-message", "&eHeroes never die!");

    public MessageConfig(Plugin plugin) {
        super(new BukkitConfig(plugin, "messages.yml"));
    }
}
