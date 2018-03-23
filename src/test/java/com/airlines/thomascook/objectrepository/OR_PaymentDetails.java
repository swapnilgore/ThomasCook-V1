package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_PaymentDetails {

    String creditCardRadioBtn="css=[for='credit_card']";
    String debitCardRadioBtn="css=[for='debit_card']";
    String cardTypeBtn="xpath=//h6[text()='%s']/..";
    String cardHolderNameTxtBox="css=[id*='CardHolderName']";
    String cardNumberTxtBox="id=cccard";
    String monthDropDwnBtn="css=[title='Month']";
    String monthDropDwnBtn_DE="css=[title='Monat']";
    String monthLink="xpath=//span[text()='%s']/../..";
//    String monthTest="id=ccmonth";
//    String yearTest="id=ccyear";
    //String monthLink="css=ul[class='dropdown-menu inner selectpicker']>li:nth-child(2)>a";
    String yearDropDwnBtn="css=[title='Year']";
    String yearDropDwnBtn_DE="css=[title='Jahr']";
    String yearLink="xpath=//span[text()='%s']/../..";
    //String yearLink="xpath='//span[text()='%s']/../..'";
    String cVVTxtBox="id=cccvccvv";
    String acceptTCChkBox="xpath=//input[@id='termsAndConditions']/..";
    String payByBtn="css=a[aria-label='payment.paymentButtonLabel']";
    String continueBtn="className=btn.btn--plane.ng-binding";
    String iframe="css=iframe[class='ng-binding']";

    String bookingconfirmMessage = "xpath=//h2[contains(text(),'Thank you for your booking')]";
    String bookingconfirmMessage_DE = "xpath=//h2[contains(text(),'Vielen Dank für Ihre Buchung')]";
    String bookingOverview = "xpath=//*[contains(text(),'Overview of your booking')]";
    String pwdField = "css=.ncolinput>input";
    String goBtn = "id=btn_Continue";
    String backBtn = "id=btn_Back";
    String paymentSection = "id=paymentContent";

}
