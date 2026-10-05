package se.kth.labb.db;

import se.kth.labb.bo.Item;

import java.math.BigDecimal;
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

    public static double getPrice(Connection connection, int itemId) throws SQLException {
        String sql = "SELECT price FROM items WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("price");
                }
                throw new IllegalStateException("Produkten hittades inte");
            }
        }
    }

    public static void decreaseStock(Connection connection, int itemId, int quantity) throws SQLException {
        String sql =    "UPDATE items " +
                        "SET stock_quantity = stock_quantity - ? " +
                        "WHERE id = ? AND stock_quantity >= ? ";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, quantity);
            statement.setInt(2, itemId);
            statement.setInt(3, quantity);

            int updatedRows = statement.executeUpdate();

            if(updatedRows != 1){
                throw new IllegalStateException("Kunde inte uppdatera quantity i databasen");
            }
        }
    }
}