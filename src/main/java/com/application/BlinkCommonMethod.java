package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BlinkCommonMethod extends BaseAction {

//Select menu from the home page
public void fnSelectMenu(ConfigTestRunner configTestRunner, String menu, String menuName,String formName, String objectName) {
    configTestRunner.setChildTest(configTestRunner.getParentTest().createNode(menuName+" , Page Navigation Verification"));
    if (fnWaitForVisibility(getWebElement("HomePage","hamburgMenu",  configTestRunner), Constants.AJAX_TIMEOUT)) {
        configTestRunner.elementUtil.waitAndClick(getWebElement("HomePage","hamburgMenu",  configTestRunner), Constants.AJAX_TIMEOUT);
        try {
            WebElement cnd = configTestRunner.elementUtil.HomeMenuSelection(menu);
            configTestRunner.elementUtil.waitAndClick(cnd, Constants.AJAX_TIMEOUT);
            configTestRunner.getChildTest().log(Status.PASS, "User Click on the " + menuName + " option from the hamburg menu.");
            try {
                if (fnWaitForVisibility(getWebElement(formName,objectName,configTestRunner), Constants.AJAX_TIMEOUT)) {
                    configTestRunner.getChildTest().log(Status.PASS, "User is successfully directed to " + menuName + " page.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                configTestRunner.getChildTest().log(Status.FAIL, "User is successfully directed to " + menuName + " page.");
            }
        } catch (Exception e) {
            configTestRunner.getChildTest().log(Status.FAIL, "Unable to click on hamburg menu from the home page.");
        }
    }
}

public String fnDateSelection(ConfigTestRunner configTestRunner, String formName,String startDate, String endDate){
    configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Filter Date Selection Verification"));
    String ProvidedDateRange=null;
    //user click on the Date field
    if(fnWaitForVisibility(getWebElement(formName,"dateFilter",configTestRunner),Constants.AJAX_TIMEOUT)){
        configTestRunner.elementUtil.waitAndClick(getWebElement(formName,"dateFilter",configTestRunner),Constants.AJAX_TIMEOUT);
        sleep(1000);
        //select from date
        fnselectMonthYearDate(configTestRunner,"left",startDate);
        configTestRunner.getChildTest().log(Status.INFO, "User select start date as: "+startDate);
        //select to date
        fnselectMonthYearDate(configTestRunner,"right",endDate);
        configTestRunner.getChildTest().log(Status.INFO, "User select end date as: "+endDate);
        //click on apply button
        sleep(500);
        fnWaitForVisibility(getWebElement(formName,"DateAfterSelection",configTestRunner),Constants.AJAX_TIMEOUT);
        String dateSelected=getWebElement(formName,"DateAfterSelection",configTestRunner).getText();
        configTestRunner.elementUtil.executeExtJsClick(configTestRunner.driver,getWebElement(formName,"DateFilterApplyBtn",configTestRunner));
        sleep(1000);
        fnWaitForVisibility(getWebElement(formName,"dateFilter",configTestRunner),Constants.AJAX_TIMEOUT);
        String dateRangeSelectedis=getWebElement(formName,"dateFilter",configTestRunner).getAttribute("value");
        String SDate=configTestRunner.elementUtil.fnMonthValue(configTestRunner.elementUtil.fnDate(startDate))+"/"+configTestRunner.elementUtil.fnMonthNo(configTestRunner.elementUtil.fnMonth(startDate))+"/"+configTestRunner.elementUtil.fnYear(startDate);
        String EDate=configTestRunner.elementUtil.fnMonthValue(configTestRunner.elementUtil.fnDate(endDate))+"/"+configTestRunner.elementUtil.fnMonthNo(configTestRunner.elementUtil.fnMonth(endDate))+"/"+configTestRunner.elementUtil.fnYear(endDate);
        ProvidedDateRange = SDate+" - "+EDate;
        if(dateRangeSelectedis.equals(ProvidedDateRange)){
            configTestRunner.getChildTest().log(Status.PASS, "User is able to see the selected daterange in the Date Filter filed.");
        }else
            configTestRunner.getChildTest().log(Status.FAIL, "User is not able to see the selected daterange in the Date Filter filed.");
        if(dateSelected.equalsIgnoreCase(ProvidedDateRange))
            configTestRunner.getChildTest().log(Status.PASS, "User is able to see the selected daterange in the botton pane of the Date Filter panel.");
        configTestRunner.getChildTest().log(Status.PASS, "Date range selected is: "+dateSelected);
    }
    return ProvidedDateRange;
}

public void fnselectMonthYearDate(ConfigTestRunner configTestRunner,String locator,String startDate){

    WebElement year = configTestRunner.driver.findElement(By.xpath("//div[@class='drp-calendar "+locator+"']//select[@class='yearselect']"));
    sleep(400);
    configTestRunner.elementUtil.fnSelectDropDownValue(year,configTestRunner.elementUtil.fnYear(startDate));
    WebElement month = configTestRunner.driver.findElement(By.xpath("//div[@class='drp-calendar "+locator+"']//select[@class='monthselect']"));
    sleep(400);
    configTestRunner.elementUtil.fnSelectDropDownValue(month,configTestRunner.elementUtil.fnMonth(startDate));
    sleep(400);
    configTestRunner.elementUtil.waitAndClick(configTestRunner.elementUtil.fnSelectDate(configTestRunner,locator,configTestRunner.elementUtil.fnDate(startDate)),Constants.AJAX_TIMEOUT);
}

public void fnCUPanelGraphVerificaion(ConfigTestRunner configTestRunner,String formName,String graphType,String xpathElmName, String dropdownType,String xpathGraph,String SelectGraphCardName){
    //graph type option from the Capacity Utilization bottom panel
    if (!getWebElement(formName, xpathElmName, configTestRunner).getText().equalsIgnoreCase(graphType))
        configTestRunner.elementUtil.fnDropDownSelect(getWebElement(formName, xpathElmName, configTestRunner), graphType,SelectGraphCardName);
    if(fnWaitForVisibility(getWebElement(formName,xpathGraph,configTestRunner),Constants.AJAX_TIMEOUT))
        configTestRunner.getChildTest().log(Status.PASS,"Capacity Utilization panel displayed the "+graphType+" graph after selecting the "+dropdownType+" option from the dropdown.");
    else
        configTestRunner.getChildTest().log(Status.FAIL,"Capacity Utilization panel is not display the "+graphType+" graph after selecting the "+dropdownType+" option from the dropdown.");
}
public void fnSelectGrap(ConfigTestRunner configTestRunner,String graphType, String xpathCardGraph,String graphName){
    if (!getWebElement("CapacityAndDemand", xpathCardGraph, configTestRunner).getText().equalsIgnoreCase(graphType))
        configTestRunner.elementUtil.fnDropDownSelect(getWebElement("CapacityAndDemand", xpathCardGraph, configTestRunner), graphType,graphName);
}

}
