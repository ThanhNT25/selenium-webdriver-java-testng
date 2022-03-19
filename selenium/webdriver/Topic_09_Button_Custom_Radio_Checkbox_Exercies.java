package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


public class Topic_09_Button_Custom_Radio_Checkbox_Exercies {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		//WebElement radioSummer = driver.findElement(By.xpath("//input[@value='Summer']"));
		By radioSummer = By.xpath("//input[@value='Summer']");
		clickJavascript(radioSummer);
		Assert.assertTrue(driver.findElement(radioSummer).isSelected());
					
	}

	@Test
	public void TC_02_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
			
		By checkboxChecked = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By checkboxIndeterminate = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		
		//kiem tra chua duoc chon va thuc hien chon
		if(!driver.findElement(checkboxChecked).isSelected()) {
			clickJavascript(checkboxChecked);
			sleepInSecond(2);	
		}
		if(!driver.findElement(checkboxIndeterminate).isSelected()) {
			clickJavascript(checkboxIndeterminate);
			sleepInSecond(2);	
		}
		
		//verify checkbox da duoc chon
		Assert.assertTrue(driver.findElement(checkboxChecked).isSelected());
		Assert.assertTrue(driver.findElement(checkboxIndeterminate).isSelected());
				
		//kiem tra da chon va thuc hien bo chon
		if(driver.findElement(checkboxChecked).isSelected()) {
			clickJavascript(checkboxChecked);
			sleepInSecond(2);	
		}
		if(driver.findElement(checkboxIndeterminate).isSelected()) {
			clickJavascript(checkboxIndeterminate);
			sleepInSecond(2);	
		}
		
		//verify checkbox da duoc chon
		Assert.assertFalse(driver.findElement(checkboxChecked).isSelected());
		Assert.assertFalse(driver.findElement(checkboxIndeterminate).isSelected());
		
	}
	
	@Test
	public void TC_03_Custom_Radio_Google_Doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By radioCantho = By.xpath("//div[@data-value='Cần Thơ']");
		By checkboxQuangNam = By.xpath("//div[@aria-label='Quảng Nam']");
		By checkboxQuangNgai = By.xpath("//div[@aria-label='Quảng Ngãi']");
		
		Assert.assertEquals(driver.findElement(radioCantho).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(checkboxQuangNam).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(checkboxQuangNgai).getAttribute("aria-checked"), "false");
		
		clickJavascript(radioCantho);
		clickJavascript(checkboxQuangNam);
		clickJavascript(checkboxQuangNgai);
		
		Assert.assertEquals(driver.findElement(radioCantho).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkboxQuangNam).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Ngãi' and @aria-checked='true']")).isDisplayed());
			
	}
	
	
	public void clickJavascript (By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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
