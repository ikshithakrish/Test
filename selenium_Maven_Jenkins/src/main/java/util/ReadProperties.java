package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReadProperties {

	private static final Logger LOGGER = LogManager.getLogger(ReadProperties.class);

	public static Properties readPropertiesFile(String filePath) {
		Properties prop = new Properties();
		try {
			LOGGER.info("Enter into readPropertiesFile()");
			FileInputStream readPropertyFile = new FileInputStream(filePath);
			prop.load(readPropertyFile);
			LOGGER.info("Exit from readPropertiesFile()");
		} catch (IOException e) {
			LOGGER.info("Exception found in readPropertiesFile() - catch() " + e);
		}
		return prop;
	}

}
