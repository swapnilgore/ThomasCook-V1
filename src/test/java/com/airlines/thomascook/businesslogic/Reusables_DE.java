package com.airlines.thomascook.businesslogic;

import com.airlines.thomascook.accelerators.ActionEngine;
import com.relevantcodes.extentreports.LogStatus;
import com.airlines.thomascook.objectrepository.*;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/**
 * Created by E002465 on 06-06-2017.
 */
public class Reusables_DE extends ActionEngine implements OR_HomePage,OR_PassengerDetails,OR_ContactDetails,OR_PaymentDetails,OR_FlightDetails,OR_EssentialsBaggagePreference,OR_ExtrasSportsEqpAndCargo,OR_BookingConfPage,OR_EssentialsSeatPreference,OR_AddPriorityPackage,OR_EssentialsMealsPreference,OR_EssentialsBundle,OR_FlexibleFlying,OR_TravelInsurance,OR_EssentialsComman,OR_ExtrasComman {

    int totalPassengers = 0;
    List<WebElement> passCount = null;
    ArrayList<String> equipments = new ArrayList<String>();
    ArrayList<String> providedPassengerdetails = new ArrayList<String>();
    int baggageMethodCount = 0;
    int mealsMethodCount = 0;

    /**
     * Selects Origin/From country
     *
     * @param departure :	Locator of departure field
     */
    public void from(String departure) throws Throwable {

        try {
            waitForElementPresent(depMenu, 10);
            click(depMenu);
        } catch (TimeoutException | NoSuchElementException n) {
            // Driver.get(System.getProperty("baseUrl2"));
            Driver.navigate().refresh();
            System.out.println("=======refresh========");
            waitForElementPresent(depMenu);
            click(depMenu);
        }
        selectDepartureAirport(departure);

    }

    public void selectDepartureAirport(String departure) throws Throwable {
        if (waitForElementPresentE(deparAirPorttHeader)) {
            //if ((getVisibleText(deparAirPorttHeader).contains("Your departure airport"))) {
            if ((getVisibleText(deparAirPorttHeader).contains("Abflughafen wählen"))) {
                waitForElementPresent(depTextBox, 60);
                type(depTextBox, departure);
                waitForElementPresent(depDropDwn);
                for (WebElement ele : getAllElements(depDropDwn)) {
                    if (ele.getText().contains(departure)) {
                        ele.click();
                    }
                }
            }
            logger.log(LogStatus.PASS, "Entered departure city : " + departure);
        } else {
            logger.log(LogStatus.FAIL, "Unable to enter departure city", logger.addScreenCapture(capture("DepartureError")));
        }
    }

    /**
     * Selects Destination/To country
     *
     * @param destination: locator of destination field
     */
    public void to(String destination) throws Throwable {
        if (waitForElementPresentE(arrvlTextBox, 60)) {
            type(arrvlTextBox, destination);
            waitForElementPresent(arrvlDropDwn);
            for (WebElement ele : getAllElements(arrvlDropDwn)) {
                if (ele.getText().contains(destination)) {
                    ele.click();
                }
            }
            logger.log(LogStatus.PASS, "Entered destination city : " + destination);
        }
        else
        {
            logger.log(LogStatus.FAIL, "Unable to enter destination city", logger.addScreenCapture(capture("DestinationError")));
        }
    }

    /**
     * Enters Details of the passenger
     * @param gender     	:  gender of the passenger
     * @param salutation     	:  Mr/Ms
     * @param firstName     	:  firstName of the passenger
     * @param surName     	:  surName of the passenger
     * @param dOB	     	:  Date of Birth of the passenger
     * @param passNum     	:  Passenger number
     */

    public void passDetails(String gender,String salutation,String firstName,String surName,String dOB,int passNum) throws Throwable {
        if (waitForElementPresentE(passengerDetailsSection,60)) {
            providedPassengerdetails.add(firstName + " " + surName);
            //String d_MaleRadioButton = String.format(d_MaleRadioBtn, passNum);
            String d_SalutationRadioButton_MR = String.format(d_SalutationRadioBtn_MR, passNum);
            String d_FirstNameTextBox = String.format(d_FirstNameTxtBox, passNum);
            //String d_MiddleNameTextBox=String.format(d_MiddleNameTxtBox,num);
            String d_SurNameTextBox = String.format(d_SurNameTxtBox, passNum);
            String d_DOBTextBox = String.format(d_DOBTxtBox, passNum);
            //click(d_MaleRadioButton);
            click(d_SalutationRadioButton_MR);
            type(d_FirstNameTextBox, firstName);
            type(d_SurNameTextBox, surName);
            type(d_DOBTextBox, dOB);
            for (WebElement ele:getAllElements(continueBtn_pass)) {
                if(ele.isDisplayed()){
                    waitForPageLoad(2000);
                    click(ele);
                    break;
                }
            }
            logger.log(LogStatus.PASS, "Entered adult " + passNum + " details");
        }else {
            logger.log(LogStatus.FAIL, "Unable to enter adult passenger " + passNum + " details",logger.addScreenCapture(capture("AdultPassengerInfoError")));
        }
    }
    /**
     * Enters Details of Young Child/Child/Infant
     * @param gender     	:  gender of the Young Child/Child/Infant
     * @param firstName     	:  firstName of the Young Child/Child/Infant
     * @param surName     	:  surName of the Young Child/Child/Infant
     * @param dOB	     	:  Date of Birth of the Young Child/Child/Infant
     * @param passNum     	:  Passenger number
     */
    public void youngOrChildOrInfantDetails(String gender,String firstName,String surName,String dOB,int passNum) throws Throwable {
        if (Driver.getCurrentUrl().contains("flight/passenger")) {
            String d_MaleRadioButton=String.format(d_MaleRadioBtn,passNum);
            String d_FirstNameTextBox=String.format(d_FirstNameTxtBox,passNum);
            //String d_MiddleNameTextBox=String.format(d_MiddleNameTxtBox,num);
            String d_SurNameTextBox=String.format(d_SurNameTxtBox,passNum);
            String d_DOBTextBox=String.format(d_DOBTxtBox,passNum);
            click(d_MaleRadioButton);
            type(d_FirstNameTextBox,firstName);
            type(d_SurNameTextBox,surName);
            type(d_DOBTextBox,dOB);
            for (WebElement ele:getAllElements(continueBtn_pass)) {
                if(ele.isDisplayed()){
                    click(ele);
                    break;
                }
            }
            logger.log(LogStatus.PASS,"Entered passenger "+passNum+" details");
        }else {
            logger.log(LogStatus.FAIL, "Unable to enter passenger " + passNum + " details",logger.addScreenCapture(capture("PassengerInfoError")));
        }
    }

