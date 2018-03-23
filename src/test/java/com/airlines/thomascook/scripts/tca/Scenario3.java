package com.airlines.thomascook.scripts.tca;

import com.airlines.thomascook.businesslogic.Reusables_TCA_UK;
import com.airlines.thomascook.objectrepository.OR_EssentialsBaggagePreference;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import java.util.LinkedHashMap;

import static com.airlines.thomascook.utils.ExcelUtilsJxl.getExcelData;

/**
 * Created by E002465 on 10-06-2017.
 */
public class Scenario3 extends Reusables_TCA_UK implements OR_HomePage,OR_FlightDetails,OR_EssentialsBaggagePreference {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @org.testng.annotations.Test
    public void scenario3() throws Throwable {
        logger=extent.startTest("Scenario3 - RoundTrip");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario3");
        alertAccept(true);
//        from(testData.get("From"));
//        to(testData.get("To"));
       // roundTrip(testData.get("Month"),testData.get("Day"),testData.get("Return_Month"),testData.get("Return_Day"));
        //roundTrip();
        selectPassengers("Adults",1);
        selectPassengers("YoungAdults",1);
        selectPassengers("Children",1);
        clickContinue(passContBtn);
        searchFlights();
        selectOutboundClass(testData.get("OutBoundClass"));
        selectInboundClass(testData.get("InBoundClass"));
        clickContinue(continueBtn_Gen);
        handleSpinner(continueBtn_Gen);
        popUpHandler();
        bookSportsEqpBtn(bookSportsEqpBtn);
        outboundSportsEquipment("Adult 1",testData.get("A1SportsEqp"));
        outboundSportsEquipment("Youth 1",testData.get("Y1SportsEqp"));
        outboundSportsEquipment("Child 1",testData.get("C1SportsEqp"));
        clickContinue(done);
        handleSpinner(continueBtn_Gen);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        passDetails(testData.get("A1Gender"),testData.get("A1Salutation"),testData.get("A1Firstname"),testData.get("A1Surname"),testData.get("A1DOB"),1);
        youngOrChildOrInfantDetails(testData.get("Y1Gender"),testData.get("Y1Firstname"),testData.get("Y1Surname"),testData.get("Y1DOB"),2);
        youngOrChildOrInfantDetails(testData.get("C1Gender"),testData.get("C1Firstname"),testData.get("C1Surname"),testData.get("C1DOB"),3);
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),"5300000000000006",testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        payment3DSecure(testData.get("Pin"));
        verifyBooking();
    }
}
