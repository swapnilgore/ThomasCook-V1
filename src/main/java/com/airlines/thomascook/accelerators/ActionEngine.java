
package com.airlines.thomascook.accelerators;

import java.util.ArrayList;
import java.util.List;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionEngine extends TestEngine {

    private static Integer pauseS = 15;

    /**
     * Binding to get Xpath, CSS, Link, Partial link element
     *
     * @param locator locator of element in xpath=locator; css=locator etc
     * @return found WebElement
     */
    protected WebElement getElement(final String locator) {
        return getElement(locator, true);
    }


    /**
     * Get "By" object to locate element
     *
     * @param locator locator of element in xpath=locator; css=locator etc
     * @return by object
     */
    protected By byLocator(final String locator) {
        String prefix = locator.substring(0, locator.indexOf('='));
        String suffix = locator.substring(locator.indexOf('=') + 1);

        switch (prefix) {
            case "xpath":
                return By.xpath(suffix);
            case "css":
                return By.cssSelector(suffix);
            case "link":
                return By.linkText(suffix);
            case "partLink":
                return By.partialLinkText(suffix);
            case "id":
                return By.id(suffix);
            case "name":
                return By.name(suffix);
            case "tag":
                return By.tagName(suffix);
            case "className":
                return By.className(suffix);
            default:
                return null;
        }
    }

    /**
     * @param locator          locator of element in xpath=locator; css=locator etc
     * @param screenShotOnFail make screenshot on failed attempt
     * @return found WebElement
     */
    private WebElement getElement(final String locator, boolean screenShotOnFail) {
        try {
            return Driver.findElement(byLocator(locator));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param locator
     * @param secs
     * @return
     * @throws Throwable
     */
    protected boolean waitForElementPresent(String locator, int secs) throws Throwable {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, secs);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));
            status=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return status;
        }

        return status;
    }

    protected boolean waitForElementPresentIfNotThrowException(String locator, int secs) throws Throwable {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, secs);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));
            status=true;
        }
        catch (Exception e) {
           throw e;
        }

        return status;
    }

    protected boolean waitForElementToBeClickable(String locator, int secs) throws Throwable {

        boolean flag=false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, secs);
            wait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
            return flag=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return flag;
        }

    }

    protected boolean waitForElementToBeClickable(String locator) throws Throwable {
        boolean flag=false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, pauseS);
            wait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
            return flag=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
    }

    protected boolean waitForElementToBeClickable(WebElement element,int secs) throws Throwable {
        boolean flag=false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, secs);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return flag=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
    }

    protected boolean waitForElementToBeClickable(WebElement element) throws Throwable {
        boolean flag=false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, pauseS);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return flag=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
    }



    /**
     * Wait until element is invisible/not present on the page
     *
     * @param locator locator to element
     * @param timeOut time to wait
     */
    protected void waitForElementNotPresent(final String locator, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, timeOut);
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(byLocator(locator))));
        } catch (Exception e) {
            if (e.getCause() != null &&
                    !e.getCause().getClass().toString().contains("NoSuchElementException")) {
                //takeScreenshot();
                throw e;
            }
        }
    }


    /**
     * Soft wait for visibility of element with default timeout
     *
     * @param locator locator of element to wait for
     * @return true if element is present and visible / false otherwise
     */
    protected boolean waitForElementPresent(final String locator) throws Throwable {
        return waitForElementPresent(locator, pauseS);
    }

    protected boolean waitForElementPresentE(final String locator) throws Throwable {
        return waitForElementPresentIfNotThrowException(locator, pauseS);
    }

    protected boolean waitForElementPresentE(final String locator,int time) throws Throwable {
        return waitForElementPresentIfNotThrowException(locator, time);
    }

    /**
     * Wait until element is invisible/not present on the page with default timeout
     *
     * @param locator locator to element
     */
    protected void waitForElementNotPresent(final String locator) {
        waitForElementNotPresent(locator, pauseS);
    }


    /**
     * Wait for invisibility of specific object on page
     *
     * @param locator of object that we wait for invisibility
     */
    protected void waitForInvisibility(final String locator) {

        WebDriverWait wait = new WebDriverWait(Driver, pauseS);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator(locator)));
        } catch (Exception e) {
            //log.info("Try to wait little more (wait for invisibility)");
        }
    }

    /**
     * Verifies whether element is present and displayed
     *
     * @param locator locator for element to verify
     * @return true if present; false otherwise
     */
    protected boolean isElementPresent(final String locator) {
        try {
            return isElementPresent(getElement(locator, false));
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Binding to click the webElement
     *
     * @param we webElement to click
     */
    protected boolean click(final WebElement we) {
        boolean flag=false;
        try {
            we.click();
            flag=true;
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
    }



    /**
     * Binding to click Xpath, CSS, Link, Partial link element
     *
     * @param locator locator of the element in format xpath=locator; css=locator  etc
     */
    protected boolean click(final String locator) {

        try{
            return click(getElement(locator));
        }catch (StaleElementReferenceException e){
            return click(getElement(locator));
        }
    }

    /**

     * Verifies whether element is displayed

     *

     * @param we webelement to verify

     * @return true if present; false otherwise

     */

    protected boolean isElementPresent(final WebElement we) {

        try {

            return we.isDisplayed();

        } catch (Exception e) {

            return false;

        }

    }

    protected void type( final WebElement we,String testdata) throws Throwable {

        try {
            //((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", we);
            we.sendKeys(testdata);
        } catch (Exception e) {
            throw e;
        }
    }

    protected void type( String locatorName,String testdata) throws Throwable {

        type(getElement(locatorName),testdata);
    }
    protected void type( String locatorName,String testdata, boolean clear, boolean keyClear) throws Throwable {

        typeKeys(getElement(locatorName),testdata,clear,keyClear);
    }

    /**
     *
     * @param locatorName: webelement having multiple elements
     * @return list of webelements
     * @throws Throwable
     */
    protected List<WebElement> getAllElements(String locatorName) throws Throwable {

       // WebDriverWait wait = new WebDriverWait(Driver, 100);
        List<WebElement> elements = new ArrayList<WebElement>();
        try {
           // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator(locatorName)));
            elements = Driver.findElements(byLocator(locatorName));
            return elements;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }


    /**
     * Binding to check Checkbox
     *
     * @param we webElement of checkbox to check
     */
    protected void check(final WebElement we) {
        if (!we.isSelected()) {
            we.click();
        }
    }

    /**
     * Binding to check checkbox
     *
     * @param locator locator of checkbox to check
     */
    protected void check(final String locator) {
        check(getElement(locator));
    }

    /**
     * Binding to clear text field and enter text
     *
     * @param we       webElement to type to
     * @param value    value to type into the field
     * @param clear    true to clear the field first; false to enter text without clearing field
     * @param keyClear true to clean using keyboard shortcut; false without clean;
     * @return webElement with edited text
     */
    protected WebElement typeKeys(final WebElement we, final String value, final boolean clear, final boolean keyClear) {
        if (clear) we.clear();
        if (keyClear) {
            we.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            we.sendKeys(Keys.chord(Keys.DELETE));
        }
        we.sendKeys(value);
        return we;
    }

    protected  String getVisibleText(final String locator) throws Throwable {
        String text = "";
        try {
            text = getElement(locator).getText();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Binding to select item in dropdown which needs to be clicked for edit mode.
     * Fills actual value and presses "TAB" to submit, otherwise value could be not saved
     *
     * @param clickE        webElement of the field to click
     * @param selectLocator locator of the dropdown
     * @param value         value to select
     */
    protected void clickToSelect(final WebElement clickE, final String selectLocator, final String value) {
        click(clickE);
        selectDropDown(getElement(selectLocator), value);
        getElement(selectLocator).sendKeys(Keys.TAB);
    }

    /**
     * Binding to select item in dropdown by value
     *
     * @param we     WebElement of the dropdown
     * @param option value to select in the dropdown
     */
    protected void selectDropDown(final WebElement we, final String option) {

        try {
            getSelect(we).selectByVisibleText(option);
        } catch (Exception e) {
            //takeScreenshot();
            throw e;
        }
    }
    protected Select getSelect(final WebElement we) {

        return new Select(we);

    }

    protected void scrollElementIntoView(String locator){
        try {
            ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    protected void scrollElementIntoView(WebElement elementToScroll){
        try {
            ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    protected  void JSClick(String locator){

        try {
            WebElement element = getElement(locator);
            ((JavascriptExecutor)Driver).executeScript("arguments[0].click();", element);
        }

        catch (Exception e) {

            e.printStackTrace();
        }
    }

    public boolean verifyText(String actualtext, String expectedText)  throws Throwable {
        boolean flag = false;

        try {

            if(actualtext.equalsIgnoreCase(expectedText)){
                flag = true;
            }
            else{
                flag = false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (flag==false) {
                logger.log(LogStatus.FAIL,"The Actual Field Text : " +actualtext +" , doesnot matches with the expected Field Text: " +expectedText +"");
                flag = false;
            } else if (flag==true) {
                logger.log(LogStatus.PASS,"The Actual Field Text : " +actualtext +" , matches with the expected Field Text: " +expectedText +"");
                flag = true;
            }
        }
        return flag;

    }

    public boolean verifySize(List<WebElement> actualElements, int ExpectedElements, String locatorName)  throws Throwable {
        boolean flag = false;

        try {

            if(actualElements.size()==ExpectedElements){
                flag = true;
            }
            else{
                flag = false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (flag==false) {
                logger.log(LogStatus.FAIL,"The " +locatorName +" size:"+actualElements.size() +", doesnot matches with the " +locatorName +" size:"+ExpectedElements);
                flag = false;
            } else if (flag==true) {
                logger.log(LogStatus.PASS,"The " +locatorName +" size:"+actualElements.size() +", matches with the " +locatorName +" size:"+ExpectedElements);
                flag = true;
            }
        }
        return flag;

    }

    public boolean isElementVisible(By locator,String loctorName,long timeOutInSeconds) throws Throwable{
        boolean isVisible = false;
        try
        {
            WebDriverWait wait = new WebDriverWait(Driver, timeOutInSeconds);
            isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            logger.log(LogStatus.PASS,"The Element "+loctorName +" is Visible");
        }
        catch(Exception e)
        {
            logger.log(LogStatus.FAIL,"The Element "+loctorName +" is not Visible");
        }
        return isVisible;
    }

    public boolean verifyContainsText(String actualtext, String expectedText)  throws Throwable {
        boolean flag = false;

        try {

            if(actualtext.contains(expectedText)){
                flag = true;
            }
            else{
                flag = false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (flag==false) {
                logger.log(LogStatus.FAIL,"The Actual Field Text : " +actualtext +" , doesnot matches with the contains expected Field Text: " +expectedText +"");
                flag = false;
            } else if (flag==true) {
                logger.log(LogStatus.PASS,"The Actual Field Text : " +actualtext +" , matches with the contains expected Field Text: " +expectedText +"");
                flag = true;
            }
        }
        return flag;

    }

}
