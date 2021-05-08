package me.hsgamer.villagedefenseextras.powerup;

import me.hsgamer.villagedefenseextras.api.PowerUp;
import me.hsgamer.villagedefenseextras.config.MainConfig;
import me.hsgamer.villagedefenseextras.config.MessageConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;

public class LightningStrikePowerUp extends PowerUp {
    @Override
    protected void onPickup(Player player, Arena arena) {
        for (Zombie zombie : arena.getZombies()) {
            Location location = zombie.getLocation();
            location.getWorld().strikeLightningEffect(location);
            zombie.damage(zombie.getHealth() / 2, player);
        }
    }

    @Override
    public String getId() {
        return "LIGHTNING_STRIKE";
    }

    @Override
    protected boolean canRegister() {
        return MainConfig.POWER_UP_LIGHTNING_STRIKE_ENABLED.getValue();
    }

    @Override
    public String getName() {
        return MessageConfig.POWER_UP_LIGHTNING_STRIKE_NAME.getValue();
    }

    @Override
    public String getDescription() {
        return MessageConfig.POWER_UP_LIGHTNING_STRIKE_DESCRIPTION.getValue();
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.BEACON;
    }
}
