package com.project.POJOS;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    List<ItemSet> itemSets;
    int length;

    public Sequence() {
        itemSets = new ArrayList<>();
    }

    public List<ItemSet> getItemSets() {
        return itemSets;
    }

    public void setItemSets(List<ItemSet> itemSets) {
        this.itemSets = itemSets;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int removeItem(Item item) {
        int removedOccurences = 0;
        for (ItemSet itemSet : itemSets) {
            if (itemSet.remove(item))
                removedOccurences++;
        }
        return removedOccurences;
    }

    @Override
    public String toString() {
        StringBuffer outPutString = new StringBuffer("<");
        for (ItemSet itemSet : itemSets) {

            outPutString.append(itemSet.toString() + ",");

        }
        outPutString.deleteCharAt(outPutString.lastIndexOf(","));
        outPutString.append(">");
        return outPutString.toString();
    }

    public void addItemSet(ItemSet itemSet) {
        itemSets.add(itemSet);
    }
}
