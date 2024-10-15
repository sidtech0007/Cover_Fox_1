package coverfoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import coverfoxBase.Base;
import coverfoxPOM.CoverFoxResultPage;
import coverfoxPOM.CoverFox_AddressDetailsPage;
import coverfoxPOM.coverFox_HealthPlanPage;
import coverfoxPOM.coverFox_HomePage;
import coverfoxPOM.coverFox_MemberDeatailsPage;
import coverfoxUtility.Utility;

public class TC0002_CoverFox_ValidateErrorMessage extends Base {

	coverFox_HomePage homePage;
	coverFox_HealthPlanPage healthPlanPage;
	coverFox_MemberDeatailsPage memberDetailsPage;
	CoverFox_AddressDetailsPage addressDetailsPage;
	CoverFoxResultPage resultPage;
	String excelpath = System.getProperty("user.dir") + "\\datasheet\\testData.xlsx";
	String sheetname = "Sheet5";
	public static Logger logger;

	// open browser/open application

	// enter age, pincode, mob num
	@Parameters("browser")
	@BeforeMethod
	public void enterDetails(String browser) throws EncryptedDocumentException, IOException, InterruptedException {
		launchBrowser(browser);
		logger = Logger.getLogger("CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("openinng application");

		homePage = new coverFox_HomePage(driver);
		healthPlanPage = new coverFox_HealthPlanPage(driver);
		memberDetailsPage = new coverFox_MemberDeatailsPage(driver);
		addressDetailsPage = new CoverFox_AddressDetailsPage(driver);
		resultPage = new CoverFoxResultPage(driver);

		homePage.clickOnGenderButton();
		logger.info("Clicking on gender button");
		Thread.sleep(1000);
		healthPlanPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		memberDetailsPage.handleAgeDropdown(Utility.readingDataFromExcel(excelpath, sheetname, 0, 0));
		;
		logger.info("Handling age dropdown");
		memberDetailsPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);

	}

	@Test
	public void validatePincodeErrorMessage() throws EncryptedDocumentException, IOException {
		addressDetailsPage.enterMobileNum(Utility.readingDataFromExcel(excelpath, sheetname, 0, 2));
		;
		logger.info("Entering mobile number");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on conntinue button");

		String actualPinErrorMesssage = addressDetailsPage.getpinerrmsg();
		String expectedPinErrorMessage = Utility.readingDataFromExcel(excelpath, sheetname, 0, 3);

		Assert.assertEquals(actualPinErrorMesssage, expectedPinErrorMessage,
				"Error message are not matching, TC Failed");
		logger.info("Validating pincode error message");
	}

	@Test
	public void validateMobileNumberErrorMessage() throws EncryptedDocumentException, IOException {
		addressDetailsPage.enterPincode(Utility.readingDataFromExcel(excelpath, sheetname, 0, 1));
		;
		logger.info("Entering pincode");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on conntinue button");

		String actualMobErrMsg = addressDetailsPage.getmoberrmsg();
		String expectedMobErrMsg = Utility.readingDataFromExcel(excelpath, sheetname, 0, 4);

		Assert.assertEquals(actualMobErrMsg, expectedMobErrMsg, "Error message are not matching, TC failed");
		logger.info("Validating Mobile number error message");
	}

	@Test
	public void validateMoblePincodeErrorMessage() throws EncryptedDocumentException, IOException {
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on conntinue button");

		String actualPinErrMsg = addressDetailsPage.getpinerrmsg();
		String expectedPinErrMsg = Utility.readingDataFromExcel(excelpath, sheetname, 0, 3);

		String actualMobErrMsg = addressDetailsPage.getmoberrmsg();
		String expectedMobErrMsg = Utility.readingDataFromExcel(excelpath, sheetname, 0, 4);

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actualPinErrMsg, expectedPinErrMsg, "Error message are not matching, TC failed");
		soft.assertEquals(actualMobErrMsg, expectedMobErrMsg, "Error message are not matching, TC failed");

		soft.assertAll();
		logger.info("Validating pincode and mobile number error message");
	}

	// close browser/close application
	@AfterMethod
	public void closeApplication() {
		closebrowser();
	}
}
