package com.airlines.thomascook.scripts.tca;

import com.airlines.thomascook.businesslogic.Reusables_TCA_UK;
import com.airlines.thomascook.objectrepository.OR_FlightDetails;
import com.airlines.thomascook.objectrepository.OR_HomePage;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import static com.airlines.thomascook.utils.ExcelUtilsJxl.getExcelData;


/**
 * Created by E002465 on 06-06-2017.
 */
public class Scenario1 extends Reusables_TCA_UK implements OR_HomePage,OR_FlightDetails {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void scenario1() throws Throwable {
        logger=extent.startTest("Scenario1 - One ward Journey");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario1");
        alertAccept(true);
//        from(testData.get("From"));
//        to(testData.get("To"));
        //oneWayTrip(testData.get("Month"),testData.get("Day"));
        //oneWayTrip();
        selectPassengers("Adults",2);
        clickContinue(passContBtn);
        searchFlights();
        selectTravelClass(selectEconomyclass);
        selectSeat();
        clickReserveSeatsForEveryone();
        selectSeatPerPerson("5E","");
        selectSeatPerPerson("5F","");
        clickDoneAndContnue();
        waitForElementNotPresent(iconSippner);
        addPriorityPackage();
        passDetails(testData.get("A1Gender"),testData.get("A1Salutation"),testData.get("A1Firstname"),testData.get("A1Surname"),testData.get("A1DOB"),1);
        passDetails(testData.get("A2Gender"),testData.get("A2Salutation"),testData.get("A2Firstname"),testData.get("A2Surname"),testData.get("A2DOB"),2);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        clickContinue(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),testData.get("CreditCardNum"),testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBookingDetails();
    }

}
