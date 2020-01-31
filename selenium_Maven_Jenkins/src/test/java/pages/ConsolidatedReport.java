package pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import util.HtmlCreator;

public class ConsolidatedReport {
	private static Logger LOGGER = LogManager.getLogger(ConsolidatedReport.class);
	
	 @Test
	  public void testExecution() {
		 try {
			 int subReportCount = HtmlCreator.SECONDARY_REPORT_LIST.size();
			 LOGGER.info("No.of seconday report found is "+ subReportCount);
			 for(int i =0; i< subReportCount; i++) {
				 if( HtmlCreator.copyFileContent(HtmlCreator.BUFFERED_WRITER, HtmlCreator.SECONDARY_REPORT_LIST.get(i)) )
				 LOGGER.info(HtmlCreator.SECONDARY_REPORT_LIST.get(i) + "is appended");
				 else
					 LOGGER.info(HtmlCreator.SECONDARY_REPORT_LIST.get(i) + "is not appended");
			 }
			 HtmlCreator.stringAppender(HtmlCreator.BUFFERED_WRITER,  "</body></html> \n");
			 HtmlCreator.BUFFERED_WRITER.close();
		 }
		 catch(IOException e) {
			 LOGGER.error("Exception found in catch(IOException e) " + e);
		 }
		 
	 }
	  
  }