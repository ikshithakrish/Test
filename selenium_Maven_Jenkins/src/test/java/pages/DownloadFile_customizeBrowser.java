package pages;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DownloadFile_customizeBrowser {
  
	@Test
  public void downloadFiles() {
		// Referecence :  https://www.inviul.com/download-file-selenium/
		 try {
//		 System.setProperty("webdriver.ie.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\IE\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
//		 WebDriver driver = new InternetExplorerDriver();
		 
		 
		  
		  String fileDownloadPath = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Upload & Download\\Download Files";
		  String defaultPath = "C:\\Users\\KarthIkshithaKrish\\Downloads";
		  
		  Map<String, Object> prefsMap = new HashMap<String, Object>();
		  prefsMap.put("profile.default_content_settings.popups", 0);  // Disable popups
		  prefsMap.put("download.default_directory", fileDownloadPath);
		  
		  ChromeOptions option = new ChromeOptions();
		  option.setExperimentalOption("prefs", prefsMap);
		  option.addArguments("--test-type");
		  option.addArguments("--disable-extensions");
		  
		  System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		  WebDriver driver = new ChromeDriver(option);
		  driver.get("https://www.seleniumhq.org/download/");
	  	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	  driver.manage().window().maximize();
	  	  Thread.sleep(2000);
	  	  driver.findElement(By.linkText("64 bit Windows IE")).click();
	  	  System.out.println("Downloaded");
	  	System.out.println("map value before clear" + prefsMap);
	  	  prefsMap.clear();
	  	  option = null;
	  	option = new ChromeOptions();
	  	  
	  	 System.out.println("map value after clear" + prefsMap);
	  	  // reset default path value not working ---- need to check 
	  	  prefsMap.put("download.default_directory", defaultPath);
	  	  prefsMap.put("profile.default_content_settings.popups", 0);  // Disable popups
	  	  option.setExperimentalOption("prefs", prefsMap);
	  	  option.addArguments("--test-type");
		  option.addArguments("--disable-extensions");
		  
		  System.out.println("map value after reset the " + prefsMap);
	  	  System.out.println("Updated to default path");
	  	  
		 }
		 catch(Exception e) {
			 System.out.println("Exception found " + e); 
		 }
	  	  
  }
}
