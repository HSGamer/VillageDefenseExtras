package me.hsgamer.villagedefenseextras.enhance;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.ArenaRegistry;
import plugily.projects.villagedefense.handlers.language.Messages;

import java.util.Objects;

public class ClearMobEnhance implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Arena arena = ArenaRegistry.getArena(player);
        if (arena == null) {
            return;
        }

        arena.getWolves().stream()
                .filter(Entity::isValid)
                .filter(wolf -> Objects.equals(wolf.getOwner(), player))
                .forEach(arena::removeWolf);

        String spawnedGolemName = VillageDefenseExtras.getInstance().getParentPlugin().getChatManager().colorMessage(Messages.SPAWNED_GOLEM_NAME).replace("%player%", player.getName());
        arena.getIronGolems().stream()
                .filter(Entity::isValid)
                .filter(golem -> spawnedGolemName.equals(golem.getCustomName()))
                .forEach(arena::removeIronGolem);
    }
}
