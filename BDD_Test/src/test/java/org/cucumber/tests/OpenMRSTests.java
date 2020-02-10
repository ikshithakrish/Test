package org.cucumber.tests;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;


@CucumberOptions(
		features="src/test/resources/features",
		glue={"org.cucumber.stepdefs"},
		plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json" }
// Note: format is deprecate so plz replace with monochrome = true,plugin = {"html:target/cucumber-html-report", "json:target/cucumber-json-report.json" }
		)

public class OpenMRSTests {
	public static WebDriver driver;
	private TestNGCucumberRunner testRunner;
	
  @BeforeClass
  public void setUP()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Download\\Chrome\\79.0.3945.36\\chromedriver.exe");
		driver = new ChromeDriver();
		testRunner = new TestNGCucumberRunner(OpenMRSTests.class);
		
	}

  @Test(description="login",dataProvider="features")
	public void login(CucumberFeatureWrapper cFeature)
	{
		testRunner.runCucumber(cFeature.getCucumberFeature());
	}
	@DataProvider(name="features")
	public Object[][] getFeatures()
	{
		return testRunner.provideFeatures();
	}
	@AfterClass
	public void tearDown()
	{
		testRunner.finish();
	}

}