    /**
     * Enters Address Details
     * @param salutation     	:  Mr/Ms
     * @param firstName     	:  firstName of the Passenger 1
     * @param surName     	:  surName of the Passenger 1
     * @param address1	     	:  Street Name of Passenger
     * @param postCode	     	:  postal code of the address
     * @param city	     	:  city
     * @param country	     	:  country
     * @param phoneNum	     	:  phoneNum
     * @param email	     	:  email id of Passenger 1
     */
    public void contactDetails(String salutation,String firstName,String surName,String address1,String postCode,String city,String country,String phoneNum,String email) throws Throwable {

        if (waitForElementPresentE(minContactRdioBtn)) {
            String SalutationRadioButton=String.format(salutationRadioBtn,salutation.toUpperCase());
            waitForElementToBeClickable(minContactRdioBtn);
            click(minContactRdioBtn);
            //waitForElementToBeClickable(SalutationRadioButton);
            click(SalutationRadioButton);
            type(firstNameTxtBox,firstName);
            type(surNameTxtBox,surName);
            type(address1TxtBox,address1);
            type(postTxtBox,postCode);
            type(cityTxtBox,city);
            type(countryTxtBox,country,true,false);
            List<WebElement> listOfCountries=getAllElements(countryDropDown);
            for(WebElement we:listOfCountries){
                //Adding java script scroll to view might require
                if(we.getText().equalsIgnoreCase(country)){
                    click(we);
                }
            }
            type(phnNumTxtBox,phoneNum);
            type(emailTxtBox,email);
            //check(promoOfferDecChkBox);
            logger.log(LogStatus.PASS,"Entered contact details");
        }else {
            logger.log(LogStatus.FAIL, "Unable to enter Contact details ",logger.addScreenCapture(capture("ContactInfoError")));
        }
    }

    /**
     * Selects payment details Credit card/Debit Card
     * @param paymentType Type     	:  Credit card/Debit Card
     * @param cardType     	:  MasterCard/Visa
     * @param nameOnCard     	:  Name of the cardholder as printed on card
     * @param cardNumber     	:  cardNumber
     * @param month	     	:  month of card expiry
     * @param year	     	:  year of card expiry
     * @param 	cVV     	:  cVV on card
     */
    public void paymentDetails(String paymentType,String cardType,String nameOnCard,String cardNumber,String month,String year,String cVV) throws Throwable {
        if (waitForElementPresentE(paymentSection)) {
            String cardTypeButton = String.format(cardTypeBtn, cardType);
            if (paymentType.equalsIgnoreCase("Kreditkarte")) {
                waitForElementToBeClickable(creditCardRadioBtn);
                click(creditCardRadioBtn);
                waitForElementToBeClickable(cardTypeButton);
                click(cardTypeButton);
                enterCardDetails(paymentType, cardType, nameOnCard, cardNumber, month, year, cVV);
                logger.log(LogStatus.PASS,"Paid through "+paymentType+" with card type "+cardType);
            } else if (paymentType.equalsIgnoreCase("Debitkarte")) {
                waitForElementToBeClickable(debitCardRadioBtn);
                click(debitCardRadioBtn);
                waitForElementToBeClickable(cardTypeButton);
                click(cardTypeButton);
                enterCardDetails(paymentType, cardType, nameOnCard, cardNumber, month, year, cVV);
                logger.log(LogStatus.PASS,"Paid through "+paymentType+" with card type "+cardType);
            }
        }
        else {
            logger.log(LogStatus.FAIL, "Unable to enter Payment details ",logger.addScreenCapture(capture("PaymentError")));
        }
    }

    /**
     * Enters card details for payment
     * @param cardType     	:  MasterCard/Visa
     * @param nameOnCard     	:  Name of the cardholder as printed on card
     * @param cardNumber     	:  cardNumber
     * @param month	     	:  month of card expiry
     * @param year	     	:  year of card expiry
     * @param cVV	     	:  country
     */

    public void enterCardDetails(String paymentType,String cardType,String nameOnCard,String cardNumber,String month,String year,String cVV) throws Throwable {
        String cardTypeButton=String.format(cardTypeBtn,cardType);
        String expMonth=String.format(monthLink,month);
        String expYear=String.format(yearLink,year);
        waitForElementToBeClickable(acceptTCChkBox);
        click(acceptTCChkBox);
        waitForElementPresent(cardHolderNameTxtBox);
        scrollElementIntoView(cardHolderNameTxtBox);
        type(cardHolderNameTxtBox,nameOnCard);
        waitForElementPresent(iframe,30);
        Driver.switchTo().frame(getElement(iframe));
        waitForElementPresent(cardNumberTxtBox,60);
        scrollElementIntoView(cardNumberTxtBox);
        type(cardNumberTxtBox,cardNumber);
        click(monthDropDwnBtn_DE);
        waitForElementToBeClickable(expMonth);
        click(expMonth);
        waitForElementToBeClickable(yearDropDwnBtn_DE);
        click(yearDropDwnBtn_DE);
        waitForElementToBeClickable(expYear);
        click(expYear);
        type(cVVTxtBox,cVV);
        Driver.switchTo().parentFrame();
        click(payByBtn);
    }
    public void roundTrip() throws Throwable {

        if(waitForElementPresentE(roundTrip)){
            if (getVisibleText(calHeader).contains("Abflugtag wählen")) {
                datesSelection();
            }

            if(getVisibleText(calHeader).contains("Rückflugtag wählen")) {
                datesSelection();
            }
        }else {
            logger.log(LogStatus.FAIL, "Unable to select round trip ",logger.addScreenCapture(capture("RoundTripError")));
        }
    }

