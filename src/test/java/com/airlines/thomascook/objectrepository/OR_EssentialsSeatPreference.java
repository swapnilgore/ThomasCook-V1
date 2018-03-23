package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_EssentialsSeatPreference {
    String slctSeatBtn="css=tca-teaser[show-early-bird$='SEAT'] a[class^='btn ']";
    String edtSeatBtn="css=tca-teaser[show-early-bird$='SEAT'] a[aria-label='Edit selection']";
    String adult1="css=[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(1)";
    String adult2="css=[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(2)";
    String youth1="css=[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(3)";
    String seatSelectionSkipBtn="css=[aria-label='Skip']";
    String seatSelcDoneBtn="css=.col-xs-10>.btn.ng-scope";
    String seatSelcCancelBtn="css=.col-xs-10 .btn-text";
    String resSeatsForEvryOneBtn="classname=list-item__extra.ng-binding.ng-scope";
    String availableSeats="xpath=//span[contains(@class,'seat-v2--price') or contains(@class,'seat-v2--standard')]";
    String totalNoOfPassengers="css=[ng-show='flight.isSsrAvailableForSegment']>li";
    String seatbtn="css=td[aria-label$='seat %s'][tabindex='0']";
    String seatbtn_DE="css=td[aria-label$='Sitz 8A'][tabindex='0']";
    String xLSeatbtn=" span[class*='size--xl']";
    String infantSeatbtn=" span[class$='baby']";
    String infantSeatbtnLocator="css=span[class$='baby']";
    String xLSeatGenericLocator="xpath=//span[contains(@class,'seat-v2--price')]/..";
    String infantSeatGenericLocator="xpath=//span[contains(@class,'seat-v2--baby')]/..";
    String regularSeatGenericLocator="xpath=//span[@class='seat-v2--standard']/..";
    String xLSeatWarning="css=.cst-extras-message__content>p";
    String xLContinueLnk="xpath=//a[text()='Choose seat and continue']";
    String xLContinueLnk_DE="xpath=//a[text()='Sitz auswählen und fortfahren']";
    String xLChooseDiffSeatLnk="xpath=//a[text()='Choose a different seat']";
    String seatTaken="css=[tabindex='-1']";
    String columns="css=[class='seats-v2__table--row-id seats__table--small']>th";
    String rows="className=aisle";
    String reserveSeatsForEveryone = "xpath=//div[contains(text(),'Reserve seats for everyone')]";
    String reserveSeatsForEveryone_DE = "xpath=//div[contains(text(),'Sitzplatz wählen')]";
    String exitSeatWarningMsg = "xpath=//p[starts-with(text(),'IMPORTANT: Our exit ')]";
    String exitSeatWarningContinueLnk = "xpath=//a[text()='Choose seat and continue']";
    String exitSeatWarningChoseDiffSeatLnk = "xpath=//a[text()='Choose a different seat']";

    String essentialsPopUp = "css=.modal-page.page-on-center.sessioncammonitorscroll";
    String preferredSeatSelectedValidation = "xpath=//tca-teaser[contains(@message-prefix,'seats')]//div[contains(@class,'h2 ng-binding')]";
    String baggageSelectedValidation = "xpath=//tca-teaser[contains(@message-prefix,'baggage')]//div[contains(@class,'h2 ng-binding')]";
    String mealSelectedValidation = "xpath=//tca-teaser[contains(@message-prefix,'meal')]//div[contains(@class,'h2 ng-binding')]";
}
