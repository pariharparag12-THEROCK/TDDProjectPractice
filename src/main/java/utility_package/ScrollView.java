package utility_package;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollView {

	/*public static void scrollIntoView(WebDriver driver, WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//js.executeScript("Scroll(0,1800)");
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		
	}*/
	
	
	public static void scrollIntoView(WebDriver driver, WebElement element) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		
	}
	
	//23 nov 2025
}
