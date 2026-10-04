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

    public static int placeOrder(int userId, List<CartItem> cartItems) {
        try (Connection connection = DBManager.newConnection()) {
            connection.setAutoCommit(false);

            try {
                int orderId = insertOrder(connection, userId);
                for (CartItem cartItem : cartItems){
                    int itemId = cartItem.itemId();
                    int quantity = cartItem.quantity();
                    double price = ItemDB.getPrice(connection, itemId);

                    ItemDB.decreaseStock(connection, itemId, quantity);
                    insertOrderItem(connection, orderId, itemId, quantity, price);
                }

                connection.commit();
                return orderId;

            } catch (SQLException | RuntimeException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static int insertOrder(Connection connection, int userId) throws SQLException {
        String sql = "INSERT INTO orders (user_id)" +
                     "VALUES (?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, userId);

            try (ResultSet result = statement.executeQuery()){
                if(result.next()){
                    return result.getInt("id");
                }
                else {
                    throw new SQLException("Inget id kunde returneras");
                }
            }
        }
    }

    private static void insertOrderItem(
            Connection connection,
            int orderId,
            int itemId,
            int quantity,
            double price
    ) throws SQLException {
        String sql =    "INSERT INTO order_items " +
                        "(order_id, item_id, quantity, unit_price) " +
                        "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.setInt(2, itemId);
            statement.setInt(3, quantity);
            statement.setDouble(4, price);
            statement.executeUpdate();
        }
    }
}
