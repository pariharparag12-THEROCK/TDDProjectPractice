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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import utility_package.Property_Utils;
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
	@Parameters("EnviromentUrl")
	public void launchApplication(String url) throws Exception {
		
		
		try {
			//driver.get(Property_Utils.readDataFromProperties("URL2"));
			
			getURL(url);
			
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
	
	

	/* @Test (groups = {"Sanity"},priority =-1) */
	@Test (groups = {"PPparihar"},priority =-1)
	public void verifyAdminOption() throws InterruptedException {
		
		/*
		 * homepageoranghrm.clickOnAdminOption();
		 * System.out.println("Clicking on Admin Option");
		 * logger.info("Log - Clicking On Admin Option");
		 * 
		 * String actURLAdminPage = driver.getCurrentUrl(); String expectedURLAdminPage
		 * =
		 * "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
		 * 
		 * logger.warn("Log - Verifying the URL....It may be matched or unmatched");
		 * 
		 * if (actURLAdminPage.equals(expectedURLAdminPage)) {
		 * 
		 * logger.info("Log -Validating the admin urls");
		 * softassert.assertEquals(actURLAdminPage, expectedURLAdminPage);
		 * System.out.println("Admin URL matched Successfully");
		 * Reporter.log("Admin Urls matched successfully",true);
		 * logger.info("Admin Url validation matched - Test Case Passed");
		 * 
		 * }
		 * 
		 * else {
		 * 
		 * logger.fatal("Admin Urls are not matching");
		 * softassert.assertEquals(actURLAdminPage, expectedURLAdminPage);
		 * System.out.println("Admin URL in not matched- TEST CASE FAILED");
		 * Reporter.log("Admin Urls are not matched ",false);
		 * logger.info("Admin Url validation is not matched - Test Case Failed"); }
		 * 
		 * softassert.assertAll();
		 * 
		 * Thread.sleep(2000);
		 */
		
		homepageoranghrm.clickOnAdminOption();
		String actUrl = driver.getCurrentUrl();
		boolean Ispresent = actUrl.contains("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		softassert.assertTrue(Ispresent);
		
		Thread.sleep(2000);
		logger.info("Admin URL - "+ actUrl);
		
		softassert.assertAll();
		
		
		
	}
	
	
	//@Test(groups = {"Smoke"},priority =2)
	@Test(groups = {"PPparihar123"},priority =2)
	public void verifyCompanyNameInAboutWindowOption() throws InterruptedException {
		/*
		 * homepageoranghrm.clickAccoutDropDown();
		 * 
		 * String ActualCompanyValue = homepageoranghrm.ClickOnAccountOption();
		 * 
		 * if(ActualCompanyValue.equals("OrangeHRM")) {
		 * softassert.assertEquals(ActualCompanyValue, "OrangeHRM");
		 * System.out.println("Comapny Nmae is Matched Successfully"); }
		 * 
		 * else { softassert.assertEquals(ActualCompanyValue, "OrangeHRM");
		 * System.out.println("Comapny Nmae is not matched"); }
		 * 
		 * 
		 * homepageoranghrm.clickingOnAboutWindowClosebutton();
		 * 
		 * 
		 * softassert.assertAll();
		 * 
		 * Thread.sleep(2000);
		 */
		
		homepageoranghrm.clickAccoutDropDown();
		Thread.sleep(1000);
		
		String companyvalue = homepageoranghrm.ClickOnAccountOption();
		
		boolean Ispresent = companyvalue.contains("Orange");
		
		if(companyvalue.contains("Orange")) {
			
			//softassert.assertEquals(companyvalue, "OrangeHRM");
			softassert.assertTrue(Ispresent);
			logger.info("company value matched - TC passed");
			
		}

		else {
			softassert.assertTrue(Ispresent);
			logger.info("company value not matched! - TC Failed");

		}
		
		
		homepageoranghrm.clickingOnAboutWindowClosebutton();
		
		softassert.assertAll();
		
		Thread.sleep(2000);
		
	}
	
	
	
	//@Test(groups = {"Sanity"},priority=3)
	@Test(groups = {"PPpariharXYZ"},priority=3)
	public void verifyEmployeeTableData() throws InterruptedException {
		
		/*
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
		*/
		
		
		/*homepageoranghrm.clickOnAdminOption();
		
		int statuscount = homepageoranghrm.getEmployeeData();
		
		
		if (statuscount!=0) {
			Assert.assertNotNull(statuscount);
			System.out.println(" The number of employees whose status are enabled are : "+statuscount);
		}
		
		else {
			Assert.assertNotNull(statuscount);
			System.out.println("Assertion Failed");
		}
		*/
		
		homepageoranghrm.clickOnAdminOption();
		
		int statuscount = homepageoranghrm.getEmployeeData();
		
		if (statuscount!=0) {
			
			softassert.assertNotNull(statuscount);
			logger.info("Total number of employee whose status are enabled : "+ statuscount+ "\n"+"TestCase Passed");
		}
		
		else {
			
			softassert.assertNotNull(statuscount);
			logger.info("Total number of employee whose status are enabled : "+ statuscount+ "\n"+"TestCase Failed");
		}
		
		softassert.assertAll();
		
		Thread.sleep(3000);
		
	}
	
	
	
	
	
	
	
	
	
	//@Test(groups= {"Sanity"},priority=-5)
	@Test(groups= {"PPpariharPQR"},priority=-5)
	public void verifyUserCreationData() throws InterruptedException, EncryptedDocumentException, IOException {
		
		
		
		/*Actions action = new Actions(driver);
		
		
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
			
		}*/
		
		Actions action = new Actions(driver);
		
		homepageoranghrm.clickOnAdminOption();
		
		homepageoranghrm.clickOnAddButton();
		
		homepageoranghrm.selectUserRole();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		
		homepageoranghrm.selectStatus();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		
		homepageoranghrm.enterEmployeeName(FetchExcelData.getExcelFileData(2, 0));
		logger.info("User entered emplyeee Name");
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
		homepageoranghrm.enterUsername(FetchExcelData.getExcelFileData(2, 1));
		logger.info("User entered USERNAME");
		
		homepageoranghrm.enterPassword(FetchExcelData.getExcelFileData(2, 2));
		logger.info("User entered PASSWORD");
		
		homepageoranghrm.enterConfirmPassord(FetchExcelData.getExcelFileData(2, 3));
		logger.info("User entered CONFIRM PASSWORD");
		
		homepageoranghrm.clickOnSaveButton();
		
		String toastmsg = homepageoranghrm.getSaveToastMessage();
		boolean IsPresent = toastmsg.contains("Successfully Saved");
		softassert.assertTrue(IsPresent);
		logger.info("User Created the Data successfully");
		
		Thread.sleep(3000);
		
		WebElement expectedUser = driver.findElement(By.xpath("//div[contains(text(),'Rahul Dravid')]"));
		
		ScrollView.scrollIntoView(driver, expectedUser);
		
		Thread.sleep(2000);
		
		boolean IsPresent1 = expectedUser.getText().contains("Rahul Dravid");
		
		softassert.assertTrue(IsPresent);
		logger.info("Particular Username is Present");
		Thread.sleep(3000);
		softassert.assertAll();
	}
	
	
	
	@Test(groups= {"PP@Parihar@PP"})
	public void validateFileUploadAndImageUpload() throws InterruptedException, IOException {
		
		homepageoranghrm.NavigateToPIMOption();
		
		//click on browse button
		driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();
		
		Thread.sleep(5000);
		
		String path = System.getProperty("user.dir")+"\\FileUploadPIMorangeHRM.exe";
		Runtime.getRuntime().exec(path);
		Thread.sleep(3000);
		
		//Click on upload button
		driver.findElement(By.xpath("//button[text()=' Upload ']")).click();
		Thread.sleep(2000);
		
		
		String textCapture = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--card-body orangehrm-success-message']")).getText();
		boolean IsPresent = textCapture.contains("Records Successfully Imported");
		
		Assert.assertTrue(IsPresent);
		
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		Thread.sleep(1000);
		
		//Click On Add Employee
		driver.findElement(By.xpath("//li[@class='oxd-topbar-body-nav-tab']/child::a[contains(text(),'Add Employee')]")).click();
		Thread.sleep(3000);
		
		WebElement firstname = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		firstname.sendKeys(FetchExcelData.getExcelFileData(1, 5));
		Thread.sleep(2000);
		
		WebElement lastname = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		lastname.sendKeys(FetchExcelData.getExcelFileData(1, 6));
		Thread.sleep(2000);
		
		WebElement employeeId = driver.findElement(By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']/child::div/child::input[@class='oxd-input oxd-input--active']"));
		//convert double intoString
		employeeId.sendKeys(String.valueOf(FetchExcelData.getExcelFileDataNumeric(1, 7)));
		Thread.sleep(2000);
		
		WebElement imgIcon = driver.findElement(By.xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']"));
		imgIcon.click();
		Thread.sleep(5000);
		
		
		String path1 = System.getProperty("user.dir")+"\\ImageUploadPIMorangeHRM.exe";
		Runtime.getRuntime().exec(path1);
		Thread.sleep(3000);
		
		//Click On save button
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
		Thread.sleep(6000);
	}
	

	
	
	@Test(groups ="@PARAG123456789")
	public void validateInfoImageupdated() throws InterruptedException, IOException {
	
		/*homepageoranghrm.NavigateToMyInfoOption();
		
		//Click on Image
		driver.findElement(By.xpath("//div[@class='orangehrm-edit-employee-image']")).click();
		Thread.sleep(1000);
		
		//Click on uploadImageIcon/plusicon
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus']")).click();
		
		Thread.sleep(5000);
		
		String path2 = System.getProperty("user.dir")+"\\ImageUploadAtMyInfoOption.exe";
		Runtime.getRuntime().exec(path2);
		Thread.sleep(3000);
		
		//Click On Save Button
		WebElement saveButton = driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"));
		saveButton.click();
		Thread.sleep(3000);
		
		//verifyToastMsg
		 String toastMessage2 = driver.findElement(By.xpath("//p[contains(.,'Successfully Updated')]")).getText();
		 boolean IsPresent = toastMessage2.contains("Successfully Updat");
		 Assert.assertEquals(IsPresent, true);
		 Thread.sleep(6000); */
		
		
		homepageoranghrm.NavigateToMyInfoOption();
		
		//Click On Image
		driver.findElement(By.xpath("//img[@class='employee-image']")).click();		
		Thread.sleep(1000);
		
		
		//Click on plus Icon
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus']")).click();
		
		Thread.sleep(5000);
		String Path2 = System.getProperty("user.dir")+"\\Image_Upload_AtMyInfo_Option_1.exe";
		Runtime.getRuntime().exec(Path2);
		Thread.sleep(3000);
		
		//Click on SaveButton
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
		
		
		WebElement ToastMessage3 = driver.findElement(By.xpath("//p[contains(., 'Successfully')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ToastMessage3));
		
		String ToastMsg3 = ToastMessage3.getText();
		boolean IsPresent3 = ToastMsg3.contains("Successfu");
		Assert.assertTrue(IsPresent3);
		System.out.println("Image Uploaded at MyInfoOption is successfully uploaded");
		
		Thread.sleep(5000);
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
