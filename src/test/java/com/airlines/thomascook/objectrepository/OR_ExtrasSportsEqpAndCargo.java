package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_ExtrasSportsEqpAndCargo {

    String bookSportsEqpBtn="xpath=//a[text()=' Book sports equipment ']";
    String bookSportsEqpBtn_DE="xpath=//a[text()=' Sportgepäck buchen ']";
    String outboundSportsEqiAddBtn="xpath=(//h3[contains(.,'Outbound flight')])[2]/ancestor::div[@ng-repeat='flight in vm.summary']/descendant::div[contains(text(),'%s ')]/../following-sibling::div[text()=' Add ']";//Adult 1
    String inboundSportsEqiAddBtn="xpath=(//h3[contains(.,'Inbound flight')])[2]/ancestor::div[@ng-repeat='flight in vm.summary']/descendant::div[contains(text(),'%s ')]/../following-sibling::div[text()=' Add ']";//Child 1
    String sportsEqpBtn="css=a[aria-label^='%s']";//Cricket
    String sameDetailsForReturnFlightChkBox="css=.ng-scope.summary-leg label>input";
    String availableSportsItemsList="css=tca-sports-gear a[class='btn btn--warm-grey ng-binding']";


    String selectpetsBtn_DE="xpath=//a[text()=' Tierbeförderung buchen ']";
    String selectSportsBtn_DE="xpath=//a[text()=' Sportgepäck buchen ']";
    String passengersList_Pets="css=li[class='list-item list-item-avatar ng-scope']";
    String petAddBtn="css=div[class^='list-item__extra']";//to be used with passengersList_Pets
    String selectAnimalBtn="xpath=//div[text()='%s']/..";
    String weightTxt="id=weight";
    String inCabinBtn="css=[aria-label='In der Kabine']";
    String cabin55CmBtn="css=[aria-label='55cm Breite']";
    String cabin75CmBtn="css=[aria-label='75cm Breite']";
    String doneBtn_DE="xpath=//a[contains(text(),'Übernehmen')]";
    String addSportsItemBtn_DE="css=div[class^='list-item__extra']";

    String selectedSportValidation ="xpath=//div[contains(@ng-if,'sportEquipmentAvailable ')]//div[contains(@class,'h2 ng-binding')]";


}
