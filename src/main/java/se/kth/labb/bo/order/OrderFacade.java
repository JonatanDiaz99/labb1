package se.kth.labb.bo.order;

import se.kth.labb.ui.dto.CartItemInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderFacade {
    public static boolean placeOrder(long userId, List<CartItemInfo> itemsInCart){
        if (itemsInCart == null || itemsInCart.isEmpty()) {
            throw new IllegalArgumentException("Ingen kundvagn hittades");
        }
        List<OrderRow> orderRows = convertToOrderLines(itemsInCart);
        Order order = Order.placeOrder(userId, orderRows);
        if (order == null){
            throw new RuntimeException();
        }
        return true;
    }

    private static List<OrderRow> convertToOrderLines(List<CartItemInfo> itemsInCart) {
        List<OrderRow> rows = new ArrayList<>();

        for (CartItemInfo item : itemsInCart) {
            rows.add(new OrderRow(
                    item.getId(),
                    item.getQuantity()
            ));
        }

        return rows;
    }
}
