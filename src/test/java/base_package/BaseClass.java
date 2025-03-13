package base_package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	public WebDriver OpenChromeBrowser() {
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser launched successfully");
		
		driver.manage().deleteAllCookies();
		
		return driver;
		
	}
	
	
	public WebDriver OpenFirefoxBrowser() {
		
		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		System.out.println("Chrome Browser launched successfully");
		
		driver.manage().deleteAllCookies();
		return driver;
	}
}
