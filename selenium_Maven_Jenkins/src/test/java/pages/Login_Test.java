package pages;

import java.io.BufferedWriter;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constants.Constants;
import util.ExcelHandler_COPY;
import util.FolderCreation;
import util.HtmlCreator;
import util.ReadProperties;
import util.SeleniumActions_COPY;


public class Login_Test {
	public static final Logger LOGGER = LogManager.getLogger(Login_Test.class);
	public static int ROW_NUMBER = 0;
	
	public static List<String> SINGLE_ROW_DATA = null;
	public static List<String>[] ALL_ROW_DATA  = null;   // store all data for the respective ROW(s) (i.e., t)
	
	public static String EXCEL_FILE = null;
	public static String SHEET_NAME = null;
 
  @BeforeTest
  public void preConfig() {
	  LOGGER.info("ENTER into preConfig()");
	  	Properties propertiesFile = ReadProperties.readPropertiesFile(Constants.CONFIGURATION_FILE);		
		 //EXCEL_FILE = propertiesFile.getProperty(Constants.FLIGHT_BOOKING_TESTDATA);
	  	EXCEL_FILE = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\Flights_Test.xlsx";
		  SHEET_NAME = propertiesFile.getProperty(Constants.FLIGHT_BOOKING_SHEET_NAME);
		  
	  LOGGER.info("EXIT from preConfig()");
  }
  
  @Test
  public void testExecution() {
	  LOGGER.info("ENTER into testExecution()");
	  try {
		  
		  //Folder Creation
		  String folderName = FolderCreation.folderNameCreator();
		  String tempPath = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
		  String reportHeaderFile = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\HTML Report\\Primary Files\\HeaderHtmlFile.txt";
		  String reportFooterFile = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\HTML Report\\Primary Files\\FooterHtml.txt";
		  String actualPath = null;
		  
		  if(FolderCreation.REPORT_PATH == null) {
			  FolderCreation.createDirectory(tempPath, folderName);  // this is for report folder
			  actualPath = FolderCreation.REPORT_PATH;
			  
			 
			  
			  FolderCreation.createDirectory(actualPath, "screenshots"); // create screensots (dir) under report directory
			  HtmlCreator.createReportFile(actualPath);  // HtmlCreator.BUFFERED_WRITER (Report.html)
			  if(HtmlCreator.copyFileContent(HtmlCreator.BUFFERED_WRITER, reportHeaderFile)) {
				  LOGGER.info("Header content copied to the primary report");
				  HtmlCreator.SECONDARY_REPORT_LIST = new ArrayList<String>();  // to add the subReports in this list to append with primary report 
			  }
			  else {
				  LOGGER.warn("Header content not copied to the primary report");
			  }
		  }
		  
		  if(FolderCreation.REPORT_PATH != null) {
			    actualPath = FolderCreation.REPORT_PATH;     // D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\MMMDDYYYY_HHmmssSS
			// create file (report.html)
				LOGGER.info("BufferedWriter reference is " + HtmlCreator.BUFFERED_WRITER);
			//copy the content of Header.txt to ../..Report.html
//				if(HtmlCreator.copyFileContent(HtmlCreator.BUFFERED_WRITER, reportHeaderFile)) {
					//HtmlCreator.copyFileContent(HtmlCreator.BUFFERED_WRITER, reportFooterFile);
					LOGGER.info("Report exist in : " + HtmlCreator.FILE_NAME);
					
					String sourceFile ="D:\\My Assignments\\Selenium_UI_AUTOMATION\\HTML Report\\Primary Files\\mainContent.txt";
					String destinationFile= actualPath + "\\Login_Test.html";
					
					File file = new File(destinationFile);
					
					if(file.exists()) {  // D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_171418627\Login.html
						DateFormat dateFormat = new SimpleDateFormat("ssSS");  //seconds(ss)MicroSeconds(SS)
						Date currentDate = new Date();
						destinationFile = destinationFile.replace(".", "_"+ dateFormat.format(currentDate) + ".");  //28100.
						// Example: // D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_171418627\Login_28100.html
					}
					
					HtmlCreator.copyFile(sourceFile, destinationFile);  // copy file content from one to another file
					
					HtmlCreator.findReplaceString(destinationFile, "Login_Test");  // how to set it
					
					//------------------------------------
					
					BufferedWriter bw = HtmlCreator.createFile(destinationFile);
					ALL_ROW_DATA = ExcelHandler_COPY.readRowDataCopy(EXCEL_FILE, SHEET_NAME); // String filePath, String sheetNameParam
					
					if(ALL_ROW_DATA.length > 0){  // if ALL_ROW_DATA.length > 0   - BEGIN
						LOGGER.info("Array Length is " + ALL_ROW_DATA.length);
						//Table Header - BEGIN
						HtmlCreator.stringAppender(bw, "<table align=\"center\" style=\"border-style: ridge; border-width: 2px; width:95%;\" cellpadding=\"0\" cellspacing=\"0\" >");
						HtmlCreator.stringAppender(bw, "<tr>");
						HtmlCreator.stringAppender(bw, "<th width=\"5%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Step Name </th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Step Description </th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Expected Result</th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Object Name </th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Action</th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Parameter</th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Error Handler</th>");
						HtmlCreator.stringAppender(bw, "<th width=\"10%\" style=\"border-style: solid; border-width: 1px; background-color: #00B5F0\">Status</th>");
						HtmlCreator.stringAppender(bw, "</tr>");
						//Table Header - END
						
						for(ROW_NUMBER = 0; ROW_NUMBER< ALL_ROW_DATA.length; ROW_NUMBER++){  // for ROW_NUMBER - BEGIN 
							LOGGER.info(ALL_ROW_DATA[ROW_NUMBER]);
							SINGLE_ROW_DATA = ALL_ROW_DATA[ROW_NUMBER];
							LOGGER.info("this row has "+SINGLE_ROW_DATA.size()+ "columns and the data contains: " + SINGLE_ROW_DATA.get(0));
							boolean result = SeleniumActions_COPY.optionType(SINGLE_ROW_DATA);
							HtmlCreator.stringAppender(bw, "<tr>");
							for(int i=0; i< SINGLE_ROW_DATA.size(); i++ ) {
								HtmlCreator.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\">" + SINGLE_ROW_DATA.get(i) + "</td>");
							}
							if(result) {
								HtmlCreator.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\" bgcolor='Green'>PASS</td>");
							}
							else {
								HtmlCreator.stringAppender(bw, "<td style=\"border-style: solid; border-width: 1px;\" bgcolor='Red'>FAIL</td>");
							}
							HtmlCreator.stringAppender(bw, "</tr> \n");
						}
					}
					
					HtmlCreator.stringAppender(bw, "</td> \n");
					HtmlCreator.stringAppender(bw, "</tr> \n");
					HtmlCreator.stringAppender(bw, "</table> \n");
					HtmlCreator.SECONDARY_REPORT_LIST.add(destinationFile);  // add the seconday report file to the report list to append with 
					
					
					//HtmlCreator.stringAppender(bw,  "</body></html> \n");  //optional ????????
					bw.close();
					//------------------------------------
					
					//HtmlCreator.BUFFERED_WRITER.close();  // should close else it will not create file  // should be closed at the end of reportFile end
//				}
//				else {
//					LOGGER.warn("conted not copied in the primary report");					
//				}		
				
			
			 
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
  

  @AfterTest
  public void closeDriver() {
	  
  }

}
