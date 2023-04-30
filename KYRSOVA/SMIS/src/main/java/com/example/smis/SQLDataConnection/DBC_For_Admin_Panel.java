package com.example.smis.SQLDataConnection;
import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC_For_Admin_Panel extends Configs {
    public Connection databaseLink;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        databaseLink = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return databaseLink;
    }
}
