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
public class Reusables_TCA_UK extends CommonReusables implements OR_HomePage,OR_PassengerDetails,OR_ContactDetails,OR_PaymentDetails,OR_FlightDetails,OR_EssentialsBaggagePreference,OR_ExtrasSportsEqpAndCargo,OR_BookingConfPage,OR_EssentialsSeatPreference,OR_AddPriorityPackage,OR_EssentialsMealsPreference,OR_EssentialsBundle,OR_FlexibleFlying {

    int totalPassengers = 0;
    List<WebElement> passCount = null;
    ArrayList<String> equipments = new ArrayList<String>();
    ArrayList<String> providedPassengerdetails = new ArrayList<String>();
    int baggageMethodCount = 0;
    int mealsMethodCount = 0;


}
