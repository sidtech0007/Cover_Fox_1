package coverfoxBase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;

import coverfoxUtility.Utility;

public class Base {
	
	//driver
	//url
	//open browser/ launch 
	//close browser
	
	protected static WebDriver driver;
	
	public void launchBrowser(String browser ) throws IOException
	{
		ChromeOptions c_option=new ChromeOptions();
		c_option.addArguments("--disable-notifications");
		
		EdgeOptions e_option=new EdgeOptions();
		e_option.addArguments("--disable-notifications");

		FirefoxOptions f_ptions = new FirefoxOptions();
		f_ptions.addArguments("--disable-notifications");
	
		if (browser.equals("chrome")) {
			driver = new ChromeDriver(c_option);
			Reporter.log("launching chrome browser", true);
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver(e_option);
			Reporter.log("launching edge browser", true);
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver(f_ptions);
			Reporter.log("launching firefox browser", true);
		}
		
		driver.get(Utility.readDataFromPropertyFile("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		Reporter.log("Waiting...", true);
	}
	
	public void closebrowser()
	{
		Reporter.log("Closing browser", true);
		driver.quit();
	}
			

}
