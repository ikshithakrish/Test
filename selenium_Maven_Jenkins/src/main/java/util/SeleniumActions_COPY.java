package util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import constants.Constants;

public class SeleniumActions_COPY {
public static Logger LOGGER = LogManager.getLogger(SeleniumActions_COPY.class);
	
	public static WebDriver DRIVER = null;	
	public static WebElement ELEMENT = null;
	
	public static Select SELECT_ELEMENT = null; //Drop Down
	public static String SELECT_BY_TYPE = null; // Drop Down	
	public static String BY_TYPE = null;
	public static String ATTRIBUTE = null;	
	public static String OPTION_VALUE = null;
	public static String VALUE = null;
	public static String URL = null;
	public static String OBJECT_NAME = null;	
	public static String ACTION_TYPE = null;
	public static String TEST_DATA = null;
	public static String PARAM_VALUE = null;
	public static String SCREENSHOT_HREF = null;

	public static int INDEX_POSITION = 0;
	public static int BROWSER_INDEX = 0; // 0 - IE; 1 - Mozilla Firefox; 2 -  Chrome;
	public static int RADIO_BUTTON_INDEX = 0;
	public static int ELEMENT_SIZE = 0;
	
	public static boolean IS_ELEMENT_EXIST = false; 
	
	public static List<String> INPUT_PARAMS = null;
	public static List<String> OBJECT_REPO_DATA = null;

	public static boolean STATUS = false;	
	
	
/******************Options  - BEGIN **************************/
	// public static void optionType(WebDriver driver, List<String> inputs){   //commented to test browser Launcher  ------------------
	public static boolean optionType(List<String> inputs){
		LOGGER.info("Enter into to SeleniumAction - optionType(WebDriver, List<String>)");
		boolean status = false;
		// 0- Step Name, 1.Step Description, 2.Expected Result, 3.FindBy	4.Object (Element property) 5.Action	6.Parameter	7.Error Handler
		INPUT_PARAMS = inputs;
		LOGGER.info("Data Contains : " + INPUT_PARAMS);		
		// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index)		
		OBJECT_NAME = INPUT_PARAMS.get(3);
		// 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index)
		objectRepoSearch(OBJECT_NAME);		
		ACTION_TYPE = INPUT_PARAMS.get(4).trim().toUpperCase();
		switch (ACTION_TYPE) {		
		case Constants.BROWSER_SELECTION:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			BROWSER_INDEX = Integer.valueOf( INPUT_PARAMS.get(5) );
			if(BROWSER_INDEX >= 0 && BROWSER_INDEX <= 2)
				status = true;
			else
				status = false;
			break;			
		case Constants.LAUNCH_URL:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			URL = INPUT_PARAMS.get(5);
			if( (DRIVER = LaunchBrowser_Test.launchURL(BROWSER_INDEX, URL)) != null) {
				LOGGER.info("WebDriver object is " + DRIVER);
				status = true;
			}
			else {
				LOGGER.warn("WebDriver object is null");
				status = false;
			}
			break;
		case Constants.IS_ELEMENT_EXIST:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			if(isElementExist()){	
				LOGGER.info("Element Exist");
				status = true;
			}	
			else{
				LOGGER.warn("Element not Exist");
				status = false;
			}
			break;
		case Constants.INPUT:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			status = passValue();			
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.CLICK:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			status = clickAction();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.RADIO_BUTTON:
			RADIO_BUTTON_INDEX = Integer.valueOf( INPUT_PARAMS.get(5) );
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			status = clickAction();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.SELECT:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			status = selectOption();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.TERMINATE_DRIVER:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			if(DRIVER!= null) {				
				//DRIVER.close();
				DRIVER.quit();
				LOGGER.info("Browser Closed");
				status = true;
			}
			else {
				LOGGER.warn("WebDriver is null");
				status = false;
			}
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.MAXIMIZE_BROWSER:
			if(DRIVER!= null) {				
				//DRIVER.close();
				DRIVER.manage().window().maximize();
				LOGGER.info("Maximized the browser");
				status = true;
			}
			else {
				LOGGER.warn("WebDriver is null");
				status = false;
			}
			break;
		case "CAPTURE_SCREEN":
			if(FolderCreation.SCREENSHOT_PATH !=null) {
				status = captureScreen(FolderCreation.SCREENSHOT_PATH);
			}
			else {
				status = false;	
			}
			break;
		default:
			LOGGER.error("Invalid Action");
			status = false;
			break;
		}		
		LOGGER.info("Exit from SeleniumAction - optionType(WebDriver, List<String>)");
		return status;
		}
		
/*------------------------Options - END -------------------------------------*/
	
