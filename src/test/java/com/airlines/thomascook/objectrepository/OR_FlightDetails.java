package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_FlightDetails {

//    String availableFlightsList="css=vacancy-flight[class='ng-isolate-scope']";
    String fltEcnmyBtn="className=fare-v3";
    String cntBtn="className=btn.btn--large";
    String outBoundCls="xpath=//h2[text()=' Outbound flight ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='%s']/ancestor::li[@class='fare-wrap-v3 list-unstyled']/descendant::div[@class='fare-v3__select']";//Economy Class Premium Class
    String outBoundCls_DE="xpath=//h2[text()=' Hinflug ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='%s']/ancestor::li[@class='fare-wrap-v3 list-unstyled ng-scope']/descendant::div[@class='fare-v3__select']";//Economy Class Premium Class
    String inBoundCls="xpath=//h2[text()=' Inbound flight ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='%s']/ancestor::li[@class='fare-wrap-v3 list-unstyled ng-scope']/descendant::div[@class='fare-v3__select']";
    String inBoundCls_DE="xpath=//h2[text()=' Rückflug ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='%s']/ancestor::li[@class='fare-wrap-v3 list-unstyled ng-scope']/descendant::div[@class='fare-v3__select']";
    String handLuggageText="css=.fare-v3__includes__weight.ng-binding";
    String availableFlightsList="css=div[class='flight-panel-v3 -bg-white -dropshadow']";
    String flightName="css=span[class^='flight-spec-v3-flight__airline']";
    String flightStartTimeTxt="css=div[class^='flight-spec-v3-time__time h2']";
    String flightReachTimeTxt="css=div[class^='flight-spec-v3-time__time pull']";
    //String flightClassBtn="xpath=//h3[text()='%s']";//Economy Class or Premium Class or Economy Light
    String flightEconomyClassBtn="css=#flightTariffElement-0+a";
    String flightPremiumClassBtn="css=#flightTariffElement-1+a";
    String includedHandLuggageWeightTxt="css=i[class^='icon-hand-luggage']>span";//gives new line ex 6\nkg
    String includedLuggageWeightTxt="css=i[class^='icon-luggage']>span";//gives new line ex 6\nkg

    ////h2[text()=' Inbound flight ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='Premium Class']/ancestor::li[@class='fare-wrap-v3 list-unstyled ng-scope']/descendant::div[@class='fare-v3__select']
    String calenderSlider = "xpath=//div[@id='date-slider-v3_0']";

}
