package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_ContactDetails {

    String salutationRadioBtn="css=#customerAddress_salutation%s+span";
    String firstNameTxtBox="id=customerAddress_firstName";
    String surNameTxtBox="id=customerAddress_lastName";
    String address1TxtBox="id=customerAddress_address1";
    String address2TxtBox="id=customerAddress_address2";
    String postTxtBox="id=customerAddress_postalCode";
    String cityTxtBox="id=customerAddress_city";
    String countryTxtBox="id=customerAddress_country";
    String countryDropDown="id=country-list";
//    String phnNumTxtBox="id=phoneNumber";//old
    String phnNumTxtBox="id=customerAddress_phoneNumber";
    String homePhnNumTxtBox="id=customerAddress_homePhoneNumber";
    String emailTxtBox="id=customerAddress_emailAddress";
//    String promoOfferDecChkBox="css=.checkbox-label.ng-binding";//old
    String promoOfferDecChkBox="css=label.checkbox";
    String contBtn="css=.btn.btn--plane.button--block.ng-binding";
    String minContactRdioBtn="css=#btngroup_adult_contact_0+span";//0 can be parametrised
    String continueBtn_pass="css=[translate='common.continue']";

}
