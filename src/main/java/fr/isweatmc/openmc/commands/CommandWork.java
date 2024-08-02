package fr.isweatmc.openmc.commands;

import fr.isweatmc.openmc.economy.EconomyManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

public class CommandWork implements CommandExecutor, Listener {
    private final HashMap<UUID, Integer> workProgress = new HashMap<>();
    private final int requiredCount = 50;
    private final double reward = 500.0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            UUID playerId = player.getUniqueId();

            workProgress.put(playerId, 0);
            player.sendMessage("§aDéfi lancé : Cassez 50 pierres pour gagner 500 dollars !");
            return true;
        } else {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return false;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        if (workProgress.containsKey(playerId) && event.getBlock().getType() == Material.STONE) {
            int currentCount = workProgress.get(playerId);
            currentCount++;

            if (currentCount >= requiredCount) {
                EconomyManager.addMoney(playerId, reward);
                player.sendMessage("§aDéfi complété ! Vous avez gagné 500 dollars !");
                workProgress.remove(playerId);
            } else {
                workProgress.put(playerId, currentCount);
                player.sendMessage("§aProgrès du défi : " + currentCount + "/" + requiredCount);
            }
        }
    }
}
