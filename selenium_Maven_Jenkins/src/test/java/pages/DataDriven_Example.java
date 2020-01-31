package pages;

import org.testng.annotations.Test;

import util.ExcelHandler_COPY;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataDriven_Example {
	
  @Test(dataProvider = "Input_Data")
  public void f(String testCaseName, String parameter1, String parameter2, String parameter3, String parameter4, String parameter5, String parameter6, String parameter7, String parameter8, String parameter9) {
	  System.out.println("ENTER into ");
	  System.out.println("value of n and s is " + testCaseName + " - " + parameter1 + " - " + parameter2 + " - " + parameter3 + " - " + parameter4 + " - " + parameter5+ " - " + parameter6+ " - " + parameter7+ " - " + parameter8+ " - " + parameter9);
	  System.out.println("EXIT from");
  }
  
  @DataProvider(name = "Input_Data")
  public Object[][] dp() throws IOException {
//    return new Object[][] {
//    	
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
//    };
//	  Object[][] ref = null;
//	  ref = new Object[2][3];
//	  
//	  for(int i=0; i<2; i++) {
//		  for(int j=0; j<3; j++) {
//			  ref[i][j] = "hai";
//		  }
//	  }
	  
	  Object test[][] = ExcelHandler_COPY.readInputData("D:\\My Assignments\\Selenium_UI_AUTOMATION\\TestData\\Excel_Data\\SampleTestData.xlsx", "Data", "Login");
	  
	  return test;
	  
  }
}
