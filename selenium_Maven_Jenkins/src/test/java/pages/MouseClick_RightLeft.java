package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MouseClick_RightLeft  {
  @Test
  public void clickRightLeft() throws InterruptedException{  // Thread.sleep(*)
	  
	  System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
//		 System.setProperty("webdriver.ie.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\IE\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
//		 WebDriver driver = new InternetExplorerDriver();

	// Double click 
	  driver.get("http://demo.guru99.com/test/simple_context_menu.html");
	  driver.manage().window().maximize();
	  //Double click the button to launch an alertbox
	  Actions action = new Actions(driver);
	  WebElement link =driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
	  action.doubleClick(link).perform();
	  Thread.sleep(5000);  // 5 seconds
	  //Switch to the alert box and click on OK button
	  Alert alert = driver.switchTo().alert();
	  System.out.println("Alert Text\n" +alert.getText());
	  alert.accept();
	  //Closing the driver instance
	  
	  Thread.sleep(10000); // 10 seconds
	  
	  
	// Right click the button to launch right click menu options
	  WebElement link1 = driver.findElement(By.cssSelector(".context-menu-one"));
	  action.contextClick(link1).perform();
	  // Click on Edit link on the displayed menu options
	  WebElement element = driver.findElement(By.cssSelector(".context-menu-icon-copy"));
	  element.click();
	  Thread.sleep(5000);  // 5 seconds
	  // Accept the alert displayed
	  driver.switchTo().alert().accept();
	  Thread.sleep(5000);  // 5 seconds
	  driver.quit();
	  
	  
	  
  }
}
