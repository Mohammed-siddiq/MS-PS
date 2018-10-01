package com.project.msps;

import com.project.POJOS.Database;
import com.project.POJOS.Item;
import com.project.POJOS.ItemSet;
import com.project.POJOS.Sequence;

import java.util.*;
import java.util.stream.Collectors;


public class MineSequentialPatterns {
    List<String> sequenceData;
    HashMap<String, Double> mis;
    List<LinkedHashMap<String, Integer>> frequentSequences;
    Database frequentSequenceDb;
    Database skDb;

    public void setSequenceData(List<String> sequenceData) {
        this.sequenceData = sequenceData;
    }

    public void setMis(HashMap<String, Double> mis) {
        this.mis = mis;
    }

    public MineSequentialPatterns() {
        frequentSequences = new ArrayList<>();
        frequentSequenceDb = new Database();
    }
//    public MineSequentialPatterns(ArrayList<String> sequenceData, HashMap<String, Float> mis) {
//        this.sequenceData = sequenceData;
//        this.mis = mis;
//        frequentSequences = new ArrayList<>();
//    }

    int getSupportCount(String sequence) {
        int supportCount = 0;
        for (String aSequence :
                sequenceData) {
//            if (aSequence.matches("\\^\\.*" + sequence + "\\.*\\$")) // can be generalised for now directly checking
//                supportCount++;
            if (aSequence.contains(sequence))
                supportCount++;
        }
        return supportCount;
    }

    public void algorithmMSPS(List<String> sequenceData, HashMap<String, Double> mis) {

        setSequenceData(sequenceData);
        setMis(mis);

        //step 1: get support count for each item and if frequent generate f1 sequences


        HashMap<String, Integer> frequent1Sequences = new HashMap<>();
        int itemSupport = 0;
        for (String item :
                mis.keySet()) {
            itemSupport = getSupportCount(item);
            if (itemSupport >= mis.get(item)) {
                frequent1Sequences.put("< { " + item + " } >", itemSupport);
            }
        }
        LinkedHashMap<String, Integer> sortedF1Sequences = sortFrequent1Items(frequent1Sequences); // sorting based on keys first and then value
        frequentSequences.add(sortedF1Sequences);
        for (Map.Entry<String, Integer> frequentItem :
                sortedF1Sequences.entrySet()) {

            applySDCAndGenerateSk(frequentItem.getKey());

        }
        outputFrequentSequences();
    }


    private void applySDCAndGenerateSk(String item) {
        item = replaceBraces(item);
        item = item.replaceAll("\\{|\\}|<|>", "").trim();
        for (String sequence : sequenceData) {
            if (sequence.contains(item)) { // only if item is in the sequence apply sdc
                if (satisfiesSDC(sequence, item)) {

                }


            }
        }

    }

    // replaces brackets and angular brackets
    private String replaceBraces(String item) {
        return item.replaceAll("\\{|\\}|<|>", "").trim();
    }

    private boolean satisfiesSDC(String sequence, String item) {
        String rawSequence = replaceBraces(sequence);
        return true;

    }


    private LinkedHashMap<String, Integer> sortFrequent1Items(HashMap<String, Integer> frequent1Sequences) {
        LinkedHashMap<String, Integer> result = frequent1Sequences.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }

    public void outputFrequentSequences() {

        for (int i = 0; i < frequentSequences.size(); i++) {
            System.out.println("The number of length " + (i + 1) + " sequential pattern is " + frequentSequences.get(i).size());
//            for (String sequence :
//                    frequentSequences.get(i).keySet()) {
//                System.out.println("Pattern: " + sequence + ":Count=" + frequentSequences.get(i).get(sequence));
//            }
            frequentSequences.forEach(f1SequenceMap -> f1SequenceMap.forEach((key, value) ->
                    System.out.println("Pattern :" + key + ": Count=" + value)));
        }
    }

    public void generateFrequentSequences(Database database) {
        List<Item> frequentItems = new ArrayList<Item>();

        // find frequent items
        for (Sequence sequence : database.getSequences()) {
            for (ItemSet itemSet : sequence.getItemSets()) {
                for (Item item : itemSet.getItems()) {
                    if (item.getSupport() >= item.getMis()) {
                        frequentItems.add(item);
                    }
                }
            }
        }


        //sorting
        Collections.sort(frequentItems, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return i1.getItem().compareTo(i2.getItem());
            }
        });


        for (Item item :
                frequentItems) {
            skDb = database;
            for (Sequence sequence : skDb.getSequences()) {
                if (sequence.toString().contains(item.toString())) {
                    Sequence sdSequence = applySupportDifferenceConstraint(sequence, item);

                } else
                    database.getSequences().remove(sequence);

            }
        }
    }

    private Sequence applySupportDifferenceConstraint(Sequence sequence, Item itemI) {
        Sequence sequenceSDC = new Sequence();
        for (ItemSet itemSet : sequence.getItemSets()) {
            for (Item itemJ : itemSet.getItems()) {
                // different item should be.
                if (Math.abs(itemI.getSupport() - itemJ.getSupport()) > Database.SDC) {
                    sequence.removeItem(itemJ);
                }
            }
        }

        return null;
    }


}
