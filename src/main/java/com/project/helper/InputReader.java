package com.project.helper;

import com.oracle.tools.packager.Log;
import com.project.POJOS.Database;
import com.project.POJOS.Item;
import com.project.POJOS.ItemSet;
import com.project.POJOS.Sequence;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputReader {
    private static final String SDC_KEY = "SDC";
    public static int SDC_VALUE;
    BufferedReader fp;
    Database db;
    Sequence sequence;
    ItemSet itemSet;
    Item item;
    List<Item> items;


    public InputReader() {
        db = new Database();
    }

    public List<String> sequenceReader(String fileName) throws IOException {
        Log.info("Opening the data file " + fileName);

        try {
            fp = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            Log.info("File not found : " + fileName);
            e.printStackTrace();
        }
        String line;
        List<String> sequenceAsString = new ArrayList<String>();

        while ((line = fp.readLine()) != null) {
            sequenceAsString.add(line);
            Sequence sequenceInstance = generateItemSetsAndSequences(line);
            db.addSequence(sequenceInstance);
        }
        Log.info("Successfully read the Sequence file");
        fp.close();
        return sequenceAsString;

    }

    private Sequence generateItemSetsAndSequences(String line) {
        String[] itemSetString = line.split("}");
        sequence = new Sequence();
        for (String itemSetStr : itemSetString) {
            itemSetStr = itemSetStr.replaceAll("\\{|<|>", "");
            if(itemSetStr.length()==0) continue;
            itemSet = new ItemSet();
            String[] itemsStr = itemSetStr.split(",");
            for (String s : itemsStr) {
                Item itemInstance = getItemObject(s);// gets the item instance for the item read from the Sequence
                itemSet.addItem(itemInstance);
            }
            sequence.addItemSet(itemSet);
        }
        return sequence;
    }

    private Item getItemObject(String s) {
        for (Item item : items) {
            if (item.toString().equals(s))
                return item;
        }
        return null;
    }

    public HashMap<String, Double> minimumSupportReader(String fileName) throws IOException {
        Log.info("Opening the params file " + fileName);

        items = new ArrayList<>();
        try {
            fp = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            Log.info("File not found :" + fileName);
            e.printStackTrace();
        }

        String line;
        String key;
        double value;
        HashMap<String, Double> mis = new HashMap<>();
        while ((line = fp.readLine()) != null) {
            String[] keyValue = line.split("=");
            if (keyValue[0].equals(SDC_KEY)) {
                key = SDC_KEY;
                value = Double.parseDouble(keyValue[1]);
            } else {
                item = new Item();
                value = Double.parseDouble(keyValue[1]);
                key = keyValue[0].substring(keyValue[0].indexOf("(") + 1, keyValue[0].indexOf(")")); //[0] has the key
                item.setItem(key);
                item.setMis(value);
                items.add(item);
            }

            mis.put(key, value); // [1] has the value
        }
        return mis;
    }

}
