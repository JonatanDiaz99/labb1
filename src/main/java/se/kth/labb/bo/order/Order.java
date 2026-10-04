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

    public static long placeOrder(long userId, List<CartItem> cartItems){
        return OrderDB.placeOrder(userId, cartItems);
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
