package com.airlines.thomascook.scripts.tca;

import TestRail.APIClient;
import TestRail.APIException;
import com.airlines.thomascook.businesslogic.Reusables_TCA_UK;
import com.airlines.thomascook.objectrepository.OR_EssentialsComman;
import com.airlines.thomascook.objectrepository.OR_ExtrasComman;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

import static com.airlines.thomascook.utils.ExcelUtilsJxl.getTestData;
import static com.airlines.thomascook.utils.ReadFile.readFile;


/**
 * Created by E002465 on 06-06-2017.
 */
public class GeneralScenario extends Reusables_TCA_UK implements OR_HomePage,OR_FlightDetails,OR_EssentialsComman,OR_ExtrasComman {

    @Test(dataProvider = "testCases")
    public void scenario(String testCaseId,String scenarioName,String from,String to,String season,String seasonCriteria,String tripType,String pax, String essentials,String seatMapper,String extras,String salutation,String firstName,String surName,String addressLine1,String addressLine2,String postCode,String city,String country,String mobilePhone,String homePhone,String email,String a1Salutation,String a1FirstName,String a1SurName,String a1Dob,String a2Salutation,String a2FirstName,String a2SurName,String a2Dob,String a3Salutation,String a3FirstName,String a3SurName,String a3Dob,String a4Salutation,String a4FirstName,String a4SurName,String a4Dob,String a5Salutation,String a5FirstName,String a5SurName,String a5Dob,String c1Gender,String c1FirstName,String c1SurName,String c1Dob,String c2Gender,String c2FirstName,String c2SurName,String c2Dob,String c3Gender,String c3FirstName,String c3SurName,String c3Dob,String y1Gender,String y1FirstName,String y1SurName,String y1Dob,String y2Gender,String y2FirstName,String y2SurName,String y2Dob,String y3Gender,String y3FirstName,String y3SurName,String y3Dob,String i1Gender,String i1FirstName,String i1SurName,String i1Dob,String i2Gender,String i2FirstName,String i2SurName, String i2Dob,String paymentType,String cardType,String nameOnCard,String creditCardNum,String expMonth,String year,String cVV,String baggage,String a1SportsEqp,String y1SportsEqp,String c1SportsEqp,String outBoundClass,String inBoundClass,String pin) throws Throwable {
        tcIdAndScenario.put(testCaseId,scenarioName);
        tcId=testCaseId.trim();
        logger=extent.startTest(testCaseId+" : "+scenarioName);
        alertAccept(true);
        selectDepartureAirport(from);
        selectDestinationAirport(to);
        if(tripType.equals("One-way")){
            oneWayTrip(seasonCriteria);
        }
        else if(tripType.equals("Return")){
            roundTrip(seasonCriteria);
        }
        selectPassengers(pax);
        clickContinue(passContBtn);
        searchFlights();
        selectOutboundClass(outBoundClass);
        if(tripType.equals("Return")){
            selectInboundClass(inBoundClass);
        }
        clickContinue(continueBtn_Gen);
        flexibleFlying();
        if(!(essentials.equals(""))){
            waitForElementPresent(essentialsPagelabel);
            selectEssentials(essentials,seatMapper);
        }
        waitForElementPresent(essentialsPagelabel);
        waitForPageLoad(3000);
        clickContinue(continueBtn_Gen);
        popUpHandler();
        if(!(extras.equals(""))){
            waitForElementPresent(extrasPagelabel);
            selectExtras(extras);
        }
        //waitForPageLoad(10000);
        waitForElementPresent(extrasPagelabel);
        waitForPageLoad(3000);
        clickContinue(continueBtn_Gen);
        enterPassengerDetails(pax,a1Salutation, a1FirstName, a1SurName, a1Dob, a2Salutation, a2FirstName, a2SurName, a2Dob, a3Salutation, a3FirstName, a3SurName, a3Dob, a4Salutation, a4FirstName, a4SurName, a4Dob, a5Salutation, a5FirstName, a5SurName, a5Dob, c1Gender, c1FirstName, c1SurName, c1Dob, c2Gender, c2FirstName, c2SurName, c2Dob, c3Gender, c3FirstName, c3SurName, c3Dob, y1Gender, y1FirstName, y1SurName, y1Dob, y2Gender, y2FirstName, y2SurName, y2Dob, y3Gender, y3FirstName, y3SurName, y3Dob, i1Gender, i1FirstName, i1SurName, i1Dob, i2Gender, i2FirstName, i2SurName,  i2Dob);
        contactDetails(salutation,firstName,surName,addressLine1,postCode,city,country,mobilePhone,email);
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        if(jobName.equals("TCA_UK_STAGE")) {
            paymentDetails(paymentType, cardType, nameOnCard, creditCardNum, expMonth, year, cVV);
            if (cardType.equalsIgnoreCase("MasterCard Debit")) {
                payment3DSecure(pin);
            }
            verifyBookingDetails();
        }
    }


    @DataProvider
    public Object[][] testCases() throws Exception{

        Object[][] testObjArray = getTestData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData");

        return (testObjArray);
    }

}
