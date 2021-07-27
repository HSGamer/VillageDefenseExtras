package me.hsgamer.villagedefenseextras.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.Utils;
import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.managers.spawner.EnemySpawner;
import plugily.projects.villagedefense.arena.managers.spawner.SimpleEnemySpawner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpawnEnemyCommand extends Command {

    public SpawnEnemyCommand() {
        super("spawnenemy", "Spawn a enemy", "/spawnenemy <zombie_name>", Collections.emptyList());
        Permission permission = new Permission("vdextra.spawnenemy", PermissionDefault.OP);
        setPermission(permission.getName());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (!(sender instanceof Player)) {
            MessageUtils.sendMessage(sender, "&cThis command is player only");
            return false;
        }
        if (args.length <= 0) {
            MessageUtils.sendMessage(sender, getUsage());
            return false;
        }
        Optional<EnemySpawner> optionalEnemySpawner = VillageDefenseExtras.getInstance().getParentPlugin().getEnemySpawnerRegistry().getSpawnerByName(args[0]);
        if (!optionalEnemySpawner.isPresent()) {
            MessageUtils.sendMessage(sender, "&cThat enemy name is not found");
            return false;
        }
        Location location = ((Player) sender).getLocation();
        EnemySpawner enemySpawner = optionalEnemySpawner.get();
        if (!(enemySpawner instanceof SimpleEnemySpawner)) {
            MessageUtils.sendMessage(sender, "&cThat enemy is not supported");
            return false;
        }
        SimpleEnemySpawner simpleEnemySpawner = (SimpleEnemySpawner) enemySpawner;
        Optional<Arena> optionalArena = Utils.getArena((Player) sender);
        if (optionalArena.isPresent()) {
            simpleEnemySpawner.spawn(location, optionalArena.get());
        } else {
            simpleEnemySpawner.spawn(location);
        }
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return VillageDefenseExtras.getInstance().getParentPlugin()
                    .getEnemySpawnerRegistry()
                    .getEnemySpawnerSet()
                    .stream()
                    .filter(SimpleEnemySpawner.class::isInstance)
                    .map(EnemySpawner::getName)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
