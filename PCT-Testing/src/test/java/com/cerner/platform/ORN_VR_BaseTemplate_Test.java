/* Test Name: ORN_VR_Template */
/* Purpose: Template to Start in Orion Test Harness */
/* Requirements: 807349, 753169, 753186, 753187 */
/* Change Control: //JW027642 November, 11th 2016 Initial Creation */


package test.java.com.cerner.platform;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.java.com.cerner.extentReporting.*;
import main.java.com.cerner.login.*;
import main.java.com.cerner.platform.pageObjects.TemplatePage;
import main.java.com.cerner.settings.PCTTestSettings;
import main.java.com.cerner.common.*;

@SuppressWarnings("unused")
public class ORN_VR_BaseTemplate_Test extends ExtentReporting {

  @BeforeTest
  public void setup() {
    PCTTestSettings.GetStarted();
  }

  @Test
  public void BaseTemplate() throws InterruptedException, IOException, ParseException {

    // Initialize web elements from the pages that will be used in the test
    TemplatePage tempView = PageFactory.initElements(driver, TemplatePage.class);

    // Declare testname - used for reporting and Excel/json file
    ExtentVerifications verify = new ExtentVerifications();
    testName = "ORN_VR_BaseTemplate";

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
    String patient = (String) jsonObject.get("patient1");
    String encounter = (String) jsonObject.get("encounter1");

    CommonActions.loginGoToPatientEncounter(baseUrl, username, password, patient, encounter);

    // Writing out reporting for the test
    ExtentReporting.endReporting();
  }

}
