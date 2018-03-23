package com.airlines.thomascook.accelerators;

import TestRail.APIClient;
import TestRail.APIException;
import com.airlines.thomascook.utils.MyListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;


import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;

/**
 *
 * @author HarishDaggupati
 *
 */

public class TestEngine {
    public static final Logger LOG = Logger.getLogger(TestEngine.class);
    public WebDriver WebDriver = null;
    public EventFiringWebDriver Driver=null;
    public String browser = null;
    public String baseUrl = null;
    public ExtentReports extent ;
    public ExtentTest logger;
    DesiredCapabilities capabilities;
    public String tcId;
    public String failedTestsPropertiesFilePath=System.getProperty("user.dir")+"\\failedTests.properties";
    APIClient client = new APIClient("https://thomascook.testrail.net");
    public LinkedHashMap<String,Integer> statusMap=new LinkedHashMap<String,Integer>();
    public LinkedHashMap<String,String> tcIdAndScenario=new LinkedHashMap<String,String>();
    public  LinkedHashMap<String,Integer> executionMap=new LinkedHashMap<String,Integer>();
    String statusMapFilePath = "C:\\Users\\E002465\\Desktop\\ThomasCook\\Framework\\ThomasCook\\statusMap.properties";
    String fXmlFile = System.getProperty("user.dir")+"\\target\\surefire-reports\\testng-results.xml";
    //Jenkins Environment variables
    public String testRailTestRun=System.getenv("TestRun");
    public String stageBuildNumber=System.getenv("stageBuildNum");
    public String buildNumber=System.getenv("BUILD_NUMBER");
    public String appUrl=System.getenv("baseUrl");
    public String jobName=System.getenv("JOB_NAME");
   /*public String stageBuildNumber="28";
    public String buildNumber="1";
    public String appUrl="https://tca:hqIVdMNvnsFk@www-stage.thomascookairlines.com/en/index2.jsp";
    public String jobName="TCA_UK_PREPROD";*/


    public String htmlFile="index_"+stageBuildNumber+".html";
    public String extentReportDirectoryPath = System.getProperty("user.dir")+"\\Reports\\"+htmlFile;
    public String ReportDirectoryPath = System.getProperty("user.dir")+"\\Reports";
    public String aggregateResultsExcelPath = System.getProperty("user.dir")+"\\Reports\\"+htmlFile;
    private String excelDestinationPath ="C:\\Users\\E002465\\Desktop\\ThomasCook\\Framework\\ThomasCook\\Reports\\ThomasCook_"+stageBuildNumber+".xls";
    private String excelTemplatePath = "C:\\Users\\E002465\\Desktop\\ThomasCook\\Framework\\ThomasCook\\src\\main\\resources\\ThomasCook_Template.xls";
    boolean deleteTRFile=false;


    @BeforeSuite(alwaysRun=true)
    public void beforeSuite() throws Throwable{
        FileUtils.cleanDirectory(new File(ReportDirectoryPath));
        extent = new ExtentReports(extentReportDirectoryPath, true);
        System.out.println("testRailTestRun========"+testRailTestRun);
        System.out.println("stageBuildNumber========"+stageBuildNumber);
        System.out.println("buildNumber========"+buildNumber);
        System.out.println("appUrl========"+appUrl);
        System.out.println("jobName========"+jobName);
    }

    @BeforeMethod(alwaysRun=true)
    @Parameters({"browser","baseUrl"})
    public void beforeMethod(String browser,String baseUrl) throws IOException, InterruptedException
    {

		/*get configuration */
        this.browser = browser;
        this.baseUrl = baseUrl;
        this.setWebDriverForLocal(browser);
        this.Driver = new EventFiringWebDriver(this.WebDriver);
        MyListener myListener = new MyListener();
        this.Driver.register(myListener);
        Driver.get(appUrl);
        Driver.get(appUrl);
        Driver.manage().deleteAllCookies();
        Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        Driver.manage().window().maximize();
    }

