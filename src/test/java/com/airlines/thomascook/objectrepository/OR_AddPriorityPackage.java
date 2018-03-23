package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 12-06-2017.
 */
public interface OR_AddPriorityPackage {
    String addPriorityPackageBtn = "css=tca-teaser[show-early-bird$='PRIORITY_PACKAGE'] a[class^='btn ']";
    String checkPass = "css=.checkbox";

    String priorityPackagePopUp = "css=.modal-page.page-on-center.sessioncammonitorscroll";
    String selectedPriorityValidation = "xpath=//tca-teaser[contains(@message-prefix,'priorityPackage')]//div[contains(@class,'h2 ng-binding')]";
}
