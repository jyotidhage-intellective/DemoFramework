package com.tests;

import com.Utility.Constants;
import com.application.ConfigTestRunner;
import com.application.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DriverClass {
    public String destFile;
    public ExtentTest test;
    public ExtentHtmlReporter htmlReports;   //to generate an html file
    private static ExtentReports extent;
    private ConfigTestRunner configTestRunnerLocal;
    private WebDriver driver;

    @BeforeSuite
    public void extentReportConfig(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss");
        destFile = System.getProperty("user.dir")+ Constants.reportsFilePath+"BlinxSolutions_"+dateFormat.format(new Date());
        File newFolder = new File(destFile);
        boolean created =  newFolder.mkdir();  //mkdir will create folder
        if(created)
            System.out.println("Folder is created !");
        else
            System.out.println("Unable to create folder");

        String destDir = "BlinxSolutions_Report"+dateFormat.format(new Date()) + ".html";
        htmlReports = new ExtentHtmlReporter(destFile + "\\" +destDir);  //to generate an html file
        extent = new ExtentReports();
        extent.attachReporter(htmlReports);
        extent.setReportUsesManualConfiguration(true);
        setExtent(extent);
        htmlReports.config().setReportName("NHS BI Application");
        htmlReports.config().setTheme(Theme.STANDARD);
        htmlReports.config().setDocumentTitle("BlinxSolutions Test Result");
    }
    @BeforeMethod
   // @Parameters("browser")
    public void initializeBrowser(){
        DriverFactory driverFactory = new DriverFactory();
        driver =driverFactory.startBrowser("chrome");
        driver.get(Constants.URL);
    }
    @Test
    public void SanityTest001(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC001");
    }
    @Test
    public void SanityTest002(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC002");
    }
    @Test
    public void SanityTest003(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC003");
    }
    @Test
    public void SanityTest004(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC004");
    }
    @Test
    public void SanityTest005(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC005");
    }
    @Test
    public void SanityTest006(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC006");
    }
    @Test
    public void SanityTest007(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC007");
    }
    @Test
    public void SanityTest008(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC008");
    }
    @Test
    public void SanityTest009(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC009");
    }
    @Test
    public void SanityTest010(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC010");
    }
    @Test
    public void SanityTest011(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC011");
    }
    @Test
    public void SanityTest012(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC012");
    }
    @Test
    public void SanityTest013(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC013");
    }

    @Test
    public void SanityTest014(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC014");
    }
    @Test
    public void SanityTest015(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC015");
    }
    @Test
    public void SanityTest016(){
        ConfigTestRunner configTestRunner = new ConfigTestRunner(extent);
        configTestRunner.setConfigTestRunner(configTestRunner);
        setConfigTestRunnerLocal(configTestRunner);
        configTestRunner.setDestFile(destFile);
        configTestRunner.run("SC016");
    }


    @AfterMethod
    public void fnCloseBrowser(ITestResult testResult){
//        testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation()
        if(testResult.getStatus()== ITestResult.FAILURE){
            String name ="Test_Fail_ScreenShot";
            try {
                getConfigTestRunnerLocal().getChildTest().log(Status.FAIL, "Test is failed" + getConfigTestRunnerLocal().getChildTest().addScreenCaptureFromPath(getConfigTestRunnerLocal().screenShotName(name)));
            }catch (Exception exc){
                exc.printStackTrace();
            }
            getConfigTestRunnerLocal().getBaseAction().setCellData("Fail",getConfigTestRunnerLocal().getBaseAction().rowValue(getConfigTestRunnerLocal().getBaseAction().getTestCase().get("SC_ID"), getConfigTestRunnerLocal().getBaseAction().ColumnValue("SC_ID","TestCase")),getConfigTestRunnerLocal().getBaseAction().ColumnValue("Status","TestCase"));
        }  else if(testResult.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP,testResult.getThrowable());
            getConfigTestRunnerLocal().getBaseAction().setCellData("Skip",getConfigTestRunnerLocal().getBaseAction().rowValue(getConfigTestRunnerLocal().getBaseAction().getTestCase().get("SC_ID"), getConfigTestRunnerLocal().getBaseAction().ColumnValue("SC_ID","TestCase")),getConfigTestRunnerLocal().getBaseAction().ColumnValue("Status","TestCase"));
        }
        getConfigTestRunnerLocal().getBaseAction().setCellData("Pass",getConfigTestRunnerLocal().getBaseAction().rowValue(getConfigTestRunnerLocal().getBaseAction().getTestCase().get("SC_ID"), getConfigTestRunnerLocal().getBaseAction().ColumnValue("SC_ID","TestCase")),getConfigTestRunnerLocal().getBaseAction().ColumnValue("Status","TestCase"));
    }
    @AfterSuite
    public void End_SetUp(){
        getConfigTestRunnerLocal().getExtent().flush();
        if(getConfigTestRunnerLocal().driver!=null)
        getConfigTestRunnerLocal().driver.quit();
    }

    public ConfigTestRunner getConfigTestRunnerLocal() {
        return configTestRunnerLocal;
    }

    public void setConfigTestRunnerLocal(ConfigTestRunner configTestRunnerLocal) {
        this.configTestRunnerLocal = configTestRunnerLocal;
    }

    public ExtentReports getExtent() {
        return extent;
    }

    public void setExtent(ExtentReports extent) {
        this.extent = extent;
    }
}
