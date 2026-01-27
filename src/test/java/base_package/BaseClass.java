package base_package;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility_package.Property_Utils;

public class BaseClass {
	
	protected static Logger logger;
	
	protected static WebDriver driver;
	// Protected because we use this driver in LoginPageTestingClass, HomePageTestingClass and ListnerClass
	// Instead of Protected we can also use public ...it will also work

	/*public WebDriver OpenChromeBrowser() {
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser launched successfully");
		
		driver.manage().deleteAllCookies();
		
		return driver;
		
	}
	*/
	
	
	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		
		
		logger = Logger.getLogger("Project-Practice");
		//PropertyConfigurator.configure("log4j.properties");
		
		logger.info("===== Test Suite Started =====");
		
		if(driver==null) {
			
			String browser = System.getProperty("browser", "chrome"); //default
			
			boolean headless = Boolean.parseBoolean(System.getProperty("headless","false")); //bydefault it takes false value
			//Boolean.parseBoolean("true") → true..... //Boolean.parseBoolean("false") → true
			
			if(browser.equalsIgnoreCase("chrome")) {
				
				
				ChromeOptions options = new ChromeOptions();
				//It creates the configuration object for Chrome
				// It is used to tell Chrome how to start
				
					if(headless) {				
						options.addArguments("--headless=new"); //It means Chrome runs without opening UI
			            options.addArguments("--disable-gpu"); //It prevents the graphic-related issues on servers
			            options.addArguments("--window-size=1920,1080"); //Headless Chrome has no screen So this gives it"fake screen size"
			            logger.info("Running Chrome in HEADLESS mode");
					}
					else {
						logger.info("Running Chrome in UI mode");
					}
					
				
					driver = new ChromeDriver(options); 
				
					logger.info("Chrome Browser launched successfully");
			}
			
			
			else if (browser.equalsIgnoreCase("firefox")) {
				
				FirefoxOptions options = new FirefoxOptions();
				
				if(headless) {				
		            options.addArguments("--headless");
		            logger.info("Running Firefox in HEADLESS mode");
				}
				else {
					logger.info("Running Firefox in UI mode");
				}
				
				driver = new FirefoxDriver(options);
				
				logger.info("FireFox Browser launched successfully");
						
			}
				
				
		
			
			else {
				
				throw new RuntimeException("Unsupported Browser : "+ browser);
				
			}
			
				
			driver.manage().window().maximize();
			
			driver.manage().deleteAllCookies();
		}
		
	}
	
	
	/*public WebDriver OpenFirefoxBrowser() {
		
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser launched successfully");
		
		driver.manage().deleteAllCookies();
		return driver;
	}
	*/
	
	
	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		
		if(driver!=null) {			
			driver.quit();
			logger.info("Browser Closed successfully");
			driver = null;
			logger=null;
			System.gc();
		}
		
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
		
		//DEBUG LOGS (WRITE HERE)
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
