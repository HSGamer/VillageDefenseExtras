package me.hsgamer.villagedefenseextras;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.command.SpawnZombieCommand;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import me.hsgamer.villagedefenseextras.enhance.AutoLapisEnchantingTableEnhance;
import me.hsgamer.villagedefenseextras.enhance.SoundEnhance;
import me.hsgamer.villagedefenseextras.fix.*;
import me.hsgamer.villagedefenseextras.kit.AngelKit;
import me.hsgamer.villagedefenseextras.kit.DefuserKit;
import me.hsgamer.villagedefenseextras.powerup.LightningStrikePowerUp;
import me.hsgamer.villagedefenseextras.zombie.BomberZombie;
import me.hsgamer.villagedefenseextras.zombie.GhostZombie;
import me.hsgamer.villagedefenseextras.zombie.TeleporterZombie;
import me.hsgamer.villagedefenseextras.zombie.WitherZombie;
import org.bukkit.plugin.java.JavaPlugin;
import plugily.projects.villagedefense.Main;
import plugily.projects.villagedefense.arena.managers.ZombieSpawnerRegistry;
import plugily.projects.villagedefense.arena.managers.spawner.ZombieSpawner;
import plugily.projects.villagedefense.kits.KitRegistry;

import java.util.List;

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
        registerPowerUp();
        registerEnhance();
        registerKit();
        registerZombie();
        registerCommand();
    }

    private void registerFix() {
        registerListener(new KitChooseExploitFix());
        registerListener(new CraftPlayerInventoryFix());
        registerListener(new DefenseTargetFix());
        registerListener(new PickupPowerUpFix());
        registerListener(new LobbyInteractFix());
        registerListener(new ChestInteractFix());
        registerListener(new BlockListenerFix());
    }

    private void registerEnhance() {
        registerListener(new AutoLapisEnchantingTableEnhance());
        registerListener(new SoundEnhance());
    }

    private void registerKit() {
        if (MainConfig.KIT_DEFUSER_ENABLED.getValue()) {
            KitRegistry.registerKit(new DefuserKit());
        }
        if (MainConfig.KIT_ANGEL_ENABLED.getValue()) {
            KitRegistry.registerKit(new AngelKit());
        }
    }

    private void registerPowerUp() {
        new LightningStrikePowerUp().tryRegister(parentPlugin);
    }

    private void registerCommand() {
        registerCommand(new SpawnZombieCommand());
    }

    private void registerZombie() {
        ZombieSpawnerRegistry spawnerRegistry = parentPlugin.getZombieSpawnerRegistry();
        List<ZombieSpawner> zombieSpawnerList = spawnerRegistry.getZombieSpawnerList();
        zombieSpawnerList.add(new GhostZombie());
        zombieSpawnerList.add(new BomberZombie());
        zombieSpawnerList.add(new WitherZombie());
        zombieSpawnerList.add(new TeleporterZombie());
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
