package com.pwisetthon.myworldsystem;

import com.pwisetthon.myworldsystem.database.connectdatabase;
import com.pwisetthon.myworldsystem.database.maindatabase;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyWorldSystem extends JavaPlugin {

    private connectdatabase litedatabase;
    private maindatabase mariadb;

    @Override
    public void onEnable() {
        // Plugin startup logic
//        WorldCreator wc = new WorldCreator("BoyPhongsakorn");
//        wc.createWorld();

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            litedatabase = new connectdatabase(getDataFolder().getAbsolutePath() + "/last_location.db");
            mariadb = new maindatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bukkit.getPluginManager().registerEvents(new Mech(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            litedatabase.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public connectdatabase getLitedatabases() {
        return litedatabase;
    }

    public maindatabase getConnectdatabases() {
        return mariadb;
    }
}
