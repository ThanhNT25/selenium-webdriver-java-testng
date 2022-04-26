package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


public class Topic_15_Wait_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver,10);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");

		// visible co tren UI va co trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
	}
	
	@Test
	public void TC_02_Invisible_In_DOM() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
		Assert.assertTrue(driver.findElement(By.id("finish")).isDisplayed());
					
	}

	@Test
	public void TC_03_Invisible_Not_In_DOM() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
			
	}
	
	@Test
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id='email']")));

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		//co trong DOM va khong co tron UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
			
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
