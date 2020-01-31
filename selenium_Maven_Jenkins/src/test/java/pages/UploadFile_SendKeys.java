package pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import util.ExcelHandler_COPY;
import util.SeleniumActions_COPY;

public class UploadFile_SendKeys {
	
	private static final Logger LOGGER = LogManager.getLogger(UploadFile_SendKeys.class);
	public static int ROW_NUMBER = 0;

	public static List<String> SINGLE_ROW_DATA = null;
	public static List<String>[] ALL_ROW_DATA  = null;   // store all data for the respective ROW(s) (i.e., t)

	public static String EXCEL_FILE = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\UploadDownload.xlsx";
	public static String SHEET_NAME = "UploadDownload";
	
	
  @Test
  public void upload() {
	// http://automationtesting.in/file-upload-using-robot-class-in-selenium/  
	 try {
		
		 //Robot robot = new Robot();
		 System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
//		 System.setProperty("webdriver.ie.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\IE\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
//		 WebDriver driver = new InternetExplorerDriver();
	       
	        driver.get("http://demo.automationtesting.in/Register.html");
	        driver.manage().window().maximize();
	        driver.findElement(By.id("imagesrc")).sendKeys("D:\\My Assignments\\Selenium_UI_AUTOMATION\\Upload & Download\\test.txt");
	       // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        Thread.sleep(5000);  // wait for 5 seconds
	        driver.quit();
//	        robot.setAutoDelay(1000);
//	 
//		 robot.setAutoDelay(40000);  // 32 secs(32000 ms)
//		 StringSelection uploadFrom = new StringSelection("D:\\My Assignments\\Selenium_UI_AUTOMATION\\Upload & Download\\test.txt");
//		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(uploadFrom, null);
//		 robot.setAutoDelay(1000); // 1 sec
//		 robot.keyPress(KeyEvent.VK_CONTROL); // PRESS Control key
//		 robot.keyPress(KeyEvent.VK_V); // PRESS V key
//		 
//		 robot.keyRelease(KeyEvent.VK_CONTROL); // Release Control key
//		 robot.keyRelease(KeyEvent.VK_V);  // Release V key
//		 
//		 robot.setAutoDelay(1000); // 1 sec
//		 
//		 robot.keyPress(KeyEvent.VK_ENTER); // PRESS Enter key		 
//		 robot.keyRelease(KeyEvent.VK_ENTER); // Release Enter key
//		 
		 
	 }
	 catch(Exception e) {
		LOGGER.error("error found " + e); 
	 }
  }
}
