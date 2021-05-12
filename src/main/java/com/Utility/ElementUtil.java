package com.Utility;
import com.application.ConfigTestRunner;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ElementUtil {
    private final WebDriver driver;

    public  ElementUtil(WebDriver driver){
        this.driver=driver;
    }

    public void waitAndClick(final WebElement element, int waitfor) {
        WebDriverWait wait = new WebDriverWait(driver, waitfor);
        wait.until(new ExpectedCondition<WebElement>() {
            public final ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(element);
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement elementx = this.visibilityOfElement.apply(driver);
                    if (elementx == null) {
                        return null;
                    }
                    if (elementx.isDisplayed() && elementx.isEnabled())  {
                        elementx.click();
                        return elementx;
                    } else {
                        return null;
                    }
                } catch (WebDriverException e) {
                    return null;
                }
            }
        });
    }

    public boolean fnWaitForVisibility(WebElement element , int waitFor){
        WebDriverWait wait = new WebDriverWait(driver,waitFor);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isDisplayed();
    }

    public WebElement getRowDoc(int id){
        return driver.findElement(By.xpath("//table//tbody//tr["+id+"]"));
    }

    public WebElement getSpanText(String text){
        return driver.findElement(By.xpath("//span[text()='"+text+"']"));
    }
    public WebElement getSpanContainsText(String text){
        return driver.findElement(By.xpath("//span[contains(text(),'"+text+"')]"));
    }
    public WebElement expandPart(String text){
        return driver.findElement(By.xpath("//button[contains(@name,'"+text+"')]"));
    }
    public WebElement getHtag(String text){
        return driver.findElement(By.xpath("//h6[text()='"+text+"']"));
    }
    public WebElement getH1tag(String text){
        return driver.findElement(By.xpath("//h1[text()='"+text+"']"));
    }


    public WebElement selectdoticontoOpencase(String text){
        return driver.findElement(By.xpath("//span[text()='"+text+"']/following::button[1]"));
    }

    public WebElement checkStatusOfCase(String caseId , String status){
        return driver.findElement(By.xpath("//span[text()='"+caseId+"']/following::span[text()='"+status+"']"));
    }
    public WebElement getButtonText(String text){
        return driver.findElement(By.xpath("//button[text()='"+text+"']"));
    }
    public WebElement getText(String text){
        return driver.findElement(By.xpath("//*[text()='"+text+"']"));
    }
    public void setFocusEnter(WebElement element, String value) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.sendKeys(value);
        action.sendKeys(Keys.DOWN);
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }
    public void moveToElement(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.build().perform();
    }
    public void setFocus(WebElement element, String value) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.sendKeys(value);
        action.build().perform();
    }

    public void sendKeysEnter(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }

    public void setFocusdblClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.doubleClick();
        action.build().perform();
    }
    public void setFocusClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click();
        action.build().perform();
    }
    public void sendKeysDownClick(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.sendKeys(Keys.DOWN);
        action.build().perform();
    }
    public void sendKeysDownClickEnter(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.sendKeys(Keys.DOWN);
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }
    public void emptyTextData(WebElement element){
        element.sendKeys(Keys.CONTROL+"a");
        element.sendKeys(Keys.DELETE);
    }
    public void executeExtJsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public void setdataSendKeysEnter(WebElement element, String value) {
        element.sendKeys(value);
        try {
        }catch (Exception e){

        }
        //element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.ENTER);
    }
    public WebElement HomeMenuSelection(String menu){
        return driver.findElement(By.xpath("//a[contains(@href,'"+menu.toLowerCase()+"') and contains(@class,'slideout')]"));
    }
    public WebElement columnValue(String columnName,int i){
        return driver.findElement(By.xpath("(//div[contains(@col-id,'"+columnName+"')])["+i+"]"));
    }
    public WebElement columnValueBooked(String columnName,int i){
        return driver.findElement(By.xpath("(//div[@col-id='"+columnName+"'])["+i+"]"));
    }

    public void fnSelectDropDownValue(WebElement element, String value){
        Select drop = new Select(element);
        drop.selectByVisibleText(value);
    }
    public void fnDropDownSelect(WebElement element,String value,String dropDownName){
        waitAndClick(element,Constants.AJAX_TIMEOUT);
        fnWaitForVisibility(driver.findElement(By.xpath("//div[contains(@class,'"+dropDownName+"')]//li[text()='"+value+"']")),Constants.AJAX_TIMEOUT);
        waitAndClick(driver.findElement(By.xpath("//div[contains(@class,'"+dropDownName+"')]//li[text()='"+value+"']")),Constants.AJAX_TIMEOUT);
    }
    public WebElement fnSearchResult(ConfigTestRunner configTestRunner, String text){
        return driver.findElement(By.xpath("//div[@id='capacity-demand']//div[contains(text(),'"+text+"')]"));
    }

    public String fnDate(String Date){
        String str1[] = Date.split("-");return str1[0];
    }
    public  String fnMonth(String Date){
        String str1[] = Date.split("-");return str1[1];
    }
    public static String fnYear(String Date){
        String str1[] = Date.split("-");return str1[2];
    }
    public String fnMonthNo(String month){
        String monthNo=null;
        if(month.equalsIgnoreCase("Jan"))
            monthNo="01";
        else if(month.equalsIgnoreCase("Feb"))
            monthNo="02";
        else if(month.equalsIgnoreCase("Mar"))
            monthNo="03";
        else if(month.equalsIgnoreCase("Apr"))
            monthNo="04";
        else if(month.equalsIgnoreCase("May"))
            monthNo="05";
        else if(month.equalsIgnoreCase("Jun"))
            monthNo="06";
        else if(month.equalsIgnoreCase("Jul"))
            monthNo="07";
        else if(month.equalsIgnoreCase("Aug"))
            monthNo="08";
        else if(month.equalsIgnoreCase("Sep"))
            monthNo="09";
        else if(month.equalsIgnoreCase("Oct"))
            monthNo="10";
        else if(month.equalsIgnoreCase("Nov"))
            monthNo="11";
        else if(month.equalsIgnoreCase("Dec"))
            monthNo="12";
        else
            System.out.println("No Moth is there to select");
        return monthNo;

    }
    public String fnMonthValue(String val){
        String month=null;
        if(val.length()<2){
            month="0"+val;
        }else
            month=val;
        return month;

    }
    public WebElement fnSelectDate(ConfigTestRunner configTestRunner,  String locator,String text){
        WebElement element=null;
        try {
            element= driver.findElement(By.xpath("(//div[@class='drp-calendar " + locator + "']//td[text()='" + text + "'])[1]"));
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL, "On the calender date is not displaying.");
        }
        return element;
    }

    public Date fnConvertToDate(String value){
        Date date = null;
        try {
            SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
             date = formDate.parse(value);
        }catch (Exception e){

        }
        return date;
    }
    public String fnParseNumber(String text){
        return text.replaceAll("[^0-9]","");
    }



}

