package testBase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	
	@BeforeClass(groups={"regression","master","sanity","datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		rb=ResourceBundle.getBundle("config");
		logger=LogManager.getLogger(this.getClass());
		//SLF4JBridgeHandler.install();
		ChromeOptions option=new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		option.setExperimentalOption("prefs", prefs);
		option.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		option.setExperimentalOption("useAutomationExtension", false);
		option.addArguments("--disable-blink-features=AutomationControlled");
		
		if(rb.getString("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")){
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os found!!!");
				return;
			}
			//browser
			if(br.equalsIgnoreCase("chrome")) {
				cap.setBrowserName("chrome");
			}
			else if(br.equalsIgnoreCase("edge")){
				cap.setBrowserName("MicrosoftEdge");
			}
			else {
				System.out.println("No matching browser found!!!");
				return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		if(rb.getString("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome":driver=new ChromeDriver(option); break;
			case "edge":driver=new EdgeDriver(); break;
			case "firefox":driver=new FirefoxDriver(); break;
			default:System.out.println("Invalid Browser"); return;
			}
		}
		
				
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get(rb.getString("appURL1"));
		driver.get("http://localhost/opencartsite/");
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"regression","master","sanity","datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}
	
	public String generateString() {
		String randomstr=RandomStringUtils.randomAlphabetic(6);
		return randomstr;
	}
	public String generateNumber() {
		String randomnum=RandomStringUtils.randomNumeric(6);
		return randomnum;
	}

}
