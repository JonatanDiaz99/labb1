package se.kth.labb.db;

/**
 * Ansvarar framöver för att skapa JDBC-anslutningar. Databas och anslutningskonfiguration är ännu inte valda. Dela inte en global Connection mellan webbförfrågningar.
 * Detta är ett klasskelett; funktionaliteten är ännu inte implementerad.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String URL =
            "jdbc:postgresql://db:5432/webshop";

    private static final String USER =
            "webshop_user";

    private static final String PASSWORD =
            "secret";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
