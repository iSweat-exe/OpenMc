package fr.isweatmc.openmc.commands;

import fr.isweatmc.openmc.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetBalance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("economy.setbalance")) {
            if (args.length != 2) {
                sender.sendMessage("§cUsage: /setbalance <joueur> <montant>");
                return false;
            }

            Player target = Bukkit.getPlayer(args[0]);
            double amount;

            try {
                amount = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cMontant invalide.");
                return false;
            }

            if (target == null) {
                sender.sendMessage("§cJoueur introuvable.");
                return false;
            }

            EconomyManager.setBalance(target.getUniqueId(), amount);
            sender.sendMessage("§aLe solde de §e" + target.getName() + " §aa été défini à §e" + amount);
            target.sendMessage("§aVotre solde a été mis à jour à §e" + amount);
            return true;
        } else {
            sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }
    }
}
