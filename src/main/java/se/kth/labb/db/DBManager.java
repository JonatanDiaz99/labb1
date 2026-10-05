package se.kth.labb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static DBManager instance = null;
    private Connection connection = null;

    private static final String URL =
            "jdbc:postgresql://db:5432/webshop";

    private static final String USER =
            "webshop_user";

    private static final String PASSWORD =
            "secret";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static DBManager getInstance() throws SQLException {
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            connection = newConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
       return getInstance().connection;
    }

    public static Connection newConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
