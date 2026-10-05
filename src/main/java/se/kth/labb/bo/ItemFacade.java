package se.kth.labb.bo;

import se.kth.labb.bo.cart.CartFacade;
import se.kth.labb.ui.dto.ItemInfo;

import java.util.*;

public class ItemFacade {
    public static List<ItemInfo> getAll(CartFacade cartFacade) {
        List<Item> itemList = Item.getAll();
        List<ItemInfo> infoList = new ArrayList<>();

        for (Iterator it = itemList.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            boolean canAddItem = cartFacade.canAddItem(item);
            infoList.add(new ItemInfo(item.getName(), item.getQuantity(), item.getPrice(), item.getId(), canAddItem));
        }
        return infoList;
    }
}