	/********* passValue Action - BEGIN ************/
	public static boolean passValue(){		
		LOGGER.info("passValue() - ENTER" );
		boolean is_value_enterted = false;
		// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index) - INPUT_PARAMS
		// 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index) - OBJECT_REPO_DATA
		LOGGER.info("OBJECT Repo Data contains: "+OBJECT_REPO_DATA);
		VALUE = INPUT_PARAMS.get(5);	// 5- Parameter (TestScript Index)
			if(isElementExist()){
				ELEMENT.sendKeys(VALUE);
				is_value_enterted = true;
			}
			else{
				LOGGER.warn("element not found");
				is_value_enterted = false;
			}	
			LOGGER.info("passValue() - EXIT" );
			return is_value_enterted;
	}
	/*----------------- passValue Action - END -----------------*/
	
	/********* passValue Action DataDriven - BEGIN ************/
	public static boolean passValue_DataDriven(){		
		LOGGER.info("passValue() - ENTER" );
		boolean is_value_enterted = false;
		// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index) - INPUT_PARAMS
		// 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index) - OBJECT_REPO_DATA
		LOGGER.info("OBJECT Repo Data contains: "+OBJECT_REPO_DATA);
		VALUE = TEST_DATA;	// 5- Parameter (TestScript Index)
		PARAM_VALUE = TEST_DATA;  // display parameter value in the report
			if(isElementExist()){
				ELEMENT.sendKeys(VALUE);
				is_value_enterted = true;
			}
			else{
				LOGGER.warn("element not found");
				is_value_enterted = false;
			}	
			LOGGER.info("passValue() - EXIT" );
			return is_value_enterted;
	}
	/*----------------- passValue Action Data Driven - END -----------------*/
	
	/*------------- captureScreen - BEGIN ---------------------*/	
	public static boolean captureScreen(String path){
		LOGGER.info("captureScreen( - EXIT" );
		boolean isCaptured = false;
		try{			
			if((! path.equals("")) || (! path.equals(null)) ){
			TakesScreenshot screenShot =((TakesScreenshot) DRIVER);
			File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
			
			DateFormat dateFormat = new SimpleDateFormat("MMMDDYYYY_HHmmssSS");  //seconds(ss)MicroSeconds(SS)
			Date currentDate = new Date();
			String fileName = path + File.separator + "Screenshot_"+ dateFormat.format(currentDate) + ".png";   
			// D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_171418627\screenshots\Screenshot_Jan152019_183209101.png
						
			File destinationFile = new File(fileName);
			FileUtils.copyFile(sourceFile, destinationFile);
			SCREENSHOT_HREF = "<a href=\"" + fileName + "\"> CLICK_HERE </a>";
			LOGGER.info("Screen shot ref--- " + SCREENSHOT_HREF);
			LOGGER.info("successfully Captured Screen");
			isCaptured = true;
			}
			else{
				LOGGER.error("path is not valid");
				isCaptured = false;
			}
		}
		catch(Exception e){
			isCaptured = false;
			LOGGER.warn("Exception in screen shot capturing " + e );
			}
		LOGGER.info("captureScreen - EXIT" );
		return isCaptured;
	}
	/*------------- captureScreen - BEGIN ---------------------*/
	
	
	/********* Click Action - BEGIN ************/
	// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index) - INPUT_PARAMS
   // 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index) - OBJECT_REPO_DATA
	public static boolean clickAction(){  // Applicable for submit button, Radio selection
		LOGGER.info("clickAction() - ENTER" );
		boolean status = false;
		if(isElementExist()){  // if elementExist() - BEGIN
			if(ACTION_TYPE.equalsIgnoreCase(Constants.RADIO_BUTTON)){ // if check Radio_Button - BEGIN
				if(! ELEMENT.isSelected()){  // if the radion button is not checked (!false)
					ELEMENT.click();
					status = true;
				} // if check Radio_Button - END
				else{ // else check Radio_Button - BEGIN
					LOGGER.warn("Radio Button already checked");
					status = false;
				} // else check Radio_Button - END
			}
			else{
			ELEMENT.click();
			status = true;
			}
		}  // if elementExist() - END
		else{ // else elementExist() - BEGIN
			LOGGER.warn("element not found");
			status = false;
		}	// else elementExist() - END
		LOGGER.info("clickAction() - ENTER" );
		return status;
	}
	/*----------------- Click Action - END -----------------*/

