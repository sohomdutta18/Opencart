package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {
	
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//td[@class='text-start text-wrap']//a[normalize-space()='iPhone']") WebElement verifyproduct;
	
	public String verifyProduct() {
		return verifyproduct.getText();
	}

}
