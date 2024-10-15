package coverfoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class coverFox_HomePage {

	// variable declaration-- webelement
	// contructor-- variable intialization
	// methods

	// variable declaration-- webelement
	// driver.findElement(by.x)
	@FindBy(xpath = "//div[text()='Male']")
	private WebElement gender;

	// contructor-- variable intialization
	public coverFox_HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// methods
	public void clickOnGenderButton() {
		Reporter.log("Click on gender Button", true);
		gender.click();
	}

}