	/********* Select Options - BEGIN ************/
	// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index) - INPUT_PARAMS
	// 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index) - OBJECT_REPO_DATA
	public static boolean selectOption(){
		LOGGER.info("selectOption() - ENTER" );
		boolean status = false;
		SELECT_BY_TYPE = OBJECT_REPO_DATA.get(2);  // 1- FindByElement (OBJECT_REPO_DATA)		
		VALUE = INPUT_PARAMS.get(5);	// 5- Parameter (TestScript Index)
		if(isElementExist()){
				SELECT_ELEMENT = new Select(ELEMENT);  // ELEMENT = DRIVER.findElement(By.xpath(ATTRIBUTE));
				if(SELECT_BY_TYPE.trim().equalsIgnoreCase(Constants.SELECT_BY_INDEX)){	
					try{
						SELECT_ELEMENT.selectByIndex(Integer.valueOf(VALUE));
						status = true;
					}
					catch(NumberFormatException e){
						LOGGER.error("index should be a number" + e);
						status = false;
					}				
				}
				else  if(SELECT_BY_TYPE.trim().equalsIgnoreCase(Constants.SELECT_BY_VALUE)){
					SELECT_ELEMENT.selectByValue(VALUE);
					status = true;
				}
				else if(SELECT_BY_TYPE.trim().equalsIgnoreCase(Constants.SELECT_BY_VISIBLE_TEXT)){
					SELECT_ELEMENT.selectByVisibleText(VALUE);
					status = true;
				}
		}
		else{
			LOGGER.error("element not found");
			status = false;
		}
		LOGGER.info("selectOption() - EXIT" );
		return status;
	}
	/*----------------- Select Options - END -----------------*/
	
	/********* Select Options Data Driven - BEGIN ************/
	// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index) - INPUT_PARAMS
	// 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index) - OBJECT_REPO_DATA
	public static boolean selectOption_DataDriven(){
		LOGGER.info("selectOption() - ENTER" );
		boolean status = false;
		SELECT_BY_TYPE = OBJECT_REPO_DATA.get(2);  // 1- FindByElement (OBJECT_REPO_DATA)		
		VALUE = TEST_DATA;	// 5- Parameter (TestScript Index)
		PARAM_VALUE = TEST_DATA;  // display parameter value in the report
		if(isElementExist()){
				SELECT_ELEMENT = new Select(ELEMENT);  // ELEMENT = DRIVER.findElement(By.xpath(ATTRIBUTE));
				if(SELECT_BY_TYPE.trim().equalsIgnoreCase(Constants.SELECT_BY_INDEX)){	
					try{
						SELECT_ELEMENT.selectByIndex(Integer.valueOf(VALUE));
						status = true;
					}
					catch(NumberFormatException e){
						LOGGER.error("index should be a number" + e);
						status = false;
					}				
				}
				else  if(SELECT_BY_TYPE.trim().equalsIgnoreCase(Constants.SELECT_BY_VALUE)){
					SELECT_ELEMENT.selectByValue(VALUE);
					status = true;
				}
				else if(SELECT_BY_TYPE.trim().equalsIgnoreCase(Constants.SELECT_BY_VISIBLE_TEXT)){
					SELECT_ELEMENT.selectByVisibleText(VALUE);
					status = true;
				}
		}
		else{
			LOGGER.error("element not found");
			status = false;
		}
		LOGGER.info("selectOption() - EXIT" );
		return status;
	}
	/*----------------- Select Options_DataDriven - END -----------------*/

