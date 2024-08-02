package fr.isweatmc.openmc.commands;

import fr.isweatmc.openmc.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPay implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 2) {
                sender.sendMessage("§cUsage: /pay <joueur> <montant>");
                return false;
            }

            Player player = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);
            double amount;

            try {
                amount = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cMontant invalide.");
                return false;
            }

            if (target == null) {
                player.sendMessage("§cJoueur introuvable.");
                return false;
            }

            if (target.equals(player)) {
                player.sendMessage("§cVous ne pouvez pas vous envoyer de l'argent à vous-même.");
                return false;
            }

            if (amount <= 0) {
                player.sendMessage("§cLe montant doit être positif.");
                return false;
            }

            if (EconomyManager.removeMoney(player.getUniqueId(), amount)) {
                EconomyManager.addMoney(target.getUniqueId(), amount);
                player.sendMessage("§aVous avez envoyé §e" + amount + " §aà §e" + target.getName());
                target.sendMessage("§aVous avez reçu §e" + amount + " §ade §e" + player.getName());
            } else {
                player.sendMessage("§cSolde insuffisant.");
            }
            return true;
        } else {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return false;
        }
    }
}
