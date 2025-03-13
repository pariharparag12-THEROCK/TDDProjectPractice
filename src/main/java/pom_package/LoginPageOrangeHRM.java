package pom_package;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility_package.Property_Utils;

public class LoginPageOrangeHRM {

	WebDriver driver;
	
	private WebDriverWait wait;
	
	@FindBy (xpath="//input[@placeholder='Username']")
	private WebElement username;
	
	@FindBy (xpath ="//input[@placeholder='Password']")
	private WebElement password;
	
	@FindBy (xpath ="//button[@type='submit']")
	private WebElement loginbutton;
	
	
	public LoginPageOrangeHRM(WebDriver driver) {
		
		this.driver = driver;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void SendUserName() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.elementToBeClickable(username));
		username.clear();
//		username.sendKeys("Admin");
		username.sendKeys(Property_Utils.readDataFromProperties("USERNAME"));
		Thread.sleep(3000);
	}
	
	
	public void SendPassword() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.elementToBeClickable(password));
		password.clear();
//		password.sendKeys("admin123");
		password.sendKeys(Property_Utils.readDataFromProperties("PASSWORD"));
		Thread.sleep(3000);
	}
	
	
	public void clickOnLoginButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loginbutton));
		loginbutton.click();
		Thread.sleep(3000);
	}
	
}
