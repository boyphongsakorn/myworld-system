package com.pwisetthon.myworldsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class maindatabase {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://49.228.131.162:5532/myworldsystem";
    private static final String USER = "root";
    private static final String PASS = "Team1556th_";
    private final Connection connection;
    public maindatabase() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Unable to load JDBC driver");
        }
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
