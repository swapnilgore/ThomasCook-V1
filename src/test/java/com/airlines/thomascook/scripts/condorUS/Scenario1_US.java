package com.airlines.thomascook.scripts.condorUS;

import com.airlines.thomascook.businesslogic.Reusables_SS;
import com.airlines.thomascook.objectrepository.OR_ExtrasComman;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static com.airlines.thomascook.utils.ExcelUtilsJxl.getExcelData;


/**
 * Created by E002465 on 06-06-2017.
 */
public class Scenario1_US extends Reusables_SS implements OR_HomePage,OR_FlightDetails,OR_ExtrasComman {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void scenario1_US() throws Throwable {
        logger=extent.startTest("Scenario1_US - Round Journey");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario1_US");
        alertAccept(true);
        from(testData.get("From"));
        to(testData.get("To"));
//        roundTrip(testData.get("Month"),testData.get("Day"),testData.get("Return_Month"),testData.get("Return_Day"));
        selectPassengers(testData.get("PAX"));
        clickContinue(passContBtn);
        searchFlights();
        selectOutboundClass(testData.get("OutBoundClass"));
        selectInboundClass(testData.get("InBoundClass"));
        clickContinue(continueBtn_Gen);
        flexibleFlying();
        if(!(testData.get("Essentials").equals(""))){
            selectEssentials(testData.get("Essentials"),testData.get("SeatMapper"));
        }
        clickContinue(continueBtn_Gen);
        popUpHandler();
        if(!(testData.get("Extras").equals(""))){
            selectExtras(testData.get("Extras"));
        }
        waitForPageLoad(10000);
        waitForElementPresent(extrasPagelabel);
        clickContinue(continueBtn_Gen);
        passDetails(testData.get("A1Gender"),testData.get("A1Salutation"),testData.get("A1Firstname"),testData.get("A1Surname"),testData.get("A1DOB"),1);
        passDetails(testData.get("A2Gender"),testData.get("A2Salutation"),testData.get("A2Firstname"),testData.get("A2Surname"),testData.get("A2DOB"),2);
        passDetails(testData.get("A3Gender"),testData.get("A3Salutation"),testData.get("A3Firstname"),testData.get("A3Surname"),testData.get("A3DOB"),3);
        youngOrChildOrInfantDetails(testData.get("C1Gender"),testData.get("C1Firstname"),testData.get("C1Surname"),testData.get("C1DOB"),4);
        youngOrChildOrInfantDetails(testData.get("I1Gender"),testData.get("I1Firstname"),testData.get("I1Surname"),testData.get("I1DOB"),5);
        youngOrChildOrInfantDetails(testData.get("I2Gender"),testData.get("I2Firstname"),testData.get("I2Surname"),testData.get("I2DOB"),6);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),testData.get("CreditCardNum"),testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBookingDetails();
    }

}
