package coverfoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coverfoxBase.Base;
import coverfoxPOM.CoverFoxResultPage;
import coverfoxPOM.CoverFox_AddressDetailsPage;
import coverfoxPOM.coverFox_HealthPlanPage;
import coverfoxPOM.coverFox_HomePage;
import coverfoxPOM.coverFox_MemberDeatailsPage;
import coverfoxUtility.Utility;

public class TC0001_CoverFox_ValidateBannerCount extends Base {
  
	coverFox_HomePage homePage;
	coverFox_HealthPlanPage healthPlanPage;
	coverFox_MemberDeatailsPage memberDetailsPage;
	CoverFox_AddressDetailsPage addressDetailsPage;
	CoverFoxResultPage resultPage;
	String excelpath=System.getProperty("user.dir")+"\\datasheet\\testData.xlsx";
	String sheetname="Sheet5";
	public static Logger logger;
	  
	//open browser/open application
	@Parameters("browser")
	@BeforeClass
	public void openApplicaton(String browser) throws IOException
	{
		launchBrowser(browser);
		logger=Logger.getLogger("CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("openinng application");
	}
	
	//enter age, pincode, mob num 
	@BeforeMethod
	public void enterDetails() throws EncryptedDocumentException, IOException, InterruptedException
	{
		homePage=new coverFox_HomePage(driver);
		healthPlanPage=new coverFox_HealthPlanPage(driver);
		memberDetailsPage=new coverFox_MemberDeatailsPage(driver);
		addressDetailsPage=new CoverFox_AddressDetailsPage(driver);
		resultPage=new CoverFoxResultPage(driver);
		
		homePage.clickOnGenderButton();
		logger.info("Clicking on gender button");
		Thread.sleep(1000);
		healthPlanPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		memberDetailsPage.handleAgeDropdown(Utility.readingDataFromExcel(excelpath, sheetname, 0, 0));;
		logger.info("Handling age dropdown");
		memberDetailsPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		logger.warn("Enter valid pincode");
		addressDetailsPage.enterPincode(Utility.readingDataFromExcel(excelpath, sheetname, 0, 1));;
		logger.info("Entering pincode");
		logger.warn("Enter valid mobile number");
		addressDetailsPage.enterMobileNum(Utility.readingDataFromExcel(excelpath, sheetname, 0, 2));;
		logger.info("Entering mobile number");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on conntinue button");
		logger.error("Worng Details: Please chek the deatils");
		Thread.sleep(4000);
	
	}
	
	@Test
  public void validatePolicy() 
  {
		int textCount = resultPage.getCountFromtext();
		int bannerCount = resultPage.getCountfrombanner();
		
		Assert.assertEquals(textCount, bannerCount, "textCount not matching with bannerCount, TC Failed");
		logger.info("validating results");
		//Assert.fail();
  }	
	
	//close browser/close application
	@AfterClass
	public void closeApplication()
	{
		closebrowser();
	}
}
