package main.java.com.cerner.platform.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EstdUserRelation {
	
	@FindBy(id = "ion-ppr-establish-relationship-header")
	public WebElement EURHeader;
	
	@FindBy(css = ".mfp-close.icon-dismiss")
	public WebElement dismissBtn;
	
	@FindBy(css = ".btn.btn-primary.ion-ppr-continue-button")
	public WebElement continueBtn;
	
	@FindBy(id = "ion-ppr-justification-textarea")
	public WebElement justificationText;
	
	@FindBy(css = ".demographics-row>h1")
	public WebElement patientName;
	
	@FindBy(css = ".ion-ppr-establish-relationship-list-item")
	public List<WebElement> eurList;
	
	
	//Click on the PPR
	public void selectPpr(String expectedPPR) {
		for (WebElement eurPpr : eurList) {
			if (eurPpr.getText().contains(expectedPPR)) {
				eurPpr.click();
				break;
			}
		}
	}
	
	public boolean isElementPresent(WebElement eurHead){
		if (eurHead.contains(EURHeader)) {
			return true;
		}
		return false;
	}
	
	/*
	public boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	*/
	
}