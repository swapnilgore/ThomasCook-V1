package com.airlines.thomascook.utils;

import TestRail.APIClient;
import TestRail.APIException;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.*;

/**
 * Created by E002465 on 14-06-2017.
 */
public class UpdateTR {
    APIClient client = new APIClient("https://thomascook.testrail.net");
    public LinkedHashMap<String,Integer> tRMap=new LinkedHashMap<String,Integer>();
    String statusMapfilePath = "C:\\Users\\E002465\\Desktop\\ThomasCook\\Framework\\ThomasCook\\statusMap.properties";

    //jenkins envronment variables
    public String testRailTestRun=System.getenv("TestRun");
    public String stageBuildNumber=System.getenv("stageBuildNum");
    public String htmlFile="\\index_"+stageBuildNumber+".html";

    public String homePath=System.getenv("HOMEPATH");
    public String stageReportsSrc=homePath+"\\.jenkins\\workspace\\TCA_UK_STAGE\\Reports";
    public String preProdReportsSrc=homePath+"\\.jenkins\\workspace\\TCA_UK_PREPROD\\Reports";
    public String prodReportsSrc=homePath+"\\.jenkins\\workspace\\TCA_UK_PROD\\Reports";

    public String stageReportsDest=homePath+"\\.jenkins\\workspace\\TCA_UK_STAGE\\Reports_"+stageBuildNumber+".zip";
    public String preProdReportsDest=homePath+"\\.jenkins\\workspace\\TCA_UK_PREPROD\\Reports_"+stageBuildNumber+".zip";
    public String prodReportsDest=homePath+"\\.jenkins\\workspace\\TCA_UK_PROD\\Reports_"+stageBuildNumber+".zip";
    private String excelDestinationPath ="C:\\Users\\E002465\\Desktop\\ThomasCook\\Framework\\ThomasCook\\Reports\\ThomasCook_"+stageBuildNumber+".xls";



    @Test
    public  void updateTR() throws IOException, APIException, XPathExpressionException, ParserConfigurationException, SAXException {

        String tRTestRun=System.getenv("TestRun");
        client.setUser("harish.daggupati@cigniti.com");
        client.setPassword("Test_1536");
        BufferedReader br = new BufferedReader(new FileReader(statusMapfilePath));
        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            String[] temp = sCurrentLine.split(":");
            String tcId=temp[0];
            String trId=tcId.trim().substring(1,tcId.length());
            tRMap.put(trId, mapStatus(Integer.parseInt(temp[1])));
        }
        br.close();
        for(Map.Entry<String,Integer> entry:tRMap.entrySet() ){
            Map data = new HashMap();
            data.put("status_id", entry.getValue());
            data.put("custom_result_environment", 3);
            data.put("comment", "Test results were updated through automation");
            JSONObject r = (JSONObject) client.sendPost("add_result_for_case/"+testRailTestRun+"/" + entry.getKey(), data);
        }
        System.out.println("testRailTestRun===="+testRailTestRun);
        System.out.println("tRMap======="+tRMap);
        System.out.println("stageBuildNum======="+System.getenv("stageBuildNum"));
    }

    private int mapStatus(int statusCode){
        int status;
        switch (statusCode){
            case 1:
                status=1;
                break;
            case 2:
                status=5;
                break;
            default:
                status=5;
        }
        return status;
    }

    @Test
    public void sendEmail() throws Exception {
        sendReport("sudhakar.gunda589@gmail.com","sudhak@R1","sudhakar.gunda589@gmail.com","Thomas Cook - Selenium Report","Tester");
    }

    private void getZip(String src,String dest ) throws Exception{
        Zip.zipDir(src, dest);
    }


    private void sendReport(String from, String pass, String to, String subject, String body) {

        File f=null;
        Properties props = System.getProperties();

        String host = "smtp.gmail.com";

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", host);

        props.put("mail.smtp.user", from);

        props.put("mail.smtp.password", pass);

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);
        DataSource source1=null;
        DataSource source2=null;
        DataSource source3=null;
        DataSource source4=null;
        BodyPart file1 = new MimeBodyPart();
        BodyPart file2 = new MimeBodyPart();
        BodyPart file3 = new MimeBodyPart();
        BodyPart file4 = new MimeBodyPart();


        try {

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);

            message.setText(body);


            Multipart multipart = new MimeMultipart();



            String aggregrateResults = excelDestinationPath;
            source1 = new FileDataSource(aggregrateResults);
            file1.setDataHandler(new DataHandler(source1));
            file1.setFileName("aggregrateResults.xls");
            multipart.addBodyPart(file1);


            if(checkForFileExistanceToSendEmail(stageReportsSrc,stageReportsDest)){
                String stageZip = stageReportsDest;
                source2 = new FileDataSource(stageZip);
                file2.setDataHandler(new DataHandler(source2));
                file2.setFileName("stage.zip");
                multipart.addBodyPart(file2);
            }

            if(checkForFileExistanceToSendEmail(preProdReportsSrc,preProdReportsDest)) {
                String preProdZip = preProdReportsDest;
                source3 = new FileDataSource(preProdZip);
                file3.setDataHandler(new DataHandler(source3));
                file3.setFileName("preprod.zip");
                multipart.addBodyPart(file3);
            }
            if(checkForFileExistanceToSendEmail(prodReportsSrc,prodReportsDest)) {
                String prodZip = prodReportsDest;
                source4 = new FileDataSource(prodZip);
                file4.setDataHandler(new DataHandler(source4));
                file4.setFileName("prod.zip");
                multipart.addBodyPart(file4);

            }
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");

            transport.connect(host, from, pass);

            transport.sendMessage(message, message.getAllRecipients());

            transport.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    private String checkforLatestIndexFileAndAddToZip(String src,String dest) throws Exception {
        File f=new File(src+htmlFile);
        if(f.exists()){
            getZip(src,dest);
        }
        return dest;
    }

    private boolean checkForFileExistanceToSendEmail(String src,String dest) throws Exception {
        boolean flag=false;
        File f=new File(checkforLatestIndexFileAndAddToZip(src, dest));
        if (f.exists()){
            flag=true;
        }
        return flag;
    }

}
