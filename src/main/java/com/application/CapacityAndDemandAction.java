package com.application;

import com.Utility.Constants;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CapacityAndDemandAction extends BaseAction{
    private final String formName="CapacityAndDemand";

    private WebDriver driver= DriverFactory.getDriver();

    public void fnSC001(ConfigTestRunner configTestRunner){
        if(fnDateVerification(configTestRunner))
            fnTakeScreenAshot(configTestRunner,"Pass","Date range in the capacity utilization graph panel is same as the filter date selected","DateRangeVerification_SC001");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Date range in the capacity utilization graph panel is not same as the filter date selected.");

    }

    public boolean fnDateVerification(ConfigTestRunner configTestRunner){
        //user select capacity & demandoption from the home page
        configTestRunner.getBlinkCommonMethod().fnSelectMenu(configTestRunner,"capacity","Capacity and Demand","CapacityAndDemand","OverviewDropDown");
        //user select the date from the date picket
         DateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy");
        String endDate= dateFormat.format(new Date());
        String dateRange[] = configTestRunner.getBlinkCommonMethod().fnDateSelection(configTestRunner,formName,"1-Jan-2005",endDate).split(" - ");
        //check the Capacity utilization page shhould display the selected filter date
        Date sDate =configTestRunner.elementUtil.fnConvertToDate(dateRange[0]);
        Date EDate =configTestRunner.elementUtil.fnConvertToDate(dateRange[1]);
        List<WebElement> element = getWebElements(configTestRunner,formName,"CapacityUtiizationGraphDate");
        boolean isPresent=false;
        for(int i=1; i<element.size();i++){
            try {
                Date dateValue = configTestRunner.elementUtil.fnConvertToDate(element.get(i).getText());
                if (dateValue.compareTo(sDate) > 0 || dateValue.compareTo(EDate) < 1 || dateValue.compareTo(sDate) == 0 || dateValue.compareTo(EDate) == 0) {
                    isPresent = true;
                } else {
                    isPresent = false;
                    configTestRunner.getChildTest().log(Status.FAIL, "Date range in the capacity utilization is not in the filter date selected range.");
                }
            }catch (Exception e){

            }
        }
        return isPresent;
    }

    public void fnSC002(ConfigTestRunner configTestRunner){
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Capacity & Utilization card options & value verification after Selecting Overview option"));
        fnOverviewOptionGrapgSelection(configTestRunner,"Bar");
    }
    public void fnSC003(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity Utilisation bar graph after selecting Appointment Type option"));
        fnSelectGraph(configTestRunner,"Appointment Type","Bar");
    }
    public void fnSC004(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity Utilisation bar graph after selecting Clinician Type option"));
        fnSelectGraph(configTestRunner,"Clinician Type","Bar");
    }
    public void fnSC005(ConfigTestRunner configTestRunner){
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Capacity & Utilization card options & value verification after Selecting Overview option"));
        fnOverviewOptionGrapgSelection(configTestRunner,"Line");
    }
    public void fnSC006(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity Utilisation Line graph after selecting Appointment Type option"));
        fnSelectGraph(configTestRunner,"Appointment Type","Line");
    }
    public void fnSC007(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity Utilisation Line graph after selecting Clinician Type option"));
        fnSelectGraph(configTestRunner,"Clinician Type","Line");
    }
    public void fnSC008(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available"));
        fnCapacityvsDemandCardGrapgSelection(configTestRunner,"Bar");
    }
    public void fnSC009(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify search functionality is working on Capacity Vs Demand Panel"));
        //click on capacity & Demand card
        configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "CapacityVsDemandCard", configTestRunner), Constants.AJAX_TIMEOUT);
        if (fnWaitForVisibility(getWebElement(formName, "CapcityVsDemandPanel", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.getChildTest().log(Status.PASS, "Capacity vs Demand card is selected successfully");
        } else
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity vs Demand card is not selected successfully");
        try {
            if (fnWaitForVisibility(getWebElement(formName, "CapcityVsDemandGraph_tableIcon", configTestRunner), Constants.AJAX_TIMEOUT)) {
                configTestRunner.elementUtil.waitAndClick(getWebElement(formName,"CapcityVsDemandGraph_tableIcon",configTestRunner),Constants.AJAX_TIMEOUT);
                try{
                    if(fnWaitForVisibility(getWebElement(formName,"CapcityVsDemandGraph_tableSearchField",configTestRunner),Constants.AJAX_TIMEOUT)){
                        configTestRunner.getChildTest().log(Status.PASS, "User click on the table icon from the capacity vs Demand panel.");
                        getWebElement(formName,"CapcityVsDemandGraph_tableSearchField",configTestRunner).sendKeys(configTestRunner.getBaseAction().getTestData().get("SearchData"));
                        configTestRunner.getChildTest().log(Status.PASS, "User enter search data as "+configTestRunner.getBaseAction().getTestData().get("SearchData")+" in the table search field");
                        try {
                            if (configTestRunner.elementUtil.fnSearchResult(configTestRunner, configTestRunner.getBaseAction().getTestData().get("SearchData")).isDisplayed()) {
//                                configTestRunner.getChildTest().log(Status.PASS, configTestRunner.getBaseAction().getTestData().get("SearchData")+" search value is displayed in the Capacity vs Demand table");
                                fnTakeScreenAshot(configTestRunner,"Pass",configTestRunner.getBaseAction().getTestData().get("SearchData")+" search value is displayed in the Capacity vs Demand table","AGTableSearchBox");
                            }
                        }catch (Exception e){
                            configTestRunner.getChildTest().log(Status.FAIL, "Enter search value is not displayed in the Capacity vs Demand table");
                        }
                    }
                }catch (Exception e){
                    configTestRunner.getChildTest().log(Status.FAIL, "After selecting Table icon search input box is not present on the Capacity Vs Demand Panel");
                }
            }
        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL, "Table icon is not present on the Capacity Vs Demand Panel");
        }

    }
    public void fnSC010(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available for Line Graph"));
        fnCapacityvsDemandCardGrapgSelection(configTestRunner,"Line");
    }
    public void fnSC012(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available for Line Graph"));
        fnCapacityvsDemandCardGrapgSelection(configTestRunner,"Area");
    }
    public void fnSC013(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available"));
        fnPatientUtilisation(configTestRunner,"Bar");
    }
    public void fnSC014(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify Patient utilisation Large Gride Table"));
        configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "PatientUtilizationCard", configTestRunner), Constants.AJAX_TIMEOUT);
        if (fnWaitForVisibility(getWebElement(formName, "CapacityandUtilizationPanel", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.getChildTest().log(Status.PASS, "PATIENT UTILISATION card is selected successfully");
        } else
            configTestRunner.getChildTest().log(Status.FAIL, "PATIENT UTILISATION card is not selected successfully");

        if (fnWaitForVisibility(getWebElement(formName, "PatientUtilizationGraph_tableIcon", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "PatientUtilizationGraph_tableIcon", configTestRunner), Constants.AJAX_TIMEOUT);
            List<WebElement> ageGroupFRomCard = getWebElements(configTestRunner, formName, "PatientUtilizationCardGraph");
            configTestRunner.getChildTest().log(Status.PASS, "Age_Group  Booked  Seen  DNA POP  bookedRate");
            try {
                for (int j = 5; j < ageGroupFRomCard.size(); j++) {
                    String ageGrpCard = ageGroupFRomCard.get(j).getText();
                    List<WebElement> ageGroup = getWebElements(configTestRunner, formName, "PatientUtilizationGraphAgeRangeColumn");
                    for (int i = 1; i < ageGroup.size(); i++) {
                        if (ageGroup.get(i).getText().contains(ageGrpCard)) {
                            String booked = configTestRunner.elementUtil.columnValueBooked("BOOKED", i + 1).getText();
                            String seen = configTestRunner.elementUtil.columnValue("SEEN", i + 1).getText();
                            String dna = configTestRunner.elementUtil.columnValue("DNA", i + 1).getText();
                            String pop = configTestRunner.elementUtil.columnValue("POP", i + 1).getText();
                            String bookedRate = configTestRunner.elementUtil.columnValue("BOOKED_RATE", i + 1).getText();
                            String appoinmentRate = configTestRunner.elementUtil.columnValue("APPOINTMENT_TYPE", i + 1).getText();
                            String clinicalType = configTestRunner.elementUtil.columnValue("CLINICIAN_TYPE", i + 1).getText();
                            String gender = configTestRunner.elementUtil.columnValue("SEX", i + 1).getText();
                            configTestRunner.getChildTest().log(Status.PASS, ageGroup.get(i).getText() + "     " + booked + "     " + seen + "      " + dna + "     " + pop + "     " + bookedRate + "    " + appoinmentRate);
                            break;
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public void fnSC011(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available for the table population"));
        configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "CapacityVsDemandCard", configTestRunner), Constants.AJAX_TIMEOUT);
        if (fnWaitForVisibility(getWebElement(formName, "CapcityVsDemandPanel", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.getChildTest().log(Status.PASS, "Capacity vs Demand card is selected successfully");
        } else
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity vs Demand card is not selected successfully");
        if (fnWaitForVisibility(getWebElement(formName, "CapcityVsDemandGraph_tableIcon", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "CapcityVsDemandGraph_tableIcon", configTestRunner), Constants.AJAX_TIMEOUT);
            try {

                configTestRunner.getChildTest().log(Status.PASS, "Clinician Type ** Available **  Booked ** Seen **  DNA **  %Booked  ** %Seen ** %DNA");
                List<WebElement> clientType = getWebElements(configTestRunner, formName, "CapacityVsDemandGraphClinicianType");
                for (int i = 1; i < clientType.size(); i++) {
                        String booked = configTestRunner.elementUtil.columnValueBooked("BOOKED", i + 1).getText();
                        configTestRunner.elementUtil.columnValueBooked("BOOKED", i + 1).click();
                        String seen = configTestRunner.elementUtil.columnValue("SEEN", i + 1).getText();
                        String dna = configTestRunner.elementUtil.columnValue("DNA", i + 1).getText();
                        String available = configTestRunner.elementUtil.columnValue("AVAILABLE", i + 1).getText();
                        String BookedPercenrage = configTestRunner.elementUtil.columnValue("% Booked", i + 1).getText();
                        String SeenPercenatge = configTestRunner.elementUtil.columnValue("% Seen", i + 1).getText();
                        String dnaPercentage = configTestRunner.elementUtil.columnValue("% DNA", i + 1).getText();
                        configTestRunner.getChildTest().log(Status.PASS, clientType.get(i).getText() + "**" + booked + "**" + seen + "**" + dna + "**" + available+"**"+BookedPercenrage+"**"+SeenPercenatge+"**"+dnaPercentage);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            fnTakeScreenAshot(configTestRunner,"Pass","Capacity vs Demand Panel display the table with data","TableVerification");
        }
    }

    public void fnSC015(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available"));
        fnPatientUtilisation(configTestRunner,"Line");
        fnTakeScreenAshot(configTestRunner,"Pass","Login to the NHS BI application is not successful","Login_Unsuccessful");
    }
    public void fnSC016(ConfigTestRunner configTestRunner) {
        fnDateVerification(configTestRunner);
        configTestRunner.setChildTest(configTestRunner.getParentTest().createNode("Verify the Capacity vs Demand card is available"));
        fnPatientUtilisation(configTestRunner,"Area");
    }
    public void fnSelectGraph(ConfigTestRunner configTestRunner, String DropDownValue,String graphType){
        //click on capacity & utilization card
        configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "CapacityandUtilizationCard", configTestRunner), Constants.AJAX_TIMEOUT);
        if (fnWaitForVisibility(getWebElement(formName, "OverviewDropDown", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.getChildTest().log(Status.PASS, "Capacity and Utilization card is selected successfully");
        } else
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity and Utilization card is not selected successfully");
        //Select the overview option from the drop down
        if (!getWebElement(formName, "OverviewDropDown", configTestRunner).getText().equalsIgnoreCase(DropDownValue))
            configTestRunner.elementUtil.fnDropDownSelect(getWebElement(formName, "OverviewDropDown", configTestRunner), DropDownValue,"capacityUtilisationDataTypeDropdown");
        if(fnWaitForVisibility(getWebElement(formName,"CapacityAndUtilizationTotalValue",configTestRunner),Constants.AJAX_TIMEOUT)){
            configTestRunner.getChildTest().log(Status.PASS,"After selecting "+DropDownValue+"Type option from drop down ,Total no of appointments present on the graph are :"+getWebElement(formName,"CapacityAndUtilizationTotalValue",configTestRunner).getText());
        }else
            configTestRunner.getChildTest().log(Status.FAIL,"After selecting "+DropDownValue+" option from drop down ,Total no of appointments are not present on the graph.");

        //bar option from the Capacity Utilization bottom panel
        configTestRunner.getBlinkCommonMethod().fnCUPanelGraphVerificaion(configTestRunner,formName,graphType,"CapacityUtilizationgraph",DropDownValue,"CUBarGraph_CUPanel","capacityUtilisationGraphDropdown");
    }
    public void fnOverviewOptionGrapgSelection(ConfigTestRunner configTestRunner, String graphType){
        //click on capacity & utilization card
        try {
            configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "CapacityandUtilizationCard", configTestRunner), Constants.AJAX_TIMEOUT);
            if (fnWaitForVisibility(getWebElement(formName, "OverviewDropDown", configTestRunner), Constants.AJAX_TIMEOUT)) {
                configTestRunner.getChildTest().log(Status.PASS, "Capacity and Utilization card is selected successfully");
            } else
                configTestRunner.getChildTest().log(Status.FAIL, "Capacity and Utilization card is not selected successfully");
            //Select the overview option from the drop down
            if (!getWebElement(formName, "OverviewDropDown", configTestRunner).getText().equalsIgnoreCase("Overview"))
                configTestRunner.elementUtil.fnDropDownSelect(getWebElement(formName, "OverviewDropDown", configTestRunner), "Overview", "capacityUtilisationDataTypeDropdown");
            WebElement available = getWebElement(formName, "CapacityandUtilizationAvailable", configTestRunner);
            WebElement booked = getWebElement(formName, "CapacityandUtilizationBooked", configTestRunner);
            WebElement dna = getWebElement(formName, "CapacityandUtilizationDNA", configTestRunner);
            WebElement seen = getWebElement(formName, "CapacityandUtilizationSeen", configTestRunner);
            if (available.isDisplayed() && booked.isDisplayed() && dna.isDisplayed() && seen.isDisplayed()) {
                configTestRunner.getChildTest().log(Status.PASS, "Available,Booked,DNA,Seen options are present in the capacity & utilization card after selecting overview option from the dropdown.");
            } else
                configTestRunner.getChildTest().log(Status.FAIL, "Available,Booked,DNA,Seen is not present in the capacity & utilization card after selecting overview option from the dropdown.");
            configTestRunner.getChildTest().log(Status.PASS, "Total no of Available Appointments present in the capacity & utilization card is: " + configTestRunner.elementUtil.fnParseNumber(available.getText()));
            configTestRunner.getChildTest().log(Status.PASS, "Total no of Booked Appointments present in the capacity & utilization card is: " + configTestRunner.elementUtil.fnParseNumber(booked.getText()));
            configTestRunner.getChildTest().log(Status.PASS, "Total no of DNA Appointments present in the capacity & utilization card is: " + configTestRunner.elementUtil.fnParseNumber(dna.getText()));
            configTestRunner.getChildTest().log(Status.PASS, "Total no of Seen Appointments present in the capacity & utilization card is: " + configTestRunner.elementUtil.fnParseNumber(seen.getText()));
            try {
                if(!getWebElement(formName, "CapacityAndUtilizationTotalValue", configTestRunner).getText().contains("0")) {
                    fnWaitForVisibility(getWebElement(formName, "CapacityandUtilizationGraph", configTestRunner), Constants.AJAX_TIMEOUT);
                    configTestRunner.getChildTest().log(Status.PASS, "Capacity & Utilization pie chart graph is displayed in the appliction after selecting Overview option");
                }else
                    configTestRunner.getChildTest().log(Status.WARNING, "Capacity & Utilization pie chart graph is not displayed in the application after selecting Overview option");
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.FAIL, "Capacity & Utilization pie chart graph is not displayed in the application for the given date selection.");
            }
            try {
                if (fnWaitForVisibility(getWebElement(formName, "CapacityAndUtilizationTotalValue", configTestRunner), Constants.AJAX_TIMEOUT) &&
                        fnWaitForVisibility(getWebElement(formName, "CapacityAndUtilizationTotalAppointment", configTestRunner), Constants.AJAX_TIMEOUT)) {
                    configTestRunner.getChildTest().log(Status.PASS, "Total no of appointments present on the graph are :" + getWebElement(formName, "CapacityAndUtilizationTotalValue", configTestRunner).getText());
                } else
                    configTestRunner.getChildTest().log(Status.PASS, "Total no of appointments are not present on the graph ");
            }catch (Exception e){
                configTestRunner.getChildTest().log(Status.PASS, "Total no of appointments are not present on the graph ");
            }
            //bar option from the Capacity Utilization bottom panel
            configTestRunner.getBlinkCommonMethod().fnCUPanelGraphVerificaion(configTestRunner, formName, "Bar", "CapacityUtilizationgraph", "Overview", "CUBarGraph", "capacityUtilisationGraphDropdown");

        }catch (Exception e){
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity & Utilization Card is not present in the application.");
        }
    }
    public void fnCapacityvsDemandCardGrapgSelection(ConfigTestRunner configTestRunner,String graphType){
        //click on capacity & Demand card
        configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "CapacityVsDemandCard", configTestRunner), Constants.AJAX_TIMEOUT);
        if (fnWaitForVisibility(getWebElement(formName, "CapcityVsDemandPanel", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.getChildTest().log(Status.PASS, "Capacity vs Demand card is selected successfully");
        } else
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity vs Demand card is not selected successfully");
        if(getWebElement(formName,"CapcityVsDemand_Available",configTestRunner).isDisplayed() &&
                getWebElement(formName,"CapcityVsDemand_Booked",configTestRunner).isDisplayed() )
            configTestRunner.getChildTest().log(Status.PASS, "Available & Booked option is available on Capacity Vs Demand Card");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Available & Booked option is not available after on Capacity Vs Demand Card");
        //check the graph is plotted
        if(fnWaitForVisibility(getWebElement(formName,"CapcityVsDemand_Graph",configTestRunner),Constants.AJAX_TIMEOUT) &&
                fnWaitForVisibility(getWebElement(formName,"CapcityVsDemand_Time",configTestRunner),Constants.AJAX_TIMEOUT) &&
                fnWaitForVisibility(getWebElement(formName,"CapcityVsDemand_Appointments",configTestRunner),Constants.AJAX_TIMEOUT))
            configTestRunner.getChildTest().log(Status.PASS, "Capacity vs Demand graph is plotted for Time vs Appointments");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity vs Demand graph is not plotted for Time vs Appointments");
        //check the graph is plotted in the capacity vs Demand Panel
        //select bar graph
        configTestRunner.getBlinkCommonMethod().fnSelectGrap(configTestRunner,graphType,"CapacityDemandGraph","capacityDemandGraphDropdown");
        if(getWebElements(configTestRunner,formName,"CapcityVsDemandPanel_Graph").size()>=1 &&
                getWebElements(configTestRunner,formName,"CapcityVsDemandPanel_Time").size()>=1 &&
                getWebElements(configTestRunner,formName,"CapcityVsDemandPanel_Appointments").size()>=1)
//            configTestRunner.getChildTest().log(Status.PASS, "Capacity vs Demand "+graphType+" graph is plotted for Time vs Appointments at bottom panel"+configTestRunner.getChildTest().addScreenCaptureFromPath(configTestRunner.screenShotName("CapacityDemandGraph")));
            fnTakeScreenAshot(configTestRunner,"Pass","Capacity vs Demand "+graphType+" graph is plotted for Time vs Appointments at bottom panel","CapacityDemandGraph");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Capacity vs Demand "+graphType+" graph is not plotted for Time vs Appointments at bottom panel");
    }
    public void fnPatientUtilisation(ConfigTestRunner configTestRunner,String graphType){

        configTestRunner.elementUtil.waitAndClick(getWebElement(formName, "PatientUtilizationCard", configTestRunner), Constants.AJAX_TIMEOUT);
        if (fnWaitForVisibility(getWebElement(formName, "CapacityandUtilizationPanel", configTestRunner), Constants.AJAX_TIMEOUT)) {
            configTestRunner.getChildTest().log(Status.PASS, "PATIENT UTILISATION card is selected successfully");
        } else
            configTestRunner.getChildTest().log(Status.FAIL, "PATIENT UTILISATION card is not selected successfully");
        if(getWebElement(formName,"PatientUtilizationMale",configTestRunner).isDisplayed() &&
                getWebElement(formName,"PatientUtilizationFemale",configTestRunner).isDisplayed() &&
                getWebElement(formName,"PatientUtilizationNeutral",configTestRunner).isDisplayed() ) {
            configTestRunner.getChildTest().log(Status.PASS, "Male,Female & Neutral option is available on Patient Utilization Card");
            configTestRunner.getChildTest().log(Status.INFO, "No of male patient present are: " + configTestRunner.elementUtil.fnParseNumber(getWebElement(formName, "PatientUtilizationMale", configTestRunner).getText()));
            configTestRunner.getChildTest().log(Status.INFO, "No of female patient present are: " + configTestRunner.elementUtil.fnParseNumber(getWebElement(formName, "PatientUtilizationFemale", configTestRunner).getText()));
            configTestRunner.getChildTest().log(Status.INFO, "No of female patient present are: " + configTestRunner.elementUtil.fnParseNumber(getWebElement(formName, "PatientUtilizationNeutral", configTestRunner).getText()));
        }else
            configTestRunner.getChildTest().log(Status.FAIL, "Male,Female & Neutral option is not available on Patient Utilization Card");
        //check the graph is plotted
        if(fnWaitForVisibility(getWebElement(formName,"CapcityVsDemand_Graph",configTestRunner),Constants.AJAX_TIMEOUT) &&
                fnWaitForVisibility(getWebElement(formName,"PatientUtilization_AgeRange",configTestRunner),Constants.AJAX_TIMEOUT) &&
                fnWaitForVisibility(getWebElement(formName,"PatientUtilization_appointment",configTestRunner),Constants.AJAX_TIMEOUT))
            configTestRunner.getChildTest().log(Status.PASS, "Patient Utilization graph is plotted for Time vs Appointments");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Patient Utilization graph is not plotted for Time vs Appointments");
        //check the graph is plotted in the capacity vs Demand Panel
        //select bar graph
        configTestRunner.getBlinkCommonMethod().fnSelectGrap(configTestRunner,graphType,"patientUtilisationGraphDropdown","patientUtilisationGraphDropdown");
        if(getWebElements(configTestRunner,formName,"PatientUtilizationPanel_Graph").size()>=1 &&
                getWebElements(configTestRunner,formName,"PatientUtilizationPanelGraphAgeRange").size()>=1 &&
                getWebElements(configTestRunner,formName,"PatientUtilizationPanelGraphAppointments").size()>=1)
            configTestRunner.getChildTest().log(Status.PASS, "Patient Utilization "+graphType+" graph is plotted for Time vs Appointments at bottom panel");
        else
            configTestRunner.getChildTest().log(Status.FAIL, "Patient Utilization "+graphType+" graph is not plotted for Time vs Appointments at bottom panel");
    }


}
