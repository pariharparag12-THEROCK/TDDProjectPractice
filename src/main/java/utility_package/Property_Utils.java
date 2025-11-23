package utility_package;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property_Utils {

	/*
	 * public static String readDataFromProperties(String key) throws IOException {
	 * 
	 * Properties properties = new Properties();
	 * 
	 * FileInputStream myfile = new FileInputStream(System.getProperty("user.dir")+
	 * "\\Project-Practice.properties");
	 * 
	 * properties.load(myfile);
	 * 
	 * String value = properties.getProperty(key);
	 * 
	 * return value;
	 * 
	 * 
	 * }
	 */
	
	
	public static String readDataFromProperties(String key) throws IOException {
		
		Properties properties = new Properties();
		
		String path = System.getProperty("user.dir")+"\\Project-Practice.properties";
		
		FileInputStream myfile = new FileInputStream(path);
		
		properties.load(myfile);
		
		String value = properties.getProperty(key);
		
		return value;
	}
	

}
