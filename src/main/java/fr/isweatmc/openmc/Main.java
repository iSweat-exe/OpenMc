package fr.isweatmc.openmc;

import fr.isweatmc.openmc.commands.CommandBroadcast;
import fr.isweatmc.openmc.utils.commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_20_R4.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin chargé ( ON! )");

        createCommand(new SimpleCommand(
                "broadcast",
                "Envoyer un message a tout le serveur",
                new CommandBroadcast(),
                "bc"
        ));

    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), command);
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin chargé ( OFF! )");
    }
}
