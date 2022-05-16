package loop;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_01_Register {

	WebDriver driver;
	String loginPageUrl, userID, userPassword, email;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@BeforeMethod
	public void beforeMethod() {
		
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		loginPageUrl = "https://demo.guru99.com/v4/";
		email = "briantraycy"+getRandomNumber()+"@gmail.net";
		
	}
	
	@Test(invocationCount =3)
	public void TC_01_Register() {
		driver.get(loginPageUrl);
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		System.out.println(userID);
		System.out.println(userPassword);
		
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	public int getRandomNumber() {
		Random rand =  new Random();
		return  rand.nextInt(9999);
	}
}
