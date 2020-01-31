package util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class FolderCreation {
	
	private static Logger LOGGER = LogManager.getLogger(FolderCreation.class);
	public static String REPORT_PATH = null;
	public static String SCREENSHOT_PATH = null;
	
	public static void main(String args[]) {
		String path = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
		createDirectory(path, folderNameCreator());
		
	}
	
	public static String folderNameCreator() {
		DateFormat dateFormat = new SimpleDateFormat("MMMDDYYYY_HHmmssSS");  //Note (:) is not accepted for direcotry creation
		Date currentDate = new Date();
		String folderName = "Report_" + dateFormat.format(currentDate);
		System.out.println("Folder name is " + folderName ); // Folder name is Report_Jan062020_130258499
		return folderName;
	}
	
	/*
	 * This method returns whether the Directory creates under the path or not
	 */
	public static boolean createDirectory(String path, String folderName) {
		LOGGER.info("ENTER into createDirectory(String path, String folderName)");
		boolean isDirectoryCreated = false;
		REPORT_PATH = path + folderName;
		LOGGER.info("Report path: " + REPORT_PATH);
		// folder details D:\My Assignments\Selenium_UI_AUTOMATION\Reports\Report_Jan062020_130258499
		File file = new File(REPORT_PATH);
		if(!file.exists()) {
			if(file.mkdir()) {
				LOGGER.info("Directory created - " + REPORT_PATH);
				isDirectoryCreated = true;
				SCREENSHOT_PATH = REPORT_PATH + File.separator + "screenshots";
				File subDirectory = new File(SCREENSHOT_PATH);
				if(!subDirectory.exists()) {
					if(subDirectory.mkdir()) {
						LOGGER.info("subDirectory created - " + SCREENSHOT_PATH);
					}
					else {
						LOGGER.warn("SubDirectory not created");
					}
				}
			}
			else {
				LOGGER.warn("Directory not created");
				isDirectoryCreated = false;
			}
		}
		else {
			LOGGER.warn("Directory already exist");
			isDirectoryCreated = false;
		}
		LOGGER.info("EXIT from createDirectory(String path, String folderName)");
		return isDirectoryCreated;
	}
	
}
