package temp;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import constants.Constants;
import util.SeleniumActions_COPY;


public class Test1 {
	
	public static String reportPath = null;
	public static String check = "test1";
	
	public static void main(String a[]) {
		
		try {
			
		
		 Robot robot = new Robot();
		 System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	 
	        driver.get("http://demo.automationtesting.in/Register.html");
	        driver.manage().window().maximize();
	        // driver.findElement(By.id("imagesrc")).click();
	        /* ERROR | 2020-01-21 22:49:50 | [main] pages.UploadDownload (UploadDownload.java:87) - error found org.openqa.selenium.InvalidArgumentException: invalid argument
  (Session info: chrome=79.0.3945.117) */
	        
	        driver.findElement(By.id("imagesrc")).click();
	        driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		}
		catch(AWTException e) {
			System.out.println("------- " + e);
		}
	
	}
	}

