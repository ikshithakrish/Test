package org.cucumber.stepdefs;

import org.cucumber.tests.OpenMRSTests;
import org.openqa.selenium.By;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPage {
	

	@Given("^Open Application and Enter url$")
	public void open_Application_and_Enter_url() throws Throwable {
	  OpenMRSTests.driver.get("https://demo.openmrs.org/openmrs/login.htm");
	  
	}
 
	@When("^enter username$")
	public void enter_username() throws Throwable {
		OpenMRSTests.driver.findElement(By.id("username")).sendKeys("Admin");
	}
	
	@When("^enter password$")
	public void enter_password() throws Throwable {
		//OpenMRSTests.driver.findElement(By.id("Inpatient Ward")).click();
		OpenMRSTests.driver.findElement(By.id("loginButton")).click();
	}
 
	@Then("^verify Msg$")
	public void verify_Msg() throws Throwable {
	   boolean result =  OpenMRSTests.driver.findElement(By.id("sessionLocationError")).getText().contains("You must choose a location!");
	   Assert.assertTrue(result);
	}
}
