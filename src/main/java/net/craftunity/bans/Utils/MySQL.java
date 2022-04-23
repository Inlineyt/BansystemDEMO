package net.craftunity.bans.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQL {

    private static Connection con;
    private static String Host;
    private static int port;
    private static String database;
    private static String user;
    private static String password;

    public MySQL(String Host, int port, String database, String user, String password) {
        MySQL.Host = Host;
        MySQL.port = port;
        MySQL.database = database;
        MySQL.user = user;
        MySQL.password = password;
        connect();
    }

    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://45.85.219.106:3306/Bansystem?autoRecconnect=true", "craftunity", "zFtf5zFTS*ncJrXT");
        } catch (SQLException var1) {
            System.out.println("SQL Disconecctde");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
            System.out.println("SQL Error");
        }

    }

    public static Connection getConnection() {
        return con;
    }
}
