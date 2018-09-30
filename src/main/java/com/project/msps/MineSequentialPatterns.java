package com.project.msps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MineSequentialPatterns {
    List<String> sequenceData;
    HashMap<String, Integer> mis;
    List<HashMap<String, Integer>> frequentSequences;

    public void setSequenceData(List<String> sequenceData) {
        this.sequenceData = sequenceData;
    }

    public void setMis(HashMap<String, Integer> mis) {
        this.mis = mis;
    }

    public MineSequentialPatterns() {
        frequentSequences = new ArrayList<>();
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

    public void algorithmMSPS(List<String> sequenceData, HashMap<String, Integer> mis) {

        setSequenceData(sequenceData);
        setMis(mis);

        //step 1: get support count for each item and if frequent generate f1 sequences


        HashMap<String, Integer> frequent1Sequences = new HashMap<>();
        int itemSupport = 0;
        for (String item :
                mis.keySet()) {
            itemSupport = getSupportCount(item);
            if (itemSupport >= mis.get(item)) {
                frequent1Sequences.put( "< { " +item + " } >", itemSupport);
            }
        }
        frequentSequences.add(frequent1Sequences);

        outputFrequentSequences();
    }

    public void outputFrequentSequences() {

        for (int i = 0; i < frequentSequences.size(); i++) {
            System.out.println("The number of length " + (i + 1) + " sequential pattern is " + frequentSequences.get(i).size());
            for (String sequence :
                    frequentSequences.get(i).keySet()) {
                System.out.println("Pattern: " + sequence + ":Count=" + frequentSequences.get(i).get(sequence));
            }
        }
    }

}
