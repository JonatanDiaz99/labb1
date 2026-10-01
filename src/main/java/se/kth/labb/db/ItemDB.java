package se.kth.labb.db;

/**
 * Hämtar produkter från tabellen items via JDBC.
 */

import se.kth.labb.bo.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDB {
    // Item direkt från BO. Kanske inte är så bra...
    public static List<Item> getAll() {

        List<Item> items = new ArrayList<>();

        String sql =
                "SELECT id, name, stock_quantity, price " +
                        "FROM items";


        try (
                Connection con = DBManager.getConnection();
                PreparedStatement statement = con.prepareStatement(sql);
                ResultSet resultset = statement.executeQuery()
        ) {

            while (resultset.next()) {

                Item item = new Item(
                        resultset.getInt("id"),
                        resultset.getString("name"),
                        resultset.getInt("stock_quantity"),
                        resultset.getDouble("price")
                );

                items.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}