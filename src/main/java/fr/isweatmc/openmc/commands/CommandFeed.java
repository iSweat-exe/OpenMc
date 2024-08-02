package fr.isweatmc.openmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class CommandFeed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setFoodLevel(20);
            player.sendMessage(ChatColor.GREEN + "Vous avez été nourri !");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande.");
            return false;
        }
    }
}
