package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler_COPY {
	
//	public static Workbook EXCEL_DOC = null;
//	public static Sheet EXCEL_SHEET = null;
//	public static Row ROW_REF = null;
//	public static Cell CELL_REF = null;
//	
//	public static File FILE = null;
//	public static FileInputStream FILE_READER = null;
//	
//	public static int ROW_COUNT = 0;
//	public static int ROW_NUMBER = 0;
//	public static int ROW_NUMBER_READ = 0;
//	public static int ROW_NUMBER_TEMP = 0;  // to check the content exist in the cell / not
//	public static int COLUMN_COUNT = 0;
//	public static int COLUMN_NUMBER = 0;	
//	
//	public static String CELL_VALUE = null;
//	public static String SEARCH_STATUS = null;
//	public static String FILE_EXTESION = null;
//	
//	public static boolean IS_SEARCH_FOUND = false;	
//	public static ArrayList<String> ROW_DATA = null;	
//	public static ArrayList<ArrayList <String>> ALL_DATA = null;  // ArrayList within another ArrayList (Added Rows in this arrayList
//	
//	public static List<String> SINGLE_ROW_DATA = null;
//	public static List<String>[] ALL_ROW_DATA = null;   // Arrayof List
	
	public static Logger LOGGER = LogManager.getLogger(ExcelHandler_COPY.class);
		
	
	
	
	//**************************************************************************
	// Read ROW(s) Data from Excel Sheet - BEGINs
		public static List<String>[] readRowDataCopy(String filePath, String sheetNameParam){  // return the Array of ArrayList
			List<String>[] ALL_ROW_DATA = null; 
			try{
				List<String> SINGLE_ROW_DATA = null;
				//List<String>[] ALL_ROW_DATA = null;   // Arrayof List
				LOGGER.info("Enter into main() try block");
				if(filePath.lastIndexOf(".") >=0 ){  // if check index "." - BEGIN
					String FILE_EXTESION = filePath.substring(filePath.lastIndexOf(".")+1, filePath.length());
					if(FILE_EXTESION.equals("") || FILE_EXTESION.equals(null)){   // if check file extension? - BEGIN
					LOGGER.error("No extenison found");
					} // if check file extension? - BEGIN
					else if(FILE_EXTESION.equalsIgnoreCase("xlsx")){ // else if check file extension xlsx? - BEGIN
						LOGGER.info("file extension is xlsx and SheetName is " + sheetNameParam);
						File FILE = new File(filePath);
						FileInputStream FILE_READER = new FileInputStream(FILE);
						Workbook EXCEL_DOC = new XSSFWorkbook(FILE_READER);
						//LOGGER.info("***************" +sheetNameParam );
						Sheet EXCEL_SHEET = null;
						if( (EXCEL_SHEET = EXCEL_DOC.getSheet(sheetNameParam)) == null){  //if true sheetObject is  null - BEGIN  
							LOGGER.warn("Invalid sheet Name, getting sheet reference as " + EXCEL_SHEET);
						} //if true sheetObject is  null - END
						else{ //else false sheetObject is  valid getting (sheet exist)  - BEGIN											
							//ROW_COUNT = EXCEL_SHEET.getLastRowNum() - EXCEL_SHEET.getFirstRowNum() - 1; // -1 ignoring header count (wrong assumption)
							LOGGER.info("first row index: " + EXCEL_SHEET.getLastRowNum() + "last row index: "+ EXCEL_SHEET.getFirstRowNum());   // 0 ----   n
							int ROW_COUNT = EXCEL_SHEET.getLastRowNum() - EXCEL_SHEET.getFirstRowNum();  // n - 0 = n
							LOGGER.info("sheet reference value " + EXCEL_SHEET + "sheetName is " + EXCEL_SHEET.getSheetName() + "no. of rows(excluding header): " + ROW_COUNT);
							if(ROW_COUNT >= 1 ){  // if RowCount Check - BEGIN							
								//List<String>[] ALL_ROW_DATA = new List[ROW_COUNT];  // Arrayof List
								ALL_ROW_DATA = new List[ROW_COUNT];  // Arrayof List
								for(int ROW_NUMBER =1; ROW_NUMBER <= ROW_COUNT; ROW_NUMBER++){ // skip the header 0 (ROW_NUMBER =1: starts from row 2) - header row
								Row ROW_REF = EXCEL_SHEET.getRow(ROW_NUMBER);
								String CELL_VALUE = ROW_REF.getCell(0).getStringCellValue();
								LOGGER.info("cell value " + CELL_VALUE);
								int ROW_NUMBER_TEMP = ROW_NUMBER;
								if(ROW_NUMBER_TEMP > 0) { // if not in Header ROW 0 - BEGIN
									int COLUMN_COUNT = EXCEL_SHEET.getRow(0).getLastCellNum();
									LOGGER.info("Cloumn coutn for ROW " + ROW_NUMBER  + " is " +COLUMN_COUNT);
									//List<String> SINGLE_ROW_DATA = new ArrayList<String>();  // creating new object for each loop for a row								
									SINGLE_ROW_DATA = new ArrayList<String>();  // creating new object for each loop for a row
									for(int COLUMN_NUMBER =0; COLUMN_NUMBER < COLUMN_COUNT;COLUMN_NUMBER++){  // read from first column (COLUMN_NUMBER =0)	
										// for read data in specified Row - BEGIN									
										Cell CELL_REF = EXCEL_SHEET.getRow(ROW_NUMBER_TEMP).getCell(COLUMN_NUMBER);
										if(CELL_REF.getCellType()  ==  Cell.CELL_TYPE_STRING){  // if check cell type string ? - BEGIN
											// 1 - String 
											LOGGER.info(CELL_REF + " type is String");
											SINGLE_ROW_DATA.add(EXCEL_SHEET.getRow(ROW_NUMBER_TEMP).getCell(COLUMN_NUMBER).getStringCellValue());
										} // if check cell type string ? - BEGIN
										else if(CELL_REF.getCellType() ==  Cell.CELL_TYPE_NUMERIC){ //else  if check cell type numeric ? - BEGIN
											// 0 - number   --- CELL_REF.getCellType()										
											String tempString = EXCEL_SHEET.getRow(ROW_NUMBER_TEMP).getCell(COLUMN_NUMBER).toString();										  
											if(tempString.contains("E")){   // 9.999999087E9
												float tempFloat = Float.parseFloat(tempString); // 9.999999087E9
												long tempLong = (long) tempFloat; // 9999998976
												LOGGER.info(CELL_REF + " type is converted to Float as " + tempLong);
												SINGLE_ROW_DATA.add(String.valueOf(tempLong));  // convert long to string and add into list
											}
											else if(tempString.contains(".")){   // else if - check decimal value - BEGIN
												int position = tempString.indexOf(".");
												String decimalString = tempString.substring(position+1);
												 if(decimalString.contains("1") || decimalString.contains("2") || decimalString.contains("3") || decimalString.contains("4") || 
														 decimalString.contains("5") || decimalString.contains("6") || decimalString.contains("7") || decimalString.contains("8") || 
														 decimalString.contains("9")){  // contain greater that zero after the decimal
													 LOGGER.info(tempString + " decimal contains > 0 ");
													 SINGLE_ROW_DATA.add(tempString);
												 }
												 else{ // contain only zero after the decimal
													 float tempFloat = Float.parseFloat(tempString);
													 int tempInt = (int) tempFloat;
													 LOGGER.info(String.valueOf(tempInt) + " decimal contains only 0");
													 SINGLE_ROW_DATA.add(String.valueOf(tempInt)); // convert int value to string
												 }
											}  // else if - check decimal value - END
										} //else  if check cell type numeric ? - END
										else if(CELL_REF.getCellType() == Cell.CELL_TYPE_BLANK){
											LOGGER.warn("Cell is empty / blank");
											SINGLE_ROW_DATA.add("NA");
										}
										else{   //else check cell type others ? - BEGIN
											LOGGER.info("type is ???????");
										} //else check cell type others ? - END
									} // for read data in specified Row - END
									LOGGER.info("ROW_DATA size: " + SINGLE_ROW_DATA.size() + " ROW_DATA contains --" + SINGLE_ROW_DATA);																
								} // if not in Header ROW 0 - BEGIN  								 
								 ALL_ROW_DATA[ROW_NUMBER-1] = SINGLE_ROW_DATA;   // row index starts from 1 but array index starts from 0 (i.e., ALL_ROW_DATA[ROW_NUMBER-1])														
							}  // for loop check row wise - END
						} // if RowCount Check - END
							else{ // else RowCount Check - BEGIN
								LOGGER.error("No row(s) found ");
							} // else RowCount Check - BEGIN
						} //else false sheetObject is  valid getting (sheet exist)  - END
						FILE_READER.close();			
					}   // else if check file extension xlsx? - END
					else if(FILE_EXTESION.equalsIgnoreCase("xls")){  // else if check file extension xls? - BEGIN
						LOGGER.info("file extension is xls");
					} // else if check file extension xls? - END
					else{  // else check file extension neither xls / xlsx ? - BEGIN
						LOGGER.info("file extension is neither xlsx nor xls");
					}  // else check file extension neither xls / xlsx ? - BEGIN
				} // if check index "." - End
				else{ //else of  if check index "." - BEGIN
					LOGGER.error("Cannot find file extension");
				} //else of  if check index "." - END
			}
			catch(Exception e){
				LOGGER.error("excetpion found in readRowData(String filePath, String sheetNameParam) " + e);
			}
			return ALL_ROW_DATA;
		}  // Read Data from Excel Sheet - Ends

	
	//**************************************************************************
		
		
	/*-----------------------------------------------------*/
		// Read ROW(s) Data based on the testData
				public static Object[][] readInputData(String filePath, String sheetNameParam, String testScriptName){  // return the Array of ArrayList
					List<String>[] ALL_ROW_DATA = null; 
					Object[][] objectRef_Data = null;  //----------------
					try{
						
						LOGGER.info("Enter into main() try block");
						if(filePath.lastIndexOf(".") >=0 ){  // if check index "." - BEGIN
							String FILE_EXTESION = filePath.substring(filePath.lastIndexOf(".")+1, filePath.length());
							if(FILE_EXTESION.equals("") || FILE_EXTESION.equals(null)){   // if check file extension? - BEGIN
							LOGGER.error("No extenison found");
							} // if check file extension? - BEGIN
							else if(FILE_EXTESION.equalsIgnoreCase("xlsx")){ // else if check file extension xlsx? - BEGIN
								LOGGER.info("file extension is xlsx and SheetName is " + sheetNameParam);
								File FILE = new File(filePath);
								FileInputStream FILE_READER = new FileInputStream(FILE);
								Workbook EXCEL_DOC = new XSSFWorkbook(FILE_READER);
								//LOGGER.info("***************" +sheetNameParam );
								Sheet EXCEL_SHEET = null;
								if( (EXCEL_SHEET = EXCEL_DOC.getSheet(sheetNameParam)) == null){  //if true sheetObject is  null - BEGIN  
									LOGGER.warn("Invalid sheet Name, getting sheet reference as " + EXCEL_SHEET);
								} //if true sheetObject is  null - END
								else{ //else false sheetObject is  valid getting (sheet exist)  - BEGIN											
									//ROW_COUNT = EXCEL_SHEET.getLastRowNum() - EXCEL_SHEET.getFirstRowNum() - 1; // -1 ignoring header count (wrong assumption)
									LOGGER.info("first row index: " + EXCEL_SHEET.getLastRowNum() + "last row index: "+ EXCEL_SHEET.getFirstRowNum());   // 0 ----   n
									int ROW_COUNT = EXCEL_SHEET.getLastRowNum() - EXCEL_SHEET.getFirstRowNum();  // n - 0 = n
									LOGGER.info("sheet reference value " + EXCEL_SHEET + "sheetName is " + EXCEL_SHEET.getSheetName() + "no. of rows(excluding header): " + ROW_COUNT);
									if(ROW_COUNT >= 1 ){  // if RowCount Check - BEGIN									
										List<Integer> dataFoundInRow = new ArrayList<Integer>();
									for(int ROW_NUMBER = 1; ROW_NUMBER <= ROW_COUNT; ROW_NUMBER++){ // skip the header 0 (ROW_NUMBER =1: starts from row 2) - header row
										Row ROW_REF = EXCEL_SHEET.getRow(ROW_NUMBER);
										String CELL_VALUE = ROW_REF.getCell(0).getStringCellValue();
										LOGGER.info("cell value " + CELL_VALUE);
										int ROW_NUMBER_TEMP = ROW_NUMBER;
										if(ROW_NUMBER_TEMP > 0) { // if not in Header ROW 0 - BEGIN	
											if((CELL_VALUE.trim()).equals(testScriptName)) {
												LOGGER.info("TestCase Data found in row no. " + ROW_NUMBER);
												dataFoundInRow.add(ROW_NUMBER);
											}																
										} // if not in Header ROW 0 - BEGIN					
									}  // for loop check row wise - END
									
									int searchFoundCount = dataFoundInRow.size(); 
									 
									int COLUMN_COUNT = EXCEL_SHEET.getRow(0).getLastCellNum(); 
									objectRef_Data = new Object[searchFoundCount][COLUMN_COUNT];
									
									
									
									if( searchFoundCount > 0) {  //  if(dataFoundInRow.size() > 0) - BEGIN
										LOGGER.info("TestData found in row no(s): " + dataFoundInRow);
										
										for(int rowIndex = 0; rowIndex < searchFoundCount; rowIndex++) { 
											int searchIndex = dataFoundInRow.get(rowIndex);
											// dataFoundInRow.get(rowIndex) -- to get the rowIndex of the respective testdata row
											int columnCount = EXCEL_SHEET.getRow(searchIndex).getLastCellNum();											
										  for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {											  
											  Cell CELL_REF = EXCEL_SHEET.getRow(searchIndex).getCell(columnIndex);
												if(CELL_REF.getCellType()  ==  Cell.CELL_TYPE_STRING){  // if check cell type string ? - BEGIN
													// 1 - String 
													LOGGER.info(CELL_REF + " type is String");
													objectRef_Data[rowIndex][columnIndex] = CELL_REF.getStringCellValue();
												} // if check cell type string ? - BEGIN
												else if(CELL_REF.getCellType() ==  Cell.CELL_TYPE_NUMERIC){ //else  if check cell type numeric ? - BEGIN
													// 0 - number   --- CELL_REF.getCellType()										
													String tempString = CELL_REF.toString();										  
													if(tempString.contains("E")){   // 9.999999087E9
														float tempFloat = Float.parseFloat(tempString); // 9.999999087E9
														long tempLong = (long) tempFloat; // 9999998976
														LOGGER.info(CELL_REF + " type is converted to Float as " + tempLong);
														objectRef_Data[rowIndex][columnIndex] = String.valueOf(tempLong);
													}
													else if(tempString.contains(".")){   // else if - check decimal value - BEGIN
														int position = tempString.indexOf(".");
														String decimalString = tempString.substring(position+1);
														 if(decimalString.contains("1") || decimalString.contains("2") || decimalString.contains("3") || decimalString.contains("4") || 
																 decimalString.contains("5") || decimalString.contains("6") || decimalString.contains("7") || decimalString.contains("8") || 
																 decimalString.contains("9")){  // contain greater that zero after the decimal
															 LOGGER.info(tempString + " decimal contains > 0 ");
															 objectRef_Data[rowIndex][columnIndex] = tempString;
														 }
														 else{ // contain only zero after the decimal
															 float tempFloat = Float.parseFloat(tempString);
															 int tempInt = (int) tempFloat;
															 LOGGER.info(String.valueOf(tempInt) + " decimal contains only 0");
															 objectRef_Data[rowIndex][columnIndex] = String.valueOf(tempInt);
														 }
													}  // else if - check decimal value - END
												} //else  if check cell type numeric ? - END
												else if(CELL_REF.getCellType() == Cell.CELL_TYPE_BLANK){
													LOGGER.warn("Cell is empty / blank");
													objectRef_Data[rowIndex][columnIndex] = "NA";
												}
												else if(CELL_REF.toString().equals(null)){
													LOGGER.warn("Cell is null");
													objectRef_Data[rowIndex][columnIndex] = "not set";
												}
												else{   //else check cell type others ? - BEGIN
													LOGGER.info("type is ???????");
												} //else check cell type others ? - END
										  }
										}
										
									}  //  if(dataFoundInRow.size() > 0) - END									
									else { //  else(dataFoundInRow.size() > 0) - BEGIN
										LOGGER.info("No test data found");	
									} //  else(dataFoundInRow.size() > 0) - END
									
								} // if RowCount Check - END
									else{ // else RowCount Check - BEGIN
										LOGGER.error("No row(s) found ");
									} // else RowCount Check - BEGIN
								} //else false sheetObject is  valid getting (sheet exist)  - END
								FILE_READER.close();			
							}   // else if check file extension xlsx? - END
							else if(FILE_EXTESION.equalsIgnoreCase("xls")){  // else if check file extension xls? - BEGIN
								LOGGER.info("file extension is xls");
							} // else if check file extension xls? - END
							else{  // else check file extension neither xls / xlsx ? - BEGIN
								LOGGER.info("file extension is neither xlsx nor xls");
							}  // else check file extension neither xls / xlsx ? - BEGIN
						} // if check index "." - End
						else{ //else of  if check index "." - BEGIN
							LOGGER.error("Cannot find file extension");
						} //else of  if check index "." - END
					}
					catch(Exception e){
						LOGGER.error("excetpion found in readRowData(String filePath, String sheetNameParam) " + e);
					}
					//return ALL_ROW_DATA;
					return objectRef_Data;
				}  // Read Data from Excel Sheet - Ends
	/******************************************************/
	
}
