package se.kth.labb.bo.cart;

import se.kth.labb.bo.Item;
import se.kth.labb.ui.dto.CartInfo;
import se.kth.labb.ui.dto.CartItemInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartFacade {
    private final Cart cart;

    public CartFacade(){
        cart = new Cart();
    }

    public void addItem(CartInfo cartInfo) {
        int itemId = cartInfo.getItemId();
        Item selectedItem = Item.getItemById(itemId);
        if (selectedItem == null) {
            throw new IllegalArgumentException("Produkten hittades inte");
        }
        cart.addItem(selectedItem);
    }

    public List<CartItemInfo> getItems() {
        List<CartItemInfo> cartItemList = new ArrayList<>();

        for (CartItem cartItem : cart.getItemsInCart()) {
            CartItemInfo itemInfo = new CartItemInfo(
                    cartItem.getItem().getName(),
                    cartItem.getQuantity(),
                    cartItem.getItem().getPrice()
            );

            cartItemList.add(itemInfo);
        }

        return cartItemList;
    }
}
