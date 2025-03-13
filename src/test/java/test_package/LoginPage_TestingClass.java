package test_package;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base_package.BaseClass;
import pom_package.HomePageOrangeHRM;
import pom_package.LoginPageOrangeHRM;
import utility_package.Utility_Parametrization;

public class LoginPage_TestingClass extends BaseClass{

	
	
	WebDriver driver;
	
	LoginPageOrangeHRM loginpageorangehrm;
	HomePageOrangeHRM homepageoranghrm;

	SoftAssert softassert;
	
	@BeforeTest
	@Parameters("browser1")
	public void launchBrowser(String browser) throws InterruptedException {
		
		if (browser.equals("chrome")) {
			
			driver = OpenChromeBrowser();
		}
		
		/*
		 * if (browser.equals("chrome")) {
		 * 
		 * driver = OpenFirefoxBrowser(); }
		 */
		Thread.sleep(2000);
	}
	
	@BeforeClass
	public void launchApplication() throws InterruptedException {
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(3000);
		
		loginpageorangehrm = new LoginPageOrangeHRM(driver);
		
		homepageoranghrm = new HomePageOrangeHRM(driver);
		
		softassert =new SoftAssert();
		
	}
	
	
	
	@Test(dataProvider="loginData")
	public void VerifyloginTest(String user, String pwd, String exp) throws InterruptedException {
		
		WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		username.clear();
		username.sendKeys(user);
		
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		password.clear();
		password.sendKeys(pwd);
		
		WebElement loginbutton = driver.findElement(By.xpath("//button[@type='submit']"));
		loginbutton.click();
		
		Thread.sleep(5000);
		
		String actURL = driver.getCurrentUrl();
		String expURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		if(exp.equals("Valid")) {
			
			if (actURL.equals(expURL)) {
				
				driver.findElement(By.xpath("//p[contains(@class,'oxd-userdropdown-name')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@href='/web/index.php/auth/logout']")).click();
				Thread.sleep(2000);
				softassert.assertTrue(true);
			}
			
			else {
				softassert.assertTrue(false);
			}
		}
		
		else if(exp.equals("InValid")){
			
			if(actURL.equals(expURL)) {

				driver.findElement(By.xpath("//p[contains(@class,'oxd-userdropdown-name')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@href='/web/index.php/auth/logout']")).click();
				Thread.sleep(2000);
				softassert.assertTrue(false);
			}
			
			else {
				softassert.assertTrue(true);
			}
		}
		
	}
	
	
	
	
	@DataProvider(name="loginData")
	public String[][] getData() throws EncryptedDocumentException, IOException{
		/*
		 * String[][] data= {
		 * 
		 * {"admin", "admin124", "Invalid"}, 
		 * {"Admin", "admin123", "Valid"}, 
		 * {"Admin1", "Admin127", "Invalid"}, 
		 * {"admin", "Admin23", "Invalid"} };
		 */
		
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Login_DATA.xlsx";
		
		Utility_Parametrization utils = new Utility_Parametrization(path);
		
		int totalrows = utils.getRowCount("Sheet1") +1;  //4+1=5
		System.out.println("TotalNumber of Rows :"+totalrows);
		
		int totalcolumns = utils.getCellCount("Sheet1", 0); //3
		System.out.println("TotalNumber of colums :"+totalcolumns);
		
		
		String data[][] = new String[totalrows-1][totalcolumns];
									//[4][3]
		
		for (int i=1; i<=totalrows-1; i++) {
			
			for (int j=0; j<=totalcolumns-1; j++) {
				
			 data[i-1][j]= utils.getCellDataString("Sheet1", i, j);
			
			}
		}
	
		
		return data;
		
	}
	
	
	
	
	
	
	
	
	
	
	@AfterClass
	public void cleanObjects() {
		
		loginpageorangehrm = null;
		homepageoranghrm =null;
		softassert = null;
		
		System.out.println("Objects are cleaned Successfully");
	}
	
	
	@AfterTest
	public void closedBrowser() {
		
		driver.quit();
		System.out.println("Browser closed successfully");
		driver=null;
		System.gc();
	}
	
}
