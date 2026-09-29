package se.kth.labb.db;

/**
 * Hämtar produkter från tabellen items via JDBC.
 */



import se.kth.labb.bo.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDB {

    public static List<Item> getAll() {

        List<Item> items = new ArrayList<>();

        String sql =
                "SELECT id, name, stock_quantity, price " +
                        "FROM items";

        try (
                Connection con = DBManager.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Item item = new Item(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("stock_quantity"),
                        rs.getDouble("price")
                );

                items.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}