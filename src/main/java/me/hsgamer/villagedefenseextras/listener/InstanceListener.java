package me.hsgamer.villagedefenseextras.listener;

import me.hsgamer.villagedefenseextras.VillageDefenseExtras;
import org.bukkit.entity.Player;
import plugily.projects.villagedefense.user.User;

public abstract class InstanceListener implements ArenaListener {
    private final VillageDefenseExtras instance;

    protected InstanceListener(VillageDefenseExtras instance) {
        this.instance = instance;
    }

    public VillageDefenseExtras getInstance() {
        return instance;
    }

    public User getUser(Player player) {
        return instance.getParentPlugin().getUserManager().getUser(player);
    }
}
