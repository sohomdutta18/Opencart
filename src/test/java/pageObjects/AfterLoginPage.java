package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AfterLoginPage extends BasePage{
	
	public AfterLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[normalize-space()='My Account']")WebElement title;
	@FindBy(xpath="//div[@class='list-group mb-3']//a[normalize-space()='Logout']")WebElement logout;
	
	public boolean check() {
		try {
		return (title.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		Actions actions = new Actions(driver);
		actions.moveToElement(logout).perform();
		logout.click();
		}

}
