package se.kth.labb.db;

import se.kth.labb.bo.order.Order;
import se.kth.labb.bo.cart.CartItem;
import se.kth.labb.bo.order.OrderRow;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDB {

    public static long placeOrder(long userId, List<OrderRow> orderRows) {
        long orderId;
        try (Connection connection = DBManager.getConnection()){
            connection.setAutoCommit(false);
            try {
                orderId = insertOrder(connection, userId);
                for (OrderRow row : orderRows){
                    int itemId = row.itemId();
                    int quantity = row.quantity();
                    double price = ItemDB.getById(itemId).getPrice();

                    ItemDB.decreaseStock(connection, itemId, quantity);
                    insertOrderItem(connection, orderId, itemId, quantity, price);
                }
                connection.commit();
            } catch (SQLException | RuntimeException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderId;
    }


    private static Long insertOrder(Connection connection, long userId) throws SQLException {
        String sql = "INSERT INTO orders (user_id)" +
                     "VALUES (?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, userId);

            try (ResultSet result = statement.executeQuery()){
                if(result.next()){
                    return result.getLong("id");
                }
                else {
                    throw new SQLException("Inget id kunde returneras");
                }
            }
        }
    }

    private static void insertOrderItem(
            Connection connection,
            long orderId,
            int itemId,
            int quantity,
            double price
    ) throws SQLException {
        String sql =    "INSERT INTO order_items " +
                        "(order_id, item_id, quantity, unit_price) " +
                        "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId);
            statement.setInt(2, itemId);
            statement.setInt(3, quantity);
            statement.setDouble(4, price);
            statement.executeUpdate();
        }
    }

    public static Order getOrder(long orderId) {
        String sql = "SELECT id, user_id, created_at, is_packed " +
                "FROM orders " +
                "WHERE id = ?";

        try {
            Connection connection = DBManager.getConnection();

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setLong(1, orderId);

                try (ResultSet result = statement.executeQuery()) {
                    if (!result.next()) {
                        return null;
                    }

                    return new Order(
                            BigInteger.valueOf(result.getLong("id")),
                            BigInteger.valueOf(result.getLong("user_id")),
                            result.getTimestamp("created_at"),
                            result.getBoolean("is_packed")
                    );
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Kunde inte hämta order " + orderId,
                    e
            );
        }
    }
}
