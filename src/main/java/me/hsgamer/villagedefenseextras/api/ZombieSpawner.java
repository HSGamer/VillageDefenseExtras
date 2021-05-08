package me.hsgamer.villagedefenseextras.api;

import org.bukkit.Location;

import java.util.List;

public interface ZombieSpawner {
    String getName();

    int getSpawnWave();

    double getSpawnRate();

    default int getFinalAmount(int spawnAmount) {
        return spawnAmount;
    }

    List<Integer> getSpawnPhases();

    void spawnZombie(Location location);
}
