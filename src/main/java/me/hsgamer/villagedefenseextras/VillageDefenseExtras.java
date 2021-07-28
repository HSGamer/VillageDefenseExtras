package me.hsgamer.villagedefenseextras;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.command.SpawnEnemyCommand;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import me.hsgamer.villagedefenseextras.enemy.BomberZombie;
import me.hsgamer.villagedefenseextras.enemy.GhostZombie;
import me.hsgamer.villagedefenseextras.enemy.TeleporterZombie;
import me.hsgamer.villagedefenseextras.enemy.WitherZombie;
import me.hsgamer.villagedefenseextras.enhance.AutoLapisEnchantingTableEnhance;
import me.hsgamer.villagedefenseextras.enhance.EnemyTargetOnSpawnEnhance;
import me.hsgamer.villagedefenseextras.enhance.ResetCounterEnhance;
import me.hsgamer.villagedefenseextras.enhance.SoundEnhance;
import me.hsgamer.villagedefenseextras.fix.*;
import me.hsgamer.villagedefenseextras.kit.AngelKit;
import me.hsgamer.villagedefenseextras.kit.DefuserKit;
import me.hsgamer.villagedefenseextras.powerup.LightningStrikePowerUp;
import org.bukkit.plugin.java.JavaPlugin;
import plugily.projects.villagedefense.Main;
import plugily.projects.villagedefense.arena.managers.EnemySpawnerRegistry;
import plugily.projects.villagedefense.arena.managers.spawner.EnemySpawner;
import plugily.projects.villagedefense.kits.KitRegistry;

import java.util.Set;

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
        registerEnemy();
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
        registerListener(new JoinStateFix());
        registerListener(new IngameBossbarFix());
    }

    private void registerEnhance() {
        registerListener(new AutoLapisEnchantingTableEnhance());
        registerListener(new SoundEnhance());
        registerListener(new EnemyTargetOnSpawnEnhance());
        registerListener(new ResetCounterEnhance());
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
        registerCommand(new SpawnEnemyCommand());
    }

    private void registerEnemy() {
        EnemySpawnerRegistry spawnerRegistry = parentPlugin.getEnemySpawnerRegistry();
        Set<EnemySpawner> enemySpawnerSet = spawnerRegistry.getEnemySpawnerSet();
        enemySpawnerSet.add(new GhostZombie());
        enemySpawnerSet.add(new BomberZombie());
        enemySpawnerSet.add(new WitherZombie());
        enemySpawnerSet.add(new TeleporterZombie());
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
