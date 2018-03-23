package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_BookingConfPage {
    String thanksHeadre="css=.image--caption h2";
    String bookingNum="css=[class*='summary-overlap'] div:nth-child(1)>h2";
    String bookingTotal="css=[class*='summary-overlap'] div:nth-child(2)>h2";
    String passengers="css=h3[class*='avatar--summary']";

    String includedSevices="xpath=//span[text()=' Included services ']/../../../following-sibling::div/descendant::span[@class='ng-binding ng-scope']";
    String includedSevicesHeader="xpath=//span[text()=' Included services ']";


    String essentilas="xpath=//span[text()=' Essentials ']/../../../following-sibling::div/descendant::span[@class='ng-binding ng-scope']";
    String essentilasHeader="xpath=//span[text()=' Essentials ']";


    String moreExtras="xpath=//span[text()=' More extras ']/../../../following-sibling::div/descendant::span[@class='ng-binding ng-scope']";
    String moreExtrasHeader="xpath=//span[text()=' More extras ']";


    String itineraryHeader="css=confirmation-itinerary h2";
    String itineraryDetails="css=confirmation-flight-segment>div";
    String outBoundFlightDoj="css=.text-center.h3.ng-binding";
    String outBoundFlightFrom="css=div[class$='pull-left']>.h3";
    String outBoundFlightTo="css=div[class$='pull-right']>.h3";
    String outBoundFlightName="css=.flight-spec-v3-flight__airline";
    //https://www-stage.thomascookairlines.com/tca/tcauk/en/flight/confirmation
}
