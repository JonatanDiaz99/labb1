package se.kth.labb.bo.order;

import se.kth.labb.bo.cart.CartItem;
import se.kth.labb.ui.dto.CartItemInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderFacade {
    public static void placeOrder(int userId, List<CartItemInfo> itemsInCart){
        if (itemsInCart == null || itemsInCart.isEmpty()) {
            throw new IllegalArgumentException("Ingen kundvagn hittades");
        }
        List<CartItem> cartItems = convertToCartItems(itemsInCart);
        Order.placeOrder(userId, cartItems);
    }

    private static List<CartItem> convertToCartItems(List<CartItemInfo> cartItemInfoList) {
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemInfo item : cartItemInfoList) {
            cartItems.add(new CartItem(
                    item.getId(),
                    item.getQuantity()
            ));
        }
        return cartItems;
    }
}
