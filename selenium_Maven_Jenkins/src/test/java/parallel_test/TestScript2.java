package parallel_test;

import java.io.BufferedWriter;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import constants.Constants;
import parallel.Excel_Reader;
import parallel.HTML_FileCreator;
import parallel.SeleniumActions_Parallel;
import util.ExcelHandler_COPY;
import util.FolderCreation;
import util.HtmlCreator;
import util.ReadProperties;
import util.SeleniumActions_COPY;

public class TestScript2 {
	
	Logger LOGGER = LogManager.getLogger(TestScript2.class);
	int ROW_NUMBER = 0;
	
	List<String> SINGLE_ROW_DATA = null;
	List<String>[] ALL_ROW_DATA  = null;   // store all data for the respective ROW(s) (i.e., t)
	
	String EXCEL_FILE = null;
	String SHEET_NAME = null;
	
	String actualPath = null;

@BeforeMethod
public void preConfig() {
	  LOGGER.info("ENTER into preConfig()");
	  	Properties propertiesFile = ReadProperties.readPropertiesFile(Constants.CONFIGURATION_FILE);		
		//EXCEL_FILE = propertiesFile.getProperty(Constants.REGISTRATION_TESTDATA);
	  	EXCEL_FILE = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\Registration_Test.xlsx";
		  SHEET_NAME = propertiesFile.getProperty(Constants.REGISTRATION_SHEET_NAME);
		  
		if( HTML_FileCreator.actualPath != null ) {			  
			  LOGGER.info("Report path created ----- " + HTML_FileCreator.actualPath);
		  }
		  else {
			  HTML_FileCreator.reportGenerator();
			  LOGGER.info("Report path already exist ----- " + HTML_FileCreator.actualPath);
		  }
	  LOGGER.info("EXIT from preConfig()");
}

  @Test
  public void testExecute() {
	  LOGGER.info("ENTER into testExecution()");
	  try {
		  if(HTML_FileCreator.actualPath != null) {
			    actualPath = HTML_FileCreator.actualPath;   // D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\MMMDDYYYY_HHmmssSS
			    String fileName = "Registration.html";
			    String sourceFile ="D:\\My Assignments\\Selenium_UI_AUTOMATION\\HTML Report\\Primary Files\\mainContent.txt";
			    String destinationFile= actualPath + File.separator + fileName;  //D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\MMMDDYYYY_HHmmssSS\\Report.html
			    
			    HTML_FileCreator html_FileCreator_ref1 = new HTML_FileCreator();
				File file = new File(destinationFile); // D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_171418627\Registration.html
					
					if(file.exists()) {  // D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_171418627\Registration.html
						DateFormat dateFormat = new SimpleDateFormat("ssSS");  //seconds(ss)MicroSeconds(SS)
						Date currentDate = new Date();
						destinationFile = destinationFile.replace(".", "_"+ dateFormat.format(currentDate) + ".");  //28100.
						// Example: // D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_171418627\Registration_28100.html
					}
					
					html_FileCreator_ref1.copyFile(sourceFile, destinationFile);  // copy file content from one to another file					
					HTML_FileCreator.findReplaceString(destinationFile, "Regisration_Test");  // how to set it
					
					LOGGER.info("Report exist in : " + destinationFile);
					
					//------------------------------------
					
					BufferedWriter bw = HtmlCreator.createFile(destinationFile);
					ALL_ROW_DATA = ExcelHandler_COPY.readRowDataCopy(EXCEL_FILE, SHEET_NAME); // String filePath, String sheetNameParam
					
					if(ALL_ROW_DATA.length > 0){  // if ALL_ROW_DATA.length > 0   - BEGIN
						LOGGER.info("Array Length is " + ALL_ROW_DATA.length);
						//Table Header - BEGIN
						html_FileCreator_ref1.stringAppender(bw, "<table align=\"center\" style=\"border-style: ridge; border-width: 2px; width:95%;\" cellpadding=\"0\" cellspacing=\"0\" >");
						html_FileCreator_ref1.stringAppender(bw, "<tr>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"5%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Step Name </th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Step Description </th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Expected Result</th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Object Name </th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Action</th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Parameter</th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Error Handler</th>");
						html_FileCreator_ref1.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Status</th>");
						html_FileCreator_ref1.stringAppender(bw, "</tr>");
						//Table Header - END
						SeleniumActions_Parallel seleniumActionsParallelRef1  = new SeleniumActions_Parallel();
						
						for(ROW_NUMBER = 0; ROW_NUMBER< ALL_ROW_DATA.length; ROW_NUMBER++){  // for ROW_NUMBER - BEGIN 
							LOGGER.info(ALL_ROW_DATA[ROW_NUMBER]);
							SINGLE_ROW_DATA = ALL_ROW_DATA[ROW_NUMBER];
							LOGGER.info("this row has "+SINGLE_ROW_DATA.size()+ "columns and the data contains: " + SINGLE_ROW_DATA.get(0));
							boolean result = seleniumActionsParallelRef1.optionType(SINGLE_ROW_DATA);
							html_FileCreator_ref1.stringAppender(bw, "<tr>");
							for(int i=0; i< SINGLE_ROW_DATA.size(); i++ ) {
								if( (i == 5) && (seleniumActionsParallelRef1.PARAM_VALUE != null) ) {
									//SINGLE_ROW_DATA.add(i, SeleniumActions_COPY.PARAM_VALUE);
									SINGLE_ROW_DATA.remove(i); // remove existing value(PARAMETER_*)
									SINGLE_ROW_DATA.add(i, seleniumActionsParallelRef1.PARAM_VALUE);  // add new value in PARAMETER_* position
									html_FileCreator_ref1.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\">" + SINGLE_ROW_DATA.get(i) + "</td>");	
								}
								else if( (i == 5) && (seleniumActionsParallelRef1.ACTION_TYPE.equals(Constants.CAPTURE_SCREEN)) ) {
									//SINGLE_ROW_DATA.add(i, SeleniumActions_COPY.PARAM_VALUE);
									SINGLE_ROW_DATA.remove(i); // remove existing value(PARAMETER_*)
									SINGLE_ROW_DATA.add(i, seleniumActionsParallelRef1.SCREENSHOT_HREF);  // add new value in PARAMETER_* position
									LOGGER.info("Screen shot data is ******* " + SINGLE_ROW_DATA.get(i));
									html_FileCreator_ref1.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\">" + SINGLE_ROW_DATA.get(i) + "</td>");	
								}
								else {
									html_FileCreator_ref1.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\">" + SINGLE_ROW_DATA.get(i) + "</td>");
								}			
							}
							if(result) {
								html_FileCreator_ref1.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\" bgcolor='Green'>PASS</td>");
							}
							else {
								html_FileCreator_ref1.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\" bgcolor='Red'>FAIL</td>");
							}
							html_FileCreator_ref1.stringAppender(bw, "</tr> \n");
						}
					}
					
					html_FileCreator_ref1.stringAppender(bw, "</td> \n");
					html_FileCreator_ref1.stringAppender(bw, "</tr> \n");
					html_FileCreator_ref1.stringAppender(bw, "</table> \n");
					HTML_FileCreator.SECONDARY_REPORT_LIST.add(destinationFile);  // add the seconday report file to the report list to append with 
					bw.close();
					
		  }
		  else {
			  LOGGER.warn("Report Path not exist");
		  }	 
					
	  }

	  catch(Exception e) {
		  LOGGER.error("Exception found in catch(Exception e) block " + e);
	  }
	  LOGGER.info("EXIT from testExecution()"); 
}
  
  
			
}
		
  
