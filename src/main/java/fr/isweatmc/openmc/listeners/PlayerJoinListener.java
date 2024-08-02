package fr.isweatmc.openmc.listeners;

import fr.isweatmc.openmc.challenge.ChallengeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinListener implements Listener {

    private final ChallengeManager challengeManager;

    public PlayerJoinListener(ChallengeManager challengeManager) {
        this.challengeManager = challengeManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        challengeManager.startMarketChallenge(event.getPlayer());
    }
}
