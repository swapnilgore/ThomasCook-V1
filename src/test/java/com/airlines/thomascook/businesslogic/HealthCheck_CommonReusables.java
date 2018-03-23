package com.airlines.thomascook.businesslogic;

import com.airlines.thomascook.accelerators.ActionEngine;
import com.airlines.thomascook.objectrepository.*;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by E002465 on 06-06-2017.
 */
public class HealthCheck_CommonReusables extends ActionEngine implements OR_HomePage,OR_PassengerDetails,OR_ContactDetails,OR_PaymentDetails,OR_FlightDetails,OR_EssentialsBaggagePreference,OR_ExtrasSportsEqpAndCargo,OR_BookingConfPage,OR_EssentialsSeatPreference,OR_AddPriorityPackage,OR_EssentialsMealsPreference,OR_EssentialsBundle,OR_FlexibleFlying {

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
    public void selectDepartureAirport(String departure) throws Throwable {
        String fromActualText=null;
        try {
            waitForElementPresent(depMenu, 10);
            if(isElementPresent(depMenu)){
                fromActualText=getVisibleText(depMenu);
                verifyText(fromActualText,"From");

            }
            click(depMenu);
        } catch (TimeoutException | NoSuchElementException n) {
//             Driver.get("https://www-stage.condor.com/us/index2.jsp");
            Driver.navigate().refresh();
            System.out.println("=======refresh========");
            waitForElementPresent(depMenu);
            click(depMenu);
        }
        departureAirport(departure);

    }

