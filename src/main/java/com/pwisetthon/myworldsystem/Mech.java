package com.pwisetthon.myworldsystem;

import com.pwisetthon.myworldsystem.database.connectdatabase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mech implements Listener {

    private final MyWorldSystem plugin;

    public Mech(MyWorldSystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        event.getPlayer().sendMessage("Welcome to the server!");
        //check have world BoyPhongsakorn
        if (Bukkit.getServer().getWorld("BoyPhongsakorn") == null) {
            System.out.println("Create world BoyPhongsakorn");
            //create world BoyPhongsakorn
            Bukkit.createWorld(new WorldCreator("BoyPhongsakorn"));
        }
        System.out.println(event.getPlayer().getWorld());
        System.out.println(Bukkit.getWorld("world"));
        //if player in world have world
        if (event.getPlayer().getWorld() == Bukkit.getWorld("world")) {
            //set player location to world BoyPhongsakorn
            event.getPlayer().teleport(Bukkit.getWorld("BoyPhongsakorn").getSpawnLocation());
        }
        try {
//            List<Map<String, Object>> lastLocation = connectdatabases.getLastLocation(event.getPlayer().getName());
            List<Map<String, Object>> lastLocation = plugin.getLitedatabases().getLastLocation(event.getPlayer().getName());
            if (lastLocation != null) {
                //get player BoyPhongsakorn in list
                for (Map<String, Object> map : lastLocation) {
                    if (map.get("player_name").equals(event.getPlayer().getName())) {
                        System.out.println("Player " + event.getPlayer().getName() + " last location is " + map.get("world") + " " + map.get("x") + " " + map.get("y") + " " + map.get("z"));
                        //set player location to last location and same move direction
                        event.getPlayer().teleport(new Location(Bukkit.getWorld((String) map.get("world")), (double) map.get("x"), (double) map.get("y"), (double) map.get("z"), (float) map.get("yaw"), (float) map.get("pitch")));
//                        event.getPlayer().teleport(new Location(Bukkit.getWorld((String) map.get("world")), (double) map.get("x"), (double) map.get("y"), (double) map.get("z")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws IOException {
        //save last location to file in resources
        System.out.println("Player " + event.getPlayer().getName() + " leave server");
//        String path = "/tmp/";
//        System.out.println(path);
        //save last location to file in resources
        //read json file use path and file name
//        Path g = Paths.get(path + "last_location.json");
//        InputStream is = new FileInputStream(g.toFile());
//        if (is != null) {
        if (true) {
            //convert list in file to list in map string object
//            List<Map<String, Object>> list = new java.util.ArrayList<>();
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    //convert string to list in map string object
////                    list = (List<Map<String, Object>>) com.google.gson.internal.LinkedTreeMap.class.cast(com.google.gson.internal.Streams.parse(new com.google.gson.stream.JsonReader(new StringReader(line)))).get("list");
//                    list.add(line);
//                    System.out.println(line);
//                }
//                System.out.println(list);
//                //get player BoyPhongsakorn in list
//                for (Map<String, Object> map : list) {
//                    if (map.get("player").equals(event.getPlayer().getName())) {
//                        System.out.println("Player " + event.getPlayer().getName() + " last location is " + map.get("world") + " " + map.get("x") + " " + map.get("y") + " " + map.get("z"));
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            //create list in list in map string object
//            List<Map<String, Object>> list = new ArrayList<>();
//            //create map string object
//            Map<String, Object> map = new java.util.HashMap<>();
//            //put key value to map
//            map.put("player", event.getPlayer().getName());
//            map.put("world", event.getPlayer().getWorld().getName());
//            map.put("x", event.getPlayer().getLocation().getX());
//            map.put("y", event.getPlayer().getLocation().getY());
//            map.put("z", event.getPlayer().getLocation().getZ());
//            //add map to list
//            list.add(map);
//            //write json file
//            File file = new File(path + "last_location.json");
//            try (FileWriter writer = new FileWriter(file)) {
//                writer.write(list.toString());
//                writer.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        try {
//            connectdatabases.saveLocation(event.getPlayer().getName(), event.getPlayer().getWorld().getName(), event.getPlayer().getLocation().getX(), event.getPlayer().getLocation().getY(), event.getPlayer().getLocation().getZ(), event.getPlayer().getLocation().getYaw(), event.getPlayer().getLocation().getPitch());
            plugin.getLitedatabases().saveLocation(event.getPlayer().getName(), event.getPlayer().getWorld().getName(), event.getPlayer().getLocation().getX(), event.getPlayer().getLocation().getY(), event.getPlayer().getLocation().getZ(), event.getPlayer().getLocation().getYaw(), event.getPlayer().getLocation().getPitch());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

                              //Handle event player jump to nether
    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event) {
        World fromWorld = event.getFrom().getWorld();
        System.out.println("Player " + event.getPlayer().getName() + " jump to " + fromWorld.getName().replace("_nether", ""));
        //check have world fromWorld_nether
        switch (event.getCause()) {
            case NETHER_PORTAL:
                switch (event.getFrom().getWorld().getEnvironment()) {
                    case NORMAL:
                        if (Bukkit.getServer().getWorld(fromWorld.getName() + "_nether") == null) {
                            System.out.println("Create world " + fromWorld.getName() + "_nether");
                            //create world fromWorld_nether
                            Bukkit.createWorld(new WorldCreator(fromWorld.getName() + "_nether").environment(World.Environment.NETHER));
                        }
                        break;
                }
        }
        switch (event.getCause()) {
            case NETHER_PORTAL:
                switch (event.getFrom().getWorld().getEnvironment()) {
                    case NORMAL:
//                        event.setTo(Bukkit.getWorld(fromWorld.getName().replace("_nether", "")).getSpawnLocation().multiply(8.0D));
//                        event.setTo(Bukkit.getWorld(fromWorld.getName() + "_nether").getSpawnLocation());
                        //get current location
                        Location loc = event.getFrom();
                        System.out.println("Current location: " + loc.getX() + " " + loc.getY() + " " + loc.getZ());
                        //medium package
//                        event.setTo(new Location(Bukkit.getWorld(fromWorld.getName() + "_nether"), loc.getX() / 8.0D, loc.getY(), loc.getZ() / 8.0D));
                        //low package
                        event.setTo(new Location(Bukkit.getWorld( "world_nether"), loc.getX() / 8.0D, loc.getY(), loc.getZ() / 8.0D));
                        break;
                    case NETHER:
//                        event.setTo(Bukkit.getWorld(fromWorld.getName() + "_nether").getSpawnLocation());
//                        event.setTo(Bukkit.getWorld(fromWorld.getName().replace("_nether", "")).getSpawnLocation().multiply(8.0D));
                        //get current location
                        Location loc2 = event.getFrom();
                        System.out.println("Current location: " + loc2.getX() + " " + loc2.getY() + " " + loc2.getZ());
                        //medium package
//                        event.setTo(new Location(Bukkit.getWorld(fromWorld.getName().replace("_nether", "")), loc2.getX() * 8.0D, loc2.getY(), loc2.getZ() * 8.0D));
                        //low package
                        event.setTo(new Location(Bukkit.getWorld(event.getPlayer().getName()), loc2.getX() * 8.0D, loc2.getY(), loc2.getZ() * 8.0D));
                        break;
                    default:
                        break;
                }
        }
    }
}
