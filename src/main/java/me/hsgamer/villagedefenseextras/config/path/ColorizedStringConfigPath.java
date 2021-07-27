package me.hsgamer.villagedefenseextras.config.path;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.config.BaseConfigPath;

public class ColorizedStringConfigPath extends BaseConfigPath<String> {
    public ColorizedStringConfigPath(String path, String def) {
        super(path, def, o -> MessageUtils.colorize(String.valueOf(o)));
    }
}
