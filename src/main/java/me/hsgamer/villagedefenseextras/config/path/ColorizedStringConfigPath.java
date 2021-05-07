package me.hsgamer.villagedefenseextras.config.path;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.config.BaseConfigPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColorizedStringConfigPath extends BaseConfigPath<String> {
    public ColorizedStringConfigPath(@NotNull String path, @Nullable String def) {
        super(path, def, o -> MessageUtils.colorize(String.valueOf(o)));
    }
}
