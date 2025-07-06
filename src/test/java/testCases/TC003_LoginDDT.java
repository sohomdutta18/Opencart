package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AfterLoginPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;




public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven")
	public void verify_loginDDT(String email,String pwd,String exp) {
		
		logger.info("***** Starting login data driven test *****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		AfterLoginPage alp=new AfterLoginPage(driver);
		Thread.sleep(2000);
		boolean target=alp.check();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		/* Data is valid - login success - test pass - logout
		 data is valid - login failed - test fail
		 data is invalid - login failed - test pass
		 data is invalid - login success - test fail - logout
		 */
		
		if(exp.equalsIgnoreCase("valid")) {
			if(target==true) {
				
				Thread.sleep(2000);
				alp.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid")) {
			if(target==true) {
				
				Thread.sleep(2000);
				alp.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
//		else {
//			Assert.assertTrue(false);
//		}
		
	
	}
	catch(Exception e) {
		e.printStackTrace();
		Assert.fail();
	}
		
		logger.info("***** Finished login data driven test *****");

}
}
