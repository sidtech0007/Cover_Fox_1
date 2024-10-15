package coverfoxUtility;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverfoxBase.Base;

public class Listener extends Base implements ITestListener {
	
	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("TC"+result.getName()+"Passed", true);
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		Reporter.log("TC"+result.getName()+"Failed", true);
		try {
			Reporter.log("Taking Screen shot...", true);
			Utility.takescreenshot(driver  , result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

}
