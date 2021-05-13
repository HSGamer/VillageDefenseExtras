package me.hsgamer.villagedefenseextras.enhance;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.api.event.game.VillageGameStartEvent;

import java.util.concurrent.ThreadLocalRandom;

public class VillagerProfessionEnhance implements Listener {

    @EventHandler
    public void onStart(VillageGameStartEvent event) {
        Villager.Profession[] professions = Villager.Profession.values();
        event.getArena().getVillagers().forEach(villager -> villager.setProfession(professions[ThreadLocalRandom.current().nextInt(professions.length)]));
    }
}
