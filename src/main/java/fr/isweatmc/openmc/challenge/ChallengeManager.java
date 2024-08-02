package fr.isweatmc.openmc.challenge;

import fr.isweatmc.openmc.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChallengeManager {

    private final Main plugin;
    private final FileConfiguration config;

    private final Map<UUID, Double> playerChallenges = new HashMap<>();
    private final Map<UUID, Vector> lastPosition = new HashMap<>();

    private static final double DISTANCE_THRESHOLD = 1.0; // Distance minimale pour considérer un déplacement

    public ChallengeManager(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public void startMarketChallenge(Player player) {
        UUID playerId = player.getUniqueId();
        playerChallenges.put(playerId, 100.0); // 100 blocs à parcourir
        lastPosition.put(playerId, player.getLocation().toVector());
        player.sendMessage("Vous avez commencé un défi de marché ! Parcourez 100 blocs pour le compléter.");
        saveChallenges(); // Sauvegarder les données après le début du défi
    }

    public void updateChallengeProgress(Player player) {
        UUID playerId = player.getUniqueId();
        Vector currentPosition = player.getLocation().toVector();
        Vector lastPos = lastPosition.get(playerId);

        if (lastPos != null) {
            double distanceMoved = currentPosition.distance(lastPos);
            if (distanceMoved >= DISTANCE_THRESHOLD) {
                double totalDistance = playerChallenges.getOrDefault(playerId, 0.0);
                totalDistance -= distanceMoved;

                if (totalDistance <= 0) {
                    playerChallenges.remove(playerId);
                    lastPosition.remove(playerId);
                    player.sendMessage("Félicitations ! Vous avez complété le défi de marché.");
                } else {
                    playerChallenges.put(playerId, totalDistance);
                    lastPosition.put(playerId, currentPosition);
                    player.sendMessage("Il vous reste " + Math.round(totalDistance) + " blocs à parcourir pour compléter le défi.");
                }
                saveChallenges(); // Sauvegarder les données après mise à jour
            }
        }
    }

    public void loadChallenges() {
        if (config.contains("challenges")) {
            for (String uuidStr : config.getConfigurationSection("challenges").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                double distance = config.getDouble("challenges." + uuidStr + ".distance");
                playerChallenges.put(uuid, distance);

                // Charger la dernière position si elle est enregistrée
                if (config.contains("challenges." + uuidStr + ".lastPosition")) {
                    double x = config.getDouble("challenges." + uuidStr + ".lastPosition.x");
                    double y = config.getDouble("challenges." + uuidStr + ".lastPosition.y");
                    double z = config.getDouble("challenges." + uuidStr + ".lastPosition.z");
                    lastPosition.put(uuid, new Vector(x, y, z));
                } else {
                    // Valeur par défaut si aucune position n'est enregistrée
                    lastPosition.put(uuid, new Vector(0, 0, 0));
                }
            }
        }
    }

    public void saveChallenges() {
        // Effacer les anciennes données
        config.set("challenges", null);

        // Sauvegarder les données des défis
        for (Map.Entry<UUID, Double> entry : playerChallenges.entrySet()) {
            UUID uuid = entry.getKey();
            double distance = entry.getValue();
            config.set("challenges." + uuid + ".distance", distance);

            // Sauvegarder la position actuelle
            Vector pos = lastPosition.get(uuid);
            config.set("challenges." + uuid + ".lastPosition.x", pos.getX());
            config.set("challenges." + uuid + ".lastPosition.y", pos.getY());
            config.set("challenges." + uuid + ".lastPosition.z", pos.getZ());
        }

        plugin.saveConfig(); // Sauvegarder le fichier config.yml
    }
}
