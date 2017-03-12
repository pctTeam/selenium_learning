/*
 * Identify WebElements for use on the Encounter Select Page and any methods unique to actions on
 * this page
 */package main.java.com.cerner.platform.pageObjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EncounterViewPage {

  // Locate name in Demographics bar
  @FindBy(css = ".demographics-row > h1")
  public WebElement demographicsName;

  // Locate the "Active" button on the segmented control
  @FindBy(css = "button.ion-encounters-active-btn")
  public WebElement activeButton;

  // Locate the "Discharged" button on the segmented control
  @FindBy(css = "button.ion-encounters-discharged-btn")
  public WebElement dischargedButton;

  // Locate the "Future" button on the segmented control
  @FindBy(css = "button.ion-encounters-future-btn")
  public WebElement futureButton;

  // Locate the "All" button on the segmented control
  @FindBy(css = "button.ion-encounters-all-btn")
  public WebElement allButton;

  // Locate the back arrow (<--) button
  @FindBy(css = "button.icon-arrow-left")
  public WebElement backButton;

  // List of all encounters
  @FindBy(css = ".ion-encounter-detail > h6")
  public List<WebElement> encounterElements;


  // Method to get name in Demographics bar
  public String getDemoBarName() {
    return demographicsName.getText();
  }

  // Find encounter in list of encounter results
  public Boolean findEncounter(String expectedEncounter) {
    for (WebElement encElement : encounterElements) {
      if (encElement.getText().contains(expectedEncounter)) {
        return true;
      }
    }
    return false;
  }

}
