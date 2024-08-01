package fr.isweatmc.openmc;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin chargé ( ON! )");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin chargé ( OFF! )");
    }
}
