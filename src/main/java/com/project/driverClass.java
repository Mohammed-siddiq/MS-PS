package com.project;

import com.oracle.tools.packager.Log;
import com.project.helper.InputReader;
import com.project.msps.MineSequentialPatterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class driverClass {
    public static int NUMBER_OF_SEQUENCES;

    static void printlist(List<String> list) {

        for (String s :
                list) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        InputReader inputReader = new InputReader();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter the data file name");
//        String dataFile = bufferedReader.readLine();
        String dataFile = "TestData/data.txt";
        String paramsFile = "TestData/params.txt";
//        System.out.println("Enter the params file name");
//        String paramsFile = bufferedReader.readLine();

        List<String> inputSequences = null;
        HashMap<String, Integer> mis = null;

        try {
            inputSequences = inputReader.sequenceReader(dataFile);
            NUMBER_OF_SEQUENCES = inputSequences.size();
            System.out.println("sequences ");
            printlist(inputSequences);
        } catch (IOException e) {
            Log.info("Error while reading the Sequences from the data");
            e.printStackTrace();
        }
        try {
            mis = inputReader.minimumSupportReader(paramsFile, NUMBER_OF_SEQUENCES);
            System.out.println("mis values");
            for (String key :
                    mis.keySet()) {
                System.out.println(key + " : " + mis.get(key));
            }

        } catch (IOException e) {
            Log.info("Error while");
        }
        MineSequentialPatterns mineSequentialPatterns = new MineSequentialPatterns();
        mineSequentialPatterns.algorithmMSPS(inputSequences, mis);


    }
}
