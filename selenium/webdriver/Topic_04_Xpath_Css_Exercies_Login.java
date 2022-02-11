package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_04_Xpath_Css_Exercies_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
	
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Login_empty_email_and_password() {
		
		driver.get("http://live.techpanda.org/");
		// Click button ACCOUNT
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		// Click button My Account
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account' and text()='My Account']")).click();
		//Click button login
		driver.findElement(By.xpath("//button[@id='send2' and @title='Login']")).click();
		// Verify error message
		String validateEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(validateEmail, "This is a required field.");
		
		String validatePW = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(validatePW, "This is a required field.");
		
	}
	
	
	@Test
	public void TC_02_Login_Email_Invalid() {
		
		driver.get("http://live.techpanda.org/");
		// Click button ACCOUNT
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		// Click button My Account
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account' and text()='My Account']")).click();
		// Input email invalid
		driver.findElement(By.xpath("//input[@id='email' and @title='Email Address']")).sendKeys("1234123@12341234");
		// Input password valid
		driver.findElement(By.xpath("//input[@id='pass' and @title='Password']")).sendKeys("123456");
		// Click button login
		driver.findElement(By.xpath("//button[@id='send2' and @title='Login']")).click();
		// Verify error message
		
		String validateEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(validateEmail, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	
	@Test
	public void TC_03_Login_Password_Lessthan_6characters() {
		
		driver.get("http://live.techpanda.org/");
		// Click button ACCOUNT
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		// Click button My Account
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account' and text()='My Account']")).click();
		// Input email invalid
		driver.findElement(By.xpath("//input[@id='email' and @title='Email Address']")).sendKeys("automation@gmail.com");
		// Input password valid
		driver.findElement(By.xpath("//input[@id='pass' and @title='Password']")).sendKeys("123");
		// Click button login
		driver.findElement(By.xpath("//button[@id='send2' and @title='Login']")).click();
		// Verify error message
		
		String validatePW = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(validatePW, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	
	@Test
	public void TC_04_Login_Incorrect_email_password() {
		
		driver.get("http://live.techpanda.org/");
		// Click button ACCOUNT
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		// Click button My Account
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account' and text()='My Account']")).click();
		// Input email invalid
		driver.findElement(By.xpath("//input[@id='email' and @title='Email Address']")).sendKeys("automation@gmail.com");
		// Input password valid
		driver.findElement(By.xpath("//input[@id='pass' and @title='Password']")).sendKeys("123345689");
		// Click button login
		driver.findElement(By.xpath("//button[@id='send2' and @title='Login']")).click();
		// Verify error message
		
		String errormgs = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errormgs, "Invalid login or password.");
		
	}
	
	
	@Test
	public void TC_05_Create_New_Account() {
		
		driver.get("http://live.techpanda.org/");
		// Click button ACCOUNT
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		
		// Click button My Account
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account' and text()='My Account']")).click();
		// Click button Create an Account
		driver.findElement(By.xpath("//div[@class='buttons-set']/a[@class='button' and @title='Create an Account']")).click();
		// Input first name
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Nguyen");
		// Input last name
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Thanh");
		// Input email address
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("nguyenthithanh0894+6@gmail.com");
		// Input password
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456Aa@");
		// Input confirm password
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456Aa@");
		// Click button Register
		driver.findElement(By.xpath("//button[@title='Register' and @type='submit']")).click();
		//Verify mgs sucess
		String successmgs = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successmgs, "Thank you for registering with Main Website Store.");
		
		String pagetitle = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(pagetitle, "MY DASHBOARD");
		
		String welcome = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		Assert.assertEquals(welcome, "Hello, Nguyen Thanh!");
		
		String contactText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div")).getText();
		
		Assert.assertTrue(contactText.contains("Nguyen Thanh"));
		Assert.assertTrue(contactText.contains("nguyenthithanh0894+6@gmail.com"));
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		
		// logout
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out' and text()='Log Out']")).click();
		
		// Verify logout success
//		String logoutsuccess = driver.findElement(By.xpath("//div[@class='col-main']//h1[text()='You are now logged out']")).getText();
//		Assert.assertEquals(logoutsuccess, "You are now logged out");
				
	}
	
	@Test
	public void TC_06_Login_data_correct() {
		
		driver.get("http://live.techpanda.org/");
		// Click button ACCOUNT
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		// Click login
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log In' and text()='Log In']")).click();
		
		// Input email address
		driver.findElement(By.xpath("//ul[@class='form-list']//input[@id='email']")).sendKeys("nguyenthithanh0894+1@gmail.com");
		// Input password
		driver.findElement(By.xpath("//ul[@class='form-list']//input[@id='pass']")).sendKeys("123456Aa@");
		
		// Click button Login
		driver.findElement(By.xpath("//button[@class='button' and @title='Login']")).click();
		
		String pagetitle = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(pagetitle, "MY DASHBOARD");
		
		String welcome = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		Assert.assertEquals(welcome, "Hello, Nguyen Thanh!");
		
		String contactText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div")).getText();
		
		Assert.assertTrue(contactText.contains("Nguyen Thanh"));
		Assert.assertTrue(contactText.contains("nguyenthithanh0894+1@gmail.com"));
		

		
				
	}
	


	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
