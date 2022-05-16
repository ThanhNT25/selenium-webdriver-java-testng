package tech;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Multiple_Server {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
		
	@Parameters("browser")	
	@BeforeClass
	public void beforeClass(String serverName) {
		switch(serverName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			System.out.println("Browser name is not valid");
			break;		
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Parameters ("server")
	@Test
	public void TC_01_Login(String serverName) {
		String serverUrl = getServerUrl(serverName);
		driver.get("http://" +serverUrl+ "/index.php/customer/account/login/");
		
		driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("111111");
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	
	private String getServerUrl(String serverName) {
		switch(serverName) {
		case "DEV":
			serverName = "dev.live.techpanda.org";
			break;
		case "LIVE":
			serverName = "live.techpanda.org";
			break;
		default:
			break;
		}
		return serverName;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
