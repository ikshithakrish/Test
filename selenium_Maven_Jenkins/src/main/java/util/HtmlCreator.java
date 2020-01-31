package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class HtmlCreator {	
	private static Logger LOGGER = LogManager.getLogger(HtmlCreator.class);
	
	public static String REPORT_PATH = null;
	public static String FILE_NAME = null;
	
	public static int TEMP_COUNT = 0;
	public static BufferedWriter BUFFERED_WRITER = null;
	public static FileWriter FILE_WRITER = null;
	
	public static List<String> SECONDARY_REPORT_LIST = null;


	public static FileWriter fileCreator(String reportPath) {
		//FileWriter fw = null;
		try {
			String fileName = reportPath + "\\Report.html"; // D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\MMMDDYYY_HHmmssSS\\Report.html"
			File file = new File(fileName);
			if(file.exists()) {
				file = new File(fileName);
			}			
			FILE_WRITER = new FileWriter(file, true); // true to append
			//FILE_WRITER.write("<tr><td>Step Name</td><td>Step Description</td><td>Expected Result</td><td>Object Name</td><td>Action</td><td>Parameter</td><td>Status</td></tr>");
			//FILE_WRITER.close();  // need to close		
			}
		catch(IOException e) {
			System.out.println("Error found in file creations");
		}
		return FILE_WRITER; 
	}
	
	/*
	 * this method is to create and return a bufferedwriter reference of a file
	 */
	public static BufferedWriter createFile(String reportPath) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter bufferedWriter = null;
		try {
			System.out.println("file path value is  " + reportPath);
		 file = new File(reportPath);
		 fw = new FileWriter(file, true);  // false will create new file every time, true will overwrite with the existing file 
		 bufferedWriter = new BufferedWriter(fw);		
		}
		catch(IOException e){
			System.out.println("Exception found in createFile(String filePath) " + e);
		}
		return bufferedWriter;
	}
	
	/*
	 * this method is to append and return a bufferedwriter reference of a file
	 */
	public static BufferedWriter appendFile(String reportPath) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter bufferedWriter = null;
		try {
			System.out.println("file path value is  " + reportPath);
		 file = new File(reportPath);
		 fw = new FileWriter(file, true);  // true will overwrite in the existing file every time, false will create new file every time 
		 bufferedWriter = new BufferedWriter(fw);		
		}
		catch(IOException e){
			System.out.println("Exception found in createFile(String filePath) " + e);
		}
		return bufferedWriter;
	}
	
	public static BufferedWriter createReportFile(String reportPath)  {
		LOGGER.info("ENTER into createReportFile(String reportPath)");
		FILE_NAME = reportPath + "\\Report.html"; // D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\MMMDDYYY_HHmmssSS\\Report.html"
		File file = null;
		FileWriter fileWriter = null;
		//BufferedWriter fileAppender = null;
		try {  // createFile(String reportPath) - try BEGIN
			LOGGER.info("ENTER into createFile(String reportPath) try");
			LOGGER.info("File path is " + FILE_NAME);
			file = new File(FILE_NAME);
			fileWriter = new FileWriter(file, true);  // true will keep appending on the existing file if the file not exist it we create a new file
		 if(file.exists()) {				
				BUFFERED_WRITER = new BufferedWriter(fileWriter);		
			}		 
		 else {
			LOGGER.warn("file not exist"); 
		 } 
		 LOGGER.info("ENTER into createFile(String reportPath) try");
		 		
		} // createFile(String reportPath) - try END
		catch(IOException e){ 
			LOGGER.info("ENTER into createFile(String reportPath) catch(IOException e)");
			LOGGER.info("Exception found in catch(IOException e) " + e);
			LOGGER.info("EXIT from createFile(String reportPath) catch(IOException e)");
		}
		LOGGER.info("EXIT from createReportFile(String reportPath)");
		return BUFFERED_WRITER;
	}
	
	
	public static void fileAppender(FileWriter fw, String str) {
		try {
			fw.write(str);
		}
		catch(IOException e) {
			System.out.println("Faild in appending the file " + e);
		}
		
	}
	
	public static boolean copyFileContent(BufferedWriter writer, String sourceFile) {
		LOGGER.info("ENTER into copyFileContent(BufferedWriter writer, String sourceFile)");
		 boolean isFileCopied = false;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));
			String line = bufferedReader.readLine();
			while(line != null){
			stringAppender(writer, line);
			line = bufferedReader.readLine();
			}
			bufferedReader.close();
			isFileCopied = true;
		}
		catch(FileNotFoundException e) {
			System.out.println("Exception found in catch(FileNotFoundException e) " + e);
			isFileCopied = false;
		}
		catch(IOException e) {
			System.out.println("Exception found in catch(IOException e) " + e);
			isFileCopied = false;
		}
		LOGGER.info("EXIT from copyFileContent(BufferedWriter writer, String sourceFile)");
		return isFileCopied;
	}
	
	
	public static void stringAppender(BufferedWriter fileAppender, String appendString) {
		try {
			fileAppender.write(appendString + "\n");
		} catch (IOException e) {
			System.out.println("Exception found in createFile(String filePath) " + e);
		}
	}
	
	/*
	 * Copy source file content to the destination file
	 */
	public static void copyFile(String sourceFile, String destinationFile) {
		LOGGER.info("ENTER into copyFile(String sourceFile, String destinationFile)");
		try {
			FileInputStream fis = new FileInputStream(sourceFile);		
			
			FileOutputStream fos = new FileOutputStream(destinationFile);
			int b;
			while( (b=fis.read()) != -1) {  // -1 is end of line  fis.read() IOException required 
				fos.write(b);
			}
			fis.close();
			fos.close();
		}
		catch(FileNotFoundException e) {
			LOGGER.warn("Exception found in catch(FileNotFoundException e) " + e);
		}
		catch(IOException e) {
			LOGGER.warn("Exception found in catch(IOException e) " + e);
		}
		LOGGER.info("Exit from copyFile(String sourceFile, String destinationFile)");
	}
	
	/*
	 * this menthod is use to find and replace the string in an existing file
	 */
	public static void findReplaceString(String filePath, String testScriptName) {
		LOGGER.info("ENTER into findReplaceString(String toFind, String toReplace, String filePath)");
		TEMP_COUNT = TEMP_COUNT + 1;
		String oldContent = "";
		File file = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		FileWriter fileWriter = null;
		String line = null;
		try {			
			HashMap<String, String> paramList = new HashMap<String, String>();
			paramList.put("PARAMETER_main", "main" + TEMP_COUNT);  //main1
			paramList.put("PARAMETER_TOGGLE_INDEX_1", "" + TEMP_COUNT); // 1
			paramList.put("PARAMETER_S.No", "" + TEMP_COUNT); // 
			paramList.put("PARAMETER_TestName", testScriptName);
			paramList.put("PARAMETER_subItem", "subItem" + TEMP_COUNT);
			paramList.put("PARAMETER_testData", "testData" + TEMP_COUNT);
			paramList.put("PARAMETER_dataItem", "dataItem" + TEMP_COUNT);
			paramList.put("PARAMETER_TOGGLE_INDEX_2", "" + TEMP_COUNT);
			
			file = new File(filePath);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			//Reading all the lines of input text file into oldContent
			line = bufferedReader.readLine();
			while(line !=null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = bufferedReader.readLine();
			}
			//Replacing oldString with newString in the oldContent
			// String newContent = oldContent.replaceAll(toFind, toReplace);
			String newContent = oldContent;
			Set set = paramList.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		         Map.Entry<String, String> map = (Map.Entry) iterator.next();
//		         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
//		         System.out.println(mentry.getValue());
		         newContent = newContent.replaceAll(map.getKey(), map.getValue());
		      }
			
			//Rewriting the input text file with newContent
			fileWriter = new FileWriter(file);
			fileWriter.write(newContent);
			fileReader.close();
		}
		catch(FileNotFoundException e) {
			LOGGER.warn("Exception found in catch(FileNotFoundException e) " + e);
		}
		catch(IOException e) {
			LOGGER.warn("Exception found in catch(IOException e) " + e);
		}
		finally {
			try {
				bufferedReader.close();
				fileWriter.close();
			}
			catch(IOException e) {
				LOGGER.warn("Exception found in finally - catch(IOException e) " + e);
			}			
		}		
		LOGGER.info("Exit from findReplaceString(String toFind, String toReplace, String filePath)");		
	}

	/*
	 * this method is will set the report path append with updated Folder name 
	 */
	public static String folderPath(String path) { // folderPath(String path) - BEGINs
		LOGGER.info("ENTER into folderPath(String path)");
		String folderName = FolderCreation.folderNameCreator();
		if(FolderCreation.createDirectory(path, folderName)) { // if(FolderCreation.createDirectory(tempPath, folderName) - BEGINs
			REPORT_PATH = path + folderName + "\\";
			LOGGER.info("Report Path is " + REPORT_PATH);
			// String primaryFile = actualPath + "Report.html";
		} // if(FolderCreation.createDirectory(tempPath, folderName) - ENDs
		else {
			LOGGER.warn("Directory not created / already exist");
		}
		LOGGER.info("EXIT from folderPath(String path)");
		return "REPORT_PATH";
	} // folderPath(String path) - ENDs
	
	
	
} // End of class