package me.hsgamer.villagedefenseextras;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import me.hsgamer.villagedefenseextras.enhance.*;
import me.hsgamer.villagedefenseextras.fix.*;
import org.bukkit.plugin.java.JavaPlugin;
import plugily.projects.villagedefense.Main;

public final class VillageDefenseExtras extends BasePlugin {
    private static VillageDefenseExtras instance;

    private final MainConfig mainConfig = new MainConfig(this);
    private final MessageConfig messageConfig = new MessageConfig(this);

    private Main parentPlugin;

    public static VillageDefenseExtras getInstance() {
        return instance;
    }

    @Override
    public void preLoad() {
        instance = this;
    }

    @Override
    public void load() {
        MessageUtils.setPrefix("");
    }

    @Override
    public void enable() {
        mainConfig.setup();
        messageConfig.setup();

        parentPlugin = JavaPlugin.getPlugin(Main.class);

        registerFix();
        registerEnhance();
    }

    private void registerFix() {
        registerListener(new KitChooseExploitFix());
        registerListener(new CraftPlayerInventoryFix());
        registerListener(new DefenseTargetFix());
        registerListener(new PickupPowerUpFix());
        registerListener(new LobbyInteractFix());
        registerListener(new ChestInteractFix());
        registerListener(new BlockListenerFix());
        registerListener(new JoinStateFix());
        registerListener(new IngameBossbarFix());
    }

    private void registerEnhance() {
        registerListener(new AutoLapisEnchantingTableEnhance());
        registerListener(new SoundEnhance());
        registerListener(new EnemyTargetOnSpawnEnhance());
        registerListener(new ResetCounterEnhance());
        registerListener(new EnemyTargetEnhance());
    }

    public Main getParentPlugin() {
        return parentPlugin;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }
}
