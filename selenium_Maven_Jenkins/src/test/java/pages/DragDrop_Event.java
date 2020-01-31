package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DragDrop_Event {
  @Test
  public void mouse() {
	  
	  try {
	  System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
//		 System.setProperty("webdriver.ie.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\IE\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
//		 WebDriver driver = new InternetExplorerDriver();

		 driver.get("http://demo.guru99.com/test/drag_drop.html");
		 driver.manage().window().maximize();
		 
		 // ------ Scroll - BEGIN ----------------------
		 	JavascriptExecutor js = (JavascriptExecutor) driver;
		 // This  will scroll down the page by  1000 pixel vertical		
	        js.executeScript("window.scrollBy(0,1000)");  // window.scrollBy(x-pixels,y-pixels)	      	        
		 // ------ Scroll - END ----------------------
		 
		//Element(BANK) which need to drag.		
     	 WebElement From1=driver.findElement(By.xpath("//*[@id='credit2']/a"));	
         
     	//Element(DEBIT SIDE) on which need to drop.		
     	WebElement To1=driver.findElement(By.xpath("//*[@id='bank']/li"));					
      
	//Element(SALES) which need to drag.		
     	WebElement From2=driver.findElement(By.xpath("//*[@id='credit1']/a"));
        
	//Element(CREDIT SIDE) on which need to drop.  		
     	WebElement To2=driver.findElement(By.xpath("//*[@id='loan']/li"));					
     
     	//Element(500) which need to drag.		
        WebElement From3=driver.findElement(By.xpath("//*[@id='fourth']/a"));					
        
        //Element(DEBIT SIDE) on which need to drop.		
     	WebElement To3=driver.findElement(By.xpath("//*[@id='amt7']/li"));					
         
	//Element(500) which need to drag.		
        WebElement From4=driver.findElement(By.xpath("//*[@id='fourth']/a"));					
        
        //Element(CREDIT SIDE) on which need to drop.		
     	WebElement To4=driver.findElement(By.xpath("//*[@id='amt8']/li"));					
      
	//Using Action class for drag and drop.		
     	Actions act=new Actions(driver);					

	//BANK drag and drop.
     	act.dragAndDrop(From1, To1).build().perform();
     	//act.dragAndDropBy(From1, 135, 40).build().perform();  // (source, xOffset, yOffset)  135, 40 browser 
     	//act.dragAndDropBy(From1, 120, 40).build().perform();  // (source, xOffset, yOffset)   120, 40 if browser maximized
     	//Note:  The pixels values (xOffset, yOffset) change with screen resolution and browser size. This method is hence not reliable and not widely used.
     	Thread.sleep(3000);  // 3 seconds need to add try and catch block
	//SALES drag and drop.		
     	act.dragAndDrop(From2, To2).build().perform();
     	Thread.sleep(3000);  // 3 seconds need to add try and catch block
	//500 drag and drop debit side.		
     	act.dragAndDrop(From3, To3).build().perform();	
     	Thread.sleep(3000);  // 3 seconds need to add try and catch block
	//500 drag and drop credit side. 		
     	act.dragAndDrop(From4, To4).build().perform();		
     	Thread.sleep(3000);  // 3 seconds need to add try and catch block
	//Verifying the Perfect! message.	
	if(driver.findElement(By.xpath("//a[contains(text(),'Perfect')]")).isDisplayed())							
     	{
         	System.out.println("Perfect Displayed !!!");					
     	}
		else
     	{
        	System.out.println("Perfect not Displayed !!!");					
     	}
	
	driver.quit();
	  }
	  catch(InterruptedException e) {
		  System.out.println("Exception found is " + e);
	  }
	         
  }
}
