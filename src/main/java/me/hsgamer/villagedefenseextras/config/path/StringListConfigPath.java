package me.hsgamer.villagedefenseextras.config.path;

import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.BaseConfigPath;

import java.util.List;

public class StringListConfigPath extends BaseConfigPath<List<String>> {

    public StringListConfigPath(String path, List<String> def) {
        super(path, def, o -> CollectionUtils.createStringListFromObject(o, true));
    }
}
