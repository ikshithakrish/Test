package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.function.Function;


public class AjaxCall_Handling {
	
  @Test
  public void AjaxCall_test() {
	  	String URL = "http://demo.guru99.com/test/ajax.html";		
		WebDriver driver;
		WebDriverWait wait;
		
	  System.setProperty("webdriver.chrome.driver","D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		//create chrome instance
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(URL);
		By container = By.cssSelector(".container");
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(container));
		
		//Get the text before performing an ajax call
		WebElement noTextElement = driver.findElement(By.className("radiobutton"));
		String textBefore = noTextElement.getText().trim();
		
		//Click on the radio button (yes)
		driver.findElement(By.id("yes")).click();
	
		//Click on Check Button
		driver.findElement(By.id("buttoncheck")).click();
		
		/*Get the text after ajax call*/
		WebElement TextElement = driver.findElement(By.className("radiobutton"));
		wait.until(ExpectedConditions.visibilityOf(TextElement));
		String textAfter = TextElement.getText().trim();
		
		/*Verify both texts before ajax call and after ajax call text.*/
		Assert.assertNotEquals(textBefore, textAfter);
		System.out.println("Ajax Call Performed for the option yes");
		
		String expectedText = "Radio button is checked and it's value is Yes";
		
		/*Verify expected text with text updated after ajax call*/
		Assert.assertEquals(textAfter, expectedText);
		
		
		//Click on the radio button (no)
		driver.findElement(By.id("no")).click();			
		//Click on Check Button
		driver.findElement(By.id("buttoncheck")).click();
		
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(Exception.class);
		
		WebElement TextElement1 = fluentWait.until(new Function<WebDriver, WebElement>(){
			
			public WebElement apply(WebDriver driver ) {
				return driver.findElement(By.className("radiobutton"));
			}
		});
		
		textAfter = TextElement1.getText().trim();
		expectedText = "Radio button is checked and it's value is No";
		Assert.assertNotEquals(textBefore, textAfter);
		System.out.println("Ajax Call Performed for the option No");
		
		driver.close();
  }
}
