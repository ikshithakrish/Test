package pages;

import java.io.BufferedWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.FolderCreation;
import util.ReportGenerator;

public class ReportHeader {
	
	public static Logger LOGGER = LogManager.getLogger(ReportHeader.class);
	
	public static String actualPath = null;
	public static String primaryFile = null;
	
  
  public void primaryReportHeader() {
	  LOGGER.info("ENTER into executeTestCases()");
	  String tempPath = null;
	  try {
		  LOGGER.info("ENTER into primaryReportHeader() - try block");
		  String folderName = FolderCreation.folderNameCreator();
		  tempPath = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
		  if(FolderCreation.createDirectory(tempPath, folderName) ) {
			    String actualPath = tempPath + folderName + "\\";
				String primaryFile = actualPath + "Report.html";
				BufferedWriter writer = ReportGenerator.createFile(primaryFile);
				ReportGenerator.stringAppender(writer, "<html><body><table border='1'>");				
			}
			else {
				LOGGER.warn("Directory not created");
			} 
		  LOGGER.info("EXIT from executeTestCases() - try block");
	  }
	  catch(Exception e) {
		  LOGGER.info("ENTER into executeTestCases() - catch(Exception e) block");
		  LOGGER.error("Exception in catch(IOException e) " + e);
		  LOGGER.info("EXIT from executeTestCases() - catch(Exception e) block");
	  }
	  LOGGER.info("EXIT from primaryReportHeader()");
	  
  }

  
  
  
}

