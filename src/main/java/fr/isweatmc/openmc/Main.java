package fr.isweatmc.openmc;

import fr.isweatmc.openmc.commands.*;
import fr.isweatmc.openmc.challenge.ChallengeManager;
import fr.isweatmc.openmc.listeners.PlayerMoveListener;
import fr.isweatmc.openmc.listeners.PlayerJoinListener;
import fr.isweatmc.openmc.utils.commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_20_R4.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    private ChallengeManager challengeManager;

    @Override
    public void onEnable() {
        System.out.println("Plugin chargé ( ON! )");

        saveDefaultConfig(); // Sauvegarde le fichier config.yml s'il n'existe pas déjà

        challengeManager = new ChallengeManager(this);

        createCommand(new SimpleCommand(
                "broadcast",
                "Envoyer un message à tout le serveur",
                new CommandBroadcast(),
                "bc"
        ));

        createCommand(new SimpleCommand(
                "feed",
                "Nourrit le joueur qui l'exécute",
                new CommandFeed(),
                ""
        ));

        createCommand(new SimpleCommand(
                "heal",
                "Soigne le joueur qui l'exécute",
                new CommandHeal(),
                ""
        ));

        createCommand(new SimpleCommand(
                "balance",
                "Affiche votre solde",
                new CommandBalance(),
                "bal"
        ));

        createCommand(new SimpleCommand(
                "pay",
                "Envoie de l'argent à un autre joueur",
                new CommandPay(),
                ""
        ));

        createCommand(new SimpleCommand(
                "setbalance",
                "Définit le solde d'un joueur",
                new CommandSetBalance(),
                "setbal"
        ));

        CommandWork commandWork = new CommandWork();
        createCommand(new SimpleCommand(
                "work",
                "Lance un défi de travail",
                commandWork,
                ""
        ));
        getServer().getPluginManager().registerEvents(commandWork, this);

        // Enregistrement des écouteurs d'événements
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(challengeManager), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(challengeManager), this);

        // Chargement des données
        challengeManager.loadChallenges();
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin chargé ( OFF! )");

        // Sauvegarde des données
        challengeManager.saveChallenges();
    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }
}