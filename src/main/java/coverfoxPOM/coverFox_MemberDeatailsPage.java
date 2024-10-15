package coverfoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class coverFox_MemberDeatailsPage {

	@FindBy(id = "Age-You")
	private WebElement agedropdown;
	@FindBy(className = "next-btn")
	private WebElement nextbutton;

	public coverFox_MemberDeatailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void handleAgeDropdown(String age) {
		Reporter.log("Handeling age dropdown", true);
		Select s = new Select(agedropdown);
		s.selectByValue(age+"y");
	}

	public void clickOnNextButton() {
		Reporter.log("Click on Next Button", true);
		nextbutton.click();
	}

}
