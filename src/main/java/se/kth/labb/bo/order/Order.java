package se.kth.labb.bo.order;

import se.kth.labb.bo.cart.CartItem;
import se.kth.labb.db.OrderDB;

import java.util.List;

public class Order {
    public static int placeOrder(int userId, List<CartItem> cartItems){
        return OrderDB.placeOrder(userId, cartItems);
    }
}
