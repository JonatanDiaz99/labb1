package se.kth.labb.bo.cart;

import se.kth.labb.bo.Item;
import se.kth.labb.ui.dto.CartItemInfo;

import java.util.ArrayList;
import java.util.List;

public class CartFacade {
    private final Cart cart;

    public CartFacade(){
        cart = new Cart();
    }

    public void addItem(int itemId) {
        Item selectedItem = Item.getItemById(itemId);
        if (selectedItem == null) {
            throw new IllegalArgumentException("Produkten hittades inte");
        }
        cart.addItem(selectedItem);
    }

    public void removeItem(int itemId) {
        Item selectedItem = Item.getItemById(itemId);
        if (selectedItem == null) {
            throw new IllegalArgumentException("Produkten hittades inte");
        }
        cart.removeItem(selectedItem);
    }

    public List<CartItemInfo> getItems() {
        List<CartItemInfo> cartItemList = new ArrayList<>();

        for (CartItem cartItem : cart.getItemsInCart()) {
            CartItemInfo itemInfo = new CartItemInfo(
                    cartItem.getItem().getId(),
                    cartItem.getItem().getName(),
                    cartItem.getQuantity(),
                    cartItem.getItem().getPrice()
            );

            cartItemList.add(itemInfo);
        }

        return cartItemList;
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    public void clear() {
        cart.clear();
    }
}