    public void oneWayTrip( ) throws Throwable {
        if (waitForElementPresentE(calHeader)) {
            if (getVisibleText(calHeader).contains("Abflugtag wählen")) {
                waitForElementToBeClickable(oneWayTripBtn);
                click(oneWayTripBtn);
                datesSelection();
            }

        }else {
            logger.log(LogStatus.FAIL, "Unable to select one ward journey ",logger.addScreenCapture(capture("OneWardjourneyError")));
        }
    }

    /**
     * Logic for Journey date selection
     * @param month     	:  Month of Journey
     * @param date	     	:  Date of Journey
     */
    public void selectMonthandDayOfJourney(String month,String date) throws Throwable {
        String currentMonth = getVisibleText(departuremonthText);
        String journeyDate=String.format(selectDayPart1,date)+String.format(selectDayPart2_DE,month);
        System.out.println("The current month is :" + currentMonth);
        while (!currentMonth.contains(month)) {
            waitForElementPresent(calNextBtn_DE);
            click(calNextBtn_DE);
            currentMonth = getVisibleText(departuremonthText);
            System.out.println("The current month is :" + currentMonth);
        }
        waitForElementToBeClickable(journeyDate);
        try {
            waitForPageLoad(3000);
            click(journeyDate);
        }catch (NoSuchElementException e){
            logger.log(LogStatus.FAIL,"No flights are available on opted date");
            throw e;
        }
    }

    /**
     * Selects  adults and number of passengers
     * @param reqpass     	:	Number of passenger
     */

