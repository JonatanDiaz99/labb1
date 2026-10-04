package se.kth.labb.bo.order;

import se.kth.labb.db.OrderDB;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class Order {
    private BigInteger id;
    private BigInteger userId;
    private Timestamp createdAt;
    private boolean isPacked;

    public Order(BigInteger id, BigInteger userId, Timestamp createdAt, boolean isPacked) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isPacked = isPacked;
    }

    public static Order placeOrder(long userId, List<OrderRow> orderRows){
        long orderId;
        Order order;
        try {
            orderId = OrderDB.placeOrder(userId, orderRows);
            order = OrderDB.getOrder(orderId);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
        return order;
    }

    public BigInteger getId() {
        return id;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public boolean isPacked() {
        return isPacked;
    }


}