	/************** is elementExist? - BEGIN *****************/
	public static boolean isElementExist(){	
		// 0- Step Name, 1.Step Description, 2.Expected Result, 3.FindBy	4.Object (Element property) 5.Action	6.Parameter	7.Error Handler
		LOGGER.info("Enter into SeleniumActions - elementExist()");
		switch(BY_TYPE){
		case "xpath":
			LOGGER.info("Enter into switch action for " + BY_TYPE );
			IS_ELEMENT_EXIST = locatorIdentifier(BY_TYPE , ATTRIBUTE);	
			break;
		case "name":
			LOGGER.info("Enter into switch action for " + BY_TYPE );
			IS_ELEMENT_EXIST = locatorIdentifier(BY_TYPE , ATTRIBUTE);
			break;
		case "id":
			LOGGER.info("Enter into switch action for " + BY_TYPE );
			IS_ELEMENT_EXIST = locatorIdentifier(BY_TYPE , ATTRIBUTE);
			break;
		case "cssSelector":
			LOGGER.info("Enter into switch action for " + BY_TYPE );
			IS_ELEMENT_EXIST = locatorIdentifier(BY_TYPE , ATTRIBUTE);		
			break;
		default:
			LOGGER.warn("Check the Element Locator");
			break;
		}
		LOGGER.info("Exit from SeleniumActions - elementExist()");
		return IS_ELEMENT_EXIST;		
	}

	/************** is elementExist? - END *****************/
	
	/*----------------------------- Element Locator Identifier(byXpath / byName / byCssSelector ) - BEGIN -------------------------------*/
	public static boolean locatorIdentifier(String locatorType, String locatorValue){ 
	// make the By.? using java reflection (refer: https://stackoverflow.com/questions/45959319/java-call-a-method-with-name-stored-in-variable)
		try{  // try - locatorIdentifier(String locatorType, String locatorValue) - BEGIN
			Method byMethod = By.class.getMethod(locatorType, String.class);	// NoSuchMethodException needs to be adder for By.class.getMethod(locatorType, String.class)
			if(! ACTION_TYPE.equalsIgnoreCase(Constants.RADIO_BUTTON)){   // if not Radio_Button - BEGIN
				if(ELEMENT_SIZE(locatorType, locatorValue) > 0){  // if ELEMENT_SIZE(locatorType, locatorValue) - BEGIN 
				ELEMENT = DRIVER.findElement((By) byMethod.invoke(null, locatorValue));  // InvocationTargetException and IllegalAccessException needs to be adder for byMethod.invoke(null, "")
				STATUS = ELEMENT.isDisplayed();
				} // if ELEMENT_SIZE(locatorType, locatorValue) - END
				else{ // else ELEMENT_SIZE(locatorType, locatorValue) - BEGIN
					LOGGER.warn("No Element found and the size is ");
				} // else ELEMENT_SIZE(locatorType, locatorValue) - END
			} // if not Radio_Button - END
			else{ // else Radio_Button - BEGIN
				if(ELEMENT_SIZE(locatorType, locatorValue) > 0){
					List<WebElement> temp = DRIVER.findElements((By) byMethod.invoke(null, locatorValue));
					ELEMENT = temp.get(RADIO_BUTTON_INDEX);
					STATUS = ELEMENT.isDisplayed();
				} 
				else{
					LOGGER.warn("No Element found and the size is ");
				}				
			}	// else Radio_Button - END		
		} // try - locatorIdentifier(String locatorType, String locatorValue) - END		
		catch(NoSuchMethodException e){ // catch(NoSuchMethodException e) - BEGIN
			LOGGER.error("NoSuchMethodException found -- " + e);
		} // catch(NoSuchMethodException e) - END
		catch(InvocationTargetException e){  // catch(InvocationTargetException e) - BEGIN
			//import java.lang.reflect.InvocationTargetException; required for this exception
			LOGGER.error("InvocationTargetException found -- " + e);
		} // catch(InvocationTargetException e) - END
		catch(IllegalAccessException e){ // catch(IllegalAccessException e) - BEGIN
			LOGGER.error("IllegalAccessException found -- " + e);
		}	// catch(IllegalAccessException e) - END	
	return STATUS;
	}
	/***************************** Element Locator Identifier(byXpath / byName / byCssSelector ) - END *************************/
	
