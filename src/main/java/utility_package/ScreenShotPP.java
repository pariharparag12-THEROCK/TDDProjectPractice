package utility_package;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotPP {

	public static String getScreenShotAsBase64(WebDriver driver) {
		
		String screenshotAsBase64 =((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return screenshotAsBase64;
		
	}
}
