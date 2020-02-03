package pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumGrid {
	//  Ref: https://www.browserstack.com/guide/selenium-grid-tutorial
	// http://www.software-testing-tutorials-automation.com/2016/04/running-multiple-nodes-of-selenium-grid.html
	// Hub config: http://www.software-testing-tutorials-automation.com/2016/02/selenium-grid-2-configuration-setup-hub.html
	// Node config in HUB: http://www.software-testing-tutorials-automation.com/2016/03/how-to-configure-selenium-grid-2-node.html
	
	/*
	command to enable HUB
	cd D:\My Assignments\Selenium_UI_AUTOMATION\Selenium Grid\HUB  (selenium-server-standalone-3.141.59.jar is located)
	java -jar selenium-server-standalone-3.141.59.jar -port 4444 -role hub
	
	command to enable NODE(s) - opend another command prompt
	cd D:\My Assignments\Selenium_UI_AUTOMATION\Selenium Grid\HUB
	java -jar selenium-server-standalone-2.52.0.jar -role node -Dwebdriver.ie.driver="D:/IEDriverServer.exe" -Dwebdriver.chrome.driver="D:/chromedriver.exe" -hub http://localhost:4444/grid/register -port 5566
	
	// set webdriver
	D:\My Assignments\Selenium_UI_AUTOMATION\Selenium Grid\HUB>
	java -Dwebdriver.ie.driver="D:\IEDriverServer.exe" -Dwebdriver.chrome.driver="D:\chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://localhost:4444/grid/register -port 5566
	*/
	//Note: Hub should be up and running then only node can be accessible
	//how to create bat file
	
	WebDriver driver = null;
	 String baseUrl = null;
	 String nodeUrl = null;
	  
	 @BeforeTest
	 public void setUp() throws MalformedURLException {  // import java.net.MalformedURLException;
		 DesiredCapabilities chromeCapability =  DesiredCapabilities.chrome();
		  chromeCapability.setBrowserName("chrome");
		  chromeCapability.setPlatform(Platform.WINDOWS);
		  
		  ChromeOptions options = new ChromeOptions();
		    options.merge(chromeCapability);
		    
		  baseUrl = "https://www.google.com/";
		  nodeUrl = "http://192.168.225.41:4444/wd/hub";
		  driver = new RemoteWebDriver(new URL(nodeUrl), chromeCapability); // import java.net.URL; and import org.openqa.selenium.remote.RemoteWebDriver;
		  
	 }
	
	 @AfterTest	
	 public void afterTest() {
		 driver.quit();
	 }
  @Test
  public void gridSelenium() {
	driver.get(baseUrl);
	
  }
  
  
  
  
}
