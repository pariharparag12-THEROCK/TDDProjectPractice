package base_package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utility_package.Property_Utils;

public class BaseClass {
	
	protected static WebDriver driver;
	// Protected because we use this driver in LoginPageTestingClass, HomePageTestingClass and ListnerClass
	// Instead of Protected we can also use public ...it will also work

	public WebDriver OpenChromeBrowser() {
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser launched successfully");
		
		driver.manage().deleteAllCookies();
		
		return driver;
		
	}
	
	
	public WebDriver OpenFirefoxBrowser() {
		
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser launched successfully");
		
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	
	
	
	/*public static void getURL(String url) throws IOException {
		
		switch(url) {
		
			case("India") : driver.get(Property_Utils.readDataFromProperties(url));  break;
				
			case("Australia") : driver.get(Property_Utils.readDataFromProperties(url)); break;
			
			case("Turkey") : driver.get(Property_Utils.readDataFromProperties(url)); break;
			
			case("Japan") : driver.get(Property_Utils.readDataFromProperties(url)); break;
			
			default: System.err.println("Run method not choosen Properly");  break ;
				
		
		}

	}*/
	
	
	
	//It is feasible
	public static void getURL() throws Exception {
		

		//if we don't want to use the parameter then locally it will take QA+ India, if jenkins passes parameter than jenkins values are used
		String env = System.getProperty("env");
		String country = System.getProperty("country");
		
		// Local default ONLY if Jenkins did not pass values
	    if (env == null || country == null) {
	        env = "QA";
	        country = "India";
	    }
		
		String EnvironmentURL = env +"_"+country;
		
		// 🔍 DEBUG LOGS (WRITE HERE)
	    System.out.println("ENV = " + env);
	    System.out.println("COUNTRY = " + country);
	    System.out.println("KEY = " + EnvironmentURL);
		
		
		String baseUrl = Property_Utils.readDataFromProperties(EnvironmentURL);
		
		if(baseUrl == null) {
			
			throw new Exception("URL not found for the environment "+ EnvironmentURL);
			
		}
	
		driver.get(baseUrl);
	}
	
	
}