	/*----------------------------------- ELEMENT_SIZE(String locatorType, String locatorValue) - BEGIN ------------------------------------*/
	public static int ELEMENT_SIZE(String locatorType, String locatorValue){
	try{
		DRIVER.findElements(By.xpath(ATTRIBUTE)).size();
		Method byMethod = By.class.getMethod(locatorType, String.class); // NoSuchMethodException needs to be adder for By.class.getMethod(locatorType, String.class)
		ELEMENT_SIZE = DRIVER.findElements((By) byMethod.invoke(null, locatorValue)).size(); //// InvocationTargetException and IllegalAccessException needs to be adder for byMethod.invoke(null, "")
	}
	catch(NoSuchMethodException e){
		LOGGER.error("NoSuchMethodException found -- " + e);
	}
	catch(InvocationTargetException e){  //import java.lang.reflect.InvocationTargetException; required for this exception
		LOGGER.error("InvocationTargetException found -- " + e);
	}
	catch(IllegalAccessException e){
		LOGGER.error("IllegalAccessException found -- " + e);
	}
		return ELEMENT_SIZE;
	}
	/********************* ELEMENT_SIZE(String locatorType, String locatorValue) - BEGIN *************************/
	
	/*------------------------------------------ objectRepoSearch() - BEGIN -------------------------------------------*/
	public static List<String> objectRepoSearch(String objectRepoParam){
	try{  // objectRepoSearch() - try - BEGIN
		LOGGER.info("ENTER into objectRepoSearch(String objectRepoParam) try block");		
		// to avoid the NPE for the object not exist in properties file	
		if(ObjectRepoReader.objectRepo_Search(objectRepoParam)==null){  // null check for object (object == null)
			LOGGER.warn("object " + objectRepoParam + " not exist in objectRepository.properties file");
		}
		else{
			String objectRepoValue = ObjectRepoReader.objectRepo_Search(objectRepoParam);
			System.out.println("value contains : " + objectRepoValue);
			String[] arrOfStr = objectRepoValue.split(",");
			OBJECT_REPO_DATA = new ArrayList<String>();
			for(int i = 0; i< arrOfStr.length; i++){
				OBJECT_REPO_DATA.add(arrOfStr[i].trim());  // Read the objectvalues from objectRepo
			}
			//Set the Attrubute (Locator value) and BY_TYPE (Locator)
			ATTRIBUTE = OBJECT_REPO_DATA.get(0);  // 0- Object Parameter (OBJECT_REPO_DATA)
			BY_TYPE = OBJECT_REPO_DATA.get(1);   // 1 - Locator (xpath / name / )
		}		
		LOGGER.info("Object Repo Contains: " + OBJECT_REPO_DATA);
		LOGGER.info("EXIT from objectRepoSearch(String objectRepoParam) try block");
	}  // objectRepoSearch() - try - END
	catch(Exception e){ // objectRepoSearch() - catch(Exception e) - BEGIN
		LOGGER.warn("ENTER into objectRepoSearch(String objectRepoParam) catch(Exception e) block");
		LOGGER.error("Exception found " + e);
		LOGGER.warn("EXIT from objectRepoSearch(String objectRepoParam) catch(Exception e) block");
	}  // objectRepoSearch() - catch(Exception e) - END
	return OBJECT_REPO_DATA;
	}		
	/********************* objectRepoSearch() - END *************************/
	
	
	
