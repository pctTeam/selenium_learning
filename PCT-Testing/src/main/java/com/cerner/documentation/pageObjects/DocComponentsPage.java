/* Identify WebElements for use on the Landing Page and any methods unique to action on this page */
package main.java.com.cerner.documentation.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class DocComponentsPage {

  // Locating Elements
  @FindBy(linkText = "Note manager")
  public WebElement noteManager;

  @FindBy(linkText = "Workflow Components")
  public WebElement workflowComponents;

  @FindBy(linkText = "Notes List")
  public WebElement notesList;

  @FindBy(linkText = "Diagnostics List")
  public WebElement diagnosticsList;

  @FindBy(linkText = "Pathology List")
  public WebElement pathologyList;

  @FindBy(linkText = "More Documents List")
  public WebElement moreDocumentsList;


}
