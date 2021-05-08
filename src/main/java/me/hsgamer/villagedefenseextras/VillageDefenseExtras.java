package me.hsgamer.villagedefenseextras;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import me.hsgamer.villagedefenseextras.enhance.AutoLapisEnchantingTableEnhance;
import me.hsgamer.villagedefenseextras.enhance.SoundEnhance;
import me.hsgamer.villagedefenseextras.fix.*;
import me.hsgamer.villagedefenseextras.powerup.LightningStrikePowerUp;
import org.bukkit.plugin.java.JavaPlugin;
import plugily.projects.villagedefense.Main;

public final class VillageDefenseExtras extends BasePlugin {
    private final MainConfig mainConfig = new MainConfig(this);
    private final MessageConfig messageConfig = new MessageConfig(this);

    private Main parentPlugin;

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
    }

    private void registerFix() {
        registerListener(new KitChooseExploitFix(this));
        registerListener(new CraftPlayerInventoryFix());
        registerListener(new DefenseTargetFix());
        registerListener(new PickupPowerUpFix(this));
        registerListener(new LobbyInteractFix());
    }

    private void registerEnhance() {
        registerListener(new AutoLapisEnchantingTableEnhance());
        registerListener(new SoundEnhance());
    }

    private void registerPowerUp() {
        new LightningStrikePowerUp().tryRegister(parentPlugin);
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
