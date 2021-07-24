package me.hsgamer.villagedefenseextras.fix;

import me.hsgamer.villagedefenseextras.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import plugily.projects.villagedefense.api.event.game.VillageGameStartEvent;
import plugily.projects.villagedefense.api.event.game.VillageGameStopEvent;
import plugily.projects.villagedefense.handlers.language.Messages;
import plugily.projects.villagedefense.kits.premium.NakedKit;
import plugily.projects.villagedefense.user.User;

import java.util.*;

import static me.hsgamer.villagedefenseextras.VillageDefenseExtras.getInstance;

public class NakedKitAbsoluteFix implements Listener {
    private final Map<UUID, BukkitRunnable> runnableMap = new IdentityHashMap<>();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Optional.ofNullable(runnableMap.remove(event.getPlayer().getUniqueId()))
                .filter(runnable -> !runnable.isCancelled())
                .ifPresent(BukkitRunnable::cancel);
    }

    @EventHandler
    public void onGameStop(VillageGameStopEvent event) {
        event.getArena().getPlayers().stream()
                .map(Player::getUniqueId)
                .filter(runnableMap::containsKey)
                .map(runnableMap::remove)
                .filter(runnable -> !runnable.isCancelled())
                .forEach(BukkitRunnable::cancel);
    }

    @EventHandler
    public void onGameStart(VillageGameStartEvent event) {
        event.getArena().getPlayers().stream()
                .map(Utils::getUser)
                .filter(user -> user.getKit() instanceof NakedKit)
                .map(User::getUniqueId)
                .forEach(uuid -> {
                    NakedKitRunnable runnable = new NakedKitRunnable(uuid);
                    runnable.runTaskTimerAsynchronously(getInstance(), 40, 40);
                    runnableMap.put(uuid, runnable);
                });
    }

    private static final class NakedKitRunnable extends BukkitRunnable {
        private final UUID uuid;

        private NakedKitRunnable(UUID uuid) {
            this.uuid = uuid;
        }

        @Override
        public void run() {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            if (!offlinePlayer.isOnline()) {
                this.cancel();
                return;
            }
            User user = Utils.getUser(offlinePlayer.getPlayer());
            if (!Utils.isInArena(user.getPlayer()) || !(user.getKit() instanceof NakedKit)) {
                this.cancel();
                return;
            }
            ItemStack[] armorContents = user.getPlayer().getInventory().getArmorContents();
            boolean hasArmor = Arrays.stream(armorContents).anyMatch(stack -> stack != null && stack.getType() != Material.AIR);
            if (hasArmor) {
                Bukkit.getScheduler().runTask(getInstance(), () -> {
                    Player player = user.getPlayer();
                    player.sendMessage(getInstance().getParentPlugin().getChatManager().colorMessage(Messages.KITS_WILD_NAKED_CANNOT_WEAR_ARMOR));
                    player.getInventory().setHelmet(null);
                    player.getInventory().setChestplate(null);
                    player.getInventory().setLeggings(null);
                    player.getInventory().setBoots(null);
                });
            }
        }
    }
}
