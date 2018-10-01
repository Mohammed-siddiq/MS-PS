package com.project.POJOS;

import java.util.ArrayList;
import java.util.List;

public class ItemSet {
    List<Item> items;

    public ItemSet() {
        items = new ArrayList<>();
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
        for (Item item : items) {
            outPutString.append(item.item + ",");
        }
        outPutString.deleteCharAt(outPutString.lastIndexOf(","));
        outPutString.append("}");
        return outPutString.toString();
    }

    public boolean remove(Item itemI) {

        for (Item itemJ : items) {
            if (itemI.toString().equals(itemJ.toString())) {
                items.remove(itemJ);
                return true;
            }

        }
        return false;

    }

    public void addItem(Item item) {
        items.add(item);
    }
}
