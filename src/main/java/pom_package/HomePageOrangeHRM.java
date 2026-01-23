package pom_package;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageOrangeHRM {


	WebDriver driver;
	
	private WebDriverWait wait;
	
	//@FindBy(xpath = "//p[contains(@class,'oxd-userdropdown-name')]")
	@FindBy (xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	private WebElement accountdropdown;
	
	//@FindBy (xpath ="//a[@href='/web/index.php/auth/logout']")
	@FindBy (xpath ="//a[text()='Logout']")
	private WebElement logoutButton;
	
	/*@FindBy (xpath ="//a[@href='/web/index.php/admin/viewAdminModule']/child::span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
	private WebElement adminOption;*/
	@FindBy (xpath="//a[@href='/web/index.php/admin/viewAdminModule']")
	private WebElement adminOption;
	
	//@FindBy (xpath ="//p[text()='OrangeHRM']")
	@FindBy (xpath = "(//div[@class='oxd-grid-2 orangehrm-about']//div//p[contains(.,'Orange')])[1]")
	private WebElement companyname;
	
	@FindBy (xpath ="//button[@class='oxd-dialog-close-button oxd-dialog-close-button-position']")
	private WebElement aboutwindowclosebutton;
	
	//@FindBy (xpath ="//div[@role='row']")
	//private List<WebElement> rows;
	
	//@FindBy (xpath ="(//div[@role='row'])[2]//div[@role='cell']")
	//private List<WebElement> columns;
	
	
//	@FindBy (xpath = "//div[@role='row']")
//	private List<WebElement> rows;
//	
//	
//	@FindBy (xpath ="(//div[@role='row'])[2]//div[@role='cell']")
//	private List<WebElement> columns;
	
	//@FindBy (xpath ="//div[@role='row']")
	@FindBy (xpath ="//div[@role='row']")
	private List<WebElement> rows;
	

	//@FindBy (xpath="(//div[@role='row'])[2]/div[@role='cell']")
	@FindBy (xpath ="(//div[@role='row'])[2]//div[@role='cell']")
	private List<WebElement> columns;
	
	
	
	//@FindBy (xpath ="//button[@class='oxd-button oxd-button--medium oxd-button--secondary']/child::i")
	@FindBy (xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
	private WebElement addbutton;
	
	
//	@FindBy (xpath = "//div[contains(text(),'Admin')]")
//	private WebElement userrole;
//	
//	@FindBy (xpath ="/div[contains(text(),'Enabled')]")
//	private WebElement status;
//	
	
	//@FindBy (xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']/descendant::input")
	@FindBy (xpath ="//div[@class='oxd-form-row user-password-row']/descendant::div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//input[@class='oxd-input oxd-input--active']")
	private WebElement passwordfield;
	
	//@FindBy (xpath ="//div[@class='oxd-form-row user-password-row']/descendant::div[@class='oxd-grid-item oxd-grid-item--gutters']/child::div/descendant::input")
	@FindBy (xpath ="(//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active'])[2]")
	private WebElement confirmpasswordfield;
	
	//@FindBy (xpath ="//input[@placeholder='Type for hints...']")
	@FindBy (xpath ="//input[@placeholder='Type for hints...']")
	private WebElement employeenamefield;
	
	//@FindBy (xpath ="//div[@class='oxd-form-row']/descendant::div[@class='oxd-input-group oxd-input-field-bottom-space']/div/child::input[@class='oxd-input oxd-input--active']")
	@FindBy (xpath ="(//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active'])[1]")
	private WebElement usernamefield;
	
	
	
	//@FindBy (xpath="//button[text()=' Save ']")
	@FindBy (xpath ="//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
	private WebElement savebutton;
	
	
	@FindBy (xpath="//p[text()='Successfully Saved']")
	private WebElement toastmessage;
	

	@FindBy (xpath="//span[text()='PIM']") private WebElement pimOption;
	
	@FindBy (xpath="//i[@class='oxd-icon bi-chevron-down']") private WebElement configurationdropdown;
	
	@FindBy (xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']/ancestor::a[@href='/web/index.php/pim/viewMyDetails']")
	private WebElement myInfoOption;
	
	
	
	
	public HomePageOrangeHRM(WebDriver driver) {
		
		this.driver = driver;
		
	    wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void clickAccoutDropDown() throws InterruptedException {
		
		/*
		 * wait.until(ExpectedConditions.elementToBeClickable(accountdropdown));
		 * accountdropdown.click(); Thread.sleep(3000);
		 */
		
		wait.until(ExpectedConditions.elementToBeClickable(accountdropdown));
		accountdropdown.click();
		Thread.sleep(3000);
	}
	
	
	public void clickOnLogoutButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		logoutButton.click();
		Thread.sleep(3000);
	}
	
	public void clickOnAdminOption() throws InterruptedException {
		/*
		 * wait.until(ExpectedConditions.elementToBeClickable(adminOption));
		 * adminOption.click(); 
		 * Thread.sleep(2000);
		 */
		
		wait.until(ExpectedConditions.elementToBeClickable(adminOption));
		adminOption.click();
		Thread.sleep(3000);
	}
	
	public String ClickOnAccountOption() throws InterruptedException {
		
		/*
		 * List<WebElement> accountOptions =
		 * driver.findElements(By.xpath(("//a[@role='menuitem']")));
		 * 
		 * for (WebElement element : accountOptions) {
		 * 
		 * if(element.getText().contentEquals("About")) {
		 * 
		 * //accountOptions.get(1).getText().contentEquals("About"); (if u are using for
		 * loop instead of foreachloop)
		 * 
		 * element.click();
		 * 
		 * System.out.println("Clicking on About Option"); } }
		 * 
		 * Thread.sleep(2000);
		 * 
		 * String companyNameValue = companyname.getText();
		 * 
		 * return companyNameValue;
		 */
		

		List<WebElement> elements = driver.findElements(By.xpath("//a[@role='menuitem']"));
		
		for(WebElement element: elements) {
			
			if(element.getText().contentEquals("About")) {
				
				element.click();
				
				
			}
			
		}
		
		wait.until(ExpectedConditions.visibilityOf(companyname));
		String companyName = companyname.getText();
		
		return companyName;
	}
	
	
	public void clickingOnAboutWindowClosebutton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(aboutwindowclosebutton));
		aboutwindowclosebutton.click();
		Thread.sleep(2000);
	}
	
	
	
	public int getEmployeeData() throws InterruptedException {
		
		
/*		wait.until(ExpectedConditions.visibilityOfAllElements(rows));
		int rowcount = rows.size();
		System.out.println("Number of Rows present in the table is : "+ rowcount);
		
		wait.until(ExpectedConditions.visibilityOfAllElements(columns));
		int cellcount = columns.size();
		System.out.println("Number of Columns present in the table is : "+ cellcount);
		
		for (int i=2; i<=rowcount; i++) {
			
			for (int j=2; j<=cellcount; j++) {
				
				WebElement elements = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell']["+j+"]"));
				
				String emplyeeData = elements.getText();
				
				System.out.print(emplyeeData+" ");
				
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		int statuscount = 0;
		
		
		System.out.println("The Employee names whose status is enabled");
		
		for (int i=2; i<=rowcount; i++) {
			
			String Employeestatus = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][5]")).getText();
			
			if (Employeestatus.equals("Enabled")) {
				
				String EmployeeName = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][4]")).getText();
				
				System.out.println(EmployeeName+" ");
				
				statuscount = statuscount+1;
			}
				
		}
		
		System.out.println();
		
		
		return statuscount;
			*/
		
		/*wait.until(ExpectedConditions.visibilityOfAllElements(rows));
		int rowsize = rows.size();
		System.out.println("The number of row present is table is :"+rowsize);
		
		
		wait.until(ExpectedConditions.visibilityOfAllElements(columns));
		int columnsize = columns.size();
		System.out.println("The number of columns present is table is :"+columnsize);
		
		for (int i=2; i<=rowsize; i++) {
			
			for (int j=2; j<=columnsize; j++) {
				
				WebElement elements = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell']["+j+"]"));
				
				String employeeData = elements.getText();
				
				System.out.print(employeeData+" ");
				
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		int statuscount =0;
		
		for (int i=2; i<=rowsize; i++ ) {
			
			String status = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][5]")).getText();
			
			if (status.equals("Enabled")){
				
				String employeeName = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][4]")).getText();
				
				System.out.println(employeeName+" ");
				
				statuscount = statuscount+1;
			}
			
		}
		
		
		System.out.println();
		
		return statuscount;
		*/
		
		
	/*	wait.until(ExpectedConditions.visibilityOfAllElements(rows));
		int tr = rows.size();
		System.out.println("number of rows present is : "+ tr);
		
		wait.until(ExpectedConditions.visibilityOfAllElements(columns));
		int tc = columns.size();
		System.out.println("number of columns present is : "+ tc);
		
		
		for (int i=2; i<=tr; i++) {
			
			for (int j=2; j<=tc-1; j++) {
				
				String element = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]/div[@role='cell']["+j+"]")).getText();
				
				System.out.print(element+" ");
				
			}
			
			System.out.println();
		}
		
		
		System.out.println();
		
		
		
		int statuscount = 0;
		
		System.out.println("The EmployeeNames whose status are enabled : ");
		
		System.out.println();
		
		
		
		
		
		for (int i=2; i<=tr; i++) {
				
				String statusName = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]/div[@role='cell'][5]")).getText();
				
				if(statusName.equals("Enabled")) {
					
					String employeename = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]/div[@role='cell'][2]")).getText();
					
					System.out.print(employeename+" ");
					
					statuscount = statuscount+1;
				}
				
				
				System.out.println();
			
		}
		
		System.out.println();
		
		return statuscount;
		*/
		
		System.out.println();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(rows));
		int rowcount = rows.size();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfAllElements(columns));
		int cellcount = columns.size();
		Thread.sleep(2000);
		
		for(int i=2; i<=rowcount-1; i++) {
			
			for(int j=2; j<=cellcount-1; j++) {
				
				WebElement elements = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell']["+j+"]"));
				
				String employeedata = elements.getText();
				
				System.out.print(employeedata+" ");
				
			}
			
			System.out.println();
		}
		
		System.out.println();
		
		int statuscount = 0;
		
		for(int i=2; i<=rowcount-1; i++) {
			
			WebElement element = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][5]"));
			
			if (element.getText().contentEquals("Enabled")) {
				
				
				String UserName = driver.findElement(By.xpath("(//div[@role='row'])["+i+"]//div[@role='cell'][2]")).getText();
				
				System.out.println(UserName+" ");
				
				statuscount = statuscount +1;
				
			}
			
			
		}
		
		
		System.out.println();
		
		return statuscount;
		
		
		
	}
	
	
	
	/*
	 * public void clickOnAddButton() throws InterruptedException {
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(addbutton));
	 * addbutton.click(); Thread.sleep(3000);
	 * 
	 * }
	 */
	
	public void clickOnAddButton() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(addbutton));
		addbutton.click();
		Thread.sleep(3000);
	}
	
	
	
	/*
	 * public void selectUserRole() throws InterruptedException {
	 * 
	 * WebElement userrole =driver.findElement(By.
	 * xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]"
	 * )); wait.until(ExpectedConditions.elementToBeClickable(userrole));
	 * userrole.click(); Thread.sleep(3000);
	 * 
	 * 
	 * }
	 */
	
	
	
	public void selectUserRole() throws InterruptedException {
		
		WebElement userrole = driver.findElement(By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(userrole));
		userrole.click();
		Thread.sleep(2000);
		
	}
	
	
	/*
	 * public void selectStatus() throws InterruptedException {
	 * 
	 * WebElement status = driver.findElement(By.
	 * xpath("(//div[@class='oxd-input-group oxd-input-field-bottom-space']/descendant::div[@class='oxd-select-text--after']/i)[2]"
	 * )); wait.until(ExpectedConditions.elementToBeClickable(status));
	 * status.click(); Thread.sleep(3000); }
	 */
	
	
	public void selectStatus() throws InterruptedException {
		
		WebElement status = driver.findElement(By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[3]//i"));
		wait.until(ExpectedConditions.elementToBeClickable(status));
		status.click();
		Thread.sleep(3000);
	}
		
	
	
	/*
	 * public void enterEmployeeName(String employeename) throws
	 * InterruptedException {
	 * 
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(employeenamefield));
	 * employeenamefield.sendKeys(employeename); Thread.sleep(3000); }
	 */
	
	
	public void enterEmployeeName(String employeeName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(employeenamefield));
		employeenamefield.sendKeys(employeeName);
		Thread.sleep(3000);
		
	}
		

	/*
	 * public void enterUsername(String username) throws InterruptedException {
	 * 
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(usernamefield));
	 * usernamefield.sendKeys(username); Thread.sleep(3000); }
	 */
	
	
	public void enterUsername(String username) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(usernamefield));
		usernamefield.sendKeys(username);
		Thread.sleep(3000);
	}
	
	
	
	
	/*
	 * public void enterPassword(String password) throws InterruptedException {
	 * 
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(passwordfield));
	 * passwordfield.sendKeys(password); Thread.sleep(3000); }
	 */	
	
	
	public void enterPassword(String password) throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(passwordfield));
		passwordfield.sendKeys(password);
		Thread.sleep(3000);
	}
	
	
	/*
	 * public void enterConfirmPassord(String confirmpassword) throws
	 * InterruptedException {
	 * 
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(confirmpasswordfield));
	 * confirmpasswordfield.sendKeys(confirmpassword); Thread.sleep(3000); }
	 */
	
	public void enterConfirmPassord(String confirmpassword) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(confirmpasswordfield));
		confirmpasswordfield.sendKeys(confirmpassword);
		Thread.sleep(3000);
	}
	
	
	
	
	/*
	 * public void clickOnSaveButton() throws InterruptedException {
	 * wait.until(ExpectedConditions.elementToBeClickable(savebutton));
	 * savebutton.click(); Thread.sleep(3000); }
	 */
	
	
	
	public void clickOnSaveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(savebutton));
		savebutton.click();
		Thread.sleep(3000);
		
	}
	
	
	/*
	 * public String getSaveToastMessage() throws InterruptedException {
	 * wait.until(ExpectedConditions.elementToBeClickable(toastmessage)); String
	 * toastmsg = toastmessage.getText(); Thread.sleep(3000); return toastmsg; }
	 */
	
	
	public String getSaveToastMessage() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(toastmessage));
		String toastmsg = toastmessage.getText();
		
		
		Thread.sleep(3000);
		return toastmsg;
	}
	
	
	
	public void NavigateToPIMOption() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(pimOption));
		pimOption.click();
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(configurationdropdown));
		configurationdropdown.click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.partialLinkText("Data Im")).click();
		
	}
	
	
	public void NavigateToMyInfoOption() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(myInfoOption));
		myInfoOption.click();
		Thread.sleep(2000);
	}
	
}
