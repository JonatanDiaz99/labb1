package se.kth.labb.bo.cart;

import se.kth.labb.bo.Item;
import se.kth.labb.bo.order.CartItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private final Map<Integer, CartItem> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    public void addItem(Item item){
        int itemId = item.getId();
        CartItem cartItem = cart.get(itemId);

        if (cartItem == null){
            cart.put(itemId, new CartItem(itemId, 1));
        } else {
            int quantity = cartItem.quantity() + 1;
            cart.put(itemId, new CartItem(itemId, quantity));
        }
    }

    public void removeItem(Item item){
        int itemId = item.getId();
        CartItem cartItem = cart.get(itemId);

        if (cartItem == null) {
            return;
        }
        if(cartItem.quantity() <= 1) {
            cart.remove(itemId);
        } else {
            int quantity = cartItem.quantity() - 1;
            cart.put(itemId, new CartItem(itemId, quantity));
        }
    }

    public List<CartItem> getCartItems(){
        return List.copyOf(cart.values());
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    public void clear() {
        cart.clear();
    }
}
