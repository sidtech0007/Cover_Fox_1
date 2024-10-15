package coverfoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class coverFox_HealthPlanPage {

	@FindBy(className = "next-btn")
	private WebElement nextbutton;

	public coverFox_HealthPlanPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickOnNextButton() {
		Reporter.log("Click on Next Button", true);
		nextbutton.click();
	}

}
