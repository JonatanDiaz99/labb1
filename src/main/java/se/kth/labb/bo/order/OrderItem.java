package se.kth.labb.bo.order;

public class OrderItem {
    private int userId;
    private int itemId;
    private int quantity;

    public OrderItem(int orderId, int itemId, int quantity) {
        this.userId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}
