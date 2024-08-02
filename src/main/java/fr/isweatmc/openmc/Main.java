package fr.isweatmc.openmc;

import fr.isweatmc.openmc.commands.*;
import fr.isweatmc.openmc.utils.commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_20_R4.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin chargé ( ON! )");

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
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin chargé ( OFF! )");
    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }
}