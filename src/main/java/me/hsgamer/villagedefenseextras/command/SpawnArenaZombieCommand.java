package me.hsgamer.villagedefenseextras.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;
import plugily.projects.villagedefense.arena.Arena;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class SpawnArenaZombieCommand extends Command {
    private static final Map<String, Consumer<Arena>> spawnMap = new HashMap<>();

    static {
        spawnMap.put("FAST_ZOMBIE", arena -> arena.spawnFastZombie(ThreadLocalRandom.current()));
        spawnMap.put("BABY_ZOMBIE", arena -> arena.spawnBabyZombie(ThreadLocalRandom.current()));
        spawnMap.put("HARD_ZOMBIE", arena -> arena.spawnHardZombie(ThreadLocalRandom.current()));
        spawnMap.put("PLAYER_BUSTER", arena -> arena.spawnPlayerBuster(ThreadLocalRandom.current()));
        spawnMap.put("GOLEM_BUSTER", arena -> arena.spawnGolemBuster(ThreadLocalRandom.current()));
        spawnMap.put("VILLAGER_BUSTER", arena -> arena.spawnVillagerBuster(ThreadLocalRandom.current()));
        spawnMap.put("SOFT_HARD_ZOMBIE", arena -> arena.spawnSoftHardZombie(ThreadLocalRandom.current()));
        spawnMap.put("HALF_INVISIBLE_ZOMBIE", arena -> arena.spawnHalfInvisibleZombie(ThreadLocalRandom.current()));
        spawnMap.put("KNOCKBACK_RESISTANT_ZOMBIE", arena -> arena.spawnKnockbackResistantZombies(ThreadLocalRandom.current()));
        spawnMap.put("VILLAGER_SLAYER", arena -> arena.spawnVillagerSlayer(ThreadLocalRandom.current()));
    }

    public SpawnArenaZombieCommand() {
        super("spawnarenazombie", "Spawn a built-in zombie", "/spawnarenazombie <zombie_name>", Collections.emptyList());
        Permission permission = new Permission("vdextra.spawnarenazombie", PermissionDefault.OP);
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
        Optional<Arena> optionalArena = Utils.getArena((Player) sender);
        if (!optionalArena.isPresent()) {
            MessageUtils.sendMessage(sender, "&cYou are not in an arena");
            return false;
        }
        Optional<Consumer<Arena>> optionalArenaConsumer = Optional.ofNullable(spawnMap.get(args[0]));
        if (!optionalArenaConsumer.isPresent()) {
            MessageUtils.sendMessage(sender, "&cThat zombie is not found");
            return false;
        }
        optionalArenaConsumer.get().accept(optionalArena.get());
        MessageUtils.sendMessage(sender, "&aSpawned");
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return new ArrayList<>(spawnMap.keySet());
        } else {
            return Collections.emptyList();
        }
    }
}
