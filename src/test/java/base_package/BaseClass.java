package base_package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility_package.Property_Utils;

public class BaseClass {
	
	static WebDriver driver;

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
	public static void getURL(String environment) throws Exception {
		
		String baseUrl = Property_Utils.readDataFromProperties(environment);
		
		if(baseUrl == null) {
			
			throw new Exception("URL not found for the environment "+ environment);
			
		}
	
		driver.get(baseUrl);
	}
	
	
}
