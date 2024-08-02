package fr.isweatmc.openmc.commands;

import fr.isweatmc.openmc.economy.EconomyManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBalance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            double balance = EconomyManager.getBalance(player.getUniqueId());
            player.sendMessage("§aVotre solde actuel est de: §e" + balance);
            return true;
        } else {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return false;
        }
    }
}
