package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='description']//a")
	WebElement iphone;
	@FindBy(xpath = "//div[@class='product-thumb']//button[@aria-label='Add to Cart']")
	WebElement addtocartbutton;
	@FindBy(xpath = "//button[@type='button' and contains(@class, 'btn btn-lg')]")
	WebElement cartbutton;
	@FindBy(xpath = "//a[@title='Shopping Cart']")
	WebElement shoppingcart;

	public boolean productIsDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(iphone));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iphone);
		wait.until(ExpectedConditions.elementToBeClickable(iphone));
		boolean prodisplay = iphone.isDisplayed();
		return prodisplay;
	}

	public void addToCart() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(addtocartbutton));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addtocartbutton);
			wait.until(ExpectedConditions.elementToBeClickable(addtocartbutton));
			addtocartbutton.click();
		} catch (Exception e) {
			System.out.println("Add to Cart click failed: " + e.getMessage());
		}
	}

	public void clickShoppingCart() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(shoppingcart));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shoppingcart);
	        Thread.sleep(500);
	        
	        shoppingcart.click();
	    } catch (Exception e) {
	        System.out.println("Shopping Cart click failed: " + e.getMessage());
	    }
	}
}
