package com.project.msps;

import com.oracle.tools.packager.Log;
import com.project.helper.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class driverClass {
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
        } catch (IOException e) {
            Log.info("Error while reading the Sequences from the data");
            e.printStackTrace();
        }
        try {
            mis = inputReader.minimumSupportReader(dataFile);
        }
        catch (IOException e)
        {
            Log.info("Error while");
        }



    }
}
