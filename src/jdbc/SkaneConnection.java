package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SkaneConnection {
    private static Connection con;

    private static String url = System.getenv("DB_URL");
    private static String user =System.getenv("DB_USER");
    private static String pass = System.getenv("DB_PASS");;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        SkaneConnection.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        SkaneConnection.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        SkaneConnection.pass = pass;
    }

    public static Connection getConnection(String url, String user, String pass) {
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
