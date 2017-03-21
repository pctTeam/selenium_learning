/**
 * The purpose of this test plan is to validate the confidential record message in the Established 
 * user relationship section of the application.
 */

package test.java.com.cerner.platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.java.com.cerner.common.CommonActions;
import main.java.com.cerner.extentReporting.ExtentReporting;
import main.java.com.cerner.extentReporting.ExtentVerifications;
import main.java.com.cerner.platform.pageObjects.ConfRecordPage;
import main.java.com.cerner.platform.pageObjects.EncounterViewPage;
import main.java.com.cerner.platform.pageObjects.EstdUserRelation;
import main.java.com.cerner.platform.pageObjects.PatientSearchPage;
import main.java.com.cerner.settings.PCTTestSettings;

/**
 * @author am050704
 */

public class PCT_VR_EUR_ConfWarningChallenge extends PCTTestSettings{

	@BeforeTest
	public void inti(){
		PCTTestSettings.GetStarted();
	}
	
	@Test
	public void ConfWarningChallenge() throws FileNotFoundException, IOException, ParseException, InterruptedException{
		testName = "PCT_VR_EUR_ConfWarningChallenge";
		
		//Accessing the elements from the Object class
		ConfRecordPage crObj = PageFactory.initElements(driver, ConfRecordPage.class);
		EstdUserRelation eurObj = PageFactory.initElements(driver, EstdUserRelation.class);
		EncounterViewPage enObj = PageFactory.initElements(driver, EncounterViewPage.class);
		PatientSearchPage patObj = PageFactory.initElements(driver, PatientSearchPage.class);
		
		ExtentVerifications verify = new ExtentVerifications();
		
		//Create a JSON Parser
		JSONParser jp = new JSONParser();
		JSONObject jObj = (JSONObject) jp.parse(new FileReader(
		        projectFolderPath + File.separator + "DataFiles" + File.separator + testName + ".json"));
		
		//Start Extent Reporting
		ExtentReporting.startReporting();
		
		//Declare Variables
		String baseUrl = (String) jObj.get("url");
		String username = (String) jObj.get("username");
	    String password = (String) jObj.get("password");
	    String patient = (String) jObj.get("patient");
	    String patient2 = (String) jObj.get("patient2");
	    String encounter = (String) jObj.get("encounter");
	    String confMsg = (String) jObj.get("confMsg");
	    String confHeader = (String) jObj.get("confHeader");
	    String encTitle = (String) jObj.get("encTitle");
	    String eurHeader = (String) jObj.get("eurHeader");
	    
	    //Search Patient and Select Encounter
	    CommonActions.loginGoToPatientEncounter(baseUrl, username, password, patient2, encounter);
	    
	    //**Alert Accept : Function pending
	    //**On click of an encounter, there is a alert with OK button, how to accept the alert?
	    
	    //Step 2: Verify Confidential Record Page
	    verify.verifyWithScreen(crObj.confRecrdHeadTxt.getText(), confHeader, "Step 2", "Verifying Confidential Record Header", 
	    		"Step 2 - Confidential Record Page");
	    verify.verify(crObj.confParaText.getText(), confMsg, "Step 2", "Verifying Confidential Record Paragraph text");
	    //**Close and Continue Button not verified
	    
	    
	    //Step 3: Click on Close Button in the Confidential Record page
	    CommonActions.clickElement(crObj.closeBtn);
	    verify.verifyWithScreen(enObj.encounterTitle.getText(), encTitle, "Step 3", "Verifying Encounter View Page", 
	    		"Step 3 - Encounter View Page");
	    
	    //Step 4: Select Encounter
	    CommonActions.goToPatientEncounter(baseUrl, patient2, encounter);
	    //**Accept Encounter Alert
	    verify.verifyWithScreen(crObj.confRecrdHeadTxt.getText(), confHeader, "Step 4", "Verifying Confidential Record Header", 
	    		"Step 4 - Confidential Record Page");
	    
	    //Step 5: Select Continue text in the Confident record page and verify the EUR Page
	    CommonActions.clickElement(crObj.continueBtn);
	    verify.verifyWithScreen(eurObj.EURHeader.getText(), eurHeader, "Step 5", "Presence of EUR Page", 
	    		"Step 5 - Establish User Relationship");
	    verify.verify(eurObj.patientName.getText(), patient, "Step 5", "Verifying the Patient name in the EUR Page");
	    
	    //**String continueBtn = eurObj.continueBtn.getAttribute("disabled");
	    
	    
	    //Step 6: Select a relation and continue & Verify the chart
	    eurObj.selectPpr("Admitting Physician");
	    CommonActions.clickElement(eurObj.continueBtn);
	    //** Accepting and verifying the Chart pop-up
	    
	    
	    //Step 7: Logout of the Application
	    
	    //Step 8: Re-login and check the confidential message
	    CommonActions.changeUsers(baseUrl, username, password);
	    CommonActions.goToPatientEncounter(baseUrl, patient2, encounter);
	    
	    //**Alert Accept : Function pending
	    verify.verifyWithScreen(crObj.confRecrdHeadTxt.getText(), confHeader, "Step 8", "Verifying Confidential Record Header", 
	    		"Step 8 - Confidential Record Page");
	    //**Close and Continue Button not verified
	    
	    //Step 9: Click Continue and check the patient chart opens
	    CommonActions.clickElement(crObj.continueBtn);
	    
	    boolean result = eurObj.eurPresence(eurHead);
	    verify.verifyFalse(result, "Step 9", "Verifying the absence of the EUR Page");
	    
	    //**How to check absence of the EUR page
	    
	    
		//Stop Extent Reporting
		ExtentReporting.endReporting();
		
	}
	
}
