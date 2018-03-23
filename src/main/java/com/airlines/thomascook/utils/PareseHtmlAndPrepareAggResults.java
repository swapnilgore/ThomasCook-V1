package com.airlines.thomascook.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by E002465 on 08-07-2017.
 */
public class PareseHtmlAndPrepareAggResults {

    org.jsoup.nodes.Document document;
    @Test
    public void aggregateResults() throws IOException {
        String excelTemplatePath = System.getProperty("user.dir")+"\\src\\main\\resources\\ThomasCook_Template.xls";
        String excelDestinationPath = System.getProperty("user.dir")+"\\Reports\\ThomasCook_"+System.getenv("stageBuildNum")+".xls";
        String latestHtmlFile="index_"+System.getenv("stageBuildNum")+".html";
        System.out.println("==========stageBuildNum from jenkins============="+latestHtmlFile);
        String stageHtml="C:\\Users\\E002465\\.jenkins\\workspace\\Condor_US\\Reports\\HtmlResults\\"+latestHtmlFile;
        String preProductionHtml="C:\\Users\\E002465\\.jenkins\\workspace\\PreProd\\Reports\\HtmlResults\\"+latestHtmlFile;
        String productionHtml="C:\\Users\\E002465\\.jenkins\\workspace\\Prod\\Reports\\HtmlResults\\"+latestHtmlFile;
        String excelFilePath=copyExcelTemplate(excelTemplatePath,excelDestinationPath);



        feedResultsToExcelFromStage(stageHtml,excelFilePath,"DataSource");
        feedResultsToExcelFromPreProduction(preProductionHtml,excelFilePath,"DataSource");
        feedResultsToExcelFromProduction(productionHtml,excelFilePath,"DataSource");
    }


    public LinkedHashMap<String,String> parseIndexHtmlAndGetScenariosAndStatus(String htmlFilePath) throws IOException, InterruptedException {
        LinkedHashMap<String,String> scenariosWithStatus=new LinkedHashMap<>();
        File f=new File(htmlFilePath);
        document = Jsoup.parse(f, "UTF-8");
        Elements scenario_names = document.select(".test-name");
        Elements scenario_status = document.select(".test-status.label");
        if(scenario_names.size()==scenario_status.size()) {
            for (int i = 0; i < scenario_names.size(); i++) {
                String scenario=scenario_names.get(i).text().trim();
                String scenarioStatus=scenario_status.get(i).text().trim();
                System.out.println(scenario+" : "+scenarioStatus);
                scenariosWithStatus.put(scenario,scenarioStatus.substring(0,1).toUpperCase()+scenarioStatus.substring(1));
            }
        }
        return scenariosWithStatus;
    }


    private void feedResultsToExcelFromStage(String htmlFilePath,String excelFilePath,String sheetName){
        File html=new File(htmlFilePath);
        if(html.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);

                Sheet sheet = workbook.getSheet(sheetName);
                int rowCount = 9;
                for (Map.Entry<String, String> entry : parseIndexHtmlAndGetScenariosAndStatus(htmlFilePath).entrySet()) {
                    System.setProperty(entry.getKey(),entry.getValue());
                    Row row = sheet.getRow(rowCount);
                    Cell scenarioCell = row.getCell(2);
                    Cell statusCell = row.getCell(6);
                    scenarioCell.setCellValue(entry.getKey());
                    statusCell.setCellValue(entry.getValue());
                    rowCount++;
                }
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

            } catch (IOException | EncryptedDocumentException | InvalidFormatException |InterruptedException ex) {
                ex.printStackTrace();
            }
        }else {
            System.out.println("Html file not found, given file path is"+htmlFilePath);
        }
    }
    private void feedResultsToExcelFromPreProduction(String htmlFilePath,String excelFilePath,String sheetName){
        File html=new File(htmlFilePath);
        if(html.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);

                Sheet sheet = workbook.getSheet(sheetName);
                int rowCount = 9;
                for (Map.Entry<String, String> entry : parseIndexHtmlAndGetScenariosAndStatus(htmlFilePath).entrySet()) {
                    System.setProperty(entry.getKey(),entry.getValue());
                    Row row = sheet.getRow(rowCount);
                    Cell scenarioCell = row.getCell(2);
                    Cell statusCell = row.getCell(7);
                    if(scenarioCell.getStringCellValue().equals(entry.getKey())){
                        statusCell.setCellValue(entry.getValue());
                    }
                    rowCount++;
                }
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

            } catch (IOException | EncryptedDocumentException | InvalidFormatException |InterruptedException ex) {
                ex.printStackTrace();
            }
        }else {
            System.out.println("Html file not found, given file path is"+htmlFilePath);
        }
    }
    private void feedResultsToExcelFromProduction(String htmlFilePath,String excelFilePath,String sheetName){

        File html=new File(htmlFilePath);
        if(html.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);

                Sheet sheet = workbook.getSheet(sheetName);
                int rowCount = 9;
                for (Map.Entry<String, String> entry : parseIndexHtmlAndGetScenariosAndStatus(htmlFilePath).entrySet()) {
                    System.setProperty(entry.getKey(),entry.getValue());
                    Row row = sheet.getRow(rowCount);
                    Cell scenarioCell = row.getCell(2);
                    Cell statusCell = row.getCell(8);
                    if(scenarioCell.getStringCellValue().equals(entry.getKey())){
                        statusCell.setCellValue(entry.getValue());
                    }
                    rowCount++;
                }
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

            } catch (IOException | EncryptedDocumentException | InvalidFormatException |InterruptedException ex) {
                ex.printStackTrace();
            }
        }else {
            System.out.println("Html file not found, given file path is"+htmlFilePath);
        }
    }
    private String copyExcelTemplate(String excelTemplate,String destination) throws IOException {
        File source=new File(excelTemplate);
        File dest=new File(destination);
        try {
            if(dest.exists()){
                dest.delete();
            }
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getCanonicalPath();
    }
}
