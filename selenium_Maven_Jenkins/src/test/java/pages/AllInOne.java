package pages;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import util.FolderCreation;
import util.ReportGenerator;

public class AllInOne {
	public static Logger LOGGER = LogManager.getLogger(AllInOne.class);
	
  @Test
  public void executeTestCases() {
	  LOGGER.info("ENTER into executeTestCases()");
	  String tempPath = null;
	  try {
		  LOGGER.info("ENTER into executeTestCases() - try block");
		  String folderName = FolderCreation.folderNameCreator();
		  tempPath = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
		  if(FolderCreation.createDirectory(tempPath, folderName) ) {
			    String actualPath = tempPath + folderName + "\\";
				String primaryFile = actualPath + "Report.html";
				BufferedWriter writer = ReportGenerator.createFile(primaryFile);
				ReportGenerator.stringAppender(writer, "<html><body><table border='1'>");
				
				for(int i = 1; i<=2; i++) {				
					ReportGenerator.stringAppender(writer, "<tr><td>+</td><td colspan='7'>TestScript ${NAME} </td></tr> \n");	
					ReportGenerator.stringAppender(writer, "<tr><td colspan='8'>");
					ReportGenerator.mergeFile(writer, actualPath +"test.html");
					ReportGenerator.stringAppender(writer, "</td></tr>");
				}	
				
				ReportGenerator.stringAppender(writer, "</table></body></html>");	
				writer.close();  // Note: BufferedWriter should be closed then only it will Write the data to the file
			}
			else {
				LOGGER.warn("Directory not created");
			} 
		  LOGGER.info("EXIT from executeTestCases() - try block");
	  }
	  catch(IOException e) {
		  LOGGER.info("ENTER into executeTestCases() - catch(IOException e) block");
		  LOGGER.error("Exception in catch(IOException e) " + e);
		  LOGGER.info("EXIT from executeTestCases() - catch(IOException e) block");
	  }
	  LOGGER.info("EXIT from executeTestCases()");
	  
  }
}