    public void selectAdults(int reqpass) throws Throwable {
        int Passengers;
        waitForElementPresent(defaultAdults, 60);
        String defaultPassg = getVisibleText(defaultAdults);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default Adults passengers: " + defaultPass);
        if (reqpass >= defaultPass) {
            Passengers = reqpass - defaultPass;
            for (int i = 0; i < Passengers; i++) {
                click(adultPlusBtn);
            }
        } else if (reqpass < defaultPass) {
            Passengers = defaultPass - reqpass;
            for (int i = 0; i < Passengers; i++) {
                click(adultMinusBtn);
            }
        }
        logger.log(LogStatus.PASS,"Selected "+reqpass+" Adults");

    }
    /**
     * Selects  Young Adults and number of passengers
     * @param reqpass     	:	Number of passenger
     */
    public void SelectYoungAdults(int reqpass) throws Throwable {
        int pass;
        waitForElementPresent(defaultYoungAdults, 60);
        String defaultPassg = getVisibleText(defaultYoungAdults);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default YoungAdults passengers: " + defaultPass);
        waitForPageLoad(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                click(youngPlusBtn);
            }
        }
        logger.log(LogStatus.PASS,"Selected "+reqpass+" YoungAdults");

    }
    /**
     * Selects  Children and number of passengers
     * @param reqpass     	:	Number of passenger
     */
    public void SelectChildren(int reqpass) throws Throwable {
        int pass;
        waitForElementPresent(defaultChildren, 60);
        String defaultPassg = getVisibleText(defaultChildren);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default Children passengers: " + defaultPass);
        waitForPageLoad(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                click(childPlusBtn);
            }
        }
        logger.log(LogStatus.PASS,"Selected "+reqpass+" Children");
    }
    /**
     * Selects  infants and number of passengers
     * @param reqpass     	:	Number of passenger
     */
    public void SelectInfants(int reqpass) throws Throwable {
        int pass;
        waitForElementPresent(defaultInfants, 60);
        String defaultPassg = getVisibleText(defaultInfants);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default Infants passengers: " + defaultPass);
        waitForPageLoad(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                click(infantPlusBtn);
            }
        }
        logger.log(LogStatus.PASS,"Selected "+reqpass+" Infants");
    }
    /**
     * Selects  number and type of passengers
     * @param PassengerType     :	Locator of passenger type
     * @param reqpass     	:	Number of passenger
     */
    public void selectPassengers(String PassengerType, int reqpass ) throws Throwable {
        if(waitForElementPresentE(passengerSelectionHeadre)){
            totalPassengers=totalPassengers+reqpass;
            switch (PassengerType) {
                case "Adults":
                    selectAdults(reqpass);
                    break;
                case "YoungAdults":
                    SelectYoungAdults(reqpass);
                    break;
                case "Children":
                    SelectChildren(reqpass);
                    break;
                case "Infants":
                    SelectInfants(reqpass);
                    break;

                default:
                    SelectYoungAdults(reqpass);
            }
        }else {
            logger.log(LogStatus.FAIL, "Unable to select Passengers ",logger.addScreenCapture(capture("PassengerSelectionError")));
        }

    }
    /**
     * Selects  seat reservation for all passengers
     */
    public void clickReserveSeatsForEveryone() throws Throwable {
        if(waitForElementPresentE(reserveSeatsForEveryone_DE)){
            int count=1;
            waitForElementToBeClickable(reserveSeatsForEveryone_DE);
            passCount=getAllElements(totalNoOfPassengers);
            click(reserveSeatsForEveryone_DE);
            //commented because we enter seat number for each person for that have written a method "selectSeatPerPerson"
            /*for (WebElement ele:getAllElements(availableSeats)) {
                if(count>passCount.size()){
                    break;
                }
                scrollElementIntoView(ele);
                click(ele);
                count++;
            }
            waitForElementToBeClickable(Done,30);
            click(Done);
            waitForPageLoad(3000);
            waitForElementToBeClickable(continueBtn_Gen);
            click(continueBtn_Gen);
            logger.log(LogStatus.PASS,"reserved seats for every one "+passCount.size());
            */
        }else {
            logger.log(LogStatus.FAIL, "Unable to click reserve seats for every one ",logger.addScreenCapture(capture("SeatsForEvryOneError")));
        }
    }

    /**
     * Selects SportsEquipment on inbound flight
     * @param locator     :	Locator of selectbaggage field   
     * @param baggage     :	baggage  
     */
    public void selectBaggage(String locator, String baggage) throws Throwable {
        if(waitForElementPresentE(bookBaggage,30)){
//            waitForElementToBeClickable(bookBaggage);
            click(bookBaggage);
            waitForElementToBeClickable(locator);
            click(locator);
            String baggaeType=String.format(selectBaggage_DE,baggage);
            waitForElementToBeClickable(baggaeType);
            click(baggaeType);
            clickDone_DE();
            waitForPageLoad(4000);
            baggageMethodCount++;
            logger.log(LogStatus.PASS,"Selected baggage "+baggage);
        }
        else {
            logger.log(LogStatus.FAIL, "Unable to select baggage "+baggage,logger.addScreenCapture(capture("BaggageSelectionError")));
        }
    }
    /**
     * Selects  class of outbound flight
     * @param selectOutboundClass     :	Locator of outbound flight class
     */
    public void selectOutboundClass(String selectOutboundClass) throws Throwable {
        String outBoundClass=String.format(outBoundCls_DE,selectOutboundClass);
        if(waitForElementPresentE(outBoundClass)){
            waitForElementToBeClickable(outBoundClass);
            click(outBoundClass);
            logger.log(LogStatus.PASS,"Selected outbound class "+selectOutboundClass);
        }
        else {
            logger.log(LogStatus.FAIL, "Unable to select outbound class "+selectOutboundClass,logger.addScreenCapture(capture("OutBoundClsSelectinError")));
        }
    }
    /**
     * Selects  class of inbound flight
     * @param selectInboundClass     :	Locator of inbound flight class
     */
    public void selectInboundClass(String selectInboundClass) throws Throwable {
        String inBoundClass=String.format(inBoundCls_DE,selectInboundClass);
        if(waitForElementPresentE(inBoundClass,60)){
            waitForElementToBeClickable(inBoundClass);
            click(inBoundClass);
            logger.log(LogStatus.PASS,"Selected inbound class "+selectInboundClass);
        }
        else {
            logger.log(LogStatus.FAIL, "Unable to select inbound class "+selectInboundClass,logger.addScreenCapture(capture("InBoundClsSelectinError")));
        }
    }

    /**
     * Selects SportsEquipment on outbound flight
     * @param passenger     :	Locator of passenger field   
     * @param equipment     :	Locator of equipment field   
     */
    //has been modified for the demo
    public void outboundSportsEquipment(String passenger,String equipment) throws Throwable {
        String pass=String.format(outboundSportsEqiAddBtn,passenger);
        String equip=String.format(sportsEqpBtn,equipment);
        equipments.add(equip);
        waitForElementPresentE(selectSportsBtn_DE,40);
        bookSportsEqpBtn(selectSportsBtn_DE);
        if(waitForElementToBeClickable(addSportsItemBtn_DE)){
            waitForPageLoad(2000);
            click(addSportsItemBtn_DE);
            waitForElementToBeClickable(equip);
            click(equip);
            logger.log(LogStatus.PASS,"Selected outbound sports eqipment "+equipment+" for"+passenger);
        }
        else {
            logger.log(LogStatus.FAIL, "Unable select outbound sports eqipment "+equipment+" for"+passenger,logger.addScreenCapture(capture("OutBoundSportsEqpSelectinError")));
        }
    }

    /**
     * Selects SportsEquipment on inbound flight
     * @param passenger     :	Locator of passenger field   
     * @param equipment     :	Locator of equipment field   
     */
    public void inboundSportsEquipment(String passenger,String equipment) throws Throwable {
        String pass=String.format(inboundSportsEqiAddBtn,passenger);
        String equip=String.format(sportsEqpBtn,equipment);
        if(waitForElementToBeClickable(pass)){
            waitForPageLoad(2000);
            click(pass);
            waitForElementToBeClickable(equip);
            click(equip);
            logger.log(LogStatus.PASS,"Selected inbound sports eqipment "+equipment+" for"+passenger);
        }
        else {
            logger.log(LogStatus.FAIL, "Unable select inbound sports eqipment "+equipment+" for"+passenger,logger.addScreenCapture(capture("InBoundSportsEqpSelectinError")));
        }
    }

    /**
     * Display for verifying booking details
     */
    public void verifyBookingDetails() throws Throwable {
        verifyPassengerDetails();
        verfyItineraryDetails();
        //overViewOfBooking();
        logger.log(LogStatus.PASS,"Booking details verifiction done successfully");
    }

    public void verifyPassengerDetails() throws Throwable {
        ArrayList<String> passDetails=new ArrayList<>();
        if(waitForElementPresentE(bookingconfirmMessage_DE,100)){
            logger.log(LogStatus.INFO,getVisibleText(bookingNum)+", "+getVisibleText(bookingTotal));
            //Verifies passenger details provided at the time of booking
            for (WebElement pass : getAllElements(passengers)) {
                passDetails.add(pass.getText());
            }
            for (int i = 0; i < providedPassengerdetails.size(); i++) {
                String[] nameSurName=passDetails.get(i).split("\n");
                Assert.assertTrue(nameSurName[0].contains(providedPassengerdetails.get(i)));
            }
        }else {
            logger.log(LogStatus.FAIL,"Passenger details verification failed",logger.addScreenCapture(capture("PassengerDetailsEror")));
        }
    }

    public void verfyItineraryDetails() throws Throwable {
        if(getVisibleText(itineraryHeader).equalsIgnoreCase("Ihr Reiseplan")){
            logger.log(LogStatus.INFO,"OBFlight Name:"+getVisibleText(outBoundFlightName)+", "+getVisibleText(outBoundFlightFrom)+"-->"+getVisibleText(outBoundFlightTo)+" on "+getVisibleText(outBoundFlightDoj));
        }
        else{
            logger.log(LogStatus.FAIL,"OutBound Flight details Not found",logger.addScreenCapture(capture("OutBoundFlightError")));
        }
    }

    /**
     * Add Essentials on booking overview
     */
    public void overViewOfBooking() throws Throwable {

        ArrayList<String> addedEssentials=new ArrayList<>();
        ArrayList<String> moreExtrasList=new ArrayList<>();
        String includedServDetails;
        //Verifies Included services
        if(getVisibleText(includedSevicesHeader).contains("Included services")) {
            includedServDetails=getVisibleText(includedSevices);
            Assert.assertTrue(includedServDetails.contains(totalPassengers+" x "+6+" Kg hand luggage"));
            logger.log(LogStatus.PASS,"Included services verified successfully");
        }
        else
        {
            logger.log(LogStatus.FAIL, "Included services verification failed: " + logger.addScreenCapture(capture("IncludedServicesError")));
        }
        //Verifies Added essentials
        if(getVisibleText(essentilasHeader).contains("Essentials")) {
            for (WebElement essentilasList : getAllElements(essentilas)) {
                addedEssentials.add(essentilasList.getText());
            }
            for (String temp:addedEssentials){
                String[] essArray=temp.split(" ");
                switch (essArray[essArray.length-1]){
                    case "Seat":
                        //Verifies added seats in essentials
                        try {
                            if (passCount.isEmpty()) {
                            } else {

                                Assert.assertTrue(temp.contains(passCount.size() + "x Seat"));
                            }
                        }catch(NullPointerException e){
                            Assert.assertTrue(temp.contains("No seats"));
                        }
                        break;
                    case "meals":
                        //Verifies added meals in essentials
                        if(mealsMethodCount==0){
                            Assert.assertTrue((temp.contains("No meals")));
                        }
                        else{

                        }
                        break;
                    case "allowances":
                        //Verifies added baggage in essentials
                        if(baggageMethodCount==0){
                            Assert.assertTrue((temp.contains("No free baggage allowances")));
                        }
                        else {

                        }
                        break;
                }
            }
        }
        else{
            logger.log(LogStatus.FAIL, "Essentials page is not displayed: " + logger.addScreenCapture(capture("EssentialsPageError")));
        }

        //Verifies More Extras
        if(getVisibleText(moreExtrasHeader).contains("More extras")) {
            //commented for time being
            /*for (WebElement ele : getAllElements(moreExtras)) {
                moreExtrasList.add(ele.getText());
            }
            if(!equipments.isEmpty()&&(!moreExtrasList.isEmpty())){
                for (String eq:equipments){
                    Assert.assertTrue(moreExtrasList.contains(eq));
                }
            }
            else{
                logger.log(LogStatus.INFO, "No More extras added");
            }*/
        }
        else{
            logger.log(LogStatus.FAIL, "More extras page is noy displayed: " + logger.addScreenCapture(capture("MoreExtrasPageError")));
        }
    }
    /**
     * Pop up handler for add click on Reserve seats in feature
     */
    public void popUpHandler() throws Throwable {
        if(isElementPresent(popUpWindow)){
            waitForElementToBeClickable(popUpContinueBtn);
            click(popUpContinueBtn);
            waitForPageLoad(2000);
        }
        else
            System.out.println("---------------------");
    }
    /**
     * Accept alert function
     */
    public void alertAccept(boolean accept) throws InterruptedException {
        waitForPageLoad(2000);
        try
        {
            Alert alert = Driver.switchTo().alert();
            if(accept)
                alert.accept();
            else
                alert.dismiss();
        }
        catch (NoAlertPresentException Ex)
        {
            System.out.println("===No Alert present=====");
        }


    }
    /**
     * Adds Priority Package
     */
    public void addPriorityPackage () throws Throwable {
        if(waitForElementPresent(addPriorityPackageBtn)){
            click(addPriorityPackageBtn);
            waitForElementPresent(checkPass);
            click(checkPass);
            clickDone_DE();
            logger.log(LogStatus.PASS,"Selected Priority pacakge");
        }
        else{
            logger.log(LogStatus.FAIL, "Unable select Priority pacakge" + logger.addScreenCapture(capture("PirortyPackageSelectionPageError")));
        }
    }

    public void addMeals () throws Throwable {
        //add meals code goes here
        mealsMethodCount++;
    }

    /**
     * Function to Display Booking confirmation log 
     */
    public boolean verifyBooking() throws Throwable {
        waitForElementPresent(bookingconfirmMessage_DE,60);
        if (isElementPresent(bookingconfirmMessage_DE) && isElementPresent(bookingOverview)) {
            logger.log(LogStatus.PASS,"Booking has completed successfully");
            return true;
        }else {
            logger.log(LogStatus.FAIL,"Booking has failed");
            return false;

        }
    }

    /**
     * Wait and Click Logic for Buttons 
     * @param Locator     	:  Css selector of Button
     */

    public void clickContinue(String Locator) throws Throwable{
        waitForElementToBeClickable(Locator);
        click(Locator);
        //waitForElementNotPresent(iconSippner);
    }
    public void searchFlights() throws Throwable{
        waitForElementToBeClickable(searchBtn);
        click(searchBtn);
        alertAccept(true);
    }

    public void selectTravelClass(String Locator) throws Throwable{
        waitForElementToBeClickable(Locator, 60);
        click(Locator);
        clickContinue(continueBtn_Gen);
    }

    public void selectSeat() throws Throwable{
        waitForElementToBeClickable(slctSeatBtn,40);
        JSClick(slctSeatBtn);
    }

    public void handleSpinner(String locator) throws Throwable {
        waitForElementNotPresent(iconSippner);
        clickContinue(locator);
        waitForElementNotPresent(iconSippner);
    }
    public void bookSportsEqpBtn(String locator) throws Throwable {
        waitForElementToBeClickable(locator);
        click(locator);
    }
    public void waitForPageLoad(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    public void payment3DSecure(String pin) throws Throwable {
        if(waitForElementPresentE(pwdField,60)){
            type(pwdField,pin);
            waitForElementToBeClickable(goBtn);
            click(goBtn);
            waitForElementToBeClickable(backBtn,40);
            click(backBtn);
            logger.log(LogStatus.PASS,"3D Secure payment done successfully");
        }
        else{
            logger.log(LogStatus.FAIL, "3D Secure payment failed" + logger.addScreenCapture(capture("3DSecurePaymentPageError")));
        }
    }
    //selects seat int the specified category
    public void selectSeatPerPerson(String seatNumber,String seatType) throws Throwable {
        String seatButton=String.format(seatbtn_DE,seatNumber);
        switch(seatType){
            case "XL":
                try{
                    scrollElementIntoView(seatButton+xLSeatbtn);
                    click(seatButton+xLSeatbtn);
                    xLPopUpAccept();
                    logger.log(LogStatus.INFO,"Selected extra leg room seat: "+seatNumber);
                }catch(NoSuchElementException e){
                    logger.log(LogStatus.INFO,"Specified seat "+seatNumber+" is not Extra leg room seat, Selecting the available one");
                    if(clickSeatWithType(xLSeatGenericLocator)){
                        xLPopUpAccept();
                    }
                }
                break;
            case "Infant":
                try{
                    scrollElementIntoView(seatButton+infantSeatbtn);
                    click(seatButton+infantSeatbtn);
                    logger.log(LogStatus.INFO,"Selected infant seat: "+seatNumber);
                }catch(NoSuchElementException e){
                    logger.log(LogStatus.INFO,"Specified seat "+seatNumber+" is not for travelling with infant, Selecting the available one");
                    clickSeatWithType(infantSeatGenericLocator);
                }
                break;
            default:
                try{
                    scrollElementIntoView(seatButton);
                    click(seatButton);
                    logger.log(LogStatus.INFO,"Selected regular seat: "+seatNumber);
                }catch(NoSuchElementException e){
                    logger.log(LogStatus.INFO,"Specified seat "+seatNumber+" is not available, Selecting the available one");
                    clickSeatWithType(regularSeatGenericLocator);
                }
        }
    }
    private boolean clickSeatWithType(String locator) throws Throwable {
        boolean flag=false;
        for (WebElement ele:getAllElements(locator)){
            try{
                scrollElementIntoView(ele);
                flag=click(ele);
                String[] seatName=ele.getAttribute("aria-label").split(" ");
                logger.log(LogStatus.INFO,"Allocated seat is "+seatName[seatName.length-1]);
                if(flag){
                    break;
                }
                return flag;
            }catch(NoSuchElementException e){
                logger.log(LogStatus.INFO,"No seats are available for Selected flight in the specified category");
                if(!flag){
                    break;
                }
                return flag;
            }
        }
        return flag;
    }
    //accepts XL popup
    private void xLPopUpAccept() throws Throwable {
        if(isElementPresent(xLSeatWarning)){
            waitForElementToBeClickable(xLContinueLnk_DE);
            click(xLContinueLnk_DE);
        }
    }

    /**
     * purpose: Clicks on Done button and then Continue button
     * @throws Throwable
     */
    protected void clickDoneAndContnue() throws Throwable {
        waitForElementToBeClickable(done,30);
        click(done);
        waitForPageLoad(3000);
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
    }


    protected void clickDone_DE() throws Throwable {
        waitForElementToBeClickable(done_DE,30);
        click(done_DE);
    }

    protected void waitAndClickContnue() throws Throwable {
        waitForPageLoad(3000);
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
    }

    /**
     * purpose: Clicks on Done button and then Continue button
     * @throws Throwable
     */
   /* protected void checkSeatAvailability(String locator) throws Throwable {
        try{
            scrollElementIntoView(locator+seatTaken+infantSeatbtn);
            if(!(isElementPresent(locator+seatTaken+infantSeatbtn))) {
            click(locator+infantSeatbtn);
            }else {
                clickSeatWithType(locator);
            }
            logger.log(LogStatus.INFO,"Selected infant seat: "+seatNumber);
        }catch(NoSuchElementException e){
            logger.log(LogStatus.INFO,"Specified seat "+seatNumber+" is not for travelling with infant, Selecting the available one");
            clickSeatWithType(infantSeatGenericLocator);
        }
    }*/

    protected void selectMeals(String passenger,String itemName) throws Throwable {
        if(waitForElementPresentE(slctMealBtn)){
            String desiredMealItem=String.format(mealItemBtn,itemName);
            click(slctMealBtn);
            for (WebElement ele:getAllElements(passengersList_Meal)) {
//                if(ele.findElement(byLocator(passengerNameTxt)).getText().equalsIgnoreCase(passenger)){
                waitForPageLoad(3000);//time being
                System.out.println(">>>"+ele.findElement(byLocator(passengerNameTxt)).getText());
                System.out.println(">>>"+passenger);
                if(ele.findElement(byLocator(passengerNameTxt)).getText().contains(passenger)){
                    System.out.println("====meals=====");
                    waitForElementToBeClickable(ele.findElement(byLocator(mealChangeBtn)));
                    waitForPageLoad(3000);//time being
                    click(ele.findElement(byLocator(mealChangeBtn)));
                    waitForElementToBeClickable((desiredMealItem));
                    waitForPageLoad(3000);//time being
                    click(desiredMealItem);
                    logger.log(LogStatus.PASS,"Added "+itemName+" to "+passenger);
                    break;
                }
            }
            clickDone_DE();
        }else {
            logger.log(LogStatus.FAIL, "Unable to select meals ",logger.addScreenCapture(capture("AddMealsPageError")));
        }
    }

    protected void selectBundle() throws Throwable {
        if(waitForElementPresentE(slctBundle)){
            click(slctBundle);
            waitForElementToBeClickable(pssngSelectionChkBox);
            click(pssngSelectionChkBox);
            clickDone_DE();
            logger.log(LogStatus.PASS,"Selected Bundle");
        }else {
            logger.log(LogStatus.FAIL, "Unable to select Bundle ",logger.addScreenCapture(capture("AddBundlePageError")));
        }
    }

    protected void selectFlight(String desiredFlight,String fare) throws Throwable {
        if(waitForElementPresentE(availableFlightsList,40)){
            for (WebElement ele:getAllElements(availableFlightsList)) {
                if(ele.findElement(byLocator(flightName)).getText().equalsIgnoreCase(desiredFlight)){
                    if(fare.contains("Economy")){
                        try {
                            waitForElementToBeClickable(ele.findElement(byLocator(flightEconomyClassBtn)));
                            click(ele.findElement(byLocator(flightEconomyClassBtn)));
                            break;
                        }catch (NoSuchElementException e){
                            logger.log(LogStatus.FAIL,fare+" is not available in the current flight");
                            throw e;
                        }
                    }else if(fare.contains("Premium")){
                        try {
                            waitForElementToBeClickable(ele.findElement(byLocator(flightPremiumClassBtn)));
                            click(ele.findElement(byLocator(flightPremiumClassBtn)));
                            break;
                        }catch (NoSuchElementException e){
                            logger.log(LogStatus.FAIL,fare+" is not available in the current flight");
                            throw e;
                        }
                    }
                }
            }

            logger.log(LogStatus.PASS,"Selected "+fare+" for flight");
        }else {
            logger.log(LogStatus.FAIL, "Unable to select flight",logger.addScreenCapture(capture("FlightPageError")));
        }
    }

    protected void flexibleFlying() throws Throwable {
        if(waitForElementPresent(flexiFlyingLabel_DE)){
            waitForPageLoad(3000);
            clickContinue(continueBtn_Gen);
        }
    }

    /**
     * Selects  number and type of passengers
     * @param passengerList
     * @throws Throwable
     */
    public void selectPassengers(String passengerList) throws Throwable {

        String[] str=passengerList.split(",");

        if(waitForElementPresentE(passengerSelectionHeadre)){
            for (String passenger:str) {
                String[] pass=passenger.split(" ");
                if(pass[1].contains("Adults")) {

                    selectAdults(Integer.parseInt(pass[0]));
                    totalPassengers=totalPassengers+Integer.parseInt(pass[0]);
                }
                else if(pass[1].contains("Youth")) {

                    SelectYoungAdults(Integer.parseInt(pass[0]));
                    totalPassengers=totalPassengers+Integer.parseInt(pass[0]);
                }
                else if(pass[1].contains("Child")) {

                    SelectChildren(Integer.parseInt(pass[0]));
                    totalPassengers=totalPassengers+Integer.parseInt(pass[0]);
                }
                else if(pass[1].contains("Infant")) {

                    SelectInfants(Integer.parseInt(pass[0]));
                    totalPassengers=totalPassengers+Integer.parseInt(pass[0]);
                }
            }

        }else {
            logger.log(LogStatus.FAIL, "Unable to select Passengers ",logger.addScreenCapture(capture("PassengerSelectionError")));
        }

    }
    protected void selectEssentials(String essentialList,String seatMapper) throws Throwable {
        String[] list = essentialList.split("\\+");
        if(waitForElementPresent(essentialsPageLabel_DE,40)) {
            for (String particularEssential : list) {
                if (particularEssential.toLowerCase().contains("seat")) {
                    waitForPageLoad(5000);//this wait is required
                    waitForElementToBeClickable(slctSeatBtn, 40);
                    click(slctSeatBtn);
                    clickReserveSeatsForEveryone();
                    allocateSeatsForAll(seatMapper);
                    clickDone_DE();
                } else if (particularEssential.toLowerCase().contains("baggage")) {
                    selectBaggage(addAdult_DE, particularEssential.split("kg")[0]);
                } else if (particularEssential.toLowerCase().contains("meal")) {
                    waitForPageLoad(5000);//this wait is required
                    String[] mealItemName = particularEssential.split(" ");
                    if (particularEssential.contains("Child")) {
                        selectMeals("Kind 1", mealItemName[0].trim());
                    } else {
                        selectMeals("Erwachsener", mealItemName[0].trim());
                    }
                }
            }
        }else {
            logger.log(LogStatus.FAIL,"Essentials page is missing"+logger.addScreenCapture(capture("EssentialsPageError")));
        }
    }

    //selects seat int the specified category
    public void allocateSeatsForAll(String seatMapper) throws Throwable {

        int totalRows=getAllElements(rows).size();
        int totalColumns=getAllElements(columns).size();
        String[] seatArray=seatMapper.split(",");
        for (String seatTemp:seatArray) {
            String[] seatWithNoOfPassAndType=seatTemp.split("-");
            int noOfPassengers=Integer.parseInt(seatWithNoOfPassAndType[0].split(" ")[0]);
            String seatCatagory=seatWithNoOfPassAndType[1].trim();

            switch(seatCatagory){
                case "XL":
                    for (int i = 1; i <= noOfPassengers; i++) {
                        for (WebElement ele:getAllElements(xLSeatGenericLocator)){
                            scrollElementIntoView(ele);
                            if(click(ele)){
                                xLPopUpAccept();
                                String[] seatName=ele.getAttribute("aria-label").split(" ");
                                logger.log(LogStatus.INFO,"Allocated XL seat is "+seatName[seatName.length-1]);
                                break;
                            }
                        }
                    }
                    break;
                case "Infant":
                    for (int i = 1; i <= noOfPassengers; i++) {
                        for (WebElement ele:getAllElements(infantSeatGenericLocator)){
                            scrollElementIntoView(ele);
                            if(click(ele)){
                                String[] seatName=ele.getAttribute("aria-label").split(" ");
                                logger.log(LogStatus.INFO,"Allocated Infant seat is "+seatName[seatName.length-1]);
                                break;
                            }
                        }
                    }
                    break;

                case "FrontSeat":
                    for (int i = 1; i <= noOfPassengers; i++) {//for noof passengers
                        for (int j = 1; j <=1 ; j++) {//for first row of plane
                            for (WebElement column:getAllElements(columns)) {//for all the available columns
                                String seatButton=String.format(seatbtn_DE,j+""+column.getText().trim());
                                try{
                                    scrollElementIntoView(seatButton);
                                    if(click(seatButton)){
                                        String[] seatName=getElement(seatButton).getAttribute("aria-label").split(" ");
                                        logger.log(LogStatus.INFO,"Allocated Front seat is "+seatName[seatName.length-1]);
                                        break;
                                    }
                                }catch (NoSuchElementException e){
                                    logger.log(LogStatus.INFO,"Seat is not available in the specified Catagory selecting the available one");
                                    selectAvailableSeat(regularSeatGenericLocator);
                                }
                            }
                        }
                    }
                    break;
                case "BackSeat":
                    for (int i = 1; i <= noOfPassengers; i++) {//for noof passengers
                        for (int j = totalRows; j <=totalRows ; j++) {//for back row of plane
                            for (WebElement column:getAllElements(columns)) {//for all the available columns
                                String seatButton=String.format(seatbtn_DE,j+""+column.getText().trim());
                                try{
                                    scrollElementIntoView(seatButton);
                                    if(click(seatButton)){
                                        String[] seatName=getElement(seatButton).getAttribute("aria-label").split(" ");
                                        logger.log(LogStatus.INFO,"Allocated Back seat is "+seatName[seatName.length-1]);
                                        break;
                                    }
                                }catch (NoSuchElementException e){
                                    logger.log(LogStatus.INFO,"Seat is not available in the specified Catagory selecting the available one");
                                    selectAvailableSeat(regularSeatGenericLocator);
                                }
                            }
                        }
                    }
                    break;
                default:
                    selectAvailableSeat(regularSeatGenericLocator);
            }
        }
    }

    private void selectAvailableSeat(String locator) throws Throwable {
        for (WebElement ele:getAllElements(locator)){
            try{
                scrollElementIntoView(ele);
                if(click(ele)){
                    String[] selectedSeat=ele.getAttribute("aria-label").split(" ");
                    String seatName=selectedSeat[selectedSeat.length-1];
                    logger.log(LogStatus.INFO,"Allocated Standard seat is "+seatName);
                    break;
                }
            }catch(NoSuchElementException e){
                logger.log(LogStatus.FATAL,"Sorry HouseFull");
                break;
            }
        }
    }

    protected void selectExtras(String extrasList) throws Throwable {
        String[] list = extrasList.split("\\+");
        ArrayList<String> itemsList=new ArrayList<>();
        if(waitForElementPresent(extrasPagelabel,40)) {
            try{
                for (String particularExtra : list) {
                    if (particularExtra.toLowerCase().contains("priority")) {
                        addPriorityPackage();
                    }
                    else if (particularExtra.toLowerCase().contains("pet")) {
                        waitForPageLoad(5000);//wait is required
                        String[] petDetails=particularExtra.split("-");
                        String pet=petDetails[1].trim();
                        String weight=petDetails[2].trim();
                        String carryType=petDetails[3].trim();
                        selectPets("Erwachsener 1",pet,weight,carryType);
                    }
                    else if (particularExtra.toLowerCase().contains("sport")) {
                   /* for (WebElement element:getAllElements(availableSportsItemsList)) {
                        itemsList.add(element.getText().split("\n")[0].split(" ")[0].trim());
                    }
                    String requiredItemName=particularExtra.split(" ")[0].trim();
                    if(itemsList.contains(requiredItemName)){
                        outboundSportsEquipment("Adult 1",requiredItemName);
                        clickDone_DE();
                    }*/
                        String requiredItemName=particularExtra.split(" ")[0].trim();
                        outboundSportsEquipment("Adult 1",requiredItemName);
                        clickDone_DE();
                    }
                }
                clickContinue(continueBtn_Gen);
            }catch (Exception e){
                throw e;
            }
        }else {
            logger.log(LogStatus.FAIL,"Extras page is missing"+logger.addScreenCapture(capture("ExtrasPageError")));
        }
    }

    public void selectPets(String passenger,String petName,String weight,String carryingOption) throws Throwable {
        String desiredPetName=String.format(selectAnimalBtn,petName);
        if(waitForElementPresentE(selectpetsBtn_DE)){
            click(selectpetsBtn_DE);
            for (WebElement ele:getAllElements(passengersList_Pets)) {
                waitForPageLoad(3000);//time being
                System.out.println(">>>"+ele.findElement(byLocator(passengerNameTxt)).getText());
                System.out.println(">>>"+passenger);
                if(ele.findElement(byLocator(passengerNameTxt)).getText().contains(passenger)){
                    System.out.println("====pets=====");
                    waitForElementToBeClickable(ele.findElement(byLocator(petAddBtn)));
                    waitForPageLoad(3000);//time being
                    click(ele.findElement(byLocator(petAddBtn)));
                    waitForElementToBeClickable((desiredPetName));
                    waitForPageLoad(3000);//time being
                    click(desiredPetName);
                    waitForElementPresent(weightTxt);
                    type(weightTxt,weight);
                    if(carryingOption.contains("Cabin")){
                        click(inCabinBtn);
                    }else if(carryingOption.contains("55")){
                        click(cabin55CmBtn);
                    }else if(carryingOption.contains("75")){
                        click(cabin75CmBtn);
                    }
                    logger.log(LogStatus.PASS,"Added "+petName+" to "+passenger);
                    break;
                }
            }
            clickDone_DE();

        }else {
            logger.log(LogStatus.FAIL,"Unable to add pet"+logger.addScreenCapture(capture("PetAddPageError")));
        }
    }

    protected void travelInsurance() throws Throwable {
        if(waitForElementPresent(insuranceLable_DE)){
            waitForPageLoad(3000);
            clickContinue(continueBtn_Gen);
        }
    }

    /**
     * Logic for Journey date selection
     */

    public void datesSelection() throws Throwable {

        List<WebElement> datarows=null;
        waitForPageLoad(10000);
        datarows=getAllElements(flightsResult_DE);
  /*  Date date=new Date();
    Calendar currentDate = Calendar.getInstance();
    currentDate.add(Calendar.DATE,3);*/


        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // today date.
        c.add(Calendar.DATE, 3); // Adding 3 days
        String output = sdf.format(c.getTime());
        int actual= Integer.parseInt(output);

        if(datarows.size()>0){

            for(int rownum=0;rownum<datarows.size();rownum++){

                WebElement element=datarows.get(rownum);
                String elementText=element.getText();
                int expected=Integer.parseInt(elementText);
                if(expected>=actual){
                    waitForPageLoad(3000);
                    click(element);
                    logger.log(LogStatus.PASS,"Selected round trip with DOJ:" +element);
                    break;
                }

            }

        }else{

            waitForElementPresent(calNextBtn_DE);
            click(calNextBtn_DE);

            for(int rownum=0;rownum<datarows.size();rownum++){

                WebElement element=datarows.get(rownum);
                String elementText=element.getText();
                int expected=Integer.parseInt(elementText);
                if(expected>=actual){
                    click(element);
                    logger.log(LogStatus.PASS,"Selected round trip with DOJ:" +element);
                    break;
                }

            }

        }

    }

}
