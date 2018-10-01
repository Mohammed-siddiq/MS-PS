package com.project;

import com.project.POJOS.Database;
import com.project.helper.InputReader;
import com.project.msps.MineSequentialPatterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        HashMap<String, Double> mis = null;

        mis = inputReader.minimumSupportReader(paramsFile);
        System.out.println("mis values");
        mis.forEach((key, value) -> System.out.println(key + " : " + value));


        inputSequences = inputReader.sequenceReader(dataFile);
        NUMBER_OF_SEQUENCES = inputSequences.size();
        System.out.println("sequences ");
        printlist(inputSequences);


//        mis = inputReader.minimumSupportReader(paramsFile, NUMBER_OF_SEQUENCES);
//        System.out.println("mis values");
//        mis.forEach((key, value) -> System.out.println(key + " : " + value));

        MineSequentialPatterns mineSequentialPatterns = new MineSequentialPatterns();
        mineSequentialPatterns.algorithmMSPS(inputSequences, mis);

        Database database = new Database();
        mineSequentialPatterns.generateFrequentSequences(database);


    }


}
