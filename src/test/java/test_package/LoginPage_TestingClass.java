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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base_package.BaseClass;
import pom_package.HomePageOrangeHRM;
import pom_package.LoginPageOrangeHRM;
import utility_package.Utility_Parametrization;

public class LoginPage_TestingClass extends BaseClass{

	/* WebDriver driver; */
	
	//we use only driver of base class in this loginPagetestingclass thats why discommented this driver
	// If we use this driver in this class than 2 drivers will run...1 is baseclass driver and another is loginPagetestingClass driver....
	//...so that our script will confuse which driver is to take...If we execute through TestNg	byluck it will execute...
	//......but if we execute through maven it will throw null pointer exception..it will catch a bug
	//....we have method getURL()-----> this will use baseClass driver and remainings are use loginPageTesingClass driver...thats why our script will confuse
	
	String data[][];
	int currentindex;
	
	LoginPageOrangeHRM loginpageorangehrm;
	HomePageOrangeHRM homepageoranghrm;

	SoftAssert softassert;
	
	/*@BeforeTest
	@Parameters("browser1")
	public void launchBrowser(@Optional("chrome") String browser) throws InterruptedException {
		
		//Jenkins Parameter has a priority
	    String browserFromJenkins = System.getProperty("browser");

	    if(browserFromJenkins != null) {
	        browser = browserFromJenkins;
	    }
		
		
		if(browser.equals("chrome")) {
			
			driver = OpenChromeBrowser();
		} 
		else if(browser.equals("firefox")) {
		  
			driver = OpenFirefoxBrowser(); 
		  
		}
		
		
		Thread.sleep(2000);
	}
	*/
	
	
	
	@BeforeClass(alwaysRun=true)
	public void launchApplication() throws Exception {
		
		getURL();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(3000);
		
		loginpageorangehrm = new LoginPageOrangeHRM(driver);
		
		homepageoranghrm = new HomePageOrangeHRM(driver);
		
		softassert =new SoftAssert();
		
	}
	
	
	
	/*@Test(dataProvider="loginData")
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
	*/
	
	
	
	
/*	@Test (dataProvider = "loginData", alwaysRun=true)
	public void verifyloginTest(String user, String pwd, String exp) throws InterruptedException {
		
		
		WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		username.clear();
		username.sendKeys(user);
		
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		password.clear();
		password.sendKeys(pwd);
		
		WebElement loginbutton =driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));
		loginbutton.click();
		
		String actUrl = driver.getCurrentUrl();
		String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		if(exp.equals("Valid")) {
			
			if(actUrl.equals(expUrl)) {
				
				driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[text()='Logout']")).click();
				Thread.sleep(3000);
				softassert.assertTrue(true);
			}
			
			else {
				
				softassert.assertTrue(false);
				
			}
			
		}
		
		else if(exp.equals("Invalid")) {
			
			if(actUrl.equals(expUrl)) {
				
				driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[text()='Logout']")).click();
				Thread.sleep(3000);
				softassert.assertTrue(false);
			}
			
			else {
				
				softassert.assertTrue(true);
			}

		}
		
		
		Thread.sleep(2000);
		
		softassert.assertAll();
		
		
		
		
		
	}
	*/
	

	@Test(invocationCount = 4, groups= {"Smoke"}, priority=-1, alwaysRun=true)
	public void verifyloginTest() throws InterruptedException, EncryptedDocumentException, IOException {
		
		
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Login_DATA.xlsx";
		
		Utility_Parametrization utils = new Utility_Parametrization(path);
		
		int rowcount = utils.getRowCount("Sheet1") +1; //4+1=5...rowcount always start with 0
		
		int cellcount = utils.getCellCount("Sheet1", 0); //3
		
		
		data = new String[rowcount-1][cellcount];
		
		
		for(int i=1; i<=rowcount-1; i++) {
			
			for (int j=0; j<=cellcount-1; j++) {
				
				data[i-1][j] = utils.getCellData("Sheet1", i, j);
			}
		}


		
		
		
		String user = data[currentindex][0];
		String pwd = data[currentindex][1];
		String exp = data[currentindex][2];
		
		
		
		WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		username.clear();
		username.sendKeys(user);
		
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		password.clear();
		password.sendKeys(pwd);
		
		WebElement loginbutton =driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));
		loginbutton.click();
		
		String actUrl = driver.getCurrentUrl();
		String expUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		if(exp.equals("Valid")) {
			
			if(actUrl.equals(expUrl)) {
				
				driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[text()='Logout']")).click();
				Thread.sleep(3000);
				softassert.assertTrue(true);
			}
			
			else {
				
				softassert.assertTrue(false);
				
			}
			
		}
		
		else if(exp.equals("Invalid")) {
			
			if(actUrl.equals(expUrl)) {
				
				driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[text()='Logout']")).click();
				Thread.sleep(3000);
				softassert.assertTrue(false);
			}
			
			else {
				
				softassert.assertTrue(true);
			}

		}
		
		
		
		currentindex++;
		
		Thread.sleep(2000);
		
		softassert.assertAll();
		
		
		
		
		
	}
	
	
	
	
	/*@DataProvider(name="loginData")
	public String[][] getData() throws EncryptedDocumentException, IOException{
		
//		  String[][] data= {
//		  
//		  {"admin", "admin124", "Invalid"}, 
//		  {"Admin", "admin123", "Valid"}, 
//		  {"Admin1", "Admin127", "Invalid"}, 
//		  {"admin", "Admin23", "Invalid"} };
		 
		
		
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
	*/
	
//	@DataProvider(name="loginData")
//	public String[][] getData() throws EncryptedDocumentException, IOException {
//		
//		/*String data[][] = {
//							{"Admin","admin123","Valid"},
//							{"admin","admin124","Invalid"},
//							{"Admin1","Admin127","Invalid"},
//							{"admin","Admin23","Invalid"}
//				         };
//
//		return data;*/
//		
//		
//		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Login_DATA.xlsx";
//		
//		Utility_Parametrization utils = new Utility_Parametrization(path);
//		
//		int rowcount = utils.getRowCount("Sheet1") +1; //4+1=5...rowcount always start with 0
//		
//		int cellcount = utils.getCellCount("Sheet1", 0); //3
//		
//		
//		String data[][] = new String[rowcount-1][cellcount];
//		
//		
//		for(int i=1; i<=rowcount-1; i++) {
//			
//			for (int j=0; j<=cellcount-1; j++) {
//				
//				data[i-1][j] = utils.getCellData("Sheet1", i, j);
//			}
//		}
//
//		return data;
//	}
//	
	
	@AfterClass (alwaysRun=true)
	public void cleanObjects() {
		
		loginpageorangehrm = null;
		homepageoranghrm =null;
		softassert = null;
		
		System.out.println("Objects are cleaned Successfully");
	}
	
	
	/*@AfterTest (alwaysRun=true)
	public void closedBrowser() {
		
		driver.quit();
		System.out.println("Browser closed successfully");
		driver=null;
		System.gc();
	}*/

}
