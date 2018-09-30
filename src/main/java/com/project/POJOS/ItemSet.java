package com.project.POJOS;

import java.util.List;

public class ItemSet {
    List<Item> items;

    public ItemSet(List<Item> itemSet) {
        this.items = itemSet;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuffer outPutString = new StringBuffer("{");
        for (Item item :
                items) {
            outPutString.append(item.item + ",");
        }
        outPutString.deleteCharAt(outPutString.lastIndexOf(","));
        outPutString.append("}");
        return outPutString.toString();
    }

}
