package com.application;

import com.Utility.ElementUtil;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.config.Configuration;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class ConfigTestRunner {

    private String destFile;
    private ConfigTestRunner configTestRunner = null;
    private com.aventstack.extentreports.ExtentTest parentTest;
    private com.aventstack.extentreports.ExtentTest childTest;
    private ExtentReports extent;
    private BaseAction baseAction;
    private LoginAction loginAction;
    private Configuration config;
    public ElementUtil elementUtil;
    private CapacityAndDemandAction capacityAndDemandAction;
    private BlinkCommonMethod blinkCommonMethod;
    public WebDriver driver=DriverFactory.getDriver();
    public String TestCase_Id;
    public Map<String, String> testCase = new ConcurrentHashMap<>();
    public Map<String, String> testData = new ConcurrentHashMap<>();

    public ConfigTestRunner(ExtentReports extent) {
        setExtent(extent);
        //setDriver(driver);
    }

    public void run(String Tcnumber) {
        baseAction = new BaseAction();
        baseAction.ReadTestData(Tcnumber);
        if (baseAction.testCase.get("Execute").equalsIgnoreCase("YES")) {
            parentTest = extent.createTest(baseAction.testCase.get("SC_ID") + " " + baseAction.testCase.get("Scenarios"));
            // driver = baseAction.initializeBrowser(browser);
            intActions();
            TestCase_Id = baseAction.testCase.get("SC_ID");
            //driver.get(Constants.URL);
            int rowNo = baseAction.RowNo("TestData", Tcnumber, "SC_ID");
            baseAction.TestDataDic(rowNo, "TestData");
            loginAction.loginToApplication(configTestRunner);
            SCExecutor(Tcnumber);
            loginAction.LogOutFromApplication(configTestRunner);
            driver.close();
        } else
            parentTest.log(Status.INFO, "No Test Case is considered for execution");

    }

    public void SCExecutor(String tcNumber) {
        if (tcNumber.equalsIgnoreCase("SC001")) {
            capacityAndDemandAction.fnSC001(configTestRunner);
        }else if (tcNumber.equalsIgnoreCase("SC002")) {
            capacityAndDemandAction.fnSC002(configTestRunner);
        }else if (tcNumber.equalsIgnoreCase("SC003")) {
            capacityAndDemandAction.fnSC003(configTestRunner);
        }else if (tcNumber.equalsIgnoreCase("SC004")) {
            capacityAndDemandAction.fnSC004(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC005")) {
            capacityAndDemandAction.fnSC005(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC006")) {
            capacityAndDemandAction.fnSC006(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC007")) {
            capacityAndDemandAction.fnSC007(configTestRunner);
        } else if(tcNumber.equalsIgnoreCase("SC008")) {
            capacityAndDemandAction.fnSC008(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC009")) {
            capacityAndDemandAction.fnSC009(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC010")) {
            capacityAndDemandAction.fnSC010(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC011")) {
            capacityAndDemandAction.fnSC011(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC012")) {
            capacityAndDemandAction.fnSC012(configTestRunner);
        }else if (tcNumber.equalsIgnoreCase("SC013")) {
            capacityAndDemandAction.fnSC013(configTestRunner);
        }else if (tcNumber.equalsIgnoreCase("SC014")) {
            capacityAndDemandAction.fnSC014(configTestRunner);
        }
        else if(tcNumber.equalsIgnoreCase("SC015")) {
            capacityAndDemandAction.fnSC015(configTestRunner);
        }else if(tcNumber.equalsIgnoreCase("SC016")) {
            capacityAndDemandAction.fnSC016(configTestRunner);
        }
        else{
            parentTest.log(Status.INFO,"No Test Case is considered for execution");
        }
    }


    public void intActions(){
        elementUtil= new ElementUtil(driver);
        config = new Configuration();
        loginAction = new LoginAction();
        blinkCommonMethod = new BlinkCommonMethod();
        capacityAndDemandAction = new CapacityAndDemandAction();

    }
    public String screenShotName(String screenShotName){

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
        String fileName = screenShotName+"_"+dateFormat.format(new Date())+".png";
//        String fileName = screenShotName+"_"+dateFormat.format(new Date());
//        return  capture1(fileName);
        return takeFullPageScreenShot(fileName);
//        return capture1(fileName);
    }

    public String capture1(String screenShotName)  {

//        Shutterbug.shootPage(getDriver(), Capture.FULL_SCROLL,true).withName(screenShotName).save(getDestFile());
//        String dest =getDestFile()+"\\"+screenShotName+".png";

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(getDriver());
////        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(ShootingStrategies.scaling(2f), 1000)).takeScreenshot(driver);
        String dest =getDestFile()+"\\"+screenShotName;
        try {
            ImageIO.write(screenshot.getImage(), "PNG", new File(dest));
        }catch (Exception e){

        }
        return dest;
    }
    public String takeFullPageScreenShot(String screenShotName)  {

        JavascriptExecutor jsExec = (JavascriptExecutor)driver;

        //Returns a Long, Representing the Height of the window’s content area.
        Long windowHeight = (Long) jsExec.executeScript("return window.innerHeight;");

        //Returns a Long, Representing the Height of the complete WebPage a.k.a. HTML document.
        Long webpageHeight = (Long) jsExec.executeScript("return document.body.scrollHeight;");

        //Marker to keep track of the current position of the scroll point
        //Long currentWindowScroll = Long.valueOf(0);
        //Using java's boxing feature to create a Long object from native long value.

        Long currentWindowScroll = 0L;
        String dest =getDestFile()+"\\"+screenShotName;

        do{
            //System.out.println(windowHeight + ", " + webpageHeight + ", " + currentWindowScroll);

            jsExec.executeScript("window.scrollTo(0, " + currentWindowScroll + ");");

            Actions act = new Actions(driver);
            act.pause(5000).perform();
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(dest);
            try {
                FileUtils.copyFile(source, destination);
                byte[] bytes = new byte[(int) source.length()];
                String base64 = new String(Base64.encodeBase64(bytes), "UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            currentWindowScroll = currentWindowScroll + windowHeight;

        }while(currentWindowScroll <= webpageHeight);
        return dest;
    }
//    public String screenShotName(String screenShotName, WebElement element){
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
//        String fileName = screenShotName+"_"+dateFormat.format(new Date())+".png";
//        return  capture(fileName,element);
//    }
//    public String capture(String screenShotName,WebElement element)  {
//        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000)).takeScreenshot(getDriver(),element);
//        String dest =getDestFile()+"\\"+screenShotName;
//        try {
//            ImageIO.write(screenshot.getImage(), "png", new File(dest));
//        }catch (Exception e){
//
//        }
//        return dest;
//    }

    public String capture(String screenShotName)  {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        System.out.println(getDestFile());
        String dest =getDestFile()+"\\"+screenShotName;
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);
            byte[] bytes = new byte[(int) source.length()];
            String base64 = new String(Base64.encodeBase64(bytes), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return dest;
    }

    public Map<String, String> getTestData() {
        return testData;
    }

    public void setTestData(Map<String, String> testData) {
        this.testData = testData;
    }

    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        this.extent = extent;
    }

    public void setConfigTestRunner(ConfigTestRunner configTestRunner) {
        this.configTestRunner= configTestRunner;
    }
    public ConfigTestRunner getConfigTestRunner() {
        return configTestRunner;
    }

    public String getDestFile() {
        return destFile;
    }

    public void setDestFile(String destFile) {
        this.destFile = destFile;
    }

    public ExtentTest getParentTest() {
        return parentTest;
    }

    public void setParentTest(ExtentTest parentTest) {
        this.parentTest = parentTest;
    }

    public ExtentTest getChildTest() {
        return childTest;
    }

    public void setChildTest(ExtentTest childTest) {
        this.childTest = childTest;
    }


    public Map<String, String> getTestCase() {
        return testCase;
    }

    public BaseAction getBaseAction() {
        return baseAction;
    }

    public void setBaseAction(BaseAction baseAction) {
        this.baseAction = baseAction;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public BlinkCommonMethod getBlinkCommonMethod() {
        return blinkCommonMethod;
    }
}


