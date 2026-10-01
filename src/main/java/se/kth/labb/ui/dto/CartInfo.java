package se.kth.labb.ui.dto;

public class CartInfo {
    private final int itemId;

    public CartInfo(int itemId){
        this.itemId = itemId;
    }

    public int getItemId(){
       return itemId;
    }
}
