package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_EssentialsBaggagePreference {
    String bookBaggage = "css=tca-teaser[show-early-bird$='BAGGAGE'] a[class^='btn ']";
    String addAdult = "xpath=//div[contains(text(),'Adult')]/parent::div/../div[contains(.,'Add')]";
    String addAdult_DE = "xpath=//div[contains(text(),'Erwachsener')]/parent::div/../div[contains(.,'Buchen')]";
    String selectBaggage = "xpath=//a[@aria-label='Up to %s kg']";
    String selectBaggage_DE = "xpath=//a[@aria-label='Bis zu %s kg']";

    String baggageText = "id=MODAL_TITLE_BAGGAGE";
}
