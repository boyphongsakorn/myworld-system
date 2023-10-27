package com.pwisetthon.myworldsystem.database;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class connectdatabase {
    private final Connection connection;

    public connectdatabase(String path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS last_location (player_name TEXT, world TEXT, x REAL, y REAL, z REAL, yaw REAL, pitch REAL)");
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void saveLocation(String player_name, String world, double x, double y, double z, float yaw, float pitch) throws SQLException {
        Statement statement = connection.createStatement();
        //search player_name in database
        if (statement.executeQuery("SELECT * FROM last_location WHERE player_name = '" + player_name + "'").next()) {
            //update location
            statement.execute("UPDATE last_location SET world = '" + world + "', x = " + x + ", y = " + y + ", z = " + z + ", yaw = " + yaw + ", pitch = " + pitch + " WHERE player_name = '" + player_name + "'");
        } else {
            //insert location
            statement.execute("INSERT INTO last_location (player_name, world, x, y, z, yaw, pitch) VALUES ('" + player_name + "', '" + world + "', " + x + ", " + y + ", " + z + ", " + yaw + ", " + pitch + ")");
        }
    }

    public List<Map<String, Object>> getLastLocation(String player_name) throws SQLException {
        Statement statement = connection.createStatement();
        //search player_name in database
        if (statement.executeQuery("SELECT * FROM last_location WHERE player_name = '" + player_name + "'").next()) {
            //get location and convert to list in map string object
            ResultSet resultSet = statement.executeQuery("SELECT * FROM last_location WHERE player_name = '" + player_name + "'");
            List<Map<String, Object>> list = new java.util.ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> map = new java.util.HashMap<>();
                map.put("player_name", resultSet.getString("player_name"));
                map.put("world", resultSet.getString("world"));
                map.put("x", resultSet.getDouble("x"));
                map.put("y", resultSet.getDouble("y"));
                map.put("z", resultSet.getDouble("z"));
                map.put("yaw", resultSet.getFloat("yaw"));
                map.put("pitch", resultSet.getFloat("pitch"));
                list.add(map);
            }
            return list;
        } else {
            //insert location
            return null;
        }
    }

}
