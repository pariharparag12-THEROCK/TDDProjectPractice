package utility_package;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocumentReadyState {

	public static void waitForPageReady(WebDriver driver) {
		
		
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(
		        wd -> ((JavascriptExecutor) wd)
		            .executeScript("return document.readyState")
		            .equals("complete")
		    );
		
	}
}
