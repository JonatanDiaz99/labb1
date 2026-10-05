package se.kth.labb.bo.order;

import se.kth.labb.db.OrderDB;

import java.util.List;

public class Order {
    public static long placeOrder(long userId, List<CartItem> cartItems){
        return OrderDB.placeOrder(userId, cartItems);
    }
}
