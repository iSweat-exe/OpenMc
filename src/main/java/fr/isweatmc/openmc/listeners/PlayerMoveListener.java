package fr.isweatmc.openmc.listeners;

import fr.isweatmc.openmc.challenge.ChallengeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final ChallengeManager challengeManager;

    public PlayerMoveListener(ChallengeManager challengeManager) {
        this.challengeManager = challengeManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer() != null) {
            challengeManager.updateChallengeProgress(event.getPlayer());
        }
    }
}
