package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AfterLoginPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","master"})
	public void verify_login() {
		logger.info("***** Starting login test *****");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(rb.getString("email"));
		lp.setPassword(rb.getString("password"));
		lp.clickLogin();
		
		AfterLoginPage alp=new AfterLoginPage(driver);
		Thread.sleep(2000);
		boolean target=alp.check();
		Thread.sleep(2000);
		Assert.assertEquals(target, true,"Login Failed");
		System.out.println("Success");
		}
		catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
		logger.info("***** Ending login test *****");
		
		
	}

}
