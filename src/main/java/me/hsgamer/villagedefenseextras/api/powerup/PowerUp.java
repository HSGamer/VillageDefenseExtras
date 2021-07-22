package me.hsgamer.villagedefenseextras.api.powerup;

import org.bukkit.entity.Player;
import plugily.projects.villagedefense.Main;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.commonsbox.minecraft.compat.VersionUtils;
import plugily.projects.villagedefense.handlers.powerup.BasePowerup;
import plugily.projects.villagedefense.handlers.powerup.PowerupPickupHandler;

import java.util.function.Consumer;

public abstract class PowerUp implements BasePowerup {
    @Override
    public final Consumer<PowerupPickupHandler> getOnPickup() {
        return handler -> {
            onPickup(handler.getPlayer(), handler.getArena());
            broadcastTitle(handler.getArena());
        };
    }

    public final void tryRegister(Main main) {
        if (canRegister()) {
            main.getPowerupRegistry().registerPowerup(this);
        }
    }

    protected abstract void onPickup(Player player, Arena arena);

    protected abstract boolean canRegister();

    protected void broadcastTitle(Arena arena) {
        for (Player p : arena.getPlayers()) {
            VersionUtils.sendTitles(p, getName(), getDescription(), 5, 30, 5);
        }
    }
}
