package test.java.com.cerner.platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.java.com.cerner.common.CommonActions;
import main.java.com.cerner.extentReporting.ExtentReporting;
import main.java.com.cerner.extentReporting.ExtentVerifications;
import main.java.com.cerner.platform.pageObjects.ConfRecordPage;
import main.java.com.cerner.settings.PCTTestSettings;

/**
 * @author am050704
 */
//Every class should extend ExtentReporting for the reporting purpose
public class ORN_VR_DemoTC extends PCTTestSettings{
	
	@BeforeTest
	public void inti()
	{
		//Initiating browser name and report path
		PCTTestSettings.GetStarted();
	}
	
	@Test
	public void demoTest() throws FileNotFoundException, IOException, ParseException{
		
		testName = "ORN_VR_DemoTC";
		//Accessing the elements from the Object class using the crObj
		ConfRecordPage crObj = PageFactory.initElements(driver, ConfRecordPage.class);
		ExtentVerifications verify = new ExtentVerifications();
		
		//Create a JSON parser object
		JSONParser jp = new JSONParser();
		JSONObject jObj = (JSONObject) jp.parse(new FileReader(
		        projectFolderPath + File.separator + "DataFiles" + File.separator + testName + ".json"));
		
		//Start Reporting
		ExtentReporting.startReporting();

	    // Declare variables
	    String baseUrl = (String) jObj.get("url");
	    String username = (String) jObj.get("username");
	    String password = (String) jObj.get("password");
	    String patient = (String) jObj.get("patient");
	    String encounter = (String) jObj.get("encounter");
	    String confMsg = (String) jObj.get("confMsg");
	    String confHeader = (String) jObj.get("confHeader");
	    String imageName = "";
	    

	    //CommonActions.login(baseUrl, username, password);
	    try {
			CommonActions.loginGoToPatientEncounter(baseUrl, username, password, patient, encounter);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //CommonActions.loginGoToPatientEncounter(baseUrl, username, password, patient, encounter);
	    
	    imageName = "Step-1-ConfHeader-1";
	    verify.verifyWithScreen(crObj.confRecrdHeadTxt.getText(), confHeader, "Step 1", "Verifying Header", imageName);
	    
	    //imageName = "Step-1-ConfHeader-2";
	    verify.verify(crObj.confParaText.getText(), confMsg, "Step 2", "Verifying The Text");
	    
	    /* Chain Verification, The Verify function returns the class name, hence can do multiple verification
	    verify
	    .verifyWithScreen(crObj.confRecrdHeadTxt.getText(), confHeader, "Step 1", "Verifying Header", "Step 1")
	    .verify(crObj.confParaText.getText(), confMsg, "Step 1", "Verifying Header");
	    */
	    
	   
	    // Writing out reporting for the test
	    ExtentReporting.endReporting();
		
	}

}
