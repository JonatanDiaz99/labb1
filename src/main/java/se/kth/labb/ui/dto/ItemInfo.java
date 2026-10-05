package se.kth.labb.ui.dto;

public class ItemInfo {
    private String name;
    private int quantity;
    private double price;
    private int id;
    private final boolean canAdd;

    public ItemInfo(String name, int quantity, double price, int id, boolean canAdd) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
        this.canAdd = canAdd;
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

    public int getId(){
        return id;
    }

    public boolean getCanAdd() {
        return canAdd;
    }
}
