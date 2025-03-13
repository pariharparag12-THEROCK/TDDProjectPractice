package test_package;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import base_package.BaseClass;
import pom_package.HomePageOrangeHRM;
import pom_package.LoginPageOrangeHRM;
import utility_package.FetchExcelData;
import utility_package.ScrollView;

public class HomePage_TestingClass extends BaseClass{
	
	
	public static Logger logger;
	
	WebDriver driver;
	
	LoginPageOrangeHRM loginpageorangehrm;
	
	HomePageOrangeHRM homepageoranghrm;
	
	SoftAssert softassert;
	
	

	@BeforeTest(alwaysRun=true)
	@Parameters("browser1")
	public void launchBrowser(String browser) throws InterruptedException {
		
		if(browser.equals("chrome")) {
			
			driver = OpenChromeBrowser();
		}
		
		/*
		 * if(browser.equals("firefox")) {
		 * 
		 * driver = OpenFirefoxBrowser(); 
		 * }
		 */
		
		
		logger	=	Logger.getLogger("Project-Practice");
		
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Log- Chrome Browser launching");
		
		
		Thread.sleep(2000);
	}
	
	
	@BeforeClass(alwaysRun=true)
	public void launchApplication() throws InterruptedException {
		
		
		try {
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			Thread.sleep(3000);
			
			logger.info("Application is Launching");
			
			loginpageorangehrm = new LoginPageOrangeHRM(driver);
			
			homepageoranghrm = new HomePageOrangeHRM(driver);
			
			softassert =new SoftAssert();
		}
		
		catch(Exception e) {
			logger.error("Error While Launching the application : "+ e.getMessage());
			throw e;
		}
		
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void LoginInToTheApplication() throws InterruptedException, IOException {
		
		logger.info("Entering Username");
		loginpageorangehrm.SendUserName();
		Reporter.log("UserName Entered", true);
		
		logger.info("Entering Password");
		loginpageorangehrm.SendPassword();
		Reporter.log("Password Entered", true);
		
		logger.info("Clicking on logging button");
		loginpageorangehrm.clickOnLoginButton();
		Reporter.log("Login button Clicked", true);
		
		Thread.sleep(2000);
	}
	
	

	@Test (groups = {"Sanity"},priority =-1)
	public void verifyAdminOption() throws InterruptedException {
		
		homepageoranghrm.clickOnAdminOption();
		System.out.println("Clicking on Admin Option");
		logger.info("Log - Clicking On Admin Option");
		
		String actURLAdminPage = driver.getCurrentUrl();
		String expectedURLAdminPage = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
		
		logger.warn("Log - Verifying the URL....It may be matched or unmatched");
		
		if (actURLAdminPage.equals(expectedURLAdminPage)) {
			
			logger.info("Log -Validating the admin urls");
			softassert.assertEquals(actURLAdminPage, expectedURLAdminPage);
			System.out.println("Admin URL matched Successfully");
			Reporter.log("Admin Urls matched successfully",true);
			logger.info("Admin Url validation matched - Test Case Passed");
			
		}
		
		else {
			
			logger.fatal("Admin Urls are not matching");
			softassert.assertEquals(actURLAdminPage, expectedURLAdminPage);
			System.out.println("Admin URL in not matched- TEST CASE FAILED");
			Reporter.log("Admin Urls are not matched ",false);
			logger.info("Admin Url validation is not matched - Test Case Failed");
		}
		
		softassert.assertAll();
		
		Thread.sleep(2000);
	}
	
	
	@Test(groups = {"Smoke"},priority =2)
	public void verifyCompanyNameInAboutWindowOption() throws InterruptedException {
		
		homepageoranghrm.clickAccoutDropDown();
		
		String ActualCompanyValue = homepageoranghrm.ClickOnAccountOption();
		
		if(ActualCompanyValue.equals("OrangeHRM")) {
			softassert.assertEquals(ActualCompanyValue, "OrangeHRM");
			System.out.println("Comapny Nmae is Matched Successfully");
		}
		
		else {
			softassert.assertEquals(ActualCompanyValue, "OrangeHRM");
			System.out.println("Comapny Nmae is not matched");
		}
		
		
		homepageoranghrm.clickingOnAboutWindowClosebutton();
		
		
		softassert.assertAll();
		
		Thread.sleep(2000);
	}
	
	
	
	@Test(groups = {"Regression"},priority=3)
	public void verifyEmployeeTableData() throws InterruptedException {
		
		
		homepageoranghrm.clickOnAdminOption();
		
		int statuscount = homepageoranghrm.getEmployeeData();
		
		if (statuscount!=0) {
			
			softassert.assertNotNull(statuscount);
			System.out.println("Total number of employee whose status are enabled : "+ statuscount+"\n"+"TestCase Passed");
		}
		
		else {
			softassert.assertNotNull(statuscount);
			System.out.println("Total number of employee whose status are enabled : "+ statuscount+"\n"+"TestCase Failed");
		}
		
		Thread.sleep(2000);
		
	}
	
	
	
	@Test(groups= {"Parag"},priority=-5)
	public void verifyUserCreationData() throws InterruptedException, EncryptedDocumentException, IOException {
		
		
		
		Actions action = new Actions(driver);
		
		
		homepageoranghrm.clickOnAdminOption();
		homepageoranghrm.clickOnAddButton();
		System.out.println("User Clicked On Add button");
		homepageoranghrm.selectUserRole();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
		homepageoranghrm.selectStatus();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
		homepageoranghrm.enterEmployeeName(FetchExcelData.getExcelFileData(1, 0));
		System.out.println("User entered Employeename");	
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        
        
		homepageoranghrm.enterUsername(FetchExcelData.getExcelFileData(1, 1));
		System.out.println("User entered Username");
		homepageoranghrm.enterPassword(FetchExcelData.getExcelFileData(1, 2));
		System.out.println("User entered Password");
		homepageoranghrm.enterConfirmPassord(FetchExcelData.getExcelFileData(1, 3));
		System.out.println("User entered ConfirmPassord");
		homepageoranghrm.clickOnSaveButton();
		System.out.println("User Clicked on save button");
		String toastmsg = homepageoranghrm.getSaveToastMessage();
		System.out.println("User captured toast msg");
		
		
		if(toastmsg.contains("Successfully")) {
			
			Assert.assertEquals(toastmsg, "Successfully Saved");
			Thread.sleep(3000);
			System.out.println("Toast messsage Validation successfull");
			
			WebElement ExpectedUser = driver.findElement(By.xpath("//div[text()='SanathJaysuriya']"));
			ScrollView.scrollIntoView(driver, ExpectedUser);
			
			boolean IsPresent = ExpectedUser.getText().contains("SanathJaysuriya");
			Assert.assertEquals(IsPresent, true);
			System.out.println("Particular Username is Present");
			
		}
		
		
		
		
		
	}
	
	
	
	
	@AfterMethod(alwaysRun=true)
	public void ClosedTheApplication() throws InterruptedException {
		
		homepageoranghrm.clickAccoutDropDown();
		System.out.println("Clicking ON Account Drop-Down");
		homepageoranghrm.clickOnLogoutButton();
		System.out.println("Application Logged-Out Succesfully");
		
		
	}
	

	
	@AfterClass(alwaysRun=true)
	public void cleanObjects() {
		
		loginpageorangehrm = null;
		homepageoranghrm =null;
		softassert = null;
		
		System.out.println("Objects are cleaned Successfully");
	}
	
	
	@AfterTest(alwaysRun=true)
	public void closedBrowser() {
		
		driver.quit();
		System.out.println("Browser closed successfully");
		driver=null;
		logger=null;
		System.gc();
		
		
	}
	
	
}
