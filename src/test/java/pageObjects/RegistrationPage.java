package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@name='firstname']") WebElement firstname;
	@FindBy(xpath="//input[@name='lastname']") WebElement lastname;
	@FindBy(xpath="//input[@name='email']") WebElement email;
	@FindBy(xpath="//input[@name='password']") WebElement password;
	@FindBy(xpath="//input[@type='checkbox' and @name='agree']") WebElement privacy;
	@FindBy(xpath="//button[text()='Continue']") WebElement continuebutton;
	@FindBy(xpath="//div[@id='content']//h1") WebElement title;
	@FindBy(xpath="//div[@class='text-end']//a") WebElement finalcont;
	

	
	public void setFirstName(String fn) {
		firstname.sendKeys(fn);
		}
	
	public void setLastName(String ln) {
		lastname.sendKeys(ln);
		}
	
	public void setEmail(String mail) {
		email.sendKeys(mail);
		}
	
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
		}
	public void setprivacypolicy() {
		Actions action = new Actions(driver);
		action.moveToElement(privacy).click().perform();
		//privacy.click();
		}
	public void clickContinue() {
		continuebutton.click();
		}
	
	public String getConfirmation() {
		try {
			return (title.getText());
		}
		catch (Exception e) {
			return (e.getMessage());
		}
	}
	public void clickFinal() {
		finalcont.click();
	}
	
}
