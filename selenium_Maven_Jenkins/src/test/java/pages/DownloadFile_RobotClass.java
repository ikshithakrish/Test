package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DownloadFile_RobotClass {
  
	// http://automationtesting.in/file-upload-using-robot-class-in-selenium/
	@Test
  public void download_RobotClass() throws InterruptedException, AWTException{
		System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");	
		 
		  String fileDownloadPath = "C:\\Users\\Avinash\\SeleniumDownload";
		  
		  Map<String, Object> prefsMap = new HashMap<String, Object>();
		  prefsMap.put("profile.default_content_settings.popups", 0);
		  prefsMap.put("download.default_directory", fileDownloadPath);
		  
		  ChromeOptions option = new ChromeOptions();
		  option.setExperimentalOption("prefs", prefsMap);
		  option.addArguments("--test-type");
		  option.addArguments("--disable-extensions");
		  
		  WebDriver driver  = new ChromeDriver(option);
		  driver.get("https://www.seleniumhq.org/download/");
	  	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	  driver.manage().window().maximize();
	  	  Thread.sleep(2000);
	  	  driver.findElement(By.linkText("64 bit Windows IE")).click();
	  	  
	  	  Robot robotAction = new Robot();
	  	  robotAction.delay(2000);
	  	StringSelection selection = new StringSelection("D:\\My Assignments\\Selenium_UI_AUTOMATION\\Upload & Download\\Download Files\\IE_Driver.zip");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
 
        robotAction.setAutoDelay(1000);
 
        robotAction.keyPress(KeyEvent.VK_CONTROL);
        robotAction.keyPress(KeyEvent.VK_V);
 
        robotAction.keyRelease(KeyEvent.VK_CONTROL);
        robotAction.keyRelease(KeyEvent.VK_V);
 
        robotAction.setAutoDelay(1000);
 
        robotAction.keyPress(KeyEvent.VK_ENTER);
        robotAction.keyRelease(KeyEvent.VK_ENTER);
        
	  	  System.out.println("Downloaded");  
  }
}