	/******************Options-TEMP  - BEGIN **************************/
	// public static void optionType(WebDriver driver, List<String> inputs){   //commented to test browser Launcher  ------------------
	public static boolean optionType_TEMP(List<String> inputs, Map<String, String> dataTestMap){
		LOGGER.info("Enter into to SeleniumAction - optionType(WebDriver, List<String>)");
		boolean status = false;
		// 0- Step Name, 1.Step Description, 2.Expected Result, 3.FindBy	4.Object (Element property) 5.Action	6.Parameter	7.Error Handler
		INPUT_PARAMS = inputs;
		LOGGER.info("Data Contains : " + INPUT_PARAMS);		
		// 0- Step Name 1- Step Description	2- Expected Result	3- Object Name	4- Action	5- Parameter	6- Error Handler   (TestScript Index)		
		OBJECT_NAME = INPUT_PARAMS.get(3);
		// 0- Object Parameter	 1- FindByElement	 2- FindBySubElement  (ObjectRepository Index)
		objectRepoSearch(OBJECT_NAME);		
		ACTION_TYPE = INPUT_PARAMS.get(4).trim().toUpperCase();
		
		
		if(INPUT_PARAMS.get(5) !=null) {
			String paramKey = INPUT_PARAMS.get(5).toUpperCase().trim();
			LOGGER.info("-------------" + dataTestMap.get(INPUT_PARAMS.get(5)) );
			
			if( (dataTestMap.get(paramKey)!=null)  ) {
				TEST_DATA = dataTestMap.get(paramKey);			
			}
			else {
				LOGGER.warn("test data is empty / null");
			}
		}
		else {
			LOGGER.warn("param is null");
		}		

		
		
		
		switch (ACTION_TYPE) {		
		case Constants.BROWSER_SELECTION:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);	
			
				BROWSER_INDEX = Integer.valueOf(TEST_DATA);
				PARAM_VALUE = TEST_DATA;  // display parameter value in the report
				if(BROWSER_INDEX >= 0 && BROWSER_INDEX <= 2) {
					status = true;
			}
			else {
				status = false;
			}
			
			break;			
		case Constants.LAUNCH_URL:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);			
			URL = TEST_DATA;
			PARAM_VALUE = TEST_DATA;  // display parameter value in the report
			if( (DRIVER = LaunchBrowser_Test.launchURL(BROWSER_INDEX, URL)) != null) {
				LOGGER.info("WebDriver object is " + DRIVER);
				status = true;
			}
			else {
				LOGGER.warn("WebDriver object is null");
				status = false;
			}
			break;
		case Constants.IS_ELEMENT_EXIST:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			PARAM_VALUE  = null;  // this is for displaying value in report
			if(isElementExist()){	
				LOGGER.info("Element Exist");
				status = true;
			}	
			else{
				LOGGER.warn("Element not Exist");
				status = false;
			}
			break;
		case Constants.INPUT:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			status = passValue_DataDriven();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.CLICK:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			PARAM_VALUE  = null;  // this is for displaying value in report
			status = clickAction();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.RADIO_BUTTON:
			RADIO_BUTTON_INDEX = Integer.valueOf( TEST_DATA );
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			PARAM_VALUE = TEST_DATA;  // display parameter value in the report
			status = clickAction();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.SELECT:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			status = selectOption_DataDriven();
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.TERMINATE_DRIVER:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			PARAM_VALUE  = null;  // this is for displaying value in report
			if(DRIVER!= null) {				
				//DRIVER.close();
				DRIVER.quit();
				LOGGER.info("Browser Closed");
				status = true;
			}
			else {
				LOGGER.warn("WebDriver is null");
				status = false;
			}
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.MAXIMIZE_BROWSER:
			LOGGER.info("ENTER into switch for action " + ACTION_TYPE);
			PARAM_VALUE  = null;  // this is for displaying value in report
			if(DRIVER!= null) {				
				//DRIVER.close();
				DRIVER.manage().window().maximize();
				LOGGER.info("Maximized the browser");
				status = true;
			}
			else {
				LOGGER.warn("WebDriver is null");
				status = false;
			}
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		case Constants.CAPTURE_SCREEN:
			LOGGER.info("Enter into switch for action " + ACTION_TYPE);
			PARAM_VALUE  = null;  // this is for displaying value in report
			if(FolderCreation.SCREENSHOT_PATH !=null) {
				status = captureScreen(FolderCreation.SCREENSHOT_PATH);
			}
			else {
				status = false;	
			}
			LOGGER.info("EXIT from switch for action " + ACTION_TYPE);
			break;
		default:
			LOGGER.error("Invalid Action");
			status = false;
			break;
		}		
		LOGGER.info("Exit from SeleniumAction - optionType(WebDriver, List<String>)");
		return status;
		}
	//------------------------Option-Type---------------------------------
	
} // END of class
