package parallel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.FolderCreation;
import util.HtmlCreator;

public class HTML_FileCreator {

	//Folder Creation
	public static String folderName = FolderCreation.folderNameCreator();
	public static String tempPath = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
	public static String reportHeaderFile = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\HTML Report\\Primary Files\\HeaderHtmlFile.txt";
	public static String reportFooterFile = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\HTML Report\\Primary Files\\FooterHtml.txt";
	public static String actualPath = null;
	
	public static List<String> SECONDARY_REPORT_LIST = null;
	
	public static Logger LOGGER = LogManager.getLogger(HTML_FileCreator.class);
	
	public String FILE_NAME = null;
	public BufferedWriter BUFFERED_WRITER = null;
	
	public static int TEMP_COUNT = 0;
	  
	  public static String reportGenerator() {
		  if(FolderCreation.REPORT_PATH == null) {
			  FolderCreation.createDirectory(tempPath, folderName);  // this is for report folder
			  actualPath = FolderCreation.REPORT_PATH;
			 // HtmlCreator.createReportFile(actualPath);  // HtmlCreator.BUFFERED_WRITER (Report.html)
			  SECONDARY_REPORT_LIST = new ArrayList<String>();  // to add the subReports in this list to append with primary report
	  }	
		  return actualPath;
	  }
	  
	  public static void primaryReportAppender() {
		  if(HtmlCreator.copyFileContent(HtmlCreator.BUFFERED_WRITER, reportHeaderFile)) {
			  LOGGER.info("Header content copied to the primary report");
			  HtmlCreator.SECONDARY_REPORT_LIST = new ArrayList<String>();  // to add the subReports in this list to append with primary report 
		  }
		  else {
			  LOGGER.warn("Header content not copied to the primary report");
		  }
	  }	
	  
	  
	  public BufferedWriter createSubReportFile(String reportPath, String fileName)  {
		  
			LOGGER.info("ENTER into createReportFile(String reportPath)");
			FILE_NAME = reportPath + File.separator + fileName; // D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\MMMDDYYY_HHmmssSS\\Report.html"
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
	  
	  public boolean copyFileContent(BufferedWriter writer, String sourceFile) {
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
		
		
		public void stringAppender(BufferedWriter fileAppender, String appendString) {
			try {
				fileAppender.write(appendString + "\n");
			} catch (IOException e) {
				System.out.println("Exception found in createFile(String filePath) " + e);
			}
		}
	  
		/*
		 * Copy source file content to the destination file
		 */
		public void copyFile(String sourceFile, String destinationFile) {
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
				paramList.put("PARAMETER_TOGGLE_INDEX_1", "" + TEMP_COUNT); // toggle(1, 'open') / toggle(2, 'open') /....
				paramList.put("PARAMETER_S.No", "" + TEMP_COUNT); // 
				paramList.put("PARAMETER_TestName", testScriptName);
				paramList.put("PARAMETER_subItem", "subItem" + TEMP_COUNT);
				paramList.put("PARAMETER_testData", "testData" + TEMP_COUNT);
				paramList.put("PARAMETER_dataItem", "dataItem" + TEMP_COUNT);
				paramList.put("PARAMETER_TOGGLE_INDEX_2", "" + 1);
				
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
//			         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
//			         System.out.println(mentry.getValue());
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
	  
		 public BufferedWriter createFile(String filePath) {
				File file = null;
				FileWriter fw = null;
				BufferedWriter fileAppender = null;
				try {
				LOGGER.info("file path value is  " + filePath);
				 file = new File(filePath);
				// fw = new FileWriter(file, false);  // false will create new file every time, true will overwrite with the existing file 
				 fw = new FileWriter(file, false);
				 fileAppender = new BufferedWriter(fw);		
				}
				catch(IOException e){
					LOGGER.warn("Exception found in createFile(String filePath) " + e);
				}
				return fileAppender;
			}
		  
		  public void mergeFile(BufferedWriter writer, String fileToMerge) {
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToMerge));
					String line = bufferedReader.readLine();
					while(line != null){
					stringAppender(writer, line);
					line = bufferedReader.readLine();
					}
					bufferedReader.close();
				}
				catch(FileNotFoundException e) {
					System.out.println("Exception found in catch(FileNotFoundException e) " + e);
				}
				catch(IOException e) {
					System.out.println("Exception found in catch(IOException e) " + e);
				}		
			}
		  
		  
		
		
}