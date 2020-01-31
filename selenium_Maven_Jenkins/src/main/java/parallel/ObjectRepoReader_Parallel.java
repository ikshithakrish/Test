package parallel;

import java.util.Properties;

import org.apache.log4j.Logger;

import constants.Constants;

public class ObjectRepoReader_Parallel {
	Logger LOGGER = Logger.getLogger(ObjectRepoReader_Parallel.class);
	//public static final String FILE_PATH = ".\\src\\main\\resources\\properties_file\\objectRepository.properties";
	String OBJECT_REPO_VALUE = null;
	
	public String objectRepo_Search(String searchObject){
		try{
			LOGGER.info("ENTER into objectRepo_Search(String searchObject) try block" );
			Properties propertiesFile = ReadProperties.readPropertiesFile(Constants.OBJECT_REPOSITORY);
			OBJECT_REPO_VALUE = propertiesFile.getProperty(searchObject);
			LOGGER.info("EXIT from objectRepo_Search(String searchObject) try block" );
		}
		catch(Exception e){
			LOGGER.warn("ENTER into objectRepo_Search(String searchObject) catch block" );
			LOGGER.error("Exception found ---- " + e);
			LOGGER.warn("EXIT from objectRepo_Search(String searchObject) catch block" );
		}		
		return OBJECT_REPO_VALUE;
	}
	
	
}
