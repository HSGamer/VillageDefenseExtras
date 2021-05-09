package me.hsgamer.villagedefenseextras;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.command.SpawnZombieCommand;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import me.hsgamer.villagedefenseextras.enhance.AutoLapisEnchantingTableEnhance;
import me.hsgamer.villagedefenseextras.enhance.SoundEnhance;
import me.hsgamer.villagedefenseextras.fix.*;
import me.hsgamer.villagedefenseextras.manager.ExtraZombieManager;
import me.hsgamer.villagedefenseextras.powerup.LightningStrikePowerUp;
import org.bukkit.plugin.java.JavaPlugin;
import plugily.projects.villagedefense.Main;
import plugily.projects.villagedefense.arena.managers.ZombieSpawnManager;

public final class VillageDefenseExtras extends BasePlugin {
    private static VillageDefenseExtras instance;

    private final MainConfig mainConfig = new MainConfig(this);
    private final MessageConfig messageConfig = new MessageConfig(this);
    private final ExtraZombieManager extraZombieManager = new ExtraZombieManager();

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
        registerPowerUp();
        registerEnhance();
        registerCommand();

        ZombieSpawnManager.CUSTOM_ZOMBIE_SPAWN_MANAGERS.add(extraZombieManager);
    }

    @Override
    public void disable() {
        ZombieSpawnManager.CUSTOM_ZOMBIE_SPAWN_MANAGERS.remove(extraZombieManager);
        extraZombieManager.clearAll();
    }

    private void registerFix() {
        registerListener(new KitChooseExploitFix());
        registerListener(new CraftPlayerInventoryFix());
        registerListener(new DefenseTargetFix());
        registerListener(new PickupPowerUpFix());
        registerListener(new LobbyInteractFix());
    }

    private void registerEnhance() {
        registerListener(new AutoLapisEnchantingTableEnhance());
        registerListener(new SoundEnhance());
    }

    private void registerPowerUp() {
        new LightningStrikePowerUp().tryRegister(parentPlugin);
    }

    private void registerCommand() {
        registerCommand(new SpawnZombieCommand());
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

    public ExtraZombieManager getExtraZombieManager() {
        return extraZombieManager;
    }
}
