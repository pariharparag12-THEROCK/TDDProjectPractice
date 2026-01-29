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
import org.testng.annotations.Optional;
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
	
//Test local
	//public static Logger logger;
	
	//private static final Logger logger = Logger.getLogger(HomePage_TestingClass.class); // no need we extends baseclass and we will use the logger of baseclass


	/* WebDriver driver; */
	//we use only driver of base class in this HomePagetestingclass thats why we discommented this driver
	// If we use this driver in this class than 2 drivers will run...1 is baseclass driver and another is HomePagetestingClass driver....
	//...so that our script will confuse which driver is to take...If we execute through TestNg	byluck it will execute...
	//......but if we execute through maven it will throw null pointer exception..it will catch a bug
	//....we have method getURL()-----> this will use baseClass driver and remainings are use HomePagetestingClass driver...thats why our script will confuse
	
	
	
	LoginPageOrangeHRM loginpageorangehrm;
	
	HomePageOrangeHRM homepageoranghrm;
	
	SoftAssert softassert;
	
	

	/*@BeforeTest(alwaysRun=true)
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
		 
		
		
		logger	=	Logger.getLogger("Project-Practice");
		
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Log- Chrome Browser launching");
		
		
		Thread.sleep(2000);
	}
	*/
	
	
	@BeforeClass(alwaysRun=true)
	/*@Parameters("EnviromentUrl")
	public void launchApplication(@Optional("QA_India") String EnvironmentURL) throws Exception {*/
	
	public void launchApplication() throws Exception {
		
		//if we don't want to use the parameter then locally it will take QA+ India, if jenkins passes parameter than jenkins values are used
		/*
		 * String env = System.getProperty("env"); String country =
		 * System.getProperty("country");
		 */
		
		// Local default ONLY if Jenkins did not pass values
		/*
		 * if (env == null || country == null) { env = "QA"; country = "India"; }
		 */
		
		/* String EnvironmentURL = env +"_"+country; */
		
		//DEBUG LOGS (WRITE HERE)
		/*
		 * System.out.println("ENV = " + env); System.out.println("COUNTRY = " +
		 * country); System.out.println("KEY = " + EnvironmentURL);
		 */
		
	/*	//Jenkins Parameter has a priority
		String env = System.getProperty("env");			
		
		String country = System.getProperty("country");   */
		
		//String key = envFromJenkins +"_"+countryFromJenkins;
		
		//if env and country is null then key=null_null then if(key!=null){---> it means EnvironmentURL=null_null
	
//		if(key!=null) {
//			
//			EnvironmentURL = key;
//		}
		
	/*	// Jenkins values should override local/default
	    if (env != null && country != null) {
	    	
	        EnvironmentURL = env + "_" + country;
	        
	    } */

		    //driver.get(Property_Utils.readDataFromProperties("URL2"));
		 // Launch URL based on country
			/* getURL(EnvironmentURL); */
		
			getURL();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			Thread.sleep(3000);
			
			logger.info("Application is Launching");
			
			loginpageorangehrm = new LoginPageOrangeHRM(driver);
			
			homepageoranghrm = new HomePageOrangeHRM(driver);
			
			softassert =new SoftAssert();
		
		
		
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
		String actUrl = driver.getCurrentUrl();
		boolean Ispresent = actUrl.contains("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		softassert.assertTrue(Ispresent);
		
		Thread.sleep(2000);
		logger.info("Admin URL - "+ actUrl);
		
		softassert.assertAll();
		
//		logger.fatal("Admin Urls are not matching");
//		Reporter.log("Admin Urls are not matched ",false);
//		logger.info("Log -Validating the admin urls");
//		logger.warn("Log - Verifying the URL....It may be matched or unmatched");
		
	}
	
	
	@Test(groups = {"Smoke"},priority = 2)
	public void verifyCompanyNameInAboutWindowOption() throws InterruptedException {

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
	
	
	
	@Test(groups = {"Sanity"},priority=9)
	public void verifyEmployeeTableData() throws InterruptedException {
		
		homepageoranghrm.clickOnAdminOption();
		
		int statuscount = homepageoranghrm.getEmployeeData();
		
		if (statuscount!=0) {
			
			softassert.assertNotNull(statuscount);
			logger.info("Total number of employee whose status are enabled : "+ statuscount+ "\n"+"TestCase Passed");
		}
		
		else {
			
			softassert.assertNotNull(statuscount);
			logger.error("Total number of employee whose status are enabled : "+ statuscount+ "\n"+"TestCase Failed");
		}
		
		softassert.assertAll();
		
		Thread.sleep(3000);
		
	}
	
	
	
	@Test(groups= {"Sanity"},priority=1)
	public void verifyUserCreationData() throws InterruptedException, EncryptedDocumentException, IOException {
		
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
		Thread.sleep(1000);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		
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
		
		//Thread.sleep(3000);
		
		WebElement expectedUser = driver.findElement(By.xpath("//div[contains(text(),'"+FetchExcelData.getExcelFileData(2, 1)+"')]"));	
		ScrollView.scrollIntoView(driver, expectedUser);
		logger.info("User found the created data");
		//Thread.sleep(2000);
		
		boolean IsPresent1 = expectedUser.getText().contains(FetchExcelData.getExcelFileData(2, 1));
		softassert.assertTrue(IsPresent);
		
		//Thread.sleep(1000);
		//delete element created row
		//driver.findElement(By.xpath("//div[contains(text(),'"+FetchExcelData.getExcelFileData(2, 1)+"')]/ancestor::div[@class='oxd-table-row oxd-table-row--with-border']/descendant::button[1]//i")).click();
		WebElement deletebutton=driver.findElement(By.xpath("//div[contains(text(),'"+FetchExcelData.getExcelFileData(2, 1)+"')]/ancestor::div[@class='oxd-table-card']//button[1]"));
		ScrollView.scrollIntoView(driver, deletebutton);
		ScrollView.JSClick(driver, deletebutton);
		logger.info("User Clicked on delete button");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();
		logger.info("User deleted the created data");	
		//logger.info("Particular Username is Present");
		//Thread.sleep(3000);
		softassert.assertAll();
	}
	
	
	
	@Test(groups= {"Regression"}, priority=3)
	public void validateFileUploadAndImageUpload() throws InterruptedException, IOException {
		
		homepageoranghrm.NavigateToPIMOption();
	
		
		//AUTOIT doesn't work in headless...
		
		/*
		 * //click on browse button
		 * driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();
		 * logger.info("Browse button clicking");
		 * 
		 * Thread.sleep(5000);
		 * 
		 * String path = System.getProperty("user.dir")+"\\FileUploadPIMorangeHRM.exe";
		 * Runtime.getRuntime().exec(path); Thread.sleep(3000);
		 */
		
		
		//Use sendKeys() on <input type="file"> 
		//input type="file" ------> it should be present in html tree code (mandatory)
		//Then Selenium can upload directly, silently, perfectly
		//UI + Headless + Jenkins will pass
		WebElement fileuploadInput = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\importData3.csv";
		fileuploadInput.sendKeys(filePath);
		logger.info("File path sent directly to input");
		
		//Click on upload button
		/*
		 * driver.findElement(By.xpath("//button[text()=' Upload ']")).click();
		 * Thread.sleep(2000); logger.info("File is uploading");
		 */
		
		homepageoranghrm.ClickOnUploadButtonForFile();
		
		/*
		 * String textCapture = driver.findElement(By.
		 * xpath("//p[@class='oxd-text oxd-text--p oxd-text--card-body orangehrm-success-message']"
		 * )).getText(); boolean IsPresent =
		 * textCapture.contains("Records Successfully Imported");
		 */
		
		String textCapture = homepageoranghrm.getFileUploadToastMsg();
		boolean IsPresent = textCapture.contains("Records Successfully Imported");	
		Assert.assertTrue(IsPresent);
		logger.info("Toast message is verifying");
		
		/*
		 * driver.findElement(By.
		 * xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']"
		 * )).click(); Thread.sleep(1000);
		 */
		
		homepageoranghrm.clickOnOkButton();
		
		/*
		 * //Click On Add Employee driver.findElement(By.
		 * xpath("//li[@class='oxd-topbar-body-nav-tab']/child::a[contains(text(),'Add Employee')]"
		 * )).click(); logger.info("Add Employee button is clicking");
		 * Thread.sleep(3000);
		 * 
		 * WebElement firstname =
		 * driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		 * firstname.sendKeys(FetchExcelData.getExcelFileData(1, 5));
		 * logger.info("First name is entering"); Thread.sleep(2000);
		 * 
		 * WebElement lastname =
		 * driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		 * lastname.sendKeys(FetchExcelData.getExcelFileData(1, 6));
		 * logger.info("Last name is entering"); Thread.sleep(2000);
		 * 
		 * WebElement employeeId = driver.findElement(By.
		 * xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']/child::div/child::input[@class='oxd-input oxd-input--active']"
		 * )); //convert double intoString
		 * employeeId.sendKeys(String.valueOf(FetchExcelData.getExcelFileDataNumeric(1,
		 * 7))); logger.info("EmployeeId is entering"); Thread.sleep(2000);
		 */
		
		homepageoranghrm.addDetailsInAddEmpoyeeSection();
		
		
		
		
		/*
		 * WebElement imgIcon = driver.findElement(By.
		 * xpath("//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']"
		 * )); imgIcon.click(); Thread.sleep(5000);
		 * logger.info("Image icon is clicked");
		 * 
		 * String path1 =
		 * System.getProperty("user.dir")+"\\ImageUploadPIMorangeHRM.exe";
		 * Runtime.getRuntime().exec(path1); Thread.sleep(3000);
		 */
		
		
		//Use sendKeys() on <input type="file"> 
		//input type="file" ------> it should be present in html tree code (mandatory)
		//Then Selenium can upload directly, silently, perfectly
		//UI + Headless + Jenkins will pass
		
		WebElement imageuploadInput = driver.findElement(By.xpath("//input[@type='file']"));
		String imagePath = System.getProperty("user.dir")+"\\src\\test\\resources\\Screenshot 2024-05-28 163614.png";
		imageuploadInput.sendKeys(imagePath);
		logger.info("Image path sent directly to input");
		
		/*
		 * //Click On save button driver.findElement(By.
		 * xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"
		 * )).click(); //Thread.sleep(2000); logger.info("Save button is clicked");
		 */
		
		homepageoranghrm.clickOnAddEmployeeSaveButton();
		
	}
	

	
	
	@Test(groups ="Regression", priority =4)
	public void validateInfoImageupdated() throws InterruptedException, IOException {
		
		homepageoranghrm.NavigateToMyInfoOption();
		
		/*
		 * //Click On Image
		 * driver.findElement(By.xpath("//img[@class='employee-image']")).click();
		 * logger.info("Clicking on image"); Thread.sleep(1000);
		 */
		
		homepageoranghrm.clickOnImageAtMyInfo();
		
		
		/*
		 * //Click on plus Icon
		 * driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus']")).click();
		 * logger.info("Clicking on plus icon");
		 * 
		 * Thread.sleep(5000); String Path2 =
		 * System.getProperty("user.dir")+"\\Image_Upload_AtMyInfo_Option_1.exe";
		 * Runtime.getRuntime().exec(Path2); Thread.sleep(3000);
		 */
		
		//Use sendKeys() on <input type="file"> 
		//input type="file" ------> it should be present in html tree code (mandatory)
		//Then Selenium can upload directly, silently, perfectly
		//UI + Headless + Jenkins will pass

		WebElement imageuploadInput = driver.findElement(By.xpath("//input[@type='file']"));
		String imagePath = System.getProperty("user.dir")+"\\src\\test\\resources\\Screenshot 2024-07-15 172213.png";
		imageuploadInput.sendKeys(imagePath);
		logger.info("Image path sent directly to input");
		
		
		/*
		 * //Click on SaveButton driver.findElement(By.
		 * xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"
		 * )).click(); logger.info("Clicking on Save button");
		 */
		
		
		homepageoranghrm.clickOnMyInfoSaveButton();
		
		/*
		 * WebElement ToastMessage3 =
		 * driver.findElement(By.xpath("//p[contains(., 'Successfully')]"));
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.visibilityOf(ToastMessage3));
		 */
		
		
		String ImagetextCapture = homepageoranghrm.getImageUploadToastMsg();
		boolean IsPresent = ImagetextCapture.contains("Successfully Updated");	
		Assert.assertTrue(IsPresent);
		logger.info("Toast message is verifying");
		logger.info("Image is successfully uploaded at MyInfoOption");
		
//		String ToastMsg3 = ToastMessage3.getText();
//		boolean IsPresent3 = ToastMsg3.contains("Successfu");
//		Assert.assertTrue(IsPresent3);
//		System.out.println("Image Uploaded at MyInfoOption is successfully uploaded");
//		logger.info("Image is successfully uploaded at MyInfoOption");
//		Thread.sleep(5000);
		Thread.sleep(1000);
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
	
	
	/*@AfterTest(alwaysRun=true)
	public void closedBrowser() {
		
		driver.quit();
		System.out.println("Browser closed successfully");
		driver=null;
		logger=null;
		System.gc();
		
		
	}
	*/
	
}
