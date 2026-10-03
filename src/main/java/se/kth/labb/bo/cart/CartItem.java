package se.kth.labb.bo.cart;

import se.kth.labb.bo.Item;

public class CartItem {
    private final Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        if(quantity - 1 < 0){
            return;
        }
        quantity--;
    }
}
