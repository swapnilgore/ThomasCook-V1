package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_PassengerDetails {

    String d_MaleRadioBtn="id=pax%s_genderMale";//remove it and modify to genderRadioBtn later in all reusables
    String genderRadioBtn="id=pax%s_gender";
    String genderLocator="%s";//to be used with genderRadioBtn

    String d_SalutationRadioBtn_MR="css=[for='pax%s_salutation";
    String salutationLocator="%s']";//to be used with d_SalutationRadioBtn_MR locator
    String d_FirstNameTxtBox="id=pax%s_firstName";
    String d_MiddleNameTxtBox="id=pax%s_middleName";
    String d_SurNameTxtBox="id=pax%s_lastName";
    String d_DOBTxtBox="id=pax%s_birthday";
    String passengerDetailsSection="css=.pax-v2.accordion";

    String passengerText = "xpath=//div[contains(@class,'heading')]//h2";

}
