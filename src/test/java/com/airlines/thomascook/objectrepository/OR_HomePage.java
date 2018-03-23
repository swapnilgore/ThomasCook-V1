package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_HomePage {

    String depMenu = "css=div[translate='common.from']";
    String depTextBox = "id=airportinput_id_origin";
    String depDropDwn = "css=#airportSelectOutOne-airportsList div>h3";
    String arrvlTextBox = "id=airportinput_id_destination";
    String arrvlDropDwn = "css=#airportSelectInOne-airportsList div>h3";
    String oneWayTripBtn = "css=.calendar-options li:nth-child(2)";
    String roundTrip = "css=.btn.btn--warm-grey.ng-scope.active";
    String calHeader="id=MODAL_TITLE_DATES";
    String deparAirPorttHeader="css=[name='ORIGIN'] #MODAL_TITLE_AIRPORT";
    String destiAirPorttHeader="css=[name='DESTINATION'] #MODAL_TITLE_AIRPORT";

    //String dOJBtn="css=button[aria-label^='%s %s available']";//first s day second s month
    String dOJBtn = "css=button[aria-label^='22 September available']";//first s day second s month
    String calNextBtn = "css=[aria-label='next month']";
    String calNextBtn_DE = "css=[aria-label='Nächster Monat']";
    String calPrvtBtn = "css=[aria-label='previous month']";
    String adultPlusBtn = "css=.list.clearfix li:nth-child(1) .icon-plus";
    String youngPlusBtn = "css=.list.clearfix li:nth-child(2) .icon-plus";
    String childPlusBtn = "css=.list.clearfix li:nth-child(3) .icon-plus";
    String infantPlusBtn = "css=.list.clearfix li:nth-child(4) .icon-plus";
    String adultMinusBtn = "css=.list.clearfix li:nth-child(1) .icon-minus";
    String youngMinusBtn = "css=.list.clearfix li:nth-child(2) .icon-minus";
    String childMinusBtn = "css=.list.clearfix li:nth-child(3) .icon-minus";
    String infantMinusBtn = "css=.list.clearfix li:nth-child(4) .icon-minus";
    String passContBtn = "css=.btn.btn--plane";
    String searchBtn = "id=search";

    String departuremonthText = "css=.btn.btn-default.btn-sm.uib-title";
//    String selectday = "xpath=//button[contains(@id,'datepicker')]/span[1][contains(text(),'%s')]";
    String selectDayPart1 = "xpath=//button[starts-with(@aria-label,'%s";
    String selectDayPart2 = " %s available from')]";
    String selectDayPart2_DE = " %s verfügbar ab')]";
    String defaultAdults = "css=.list.clearfix>li:nth-child(1) [class='list-item-passenger__number']";
    String defaultYoungAdults = "css=.list.clearfix>li:nth-child(2) [class='list-item-passenger__number']";
    String defaultChildren = "css=.list.clearfix>li:nth-child(3) [class='list-item-passenger__number']";
    String defaultInfants = "css=.list.clearfix>li:nth-child(4) [class='list-item-passenger__number']";

    String selectEconomyclass ="className=fare-v3";
    String continueBtn_Gen= "css=.btn.btn--large";
    String selectYourSeat ="css=[aria-label='Book seats from just']";

    String noPass = "css=.list-item.list-item-avatar.ng-scope";
    String selectStandardSeat1 = "xpath=//td[1]/span[@class='seat-v2--standard']";
    String selectStandardSeat2 = "xpath=//td[2]/span[@class='seat-v2--standard']";
    String apply ="xpath=//a[contains(text(),'Apply')]";
    String done ="xpath=//a[contains(text(),'Done')]";
    String done_DE ="xpath=//a[contains(text(),'Übernehmen')]";
    String iconSippner="className=icon-animated-spinner";
    String popUpWindow="css=.overlay__inner.-dropshadow";
    String popUpContinueBtn="css=.btn-text";
    String popUpResSeatsBtn="css=.btn.cst-extras-session-button";
    String passengerSelectionHeadre="id=MODAL_TITLE_PASSENGER";

    String flightsResult_DE="xpath=//span[contains(@class,'price ') and contains(text(),',')]/preceding-sibling::span[@class='ng-binding']";
    String flightsResult_US="xpath=//span[contains(@class,'price ') and contains(text(),'.')]/preceding-sibling::span[not(contains(@class,'text-muted'))]";
    String monthYearTxt="xpath=//table[@class='uib-daypicker']//th[2]//button/strong";
    String noFLightsAvailable="xpath=//h2[@class='-white ng-binding']";

    String flightSeclectionPopup = "id=airportinput_id_origin";
    String departureCalenderPopUp = "xpath=//div[contains(@class,'modal-page page-on-center')]";
    String flyTypes = "xpath=//ul[contains(@class,'calendar-options')]//li";
    String passemgerCount = "xpath=//li[@class='list-item list-item-passenger ng-scope']//div[contains(@class,'list-item-passenger__title')]";

}