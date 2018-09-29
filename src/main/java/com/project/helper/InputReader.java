package com.project.helper;

import com.oracle.tools.packager.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputReader {
    private static final String SDC_KEY = "SDC";
    BufferedReader fp;

    //
//    public InputReader(){
//        fp = new FileReader()
//    }
    public List<String> sequenceReader(String fileName) throws IOException {
        Log.info("Opening the data file " + fileName);

        try {
            fp = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            Log.info("File not found : " + fileName);
            e.printStackTrace();
        }
        String line;
        List<String> sequences = new ArrayList<String>();

        while ((line = fp.readLine())!=null){
            sequences.add(line);
        }
        Log.info("Successfully read the Sequence file");
        fp.close();
        return sequences;

    }
    public HashMap<String,Float> minimumSupportReader(String fileName) throws IOException {
        Log.info("Opening the params file " + fileName);

        try {
            fp = new BufferedReader(new FileReader(new File(fileName)))
        } catch (FileNotFoundException e) {
            Log.info("File not found :" + fileName);
            e.printStackTrace();
        }

        String line;
        String key;
        HashMap<String,Float> mis = new HashMap<String, Float>();
        while((line=fp.readLine())!=null)
        {
           String[] keyValue =  line.split("=");
           if(keyValue[0].equals(SDC_KEY))
           {
               mis.put(SDC_KEY,Float.parseFloat(keyValue[1]));
               continue;
           }
           key = keyValue[0].substring(keyValue[0].indexOf("("),keyValue[0].indexOf(")")+1); //[0] has the key
           mis.put(key,Float.parseFloat(keyValue[1])); // [1] has the value
        }
        return mis;
    }

}
