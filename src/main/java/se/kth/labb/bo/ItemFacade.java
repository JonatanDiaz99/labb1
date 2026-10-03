package se.kth.labb.bo;

import se.kth.labb.db.ItemDB;
import se.kth.labb.ui.dto.ItemInfo;

import java.util.*;

public class ItemFacade {
    public static List<ItemInfo> getAll() {
        List<Item> itemList = Item.getAll();
        List<ItemInfo> infoList = new ArrayList<>();

        for (Iterator it = itemList.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            infoList.add(new ItemInfo(item.getName(), item.getQuantity(), item.getPrice(), item.getId()));
        }
        return infoList;
    }
}
