package util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public static Logger LOGGER = LogManager.getLogger(ScreenShot.class);
	
	public static boolean isCaptured;
	public static boolean captureScreen(String path, WebDriver driver){
		try{			
			if((! path.equals("")) || (! path.equals(null)) ){
			TakesScreenshot screenShot =((TakesScreenshot)driver);
			File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File(path);
			FileUtils.copyFile(sourceFile, destinationFile);
			LOGGER.info("Captured Screen");
			}
			else{
				LOGGER.error("path is not valid");
			}
		}
		catch(Exception e){
			LOGGER.warn("Exception in screen shot capturing " + e );
			}
		return isCaptured;
	}
}
