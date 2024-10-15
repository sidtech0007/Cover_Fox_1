package coverfoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxResultPage {

	@FindBy(xpath = "//div[contains(text(),'matching Health')]")
	private WebElement resultText;
	@FindBy(className = "plan-card-container")
	private List<WebElement> planList;

	public CoverFoxResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public int getCountFromtext()
	{
		Reporter.log("Getting count from text", true);
		String resultInString = resultText.getText().substring(0, 2);
		int countFromText = Integer.parseInt(resultInString);
		return countFromText;
	}
	
	public int getCountfrombanner()
	{
		Reporter.log("Getting count from baner", true);
		int countFromBanner = planList.size();
		return countFromBanner;

//	public void validateResult() {
//		String resultInString = resultText.getText().substring(0, 2);
//		int resultNumber = Integer.parseInt(resultInString);
//		System.out.println("Result Number is " + resultNumber);
//
//		int planListNumber = planList.size();
//		System.out.println("Plan List Number is " + planListNumber);
//
//		if (resultNumber == planListNumber) {
//			System.out.println("Result are matching, TC is Passed");
//		} else {
//			System.out.println("Result are not matching, TC is Failed");
//		}
//	}
}
}
