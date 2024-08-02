package fr.isweatmc.openmc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class CommandHeal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setHealth(20);
            sender.sendMessage(ChatColor.GREEN + "Vous avez etait soigné !");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande.");
            return false;
        }
    }
}
