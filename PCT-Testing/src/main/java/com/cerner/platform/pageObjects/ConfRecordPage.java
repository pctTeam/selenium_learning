/* Identify WebElements for use on the Template Page and any methods unique to action on this page */

//Note this page is just a copy example of using the login page 

package main.java.com.cerner.platform.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ConfRecordPage {

	@FindBy(id = "ion-ppr-confidentiality-header")
	public WebElement confRecrdHeadTxt;

	@FindBy(id = "confidentiality-continue-btn")
	public WebElement continueBtn;

	@FindBy(css = ".ion-ppr-confidentiality>p")
	public WebElement confParaText;
	
	@FindBy(id = "confidentiality-close-btn")
	public WebElement closeBtn;
	
}
