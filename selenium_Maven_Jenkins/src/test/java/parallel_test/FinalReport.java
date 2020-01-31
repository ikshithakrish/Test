package parallel_test;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import parallel.HTML_FileCreator;

public class FinalReport {
	Logger LOGGER = LogManager.getLogger(FinalReport.class);
	
  @Test
  public void FinalReport_Generator() {
	  try {
		// Append the header content to the report.html
		 String reportPath = HTML_FileCreator.actualPath;
		  
		  //BufferedWriter bw = HtmlCreator.createReportFile(reportPath);
		  LOGGER.info("Report path is " + reportPath);
		  HTML_FileCreator ref1 = new HTML_FileCreator();
		  String primaryFile = reportPath + "\\Report.html";
		  BufferedWriter writer = ref1.createFile(primaryFile);
		  LOGGER.info("Writer is " + writer);
		  ref1.mergeFile(writer, HTML_FileCreator.reportHeaderFile);
		  
		  for(int i = 0; i< HTML_FileCreator.SECONDARY_REPORT_LIST.size(); i++ ) 
		  {	  
			  LOGGER.info("File i is  " + HTML_FileCreator.SECONDARY_REPORT_LIST.get(i));
			  ref1.mergeFile(writer, HTML_FileCreator.SECONDARY_REPORT_LIST.get(i));
		  }
		  ref1.mergeFile(writer, HTML_FileCreator.reportFooterFile);
		  writer.close();  // Note: BufferedWriter should be closed then only it will Write the data to the file
		  
		  // Append the footer content to the report.html
		  
		  
		  
//		  String path = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
//		  String folderName = FolderCreation.folderNameCreator();
//		  
			
//			if(FolderCreation.createDirectory(path, folderName) ) {
//				String primaryFile = path + folderName + "\\Report.html";
//				BufferedWriter writer = createFile(primaryFile);
//				System.out.println("Writer is " + writer);
//				stringAppender(writer, "<html><body><table border='1'>");
//				for(int i = 1; i<=2; i++) {				
//					stringAppender(writer, "<tr><td colspan='8'> <a href='#'> + </a> TestScript ${NAME} </td></tr> \n");	
//					stringAppender(writer, "<tr><td colspan='8'>");
//					mergeFile(writer, "D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\test.html");
//					stringAppender(writer, "</td></tr>");
//				}
//				stringAppender(writer, "</table></body></html>");	
//				writer.close();  // Note: BufferedWriter should be closed then only it will Write the data to the file
	  }
	  catch(IOException e) {
		  LOGGER.error("Exception found " + e);
	  }	  
  }
  
  
	 
}
