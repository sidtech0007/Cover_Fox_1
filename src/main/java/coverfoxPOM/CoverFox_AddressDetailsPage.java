package coverfoxPOM;

import javax.accessibility.AccessibleEditableText;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFox_AddressDetailsPage {

	@FindBy(className = "mp-input-text")
	private WebElement pincodeField;
	
	@FindBy(id = "want-expert")
	private WebElement mobNumField;
	
	@FindBy(className = "next-btn")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[text()=' Please enter a valid pincode ']")
	private WebElement pinerrmsg;
	
	@FindBy(xpath = "//div[text()=' Please enter a valid mobile no. ']")
	private WebElement moberrmsg;

	public CoverFox_AddressDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterPincode(String pincode) {
		Reporter.log("Entering Pincode", true);
		pincodeField.sendKeys(pincode);
	}

	public void enterMobileNum(String mobNum) {
		Reporter.log("Entering Mobile Number", true);
		mobNumField.sendKeys(mobNum);
	}

	public void clickOnContinueButton() {
		Reporter.log("Click on Continue Button", true);
		continueButton.click();
	}
	
	public String getpinerrmsg()
	{
		String actualtext = pinerrmsg.getText();
		return actualtext;
		
	}
	
	public String getmoberrmsg()
	{
		String actualtext = moberrmsg.getText();
		return actualtext;
	}

}
