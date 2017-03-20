/* Test Name: ORN_VR_Template */
/* Purpose: Template to Start in Orion Test Harness */
/* Requirements: 807349, 753169, 753186, 753187 */
/* Change Control: //JW027642 November, 11th 2016 Initial Creation */


package test.java.com.cerner.platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
import main.java.com.cerner.dataFile.JSONData;

@SuppressWarnings("unused")
public class ORN_VR_BaseTemplate extends PCTTestSettings {

  @BeforeTest
  public void setup() throws IOException, ParseException{
    testName= this.getClass().getSimpleName();
    PCTTestSettings.GetStarted();
    JSONData.initJson(testName);
  }

  @Test
  public void BaseTemplate() throws InterruptedException, IOException {

    // Initialize web elements from the pages that will be used in the test
    ExtentVerifications verify = new ExtentVerifications();
    TemplatePage tempView = PageFactory.initElements(driver, TemplatePage.class);
   
    // Start report for the test
   // ExtentReporting.startReporting();

    // Declare variables
    String baseUrl = (String) jObj.get("url");
    String username = (String) jObj.get("username");
    String password = (String) jObj.get("password");
    String patient = (String) jObj.get("patient1");
    String encounter = (String) jObj.get("encounter1");
    //Start a test by logging in, search for a select a patient and encounter
    CommonActions.loginGoToPatientEncounter(baseUrl, username, password, patient, encounter);
    
    
    //Changing to a different patient and encounter for component
    //if you repeat the same url it will not log you out, if different url you are logged out
   patient = (String) jObj.get("patient2");
    encounter = (String) jObj.get("encounter2");
    CommonActions.goToPatientEncounter(baseUrl, patient, encounter);
    
    //Change users same url (could change url if needed)
    username = (String) jObj.get("username2");
    password = (String) jObj.get("password2");
    CommonActions.changeUsers(baseUrl, username, password);
    
    
    //Navigate to specific date on calendar (since new url have to login and again)
    baseUrl = (String) jObj.get("url3");
    CommonActions.login(baseUrl, username, password);

    //tempView.printMe();
    

    // Writing out reporting for the test
   // ExtentReporting.endReporting();
  }

}
