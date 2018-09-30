package com.project;

import com.oracle.tools.packager.Log;
import com.project.helper.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class driverClass {

    static void printlist(List<String> list) {

        for (String s :
                list) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        InputReader inputReader = new InputReader();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the data file name");
        String dataFile = bufferedReader.readLine();
        System.out.println("Enter the params file name");
        String paramsFile = bufferedReader.readLine();
        List<String> inputSequences;
        HashMap<String, Float> mis;
        try {
            inputSequences = inputReader.sequenceReader(dataFile);
            System.out.println("sequences ");
            printlist(inputSequences);
        } catch (IOException e) {
            Log.info("Error while reading the Sequences from the data");
            e.printStackTrace();
        }
        try {
            mis = inputReader.minimumSupportReader(paramsFile);
            System.out.println("mis values");
            for (String key :
                    mis.keySet()) {
                System.out.println(mis.get(key));
            }


        } catch (IOException e) {
            Log.info("Error while");
        }


    }
}
