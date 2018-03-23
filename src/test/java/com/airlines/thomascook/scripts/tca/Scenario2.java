package com.airlines.thomascook.scripts.tca;


import com.airlines.thomascook.businesslogic.Reusables_TCA_UK;
import com.airlines.thomascook.objectrepository.OR_EssentialsBaggagePreference;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import static com.airlines.thomascook.utils.ExcelUtilsJxl.getExcelData;

/**
 * Created by E001227 on 09-06-2017.
 */
public class Scenario2 extends Reusables_TCA_UK implements OR_HomePage,OR_FlightDetails,OR_EssentialsBaggagePreference {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void scenario2() throws Throwable {
        logger=extent.startTest("Scenario2 - One ward Journey");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario2");
        alertAccept(true);
//        from(testData.get("From"));
//        to(testData.get("To"));
        //oneWayTrip(testData.get("Month"),testData.get("Day"));
        //oneWayTrip();
        selectPassengers("Adults",1);
        selectPassengers("Infants",1);
        clickContinue(passContBtn);
        searchFlights();
        selectTravelClass(selectEconomyclass);
        selectBaggage(addAdult,testData.get("Baggage"));
        clickContinue(continueBtn_Gen);
        popUpHandler();
        handleSpinner(continueBtn_Gen);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        passDetails(testData.get("A1Gender"),testData.get("A1Salutation"),testData.get("A1Firstname"),testData.get("A1Surname"),testData.get("A1DOB"),1);
        youngOrChildOrInfantDetails(testData.get("I1Gender"),testData.get("I1Firstname"),testData.get("I1Surname"),testData.get("I1DOB"),2);
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),"4111111111111111",testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBooking();
    }
}