    public void departureAirport(String departure) throws Throwable {
        if (waitForElementPresentE(deparAirPorttHeader)) {
            isElementVisible(byLocator(flightSeclectionPopup),"FlightSelectionPopup",30);
            String deparAirPortHeaderActualText=getVisibleText(deparAirPorttHeader);
            verifyText(deparAirPortHeaderActualText,"Your departure airport");
            if ((getVisibleText(deparAirPorttHeader).contains("Your departure airport"))) {
                waitForElementPresent(depTextBox, 60);
                type(depTextBox, departure);
                waitForElementPresent(depDropDwn);
                for (WebElement ele : getAllElements(depDropDwn)) {
                    if (ele.getText().contains(departure)) {
                        ele.click();
                        //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("Departure")));
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
    public void selectDestinationAirport(String destination) throws Throwable {
        if (waitForElementPresentE(arrvlTextBox, 60)) {
            String destinationAirportActual=getVisibleText(destiAirPorttHeader);
            verifyText(destinationAirportActual,"Your destination airport");
            type(arrvlTextBox, destination);
            waitForElementPresent(arrvlDropDwn);
            for (WebElement ele : getAllElements(arrvlDropDwn)) {
                if (ele.getText().contains(destination)) {
                    ele.click();
                    //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("Destination")));
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
            String passengerActual=getVisibleText(passengerText);
            verifyText(passengerActual,"Passenger information");
            providedPassengerdetails.add(firstName + " " + surName);
            String d_SalutationRadioButton_MR = String.format(d_SalutationRadioBtn_MR, passNum);
            String d_FirstNameTextBox = String.format(d_FirstNameTxtBox, passNum);
            String d_SurNameTextBox = String.format(d_SurNameTxtBox, passNum);
            String d_DOBTextBox = String.format(d_DOBTxtBox, passNum);
            click(d_SalutationRadioButton_MR);
            type(d_FirstNameTextBox, firstName);
            type(d_SurNameTextBox, surName);
            type(d_DOBTextBox, dOB);
            for (WebElement ele:getAllElements(continueBtn_pass)) {
                if(ele.isDisplayed()){
                    click(ele);
                    break;
                }
            }
            logger.log(LogStatus.PASS, "Entered adult " + passNum + " details");
        }else {
            logger.log(LogStatus.FAIL, "Unable to enter adult passenger " + passNum + " details",logger.addScreenCapture(capture("AdultPassengerInfoError")));
        }
    }

    public void passDetails(String[] passengerDetails,int passNum) throws Throwable {
        String salutation=passengerDetails[0];
        String firstName=passengerDetails[1];
        String surName=passengerDetails[2];
        String dOB=passengerDetails[3];

        String saluationPart2 = String.format(salutationLocator, salutation.toUpperCase());
        String d_SalutationRadioButton_MR = String.format(d_SalutationRadioBtn_MR+saluationPart2, passNum);
        String d_FirstNameTextBox = String.format(d_FirstNameTxtBox, passNum);
        String d_SurNameTextBox = String.format(d_SurNameTxtBox, passNum);
        String d_DOBTextBox = String.format(d_DOBTxtBox, passNum);
        if (waitForElementPresentE(passengerDetailsSection,60)) {
            String passengerActual=getVisibleText(passengerText);
            verifyText(passengerActual,"Passenger information");
            providedPassengerdetails.add(firstName + " " + surName);
            click(d_SalutationRadioButton_MR);
            type(d_FirstNameTextBox, firstName);
            type(d_SurNameTextBox, surName);
            type(d_DOBTextBox, dOB);
            for (WebElement ele:getAllElements(continueBtn_pass)) {
                if(ele.isDisplayed()){
                    click(ele);
                    break;
                }
            }
            logger.log(LogStatus.PASS, "Entered adult " + passNum + " details");
        }else {
            logger.log(LogStatus.FAIL, "Unable to enter adult passenger " + passNum + " details",logger.addScreenCapture(capture("AdultPassengerInfoError")));
        }
    }



    public void youngOrChildOrInfantDetails(String gender,String firstName,String surName,String dOB,int passNum) throws Throwable {
        if (Driver.getCurrentUrl().contains("flight/passenger")) {
            String d_MaleRadioButton=String.format(genderRadioBtn,passNum);
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
                    //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("Passenger"+passNum +"details")));
                    click(ele);
                    break;
                }
            }
            logger.log(LogStatus.PASS,"Entered passenger "+passNum+" details");
        }else {
            logger.log(LogStatus.FAIL, "Unable to enter passenger " + passNum + " details",logger.addScreenCapture(capture("PassengerInfoError")));
        }
    }
    public void youngOrChildOrInfantDetails(String[] passengerDetails,int passNum) throws Throwable {
        String gender=passengerDetails[0];
        String firstName=passengerDetails[1];
        String surName=passengerDetails[2];
        String dOB=passengerDetails[3];

        String genderPart2=String.format(genderLocator,gender);
        String d_MaleRadioButton=String.format(genderRadioBtn+genderPart2,passNum);
        String d_FirstNameTextBox=String.format(d_FirstNameTxtBox,passNum);
        String d_SurNameTextBox=String.format(d_SurNameTxtBox,passNum);
        String d_DOBTextBox=String.format(d_DOBTxtBox,passNum);

        if (Driver.getCurrentUrl().contains("flight/passenger")) {
            providedPassengerdetails.add(firstName + " " + surName);
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
            click(SalutationRadioButton);
            type(firstNameTxtBox,firstName);
            type(surNameTxtBox,surName);
            type(address1TxtBox,address1);
            type(postTxtBox,postCode);
            type(cityTxtBox,city);
            type(countryTxtBox,country,true,false);
            List<WebElement> listOfCountries=getAllElements(countryDropDown);
            for(WebElement we:listOfCountries){
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
            isElementVisible(byLocator(paymentSection),"PaymentSectionCheck",10);
            String cardTypeButton = String.format(cardTypeBtn, cardType);
            if (paymentType.equalsIgnoreCase("Credit card") || paymentType.contains("Credit")) {
                waitForElementToBeClickable(creditCardRadioBtn);
                click(creditCardRadioBtn);
                waitForElementToBeClickable(cardTypeButton);
                click(cardTypeButton);
                enterCardDetails(paymentType, cardType, nameOnCard, cardNumber, month, year, cVV);
                logger.log(LogStatus.PASS,"Paid through "+paymentType+" with card type "+cardType);
            } else if (paymentType.equalsIgnoreCase("Debit card") || paymentType.contains("Debit")) {
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
        click(monthDropDwnBtn);
        waitForElementToBeClickable(expMonth);
        click(expMonth);
        waitForElementToBeClickable(yearDropDwnBtn);
        click(yearDropDwnBtn);
        waitForElementToBeClickable(expYear);
        click(expYear);
        type(cVVTxtBox,cVV);
        Driver.switchTo().parentFrame();
        //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("PaymentDetails")));
        click(payByBtn);
    }



    /**
     * Logic for Journey date selection
     * @param month     	:  Month of Journey
     * @param date	     	:  Date of Journey
     */
    public void selectMonthandDayOfJourney(String month,String date) throws Throwable {
        String currentMonth = getVisibleText(departuremonthText);
        String journeyDate=String.format(selectDayPart1,date)+String.format(selectDayPart2,month);
        System.out.println("The current month is :" + currentMonth);
        while (!currentMonth.contains(month)) {
            waitForElementPresent(calNextBtn);
            click(calNextBtn);
            currentMonth = getVisibleText(departuremonthText);
            System.out.println("The current month is :" + currentMonth);
        }
        waitForElementToBeClickable(journeyDate);
        try {
//            waitForPageLoad(3000);
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
                waitForElementToBeClickable(childPlusBtn);
                click(adultPlusBtn);
            }
        } else if (reqpass < defaultPass) {
            Passengers = defaultPass - reqpass;
            for (int i = 0; i < Passengers; i++) {
                waitForElementToBeClickable(adultMinusBtn);
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
//        waitForPageLoad(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                waitForElementToBeClickable(youngPlusBtn);
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
//        waitForPageLoad(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                waitForElementToBeClickable(childPlusBtn);
                waitForPageLoad(1000);
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
//        waitForPageLoad(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                waitForElementToBeClickable(infantPlusBtn);
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
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("PassengerSelection")));
        }else {
            logger.log(LogStatus.FAIL, "Unable to select Passengers ",logger.addScreenCapture(capture("PassengerSelectionError")));
        }

    }
    /**
     * Selects  seat reservation for all passengers
     */
    public void clickReserveSeatsForEveryone() throws Throwable {

    }

    /**
     * Selects SportsEquipment on inbound flight
     * @param locator     :	Locator of selectbaggage field   
     * @param baggage     :	baggage  
     */
    public void selectBaggage(String locator, String baggage) throws Throwable {
        if(waitForElementPresentE(bookBaggage,60)){
            waitForElementToBeClickable(bookBaggage);
            click(bookBaggage);
            waitForElementToBeClickable(locator);
            isElementVisible(byLocator(essentialsPopUp),"BaggagePopUpSelectionPopUp",10);
            String baggageActualText=getVisibleText(baggageText);
            verifyContainsText(baggageActualText,"Additional baggage");
            click(locator);
            String baggaeType=String.format(selectBaggage,baggage);
            waitForElementToBeClickable(baggaeType);
            waitForPageLoad(2000);
            click(baggaeType);
            clickDone();
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
        String outBoundClass=String.format(outBoundCls,selectOutboundClass);
        if(waitForElementPresentE(outBoundClass)){
            isElementVisible(byLocator(calenderSlider),"CalenderSliderDisplay",10);
            waitForElementToBeClickable(outBoundClass);
            isElementVisible(byLocator(availableFlightsList),"FlighListDisplay",10);
            click(outBoundClass);
            logger.log(LogStatus.PASS,"Selected outbound class "+selectOutboundClass);
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("OutBound")));
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
        String inBoundClass=String.format(inBoundCls,selectInboundClass);
        if(waitForElementPresentE(inBoundClass,60)){
            isElementVisible(byLocator(calenderSlider),"CalenderSliderDisplay",10);
            waitForElementToBeClickable(inBoundClass);
            isElementVisible(byLocator(availableFlightsList),"FlighListDisplay",10);
            scrollElementIntoView(inBoundClass);
            click(inBoundClass);
            logger.log(LogStatus.PASS,"Selected inbound class "+selectInboundClass);
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("InBound")));
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
    public void outboundSportsEquipment(String passenger,String equipment) throws Throwable {
        String pass=String.format(outboundSportsEqiAddBtn,passenger);
        String equip=String.format(sportsEqpBtn,equipment);
        equipments.add(equip);
        waitForElementPresentE(bookSportsEqpBtn,40);
        bookSportsEqpBtn(bookSportsEqpBtn);
        if(waitForElementToBeClickable(pass)){
            waitForPageLoad(2000);
            click(pass);
            waitForElementToBeClickable(equip);
            click(equip);
            logger.log(LogStatus.PASS,"Selected outbound sports eqipment "+equipment+" for "+passenger);
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
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("InBoundSportsEquipment")));
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
        if(waitForElementPresentE(bookingconfirmMessage,60)){
            String bookingConfirmationActualText=getVisibleText(bookingconfirmMessage);
            verifyContainsText(bookingConfirmationActualText,"Thank you for your booking");
            logger.log(LogStatus.INFO,getVisibleText(bookingNum)+", "+getVisibleText(bookingTotal));
            //Verifies passenger details provided at the time of booking
            for (WebElement pass : getAllElements(passengers)) {
                passDetails.add(pass.getText());
            }
            for (int i = 0; i < providedPassengerdetails.size(); i++) {
                String[] nameSurName=passDetails.get(i).split("\n");
                Assert.assertTrue(nameSurName[0].contains(providedPassengerdetails.get(i)));
            }
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("BookingConformation")));
        }else {
            logger.log(LogStatus.FAIL,"Passenger details verification failed",logger.addScreenCapture(capture("PassengerDetailsEror")));
        }
    }

    public void verfyItineraryDetails() throws Throwable {
        if(getVisibleText(itineraryHeader).equalsIgnoreCase("Your itinerary")){
            scrollElementIntoView(itineraryHeader);
            logger.log(LogStatus.INFO,"OBFlight Name:"+getVisibleText(outBoundFlightName)+", "+getVisibleText(outBoundFlightFrom)+"-->"+getVisibleText(outBoundFlightTo)+" on "+getVisibleText(outBoundFlightDoj));
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("IteraryDetails")));
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
//        waitForPageLoad(2000);
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
        if(waitForElementPresentE(addPriorityPackageBtn)){
            click(addPriorityPackageBtn);
            waitForElementPresent(checkPass);
            isElementVisible(byLocator(priorityPackagePopUp),"PriorityPackagePopUp",10);
            click(checkPass);
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("PriorityPackage")));
            waitForElementToBeClickable(done);
            click(done);
            waitForPageLoad(2000);
            waitForElementToBeClickable(continueBtn_Gen);
            click(continueBtn_Gen);
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
        waitForElementPresent(bookingconfirmMessage,60);
        if (isElementPresent(bookingconfirmMessage) && isElementPresent(bookingOverview)) {
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
        if(waitForElementToBeClickable(Locator)){
            click(Locator);
        }
    }
    public void searchFlights() throws Throwable{
        waitForElementToBeClickable(searchBtn);
        isElementVisible(byLocator(searchBtn),"SearchForFlightsButtonCheck",05);
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
        if(waitForElementPresent(pwdField,20)){
            type(pwdField,pin);
            waitForElementToBeClickable(goBtn);
            click(goBtn);
            //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("3DPayment")));
            waitForElementToBeClickable(backBtn,40);
            click(backBtn);
            logger.log(LogStatus.PASS,"3D Secure payment done successfully");
        }
        else{
            //logger.log(LogStatus.FAIL, "3D Secure payment failed" + logger.addScreenCapture(capture("3DSecurePaymentPageError")));
        }
    }
    //selects seat int the specified category
    public void selectSeatPerPerson(String seatNumber,String seatType) throws Throwable {
        String seatButton=String.format(seatbtn,seatNumber);
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
            waitForElementToBeClickable(xLContinueLnk);
            click(xLContinueLnk);
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

    protected void clickDone() throws Throwable {
        waitForElementToBeClickable(done,30);
        click(done);
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
        String desiredMealItem=String.format(mealItemBtn,itemName);
        if(waitForElementPresentE(slctMealBtn)){
            click(slctMealBtn);
            for (WebElement ele:getAllElements(passengersList_Meal)) {
//                if(ele.findElement(byLocator(passengerNameTxt)).getText().equalsIgnoreCase(passenger)){
                waitForPageLoad(3000);//time being
                isElementVisible(byLocator(essentialsPopUp),"MealPopUp",10);
                System.out.println(">>>"+ele.findElement(byLocator(passengerNameTxt)).getText());
                System.out.println(">>>"+passenger);
                if(ele.findElement(byLocator(passengerNameTxt)).getText().contains(passenger)){
                    waitForElementToBeClickable(ele.findElement(byLocator(mealChangeBtn)));
                    click(ele.findElement(byLocator(mealChangeBtn)));
                    waitForElementToBeClickable((desiredMealItem));
                    waitForPageLoad(4000);//time being
                    click(desiredMealItem);
                    logger.log(LogStatus.PASS,"Added "+itemName+" to "+passenger);
                    //logger.log(LogStatus.INFO, logger.addScreenCapture(capture("MealsSelection")));
                    break;
                }
            }
            clickDone();
        }else {
            logger.log(LogStatus.FAIL, "Unable to select meals ",logger.addScreenCapture(capture("AddMealsPageError")));
        }
    }

    protected void selectBundle() throws Throwable {
        if(waitForElementPresentE(slctBundle)){
            click(slctBundle);
            waitForElementToBeClickable(pssngSelectionChkBox);
            click(pssngSelectionChkBox);
            clickDone();
            logger.log(LogStatus.PASS,"Selected Bundle");
        }else {
            logger.log(LogStatus.FAIL, "Unable to select Bundle ",logger.addScreenCapture(capture("AddBundlePageError")));
        }
    }

    protected void selectFlight(String desiredFlight,String fare) throws Throwable {
        if(waitForElementPresentE(availableFlightsList,40)){
            isElementVisible(byLocator(calenderSlider),"CalenderSliderDisplay",10);
            isElementVisible(byLocator(availableFlightsList),"FlighListDisplay",10);
            for (WebElement ele:getAllElements(availableFlightsList)) {
//                if(ele.findElement(byLocator(flightName)).getText().equalsIgnoreCase(desiredFlight)){
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
//                }
            }

            logger.log(LogStatus.PASS,"Selected "+fare+" for flight");
        }else {
            logger.log(LogStatus.FAIL, "Unable to select flight",logger.addScreenCapture(capture("FlightPageError")));
        }
    }
    protected void selectEconomyFlight() throws Throwable {
        selectFlight("","Economy Class");
    }

    protected void selectPremiumFlight() throws Throwable {
        selectFlight("","Premium Class");
    }

    protected void flexibleFlying() throws Throwable {
        if(waitForElementPresent(flexiFlyingLabel)){
            isElementVisible(byLocator(flexiFlyingLabel),"FlexFlyingCheck",10);
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
            isElementVisible(byLocator(essentialsPopUp),"PassengerPopUp",10);
            String passengerActualText=getVisibleText(passengerSelectionHeadre);
            verifyText(passengerActualText,"Passengers");
            List<WebElement> elements=getAllElements(passemgerCount);
            verifySize(elements,4,"PassengerCountCheck");
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
        for (String particularEssential : list) {
            if (particularEssential.toLowerCase().contains("seat")) {
                waitForPageLoad(3000);
                allocateSeatsForAll(seatMapper);
                clickDone();
                waitForPageLoad(1000);
                waitForElementPresent(preferredSeatSelectedValidation,3000);
                String seatSelectedActual=getVisibleText(preferredSeatSelectedValidation);
                verifyContainsText(seatSelectedActual,"Preferred seat selected");
            } else if (particularEssential.toLowerCase().contains("bundle")) {
                waitForPageLoad(3000);
                selectBundle();
            }else if (particularEssential.toLowerCase().contains("baggage")) {
                waitForPageLoad(3000);
                selectBaggage(addAdult,particularEssential.split("kg")[0]);
                waitForElementPresent(baggageSelectedValidation,3000);
                String baggageSelectedActual=getVisibleText(baggageSelectedValidation);
                verifyContainsText(baggageSelectedActual,"Baggage booked");
            } else if (particularEssential.toLowerCase().contains("meal")) {
                waitForPageLoad(3000);
                String[] mealItemName=particularEssential.split(" ");
                if(particularEssential.contains("Child")){
                    waitForPageLoad(3000);
                    selectMeals("Child 1",mealItemName[0].trim());
                }else {
                    waitForPageLoad(3000);
                    selectMeals("Adult",mealItemName[0].trim());
                }
                waitForElementPresent(mealSelectedValidation,4000);
                String mealSelectedActual=getVisibleText(mealSelectedValidation);
                verifyContainsText(mealSelectedActual,"Meals added");
            }
        }
    }

    //selects seat in the specified category
    public void allocateSeatsForAll(String seatMapper) throws Throwable {
        waitForElementToBeClickable(slctSeatBtn,40);
        click(slctSeatBtn);
        isElementVisible(byLocator(essentialsPopUp),"SeatSelectionPopUpCheck",10);
        waitForElementToBeClickable(reserveSeatsForEveryone);
        passCount=getAllElements(totalNoOfPassengers);
        click(reserveSeatsForEveryone);
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
                                String seatButton=String.format(seatbtn,j+""+column.getText().trim());
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
                                String seatButton=String.format(seatbtn,j+""+column.getText().trim());
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
                    for (int i = 1; i <= noOfPassengers; i++) {
                        selectAvailableSeat(regularSeatGenericLocator);
                    }
            }
        }
    }

    private void selectAvailableSeat(String locator) throws Throwable {
        for (WebElement ele:getAllElements(locator)){
            try{
                scrollElementIntoView(ele);
                if(click(ele)){
                    exitSeatPopUpAccept();
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
        for (String particularExtra : list) {
            if (particularExtra.toLowerCase().contains("priority")) {
                waitForPageLoad(3000);
                addPriorityPackage();
                waitForElementPresent(selectedPriorityValidation,4000);
                String prioritySelectedActual=getVisibleText(selectedPriorityValidation);
                verifyContainsText(prioritySelectedActual,"Priority Package selected");
            } else if (particularExtra.toLowerCase().contains("sport")) {
                String requiredItemName=particularExtra.split(" ")[0].trim();
                waitForPageLoad(3000);
                outboundSportsEquipment("Adult 1",requiredItemName);
                clickDone();
                waitForElementPresent(selectedSportValidation,4000);
                String sportSelectedActual=getVisibleText(selectedSportValidation);
                verifyContainsText(sportSelectedActual,"Sports equipment selected");
            }
        }
    }
   

    protected void exitSeatPopUpAccept() throws Throwable {
        if(waitForElementPresent(exitSeatWarningMsg,1)){
            click(exitSeatWarningContinueLnk);
        }
    }

    public void roundTrip(String seasonCriteria) throws Throwable {

        if(waitForElementPresentE(roundTrip)){
            isElementVisible(byLocator(departureCalenderPopUp),"DepartureCalenderPopUp",10);
            List<WebElement> elements=getAllElements(flyTypes);
            verifySize(elements,3,"flyTypes");
            if (getVisibleText(calHeader).contains("Select day of departure")) {
                String departureFlightActual=getVisibleText(calHeader);
                verifyText(departureFlightActual,"Select day of departure");
                seasonSelection(seasonCriteria);
            }

            if(getVisibleText(calHeader).contains("Select return flight")) {
                String returnFlightActual=getVisibleText(calHeader);
                verifyText(returnFlightActual,"Select return flight");
                seasonSelection(seasonCriteria);
            }
        }else {
            logger.log(LogStatus.FAIL, "Unable to select round trip ",logger.addScreenCapture(capture("RoundTripError")));
        }
    }


    public void oneWayTrip(String seasonCriteria ) throws Throwable {
        if (waitForElementPresentE(calHeader)) {
            if (getVisibleText(calHeader).contains("Select day of departure")) {
                String departureFlightActual=getVisibleText(calHeader);
                verifyText(departureFlightActual,"Select day of departure");
                waitForElementToBeClickable(oneWayTripBtn);
                click(oneWayTripBtn);
                seasonSelection(seasonCriteria);
            }

        }else {
            logger.log(LogStatus.FAIL, "Unable to select one ward journey ",logger.addScreenCapture(capture("OneWardjourneyError")));
        }
    }

    /**
     * Logic for Journey date selection
     */


    public void datesSelection() throws Throwable {

        List<WebElement> datarows=null;
        waitForPageLoad(5000);
        datarows=getAllElements(flightsResult_US);

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // today date.
        c.add(Calendar.DATE, 3); // Adding 3 days
        String output = sdf.format(c.getTime());
        int actual= Integer.parseInt(output);

        boolean flag= false;

        if(datarows.size()>3){
            int count=1;
            for (WebElement ele:datarows){
                if(count==4){
                    String elementText=ele.getText();
                    ele.click();
                    logger.log(LogStatus.PASS,"Selected round trip with DOJ:" +elementText);
                    break;
                }
                count++;
            }

        }else{

            waitForElementPresent(calNextBtn);
            click(calNextBtn);
            waitForPageLoad(3000);
            System.out.println("no flights=========="+isElementPresent(noFLightsAvailable));
           // Assert.assertTrue(isElementPresent(noFLightsAvailable));
            datarows=getAllElements(flightsResult_US);

            for(int rownum=0;rownum<datarows.size();rownum++){

                WebElement element=datarows.get(rownum);
                String elementText=element.getText();
                click(element);
                logger.log(LogStatus.PASS,"Selected round trip with DOJ:" +elementText);
                break;

            }

        }

    }

    public void seasonSelection(String monthAndYear) throws Throwable {

        String[] mAndY=monthAndYear.split(",");
        //SimpleDateFormat format1 = new SimpleDateFormat("MM/yy");
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");

        Date startDate = format.parse(mAndY[0]);

        Date endDate = format.parse(mAndY[1]);

        List<Date> dates = getDaysBetweenDates(startDate, endDate);

        List<String> dateStrings = new ArrayList<String>();

        for(Date date: dates){
            dateStrings.add(format.format(date));
        }

        String monthAndYearfromApp = getVisibleText(monthYearTxt).trim();

        boolean flag=false;

        for (String dateStringToClick:dateStrings) {
            if(monthAndYearfromApp.equalsIgnoreCase(dateStringToClick)){
                datesSelection();
                flag=true;
                break;
            }
        }
        if(!flag){//if flag false
            //String endYear[]=null;
            String endYear[]=mAndY[1].split(" ");

            String monthStr=endYear[0].trim();
            String yearStr=endYear[1].trim();
            String newYearStr = null;

            while(!yearStr.equalsIgnoreCase(newYearStr)){
                click(calNextBtn);
                System.out.println("no flights=========="+isElementPresent(noFLightsAvailable));
                //Assert.assertTrue(isElementPresent(noFLightsAvailable));
                String newMonthAndYearfromApp = getVisibleText(monthYearTxt).trim();
                String[] newMonthAndYearTokens=newMonthAndYearfromApp.split(" ");
                newYearStr=newMonthAndYearTokens[1].trim();
            }
            datesSelection();
        }
    }

    public static List<Date> getDaysBetweenDates(Date startMonth, Date endMonth)
    {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startMonth);

        while (calendar.getTime().before(endMonth) || calendar.getTime().equals(endMonth))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.MONTH, 1);
        }

        return dates;
    }

    protected void enterPassengerDetails(String passengerList,String a1Salutation,String a1FirstName,String a1SurName,String a1Dob,String a2Salutation,String a2FirstName,String a2SurName,String a2Dob,String a3Salutation,String a3FirstName,String a3SurName,String a3Dob,String a4Salutation,String a4FirstName,String a4SurName,String a4Dob,String a5Salutation,String a5FirstName,String a5SurName,String a5Dob,String c1Gender,String c1FirstName,String c1SurName,String c1Dob,String c2Gender,String c2FirstName,String c2SurName,String c2Dob,String c3Gender,String c3FirstName,String c3SurName,String c3Dob,String y1Gender,String y1FirstName,String y1SurName,String y1Dob,String y2Gender,String y2FirstName,String y2SurName,String y2Dob,String y3Gender,String y3FirstName,String y3SurName,String y3Dob,String i1Gender,String i1FirstName,String i1SurName,String i1Dob,String i2Gender,String i2FirstName,String i2SurName, String i2Dob) throws Throwable {
        providedPassengerdetails.clear();
        int passCumulativeCounter=0;
        ArrayList<String[]> passData=new  ArrayList<String[]>();
        String[] adult1={a1Salutation,a1FirstName,a1SurName,a1Dob};
        String[] adult2={a2Salutation,a2FirstName,a2SurName,a2Dob};
        String[] adult3={a3Salutation,a3FirstName,a3SurName,a3Dob};
        String[] adult4={a4Salutation,a4FirstName,a4SurName,a4Dob};
        String[] adult5={a5Salutation,a5FirstName,a5SurName,a5Dob};
        String[] youngAdult1={y1Gender,y1FirstName,y1SurName,y1Dob};
        String[] youngAdult2={y2Gender,y2FirstName,y2SurName,y2Dob};
        String[] youngAdult3={y3Gender,y3FirstName,y3SurName,y3Dob};
        String[] child1={c1Gender,c1FirstName,c1SurName,c1Dob};
        String[] child2={c2Gender,c2FirstName,c2SurName,c2Dob};
        String[] child3={c3Gender,c3FirstName,c3SurName,c3Dob};
        String[] infant1={i1Gender,i1FirstName,i1SurName,i1Dob};
        String[] infant2={i2Gender, i2FirstName, i2SurName,  i2Dob};
        //feedind passenger details to arraylist inorder
        if(!a1Salutation.equals("")){
            passData.add(adult1);
        }
        if(!a2Salutation.equals("")){
            passData.add(adult2);
        }
        if(!a3Salutation.equals("")){
            passData.add(adult3);
        }
        if(!a4Salutation.equals("")){
            passData.add(adult4);
        }
        if(!a5Salutation.equals("")){
            passData.add(adult5);
        }
        if(!y1Gender.equals("")){
            passData.add(youngAdult1);
        }
        if(!y2Gender.equals("")){
            passData.add(youngAdult2);
        }
        if(!y3Gender.equals("")){
            passData.add(youngAdult3);
        }
        if(!c1Gender.equals("")){
            passData.add(child1);
        }
        if(!c2Gender.equals("")){
            passData.add(child2);
        }
        if(!c3Gender.equals("")){
            passData.add(child3);
        }
        if(!i1Gender.equals("")){
            passData.add(infant1);
        }
        if(!i2Gender.equals("")){
            passData.add(infant2);
        }

        String[] str=passengerList.split(",");
        for (String passenger:str) {
            String[] pass=passenger.split(" ");
            if(pass[1].contains("Adults")) {
                int noOfAdults=Integer.parseInt(pass[0]);
                passCumulativeCounter=noOfAdults+passCumulativeCounter;
                for (int i = 1; i <=noOfAdults ; i++) {
                    passDetails(passData.get(i-1),i);
                }
            }
            else if(pass[1].contains("Youth")) {
                int noOfYouth=Integer.parseInt(pass[0]);
                int youthPassengerStart=passCumulativeCounter;
                passCumulativeCounter=noOfYouth+passCumulativeCounter;
                for ( int i=youthPassengerStart+1; i <=passCumulativeCounter ; i++) {
                    youngOrChildOrInfantDetails(passData.get(i-1),i);
                }
            }
            else if(pass[1].contains("Child")) {
                int noOfChild=Integer.parseInt(pass[0]);
                int childPassengerStart=passCumulativeCounter;
                passCumulativeCounter=noOfChild+passCumulativeCounter;
                for ( int i=childPassengerStart+1; i <=passCumulativeCounter ; i++) {
                    youngOrChildOrInfantDetails(passData.get(i-1),i);
                }
            }
            else if(pass[1].contains("Infant")) {
                int noOfInfant=Integer.parseInt(pass[0]);
                int infantPassengerStart=passCumulativeCounter;
                passCumulativeCounter=noOfInfant+passCumulativeCounter;
                for ( int i=infantPassengerStart+1; i <=passCumulativeCounter ; i++) {
                    youngOrChildOrInfantDetails(passData.get(i-1),i);
                }
            }
        }
    }
}
