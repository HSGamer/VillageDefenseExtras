package me.hsgamer.villagedefenseextras.api.zombie;

import org.bukkit.Location;
import org.bukkit.entity.Zombie;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.creatures.CreatureUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public interface ZombieSpawner {
    List<Integer> ALL_PHASES = IntStream.range(0, 20).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

    String getName();

    int getSpawnWave();

    double getSpawnRate();

    default int getFinalAmount(int spawnAmount) {
        return spawnAmount;
    }

    List<Integer> getSpawnPhases();

    Zombie spawnZombie(Location location);

    default void spawnZombie(Location location, Arena arena) {
        Zombie zombie = spawnZombie(location);
        CreatureUtils.applyAttributes(zombie, arena);
        arena.getZombies().add(zombie);
    }
}
