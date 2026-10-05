package se.kth.labb.ui.dto;

public class CartItemInfo {
    private final int id;
    private final String name;
    private final int quantity;
    private final double price;
    private final boolean canAdd;

    public CartItemInfo(int id, String name, int quantity, double price, boolean canAdd) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.canAdd = canAdd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public boolean getCanAdd() {
        return canAdd;
    }
}