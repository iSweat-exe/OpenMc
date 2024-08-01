package fr.isweatmc.openmc;

import fr.isweatmc.openmc.commands.CommandBroadcast;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin chargé ( ON! )");

        getCommand("broadcast").setExecutor(new CommandBroadcast());
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin chargé ( OFF! )");
    }
}
