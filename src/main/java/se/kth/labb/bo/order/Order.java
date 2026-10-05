package se.kth.labb.bo.order;

import se.kth.labb.bo.Item;
import se.kth.labb.bo.cart.CartItem;
import se.kth.labb.db.OrderDB;

import java.util.List;

public class Order {
    public static int placeOrder(int userId, List<CartItem> cartItems){
        for (CartItem cartItem : cartItems) {
            Item item = Item.getItemById(cartItem.itemId());

            if (item == null) {
                throw new IllegalArgumentException(
                        "Produkten finns inte längre"
                );
            }

            if (!item.hasStock(cartItem.quantity())) {
                throw new IllegalArgumentException(
                        "Lagret räcker inte för " + item.getName()
                );
            }
        return OrderDB.placeOrder(userId, cartItems);
    }
}
