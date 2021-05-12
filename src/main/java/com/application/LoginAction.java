package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginAction extends BaseAction {
    private final String formName="Login";
/*
This method is used to login into NHS BI application
 */
    public boolean loginToApplication(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Login To NHS BI Application Verifivation"));
        configTestRunner.getChildTest().log(Status.INFO, "URL use to login into the application is "+ Constants.URL);
        boolean isPresence = false;
        String username=Constants.userName_dev;
        String password=Constants.password_dev;
        //check user id button is present
        try {
            WebElement element = getWebElement(formName,"userid", configTestRunner);
            Assert.assertEquals(configTestRunner.elementUtil.fnWaitForVisibility(element, Constants.AJAX_TIMEOUT), true);
            configTestRunner.getChildTest().log(Status.PASS, "NHS BI Application is launch successfully.");
            if (element.isDisplayed()) {
                getWebElement(formName,"userid",configTestRunner).sendKeys(username);
                configTestRunner.getChildTest().log(Status.INFO, "User Name Enter is " + username);
                getWebElement(formName,"password",  configTestRunner).sendKeys(password);
                configTestRunner.getChildTest().log(Status.INFO, "User Name Enter is " + password);
                getWebElement(formName,"loginBtn",  configTestRunner).click();
                sleep(200);
                try {
                    if (configTestRunner.elementUtil.fnWaitForVisibility(getDivWithText("Health Care Solutions"), Constants.AJAX_TIMEOUT)) {
                        sleep(100);
                        configTestRunner.getChildTest().log(Status.PASS, "Login to the NHS BI application is successful" );
                    } else
                        configTestRunner.getChildTest().log(Status.FAIL, "Login to the NHS BI application is successful." + configTestRunner.getChildTest().addScreenCaptureFromPath(configTestRunner.screenShotName("Login_Successfully")));
                } catch (Exception e) {
                    e.printStackTrace();
                    fnTakeScreenAshot(configTestRunner,"Fail","Login to the NHS BI application is not successful","Login_Unsuccessful");
                }
            }
        }catch (Exception e){
            fnTakeScreenAshot(configTestRunner,"Fail","NHS BI Application is not launch successfully.","URL_NotLaunch");
        }
        return isPresence;
    }

    /*
    This method is used for logout from the NHS BI application
     */
    public synchronized void LogOutFromApplication(ConfigTestRunner configTestRunner) {
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Logout From NSH BI Application Verification"));
        try{
            if(fnWaitForVisibility(getWebElement("HomePage","hamburgMenu",configTestRunner),Constants.AJAX_TIMEOUT)){
                configTestRunner.elementUtil.waitAndClick(getWebElement("HomePage","hamburgMenu",configTestRunner),Constants.AJAX_TIMEOUT);
                configTestRunner.getChildTest().log(Status.PASS,"User Click on the hamburg menu.");
                try {
                    WebElement profileIcon=getWebElement("HomePage","Menu_Profile",  configTestRunner);
                    if (fnWaitForVisibility(profileIcon, Constants.AJAX_TIMEOUT)) {
                            configTestRunner.elementUtil.moveToElement(profileIcon);
                            configTestRunner.getChildTest().log(Status.PASS,"User Move to the Profile Icon.");
                            try {
                                if (fnWaitForVisibility(getDivContainsWithText("Logout"),Constants.AJAX_TIMEOUT)) {
                                    waitAndClick(getDivContainsWithText("Logout"),Constants.AJAX_TIMEOUT,configTestRunner.driver);
                                    configTestRunner.getChildTest().log(Status.PASS,"User Click on logout button.");
                                    try {
                                        if (fnWaitForVisibility(getWebElement(formName,"userid", configTestRunner), Constants.AJAX_TIMEOUT))
                                            configTestRunner.getChildTest().log(Status.PASS, "User logout successfully from the NSH BI application.");
                                    }catch (Exception e){
                                        configTestRunner.getChildTest().log(Status.FAIL, "User is unable to logout from the NSH BI application.");
                                    }
                                }
                            }catch (Exception e){
                                configTestRunner.getChildTest().log(Status.FAIL,"LogOut option is not present in profile menu.");
                            }
                    }
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL,"Profile option is not present in the home page menu.");
                }
            }

        }catch (Exception e){
//            configTestRunner.getChildTest().log(Status.FAIL,"hamburgMenu is not present in NHS BI application home page.");
            fnTakeScreenAshot(configTestRunner,"Fail","hamburgMenu is not present in NHS BI application home page.","HamburgMenuNotPresent");
        }

    }

}