    @Parameters({"browser"})
    @AfterMethod(alwaysRun=true)
    public void afterMethod(ITestResult result,String browser) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE){
            statusMap.put(tcId,result.getStatus());//Prepares test cases and its status to upload to testrail
            logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(capture(Driver, "screenShot")));
            logFile(tcId,result.getThrowable().toString());
        }
        else if(result.getStatus()==ITestResult.SKIP){
            statusMap.put(tcId,result.getStatus());//Prepares test cases and its status to upload to testrail
            logger.log(LogStatus.SKIP,"C"+tcId+" : This Scenario must be passed in the previous environment");
        }
        else if (result.getStatus()==ITestResult.SUCCESS){
            statusMap.put(tcId,result.getStatus());//Prepares test cases and its status to upload to testrail
        }

        extent.endTest(logger);
        extent.flush();
//        extent.close();
        if (browser.equalsIgnoreCase("firefox")) {
            Driver.quit();
        }
        else{
            Driver.quit();
        }
    }
    @AfterSuite
    public void afterSuite() throws IOException, ParserConfigurationException, APIException, SAXException, XPathExpressionException {
        //updateTestRail();
        System.out.println("status map============");
        System.out.println(statusMap);
        feedResultsToExcelFromStage(tcIdAndScenario,excelDestinationPath,"DataSource");
        feedResultsToExcelFromPreProduction(excelDestinationPath,"DataSource");
        feedResultsToExcelFromProduction(excelDestinationPath,"DataSource");
        updateStatusMapFile(statusMapFilePath);

    }

    private void setWebDriverForLocal(String browser) throws IOException, InterruptedException
    {
        switch(browser)
        {
            case "firefox":
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                this.WebDriver = new FirefoxDriver(capabilities);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                this.WebDriver = new ChromeDriver(capabilities);
                break;

        }

    }

    protected  String capture(String screenShotName) throws IOException
    {
        return capture(this.Driver,screenShotName);
    }

    private   String capture(WebDriver driver,String screenShotName) throws IOException
    {
        String ss=String.format(getCurrentTimeStamp(),screenShotName)+".png";
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"/Reports/"+ss;
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
//        return "../ErrorScreenshots/"+screenShotName+".png";
        return ss;
    }

    private void loadConfig() {

        Properties prop = new Properties();
        InputStream input = null;
        try
        {
            input = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
            prop.load(input);
            for (final Map.Entry<Object, Object> entry : prop.entrySet()) {
                System.setProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("'%s'MMddHHmmssSSS").format(new Date());
    }

    private  void logFile(String tcId,String consoleOp) {
        try
        {
            String filename= System.getProperty("user.dir")+"\\Reports\\"+"logFile"+stageBuildNumber+".txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(tcId+" : ");//appends the string to the file
            fw.write(consoleOp+"\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }

    }

    private void feedResultsToExcelFromStage(LinkedHashMap<String,String> testIdAndScenario,String excelFilePath,String sheetName) throws IOException {
        if(jobName.equals("TCA_UK_STAGE")) {
            copyExcelTemplate(excelTemplatePath,excelDestinationPath);
            File f=new File(statusMapFilePath);
            if(f.exists()){
                deleteTRFile=f.delete();
                f.createNewFile();
            }
            System.out.println("executing TCA_UK_STAGE");
            try {
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);

                Sheet sheet = workbook.getSheet(sheetName);
                int rowCount1 = 9;
                //To fill Excel with testID and Scenario name
                for (Map.Entry<String, String> entry : testIdAndScenario.entrySet()) {
                    Row row = sheet.getRow(rowCount1);
                    Cell tetCaseIdCell = row.getCell(1);
                    Cell scenarioCell = row.getCell(2);
                    if((tetCaseIdCell.getStringCellValue().equals("NA"))&&(scenarioCell.getStringCellValue().equals("NA"))){
                        tetCaseIdCell.setCellValue(entry.getKey());
                        scenarioCell.setCellValue(entry.getValue());
                    }
                    rowCount1++;
                }
                int rowCount2 = 9;
                //to update status of the executed scenarios
                for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
                    Row row = sheet.getRow(rowCount2);
                    Cell tetCaseIdCell = row.getCell(1);
                    Cell statusCell = row.getCell(3);//Stage status cell for TCA-UK
                    if(tetCaseIdCell.getStringCellValue().equals(entry.getKey())){
                        statusCell.setCellValue(mapStatus(entry.getValue()));
                    }
                    rowCount2++;
                }
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

            } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
                ex.printStackTrace();
            }
        }

    }
    private void feedResultsToExcelFromPreProduction(String excelFilePath,String sheetName){
        if(jobName.equals("TCA_UK_PREPROD")) {
            System.out.println("executing TCA_UK_PREPROD");
            try {
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);

                Sheet sheet = workbook.getSheet(sheetName);

                int totalRows=sheet.getPhysicalNumberOfRows();
                for (int i = 9; i <totalRows ; i++) {
                    //to update status of the executed scenarios
                    for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
                        Row row = sheet.getRow(i);
                        Cell tetCaseIdCell = row.getCell(1);
                        Cell statusCell = row.getCell(4);//Preprod status cell for TCA-UK
                        if(tetCaseIdCell.getStringCellValue().equals(entry.getKey())){
                            statusCell.setCellValue(mapStatus(entry.getValue()));
                        }
                    }
                }

                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

            } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void feedResultsToExcelFromProduction(String excelFilePath,String sheetName){

        if(jobName.equals("TCA_UK_PROD")) {
            System.out.println("executing TCA_UK_PROD");
            try {
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = WorkbookFactory.create(inputStream);

                Sheet sheet = workbook.getSheet(sheetName);
                int totalRows=sheet.getPhysicalNumberOfRows();
                for (int i = 9; i <totalRows ; i++) {
                    //to update status of the executed scenarios
                    for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
                        Row row = sheet.getRow(i);
                        Cell tetCaseIdCell = row.getCell(1);
                        Cell statusCell = row.getCell(5);//Prod status cell for TCA-UK
                        if(tetCaseIdCell.getStringCellValue().equals(entry.getKey())){
                            statusCell.setCellValue(mapStatus(entry.getValue()));
                        }
                    }
                }
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

            } catch (IOException | EncryptedDocumentException | InvalidFormatException ex) {
                ex.printStackTrace();
            }
        }
    }
    private String mapStatus(int statusCode){
        String status=null;
        switch (statusCode){
            case 1:
                status="PASS";
                break;
            case 2:
                status="FAIL";
                break;
            case 3:
                status="SKIP";
                break;
        }
        return status;
    }

    private String copyExcelTemplate(String excelTemplate,String destination) throws IOException {
        File source=new File(excelTemplate);
        File dest=new File(destination);
        try {
            /*if(dest.exists()){
                dest.delete();
            }*/
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest.getCanonicalPath();
    }



    public  void updateStatusMapFile(String file) {
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            String sCurrentLine;
            if (br1.readLine() != null) {
                br1.close();
                while ((sCurrentLine = br2.readLine()) != null) {
                    String[] temp = sCurrentLine.split(":");
                    executionMap.put(temp[0], Integer.parseInt(temp[1]));
                }
                br2.close();
                executionMap.putAll(statusMap);
                FileWriter fw = new FileWriter(file);
                for (Map.Entry<String, Integer> entry : executionMap.entrySet()) {
                    fw.write(entry.getKey() + ":" + entry.getValue());
                    fw.write("\n");
                }
                fw.close();
            } else {

                FileWriter fw = new FileWriter(file, true); //the true will append the new data
                for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
                    fw.write(entry.getKey() + ":" + entry.getValue());
                    fw.write("\n");
                }
                fw.close();
            }

        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

}
