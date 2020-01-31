package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReportGenerator {
	
	public static void main(String args[]) {
		System.out.println("ENTER into main");
		try {
			String path = "D:\\My Assignments\\Selenium_UI_AUTOMATION\\Reports\\";
			String folderName = FolderCreation.folderNameCreator();		
			
			if(FolderCreation.createDirectory(path, folderName) ) {
				String primaryFile = path + folderName + "\\Report.html";
				BufferedWriter writer = createFile(primaryFile);
				System.out.println("Writer is " + writer);
				stringAppender(writer, "<html><body><table border='1'>");
				for(int i = 1; i<=2; i++) {				
					stringAppender(writer, "<tr><td colspan='8'> <a href='#'> + </a> TestScript ${NAME} </td></tr> \n");	
					stringAppender(writer, "<tr><td colspan='8'>");
					mergeFile(writer, "D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\test.html");
					stringAppender(writer, "</td></tr>");
				}
				stringAppender(writer, "</table></body></html>");	
				writer.close();  // Note: BufferedWriter should be closed then only it will Write the data to the file
			}
			else {
				System.out.println("Directory not created");
			}
		}
		catch(IOException e) {
			System.out.println("Exception  in catch(IOException e) " + e);
		}
		System.out.println("Exit from main");		
	}
	
	public void reportHeader() {
		
	}
	
	public void reportFooter() {
		
	}
	
	public static void mergeFile(BufferedWriter writer, String fileToMerge) {
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
	
	public static BufferedWriter createFile(String filePath) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter fileAppender = null;
		try {
			System.out.println("file path value is  " + filePath);
		 file = new File(filePath);
		 fw = new FileWriter(file, false);  // false will create new file every time, true will overwrite with the existing file 
		 fileAppender = new BufferedWriter(fw);		
		}
		catch(IOException e){
			System.out.println("Exception found in createFile(String filePath) " + e);
		}
		return fileAppender;
	}
	
	public static void stringAppender(BufferedWriter fileAppender, String appendString) {		
		try {			
		 fileAppender.write(appendString);		
		}
		catch(IOException e){
			System.out.println("Exception found in createFile(String filePath) " + e);
		}
	}

}
