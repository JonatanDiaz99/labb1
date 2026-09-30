package se.kth.labb.bo;

import se.kth.labb.db.DBManager;
import se.kth.labb.db.ItemDB;

import java.util.List;

/**
 * Affärsobjekt för en produkt. Komplettera med produktens egenskaper och regler.
 * Detta är ett klasskelett; funktionaliteten är ännu inte implementerad.
 */

public class Item {

    private int id;
    private String name;
    private int quantity;
    private double price;

    public Item(
            int id,
            String name,
            int quantity,
            double price
    ) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public static List<Item> getAll() {
        return ItemDB.getAll();
    }
}
