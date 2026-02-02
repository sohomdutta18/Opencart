package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC004_AddToCart extends BaseClass {
	
	@Test(groups={"sanity","master"})
	public void addToCart() {
		
		logger.info("***** Starting add to cart test *****");
		try {
		HomePage hp=new HomePage(driver);
		hp.productSearch("iphone");
		hp.clickSearchButton();
		
		
		ProductPage pp=new ProductPage(driver);
		boolean proddisplay=pp.productIsDisplayed();
		Assert.assertEquals(proddisplay, true,"Product not present");
		System.out.println("Product Found");
		pp.addToCart();
		pp.clickShoppingCart();
		Thread.sleep(5000);
		
		ShoppingCartPage scp=new ShoppingCartPage(driver);
		String verifyprod=scp.verifyProduct();
		if(verifyprod.equalsIgnoreCase("iphone")) {
			System.out.println("Product found");
			Assert.assertTrue(true);
		}
		else {
			System.out.println("Product not found");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		logger.info("***** Ending add to cart test *****");
	}

}
