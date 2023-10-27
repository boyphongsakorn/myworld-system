package com.pwisetthon.myworldsystem;

import com.pwisetthon.myworldsystem.database.connectdatabase;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyWorldSystem extends JavaPlugin {

    private connectdatabase connectdatabases;

    @Override
    public void onEnable() {
        // Plugin startup logic
//        WorldCreator wc = new WorldCreator("BoyPhongsakorn");
//        wc.createWorld();

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            connectdatabases = new connectdatabase(getDataFolder().getAbsolutePath() + "/last_location.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(new Mech(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            connectdatabases.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public connectdatabase getConnectdatabases() {
        return connectdatabases;
    }
}
