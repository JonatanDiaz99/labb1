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
        List<CartItemInfo> cartItemInfoList = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            Item item = Item.getItemById(cartItem.itemId());
            if (item == null) throw new IllegalStateException("Produkten finns inte i lagret");

            CartItemInfo itemInfo = new CartItemInfo(
                    item.getId(),
                    item.getName(),
                    cartItem.quantity(),
                    item.getPrice(),
                    cart.canAddItem(item)
            );

            cartItemInfoList.add(itemInfo);
        }

        return cartItemInfoList;
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    public void clear() {
        cart.clear();
    }

    public boolean canAddItem(Item item){
        return cart.canAddItem(item);
    }
}
