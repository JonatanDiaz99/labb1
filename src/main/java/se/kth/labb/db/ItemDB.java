package se.kth.labb.db;

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

        try {
            Connection con = DBManager.getConnection();
            try (
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    public static Item getById(int itemId) {
        String sql =
                "SELECT id, name, stock_quantity, price " +
                        "FROM items " +
                        "WHERE id = ?";

        try {
            Connection con = DBManager.getConnection();
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                // Replaces the statements parameter "?" with the itemId
                statement.setInt(1, itemId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("stock_quantity"),
                                resultSet.getDouble("price")
                        );
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not fetch item " + itemId, e);
        }
    }
}