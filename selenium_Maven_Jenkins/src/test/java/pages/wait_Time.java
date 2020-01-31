package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class wait_Time {
	/*
	 * ElementNotVisibleException - Using Waits, we can resolve this problem.
	 * Most of the web applications are developed using Ajax and Javascript. When a page is loaded by the browser the elements which we want to interact with may load at different time intervals.
	 * find an element which has some "ExpectedConditions "(Explicit Wait), If the element is not located within the time frame defined by the Explicit wait(10 Seconds)find an element which has some "ExpectedConditions "(Explicit Wait), If the element is not located within the time frame defined by the Explicit wait(10 Seconds)
	 * 
	 * 
	 *  implicit wait:
	 *  --------------
	 *  It will tell to the web driver to wait for certain amount of time before it throws a "No Such Element Exception"
	 *  default setting is 0. Once we set the time, web driver will wait for that time before throwing an exception.
	 *  SYNTAX: driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
	 *  EXAMPLE: driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 *  
	 *  Explicit wait:
	 *  -------------
	 *  The explicit wait is used to tell the Web Driver to wait for certain conditions (Expected Conditions) or the maximum time exceeded before throwing an "ElementNotVisibleException" exception.
	 *  The explicit wait is an intelligent kind of wait, but it can be applied only for specified elements. Explicit wait gives better options than that of an implicit wait as it will wait for dynamically loaded Ajax elements.
	 *  Once we declare explicit wait we have to use "ExpectedCondtions" or we can configure how frequently we want to check the condition using Fluent Wait. These days while implementing we are using Thread.Sleep() generally it is not recommended to use
	 *  
	 *  SYNTAX: WebDriverWait wait = new WebDriverWait(WebDriverRefrence,TimeOut);
	 *  EXAMPLE: WebDriverWait wait=new WebDriverWait(driver, 20);
	 *  
	 *  WebElement linkElement;
		linkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/section/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/a/i")));
		linkElement.click();
	 *  The above Java code states that we are waiting for an element for the time frame of 20 seconds as defined in the "WebDriverWait" class on the webpage until the "ExpectedConditions" are met and the condition is "visibilityofElementLocated".

		The following are the Expected Conditions that can be used in Explicit Wait
		
		alertIsPresent()
		elementSelectionStateToBe()
		elementToBeClickable()
		elementToBeSelected()
		frameToBeAvaliableAndSwitchToIt()
		invisibilityOfTheElementLocated()
		invisibilityOfElementWithText()
		presenceOfAllElementsLocatedBy()
		presenceOfElementLocated()
		textToBePresentInElement()
		textToBePresentInElementLocated()
		textToBePresentInElementValue()
		titleIs()
		titleContains()
		visibilityOf()
		visibilityOfAllElements()
		visibilityOfAllElementsLocatedBy()
		visibilityOfElementLocated()
		
		Fluent Wait
		The fluent wait is used to tell the web driver to wait for a condition, as well as the frequency with which we want to check the condition before throwing an "ElementNotVisibleException" exception.
		Frequency: Setting up a repeat cycle with the time frame to verify/check the condition at the regular interval of time
		
		Let's consider a scenario where an element is loaded at different intervals of time. The element might load within 10 seconds, 20 seconds or even more then that if we declare an explicit wait of 20 seconds. It will wait till the specified time before throwing an exception. In such scenarios, the fluent wait is the ideal wait to use as this will try to find the element at different frequency until it finds it or the final timer runs out.

		Syntax:
		
		Wait wait = new FluentWait(WebDriver reference)
		.withTimeout(timeout, SECONDS)
		.pollingEvery(timeout, SECONDS)
		.ignoring(Exception.class);
		Above code is deprecated in Selenium v3.11 and above. You need to use
		
		Wait wait = new FluentWait(WebDriver reference).withTimeout(Duration.ofSeconds(SECONDS)).pollingEvery(Duration.ofSeconds(SECONDS)).ignoring(Exception.class);
		//withTimeout(Duration.ofSeconds(SECONDS)) - max wait time, pollingEvery(Duration.ofSeconds(SECONDS)) - check every mentioned seconds
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		// max wait time 30 seconds and check if the element is exist for every 5 seconds if found it can ignore the check  before 30 seconds else throw the exception
		
		ImplicitWait VS ExplicitWait:
		1. Implicit Wait time is applied to all the elements in the script
		2. In Implicit Wait, we need not specify "ExpectedConditions" on the element to be located
		3. It is recommended to use when the elements are located with the time frame specified in implicit wait
		
		1. Explicit Wait time is applied only to those elements which are intended by us
		2. In Explicit Wait, we need to specify "ExpectedConditions" on the element to be located
		3. It is recommended to use when the elements are taking long time to load and also for verifying the property of the element like(visibilityOfElementLocated, elementToBeClickable,elementToBeSelected)
		 
	 */
	
  @Test
  public void selenium_Wait() {
	  
	  System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
//		 System.setProperty("webdriver.ie.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\IE\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
//		 WebDriver driver = new InternetExplorerDriver();
		 
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			String eTitle = "Demo Guru99 Page";
			String aTitle = "";
			// launch Chrome and redirect it to the Base URL
			driver.get("http://demo.guru99.com/test/guru99home/");
			//Maximizes the browser window
			driver.manage().window().maximize();
			//get the actual value of the title	 
			
			
  }
}
