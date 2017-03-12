/*
 * Identify WebElements for use on the Patient Search Page and any methods unique to actions on this
 * page
 */

package main.java.com.cerner.platform.pageObjects;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class PatientSearchPage {

  // Locate the 'patient search text box'
  @FindBy(id = "ion-patient-search-text-box")
  public WebElement patientSearchTextBox;

  // Locate the "x" button
  @FindBy(css = "button.icon-dismiss")
  public WebElement xButton;

  // List of all patient results
  @FindBy(css = ".ion-patient-search-result-detail > h5")
  public List<WebElement> patientResultElements;

  // Used to validate text in Patient Search box
  public String patientSearchText() {
    return patientSearchTextBox.getText();
  }

  // Method to click on the "x" button
  public void clickXButton() {
    try {
      xButton.click();
    } catch (ElementNotVisibleException e) {
    }
  }

  // Find name in initial list of patient search results. Return Boolean if found or not
  public Boolean findPatient(String expectedFullName) {
    for (WebElement patientElement : patientResultElements) {
      if (patientElement.getText().contains(expectedFullName)) {
        return true;
      }
    }
    return false;
  }

}
