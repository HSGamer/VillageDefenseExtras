package me.hsgamer.villagedefenseextras.manager;

import me.hsgamer.villagedefenseextras.api.zombie.ZombieSpawner;
import me.hsgamer.villagedefenseextras.zombie.BomberZombie;
import me.hsgamer.villagedefenseextras.zombie.GhostZombie;
import org.bukkit.Location;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.managers.CustomZombieSpawnManager;
import plugily.projects.villagedefense.arena.options.ArenaOption;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ExtraZombieManager implements CustomZombieSpawnManager {
    private final List<ZombieSpawner> zombieSpawners = new CopyOnWriteArrayList<>();

    public ExtraZombieManager() {
        addZombieSpawner(new GhostZombie());
        addZombieSpawner(new BomberZombie());
    }

    @Override
    public void spawnZombie(Random random, Arena arena, int amount) {
        int wave = arena.getWave();
        int phase = arena.getOption(ArenaOption.ZOMBIE_SPAWN_COUNTER);
        for (ZombieSpawner zombieSpawner : zombieSpawners) {
            if (wave < zombieSpawner.getSpawnWave() || !zombieSpawner.getSpawnPhases().contains(phase)) {
                continue;
            }
            int spawnAmount = zombieSpawner.getFinalAmount(amount);
            for (int i = 0; i < spawnAmount; i++) {
                if (arena.getOption(ArenaOption.ZOMBIES_TO_SPAWN) > 0 && random.nextDouble() <= zombieSpawner.getSpawnRate()) {
                    Location location = arena.getZombieSpawns().get(random.nextInt(arena.getZombieSpawns().size()));
                    zombieSpawner.spawnZombie(location, arena);
                    arena.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, arena.getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
                }
            }
        }
    }

    public void addZombieSpawner(ZombieSpawner zombieSpawner) {
        zombieSpawners.add(zombieSpawner);
    }

    public void removeZombieSpawner(ZombieSpawner zombieSpawner) {
        zombieSpawners.remove(zombieSpawner);
    }

    public void clearAll() {
        zombieSpawners.clear();
    }

    public Optional<ZombieSpawner> getZombieSpawner(String name) {
        return zombieSpawners.parallelStream().filter(spawner -> spawner.getName().equals(name)).findAny();
    }

    public List<String> getAllNames() {
        return zombieSpawners.parallelStream().map(ZombieSpawner::getName).collect(Collectors.toList());
    }
}
