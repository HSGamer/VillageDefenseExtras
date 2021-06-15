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
import plugily.projects.villagedefense.arena.managers.spawner.SimpleZombieSpawner;
import plugily.projects.villagedefense.arena.managers.spawner.ZombieSpawner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpawnZombieCommand extends Command {

    public SpawnZombieCommand() {
        super("spawnzombie", "Spawn a zombie", "/spawnzombie <zombie_name>", Collections.emptyList());
        Permission permission = new Permission("vdextra.spawnzombie", PermissionDefault.OP);
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
        Optional<ZombieSpawner> optionalZombieSpawner = VillageDefenseExtras.getInstance().getParentPlugin().getZombieSpawnerRegistry().getSpawnerByName(args[0]);
        if (!optionalZombieSpawner.isPresent()) {
            MessageUtils.sendMessage(sender, "&cThat zombie name is not found");
            return false;
        }
        Location location = ((Player) sender).getLocation();
        ZombieSpawner zombieSpawner = optionalZombieSpawner.get();
        if (!(zombieSpawner instanceof SimpleZombieSpawner)) {
            MessageUtils.sendMessage(sender, "&cThat zombie is not supported");
            return false;
        }
        SimpleZombieSpawner simpleZombieSpawner = (SimpleZombieSpawner) zombieSpawner;
        Optional<Arena> optionalArena = Utils.getArena((Player) sender);
        if (optionalArena.isPresent()) {
            simpleZombieSpawner.spawnZombie(location, optionalArena.get());
        } else {
            simpleZombieSpawner.spawnZombie(location);
        }
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return VillageDefenseExtras.getInstance().getParentPlugin()
                    .getZombieSpawnerRegistry()
                    .getZombieSpawnerList()
                    .stream()
                    .map(ZombieSpawner::getName)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
