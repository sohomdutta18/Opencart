package testCases;

import java.time.Duration;
import java.util.Arrays;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass  {
	
	//public WebDriver driver;
	
	
	
	@Test(groups={"regression","master"})
	public void accountRegistration() throws InterruptedException {
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		try{
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on my account");
		hp.clickRegister();
		logger.info("Clicked on register");
		RegistrationPage rp=new RegistrationPage(driver);
		
		logger.info("Providing customer details.......");
		rp.setFirstName(generateString());
		rp.setLastName(generateString());
		rp.setEmail(generateString()+"@gmail.com");
		rp.setPassword(generateNumber());
		rp.setprivacypolicy();
		Thread.sleep(2000);
		rp.clickContinue();
		Thread.sleep(2000);
		logger.info("Validating expected message........");
		String confmsg=rp.getConfirmation();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed........");
			logger.debug("Debug logs......");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		System.out.println(confmsg);
		rp.clickFinal();
		Thread.sleep(2000);
		}
		catch(Exception e) {
			//logger.error("Test failed........");
			//logger.debug("Debug logs......");
			System.out.println("failure");
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
	
	
	

}
