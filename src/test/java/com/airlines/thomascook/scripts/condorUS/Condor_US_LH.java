package com.airlines.thomascook.scripts.condorUS;

import com.airlines.thomascook.businesslogic.Reusables_SS;
import com.airlines.thomascook.businesslogic.Reusables_US;
import com.airlines.thomascook.objectrepository.OR_ExtrasComman;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static com.airlines.thomascook.utils.ExcelUtilsJxl.getExcelData;


/**
 * Created by E002465 on 06-06-2017.
 */
public class Condor_US_LH extends Reusables_SS implements OR_HomePage,OR_FlightDetails,OR_ExtrasComman {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void FRA() throws Throwable {
        logger=extent.startTest("Condor_US_LH");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","FRA");
        alertAccept(true);
        from(testData.get("From"));
        to(testData.get("To"));
        oneWayTrip();
        selectPassengers(testData.get("PAX"));
        clickContinue(passContBtn);
        searchFlights();
        selectOutboundClass(testData.get("OutBoundClass"));
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
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),testData.get("CreditCardNum"),testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBookingDetails();
    }

}
