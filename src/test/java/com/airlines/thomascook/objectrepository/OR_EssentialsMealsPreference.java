package com.airlines.thomascook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_EssentialsMealsPreference {

    String slctMealBtn="css=tca-teaser[show-early-bird$='MEAL'] a[class^='btn ']";
    String mealItemBtn="xpath=//a[contains(text(),'%s') and @class='btn btn--warm-grey ng-binding']";//standard menu
    String passengersList_Meal="css=li[class='list-item list-item-avatar ng-scope']";
    String mealChangeBtn="css=div[class^='list-item__extra']";//to be used with passengersList_Meal
    String passengerNameTxt="css=div[class^='list-item__title']";//to be used with passengersList_Meal
}
