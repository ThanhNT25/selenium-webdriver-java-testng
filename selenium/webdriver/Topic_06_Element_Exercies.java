package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_06_Element_Exercies {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
//	public void TC_01_Isdisplayed() {
//		driver.get("https://automationfc.github.io/basic-form/index.html");
//		Boolean Email = driver.findElement(By.xpath("//div[@class='container']//label[@for='mail' and text()='Email:' ]")).isDisplayed();
//		Boolean Age = driver.findElement(By.xpath("//div[@class='container']//label[@for='under_18' and text()='Under 18' ]")).isDisplayed();
//		Boolean Education = driver.findElement(By.xpath("//div[@class='container']//label[@for='edu' and text()='Education:' ]")).isDisplayed();
//
//	}

	
	@Test
	public void TC_01_Element_Isdisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Boolean Email = driver.findElement(By.xpath("//div[@class='container']//label[@for='mail' and text()='Email:' ]")).isDisplayed();
		Boolean Age = driver.findElement(By.xpath("//div[@class='container']//label[@for='under_18' and text()='Under 18' ]")).isDisplayed();
		Boolean Education = driver.findElement(By.xpath("//div[@class='container']//label[@for='edu' and text()='Education:' ]")).isDisplayed();
		Boolean Username = driver.findElement(By.xpath("//div[@class='figcaption']/h5[(text()='Name: User5')]")).isEnabled();
		
		
//		WebElement menuOption = driver.findElement(By.xpath(".//div[contains(text(),'Music')]"));
//    	//Mouse hover menuOption 'Music'
//    	actions.moveToElement(menuOption).perform();

	}
	
	@Test
	public void TC_02_Element_Isenabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Boolean Email = driver.findElement(By.xpath("//div[@class='container']//label[@for='mail' and text()='Email:' ]")).isDisplayed();
		Boolean Age = driver.findElement(By.xpath("//div[@class='container']//label[@for='under_18' and text()='Under 18' ]")).isDisplayed();
		Boolean Education = driver.findElement(By.xpath("//div[@class='container']//label[@for='edu' and text()='Education:' ]")).isDisplayed();
		Boolean Username = driver.findElement(By.xpath("//div[@class='figcaption']/h5[(text()='Name: User5')]")).isEnabled();
		
		
//		WebElement menuOption = driver.findElement(By.xpath(".//div[contains(text(),'Music')]"));
//    	//Mouse hover menuOption 'Music'
//    	actions.moveToElement(menuOption).perform();

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
