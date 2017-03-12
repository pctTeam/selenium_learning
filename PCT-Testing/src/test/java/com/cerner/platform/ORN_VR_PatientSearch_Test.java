/* Test Name: ORN_VR_PatientSearch */
/* Purpose: Verify the Patient Search and Encounter View components in Orion Test Harness */
/* Requirements: 807349, 753169, 753186, 753187 */
/* Change Control: //JW027642 November, 11th 2016 Initial Creation */

package test.java.com.cerner.platform;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;

import main.java.com.cerner.extentReporting.*;
import main.java.com.cerner.common.*;
import main.java.com.cerner.patientEncounterSelect.*;
import main.java.com.cerner.platform.pageObjects.*;
import main.java.com.cerner.settings.PCTTestSettings;

public class ORN_VR_PatientSearch_Test extends ExtentReporting {


  @BeforeTest
  public void setup() {
    PCTTestSettings.GetStarted();
  }

  @Test
  public void PatientSearch() throws InterruptedException, IOException, ParseException {

    // Initialize object and pages that will be used in the test
    PatientPage patient = PageFactory.initElements(driver, PatientPage.class);
    EncounterPage encounter = PageFactory.initElements(driver, EncounterPage.class);
    PatientSearchPage patientSrch = PageFactory.initElements(driver, PatientSearchPage.class);
    EncounterViewPage encounterView = PageFactory.initElements(driver, EncounterViewPage.class);
    ExtentVerifications verify = new ExtentVerifications();

    // Declare testname - used for reporting and json file
    testName = "ORN_VR_PatientSearch";

    // Testing using JSON file
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(
        projectFolderPath + File.separator + "DataFiles" + File.separator + testName + ".json"));

    // Start report for the test
    ExtentReporting.startReporting();

    // Declare variables
    String baseUrl = (String) jsonObject.get("url");
    String username = (String) jsonObject.get("username");
    String password = (String) jsonObject.get("password");
    String searchstring = "";
    String expectedResult = "";

    // Open Test Harness and login
    CommonActions.login(baseUrl, username, password);

    // Verify that X button clears patient search
    searchstring = (String) jsonObject.get("genericSearchString");
    CommonActions.enterText(patient.patientSearchTextBox, searchstring);
    CommonActions.clickElement(patient.xButton);
    verify.verifyFalse(patientSrch.patientSearchText().contains(searchstring),
        "Scenario 1 - X button clears out Patient Search text", "Patient Search text is empty");

    // Verify correct name returned using name search
    searchstring = (String) jsonObject.get("patientSearchStringName");
    expectedResult = (String) jsonObject.get("expectedFullName");
    patient.enterPatientsearchString(searchstring);
    verify.verifyWithScreen(patientSrch.findPatient(expectedResult),
        "Scenario 2 - Search Using Name", "Correct name in search results", "SearchResultbyName");

    // Verify correct name returned using FIN search
    searchstring = (String) jsonObject.get("patientSearchStringFIN");
    expectedResult = (String) jsonObject.get("expectedFullNameFIN");
    patient.enterPatientsearchString(searchstring);
    verify.verifyWithScreen(patientSrch.findPatient(expectedResult),
        "Scenario 3 - Search Using FIN", "Correct name in search results", "SearchResultbyFIN");


    // Verify correct name returned using MRN search
    searchstring = (String) jsonObject.get("patientSearchStringMRN");
    expectedResult = (String) jsonObject.get("expectedFullNameMRN");
    patient.enterPatientsearchString(searchstring);
    verify.verifyWithScreen(patientSrch.findPatient(expectedResult),
        "Scenario 4 - Search Using MRN", "Correct name in search results", "SearchResultbyMRN");

    // Select a patient - verify correct name in demographics bar of encounter results
    patient.selectPatient(expectedResult);
    String demoName = encounterView.getDemoBarName();
    verify.verifyWithScreen(demoName, expectedResult,
        "Scenario 5 - Demographics Name in encounter view", "Correct Name in demographics bar",
        "DemographicsName");

    // verify Encounter found
    CommonActions.clickElement(encounter.allButton);
    expectedResult = (String) jsonObject.get("expectedEncounter");
    verify.verifyWithScreen(encounterView.findEncounter(expectedResult),
        "Scenario 6 - Encounter Results ", "Correct encounter shown in All encounter results",
        "EncounterResult");

    // Writing out reporting for the test
    ExtentReporting.endReporting();
  }

}
