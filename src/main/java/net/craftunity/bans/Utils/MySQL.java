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
            con = DriverManager.getConnection("jdbc:mysql://<insert IP here>:3306/Bansystem?autoRecconnect=true", "<insert user here>", "<insert Password here>");
        } catch (SQLException var1) {
            System.out.println("SQL Disconeccted");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
            System.out.println("SQL Error Connection unreachable");
        }

    }

    public static Connection getConnection() {
        return con;
    }
}
