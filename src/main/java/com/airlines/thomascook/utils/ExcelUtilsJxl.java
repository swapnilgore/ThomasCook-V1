package com.airlines.thomascook.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExcelUtilsJxl {

    public static LinkedHashMap<String,String> getExcelData(String fileName, String sheetName,String testCaseID){
        LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
        ArrayList<String> key=new ArrayList<>();
        ArrayList<String> value=new ArrayList<>();
        try{
            FileInputStream fs=new FileInputStream(fileName);
            Workbook wb=Workbook.getWorkbook(fs);
            Sheet sh=wb.getSheet(sheetName);

            int totalNoOfCols=sh.getColumns();
            int totalNoOfRows=sh.getRows();


                for(int j=0;j<totalNoOfCols; j++){
                    key.add(sh.getCell(j,0).getContents());
            }
            for(int i=1;i<totalNoOfRows; i++){
                ArrayList<String> temp=new ArrayList<>();
                for(int j=0;j<totalNoOfCols; j++){
                    temp.add(sh.getCell(j,i).getContents());
                }
                if(!temp.get(0).equalsIgnoreCase(testCaseID)){
                    temp.clear();
                }else
                    value.addAll(temp);
            }
            if(key.size()==value.size()){
                for (int i = 1; i <key.size() ; i++) {
                    testData.put(key.get(i),value.get(i));
                }
            }

        }catch( IOException | BiffException e){
            e.printStackTrace();
        }
        return testData;
    }

    public static Object[][]  getTestData(String fileName, String sheetName){
        String[][] tabArray = null;
        try{
            FileInputStream fs=new FileInputStream(fileName);
            Workbook wb=Workbook.getWorkbook(fs);
            Sheet sh=wb.getSheet(sheetName);

            int totalNoOfCols=sh.getColumns();
            int totalNoOfRows=sh.getRows();
            tabArray = new String[totalNoOfRows-1][totalNoOfCols];
            for(int i=1;i<totalNoOfRows; i++) {
                ArrayList<String> temp = new ArrayList<String>();
                for (int j = 0; j < totalNoOfCols; j++) {
                    tabArray[i-1][j]=sh.getCell(j, i).getContents();
                }
            }

        }catch( IOException | BiffException e){
            e.printStackTrace();
        }
        return tabArray;
    }
}