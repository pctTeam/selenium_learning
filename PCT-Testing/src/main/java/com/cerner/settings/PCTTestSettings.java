/* Identify WebElements for use on the Template Page and any methods unique to action on this page */

//Note this page is just a copy example of using the login page 

package main.java.com.cerner.settings;

import java.io.File;

import main.java.com.cerner.dataFile.JSONData;

public class PCTTestSettings extends JSONData {
  
  public static void GetStarted() {
    browserName = "chrome" ;
    String OS = System.getProperty("os.name");
    
    if (OS.contains("Windows")) {
        reportPath = File.separator + File.separator + "hnagroup" + File.separator + "PMO"
                + File.separator + "Program Management" + File.separator + "Project GO" + File.separator
                + "Testing" + File.separator + "TestNG Selenium Project Reports";
      }
      if (OS.equals("Mac OS X")) {
          reportPath = "smb://hnagroup/PMO/Program Management/Project GO/Testing/TestNG Selenium Project Reports";
      }
  }
}