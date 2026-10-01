package se.kth.labb.bo.cart;

import se.kth.labb.bo.Item;

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
            cart.put(itemId, new CartItem(item));
        } else {
            cartItem.increaseQuantity();
        }
    }

    public List<CartItem> getItemsInCart(){
        return List.copyOf(cart.values());
    }
}
