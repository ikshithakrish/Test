package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Scroll_MouseEvent {
  @Test
  public void scrollEvent() {
	  try {
		  System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
			 WebDriver driver = new ChromeDriver();
//			 System.setProperty("webdriver.ie.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\IE\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
//			 WebDriver driver = new InternetExplorerDriver();
			 
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 
			// Launch the application		
		        driver.get("http://demo.guru99.com/test/guru99home/");
		        driver.manage().window().maximize();
		     // This  will scroll down the page by  1000 pixel vertical		
//		        js.executeScript("window.scrollBy(0,1000)");
//		        System.out.println("Scrolled from pixel 0 to 1000");
//		        Thread.sleep(3000);
//		        System.out.println("waited for 3 seconds");
		        
		      //Find element by link text and store in variable "Element"        		
		        WebElement Element = driver.findElement(By.linkText("Linux"));

		        //This will scroll the page till the element is found		
		        js.executeScript("arguments[0].scrollIntoView();", Element);
		        System.out.println("Scroll till the element mentioned");
		        Thread.sleep(6000);
		        System.out.println("waited for 3 seconds");
		        
		      //This will scroll the web page till end.		
		        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		        System.out.println("Scroll till the page bottom");
		        Thread.sleep(3000);
		        System.out.println("waited for 3 seconds");
		        // both window.scrollBy and scroll commands are working as expected can use either one of them
		        //js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		        js.executeScript("scroll(0, -document.body.scrollHeight)");
		        System.out.println("Scroll till the page top");
		        Thread.sleep(3000);
		        System.out.println("waited for 3 seconds");
		        
		        driver.quit();
		        
		        // scroll horizontal (left <-> right)
		       // System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		        WebDriver driver1 = new ChromeDriver();
		        JavascriptExecutor js1 = (JavascriptExecutor) driver1;
				 
				// Launch the application		
		        driver1.get("http://demo.guru99.com/test/guru99home/scrolling.html");
		        driver1.manage().window().maximize();
		        WebElement Element1 = driver1.findElement(By.linkText("VBScript"));
		        WebElement Element2 = driver1.findElement(By.xpath("//*[@id=\"rt-feature\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div/div[1]/div/div/h4/a"));

		        //This will scroll the page Horizontally till the element is found		
		        js1.executeScript("arguments[0].scrollIntoView();", Element1);
		        Thread.sleep(8000);
		        js1.executeScript("arguments[0].scrollIntoView();", Element2);		        
		        Thread.sleep(6000);
		        driver1.quit();
		        
		        
	  }
	  catch(Exception e) {
		  
	  }
  }
}
