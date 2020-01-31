package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import constants.Constants;




public class LaunchBrowser {
	private static final Logger LOGGER = LogManager.getLogger(LaunchBrowser.class);
	
	public static WebDriver DRIVER = null;
	
	public static int BROWSER_INDEX = 0; // 0 - IE; 1 - Mozilla Firefox; 2 -  Chrome;
	
	public static WebDriver launchURL(int browserIndex, String url){ // Browser, URL
		List<String> selectBrowser = new ArrayList<String>();
		selectBrowser.add(Constants.INTERNET_EXPLORER); // 0
		selectBrowser.add(Constants.FIREFOX); // 1
		selectBrowser.add(Constants.GOOGLE_CHROME); // 2	|
		try{  // launchURL(int browserIndex, String url) - try - BEGIN
			LOGGER.info("Enter into launchURL(int browserIndex, String url) try block");
			//CONFIG_FILE_PATH = "src\\main\\resources\\properties_file\\config.properties";
			Properties propertiesFile = ReadProperties.readPropertiesFile(Constants.CONFIGURATION_FILE);
			
		if( selectBrowser.get(browserIndex).equalsIgnoreCase(Constants.INTERNET_EXPLORER) ){
			LOGGER.info("Launch IE Browser");			
			System.setProperty(propertiesFile.getProperty(Constants.IE_WEBDRIVER), propertiesFile.getProperty(Constants.IE_DRIVER_PATH));
			DRIVER = new InternetExplorerDriver();
			DRIVER.get(url);
			LOGGER.info("launch URl: "+ url);
		}
		else if( selectBrowser.get(browserIndex).equalsIgnoreCase(Constants.GOOGLE_CHROME) ){
			LOGGER.info("Launch chrome Browser");
			System.setProperty(propertiesFile.getProperty(Constants.CHROME_WEBDRIVER), propertiesFile.getProperty(Constants.CHROME_DRIVER_PATH));
			DRIVER = new ChromeDriver();			
			DRIVER.get(url);
			LOGGER.info("launch URl: "+ url);
		} 
		else if(selectBrowser.get(browserIndex).equalsIgnoreCase(Constants.FIREFOX) ){
			LOGGER.info("Launch Firefox Browser");
			System.setProperty(propertiesFile.getProperty(Constants.FIREFOX_WEBDERIVER), propertiesFile.getProperty(Constants.FIREFOX_DRIVER_PATH));
			DRIVER= new FirefoxDriver();
			DRIVER.get(url);
			LOGGER.info("launch URl: "+ url);
			LOGGER.info("Exit from launchURL(int browserIndex, String url) try block"); 
		}		
		
		} // launchURL(int browserIndex, String url) - try - END
		catch(Exception e){ // launchURL(int browserIndex, String url) - catch(Exception e) - BEGIN
			LOGGER.error("Enter into launchURL(int browserIndex, String url) catch block");
			LOGGER.error("Exception found is "+ e);
			LOGGER.error("Exit from launchURL(int browserIndex, String url) catch block");
		} // launchURL(int browserIndex, String url) - catch(Exception e) - END
		return DRIVER;
	}

}
