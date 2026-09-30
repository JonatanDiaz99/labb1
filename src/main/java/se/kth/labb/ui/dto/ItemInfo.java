package se.kth.labb.ui.dto;

/**
 * Visningsdata för en produkt. Komplettera med de fält JSP-vyn behöver.
 * Detta är ett klasskelett; funktionaliteten är ännu inte implementerad.
 */
public class ItemInfo {
    private String name;
    private int quantity;
    private double price;

    public ItemInfo(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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
}
