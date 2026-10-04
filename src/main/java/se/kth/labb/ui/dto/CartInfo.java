package se.kth.labb.ui.dto;

import java.util.List;

public class CartInfo {
    private final List<CartItemInfo> cartItems;

    public CartInfo(List<CartItemInfo> cartItems){
        this.cartItems = List.copyOf(cartItems);
    }

    public List<CartItemInfo> getCartItems() {
        return cartItems;
    }
}
