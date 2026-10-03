package se.kth.labb.ui.dto;

public class ItemInfo {
    private String name;
    private int quantity;
    private double price;
    private int id;

    public ItemInfo(String name, int quantity, double price, int id) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
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
}
