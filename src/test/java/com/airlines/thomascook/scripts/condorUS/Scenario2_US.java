package com.airlines.thomascook.scripts.condorUS;

import com.airlines.thomascook.businesslogic.Reusables_SS;
import com.airlines.thomascook.objectrepository.OR_ExtrasComman;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static com.airlines.thomascook.utils.ExcelUtilsJxl.getExcelData;


/**
 * Created by E002465 on 06-06-2017.
 */
public class Scenario2_US extends Reusables_SS implements OR_HomePage,OR_FlightDetails,OR_ExtrasComman {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void scenario2_US() throws Throwable {
        logger=extent.startTest("Scenario1 - One ward Journey");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario2_US");
        alertAccept(true);
        logger.log(LogStatus.FAIL,"pass");
        from(testData.get("From"));
        to(testData.get("To"));
//        roundTrip(testData.get("Month"),testData.get("Day"),testData.get("Return_Month"),testData.get("Return_Day"));
        selectPassengers(testData.get("PAX"));
        clickContinue(passContBtn);
        searchFlights();
        selectFlight("Condor DE138",testData.get("OutBoundClass"));
        selectFlight("Condor DE139",testData.get("InBoundClass"));
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
        passDetails(testData.get("A4Gender"),testData.get("A4Salutation"),testData.get("A4Firstname"),testData.get("A4Surname"),testData.get("A4DOB"),4);
        youngOrChildOrInfantDetails(testData.get("C1Gender"),testData.get("C1Firstname"),testData.get("C1Surname"),testData.get("C1DOB"),5);
        youngOrChildOrInfantDetails(testData.get("C2Gender"),testData.get("C2Firstname"),testData.get("C2Surname"),testData.get("C2DOB"),6);
        youngOrChildOrInfantDetails(testData.get("I1Gender"),testData.get("I1Firstname"),testData.get("I1Surname"),testData.get("I1DOB"),7);
        youngOrChildOrInfantDetails(testData.get("I2Gender"),testData.get("I2Firstname"),testData.get("I2Surname"),testData.get("I2DOB"),8);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),testData.get("CreditCardNum"),testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBookingDetails();
    }

}
