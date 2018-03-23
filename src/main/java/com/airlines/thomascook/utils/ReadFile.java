package com.airlines.thomascook.utils;

import org.testng.SkipException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by E002465 on 20-07-2017.
 */
public class ReadFile {

    static ArrayList<String> tc=new ArrayList<>();
    public static void readFile(String fileName,String tcId) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null) {
                tc.add(sCurrentLine);
            }
            if(!tc.contains(tcId)){
                throw new SkipException("test considered as pass in the previous run");
            }

        } catch (IOException e) {

            System.out.println("===========IO Exception");

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }
}
