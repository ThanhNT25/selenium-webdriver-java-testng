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



public class Topic_06_Browser_Exercies {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_VerifyURL() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account' and text()='My Account']")).click();
		String Loginpageurl = driver.getCurrentUrl();
		Assert.assertEquals(Loginpageurl, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account' ]")).click();
		String Registerpageurl = driver.getCurrentUrl();
		Assert.assertEquals(Registerpageurl, "http://live.techpanda.org/index.php/customer/account/create/");
	}

	
	@Test
	public void TC_02_VerifyTitle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account' and text()='My Account']")).click();
		String Loginpagetitle = driver.getTitle();
		Assert.assertEquals(Loginpagetitle, "Customer Login");
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account' ]")).click();
		String Registerpagetitle = driver.getTitle();
		Assert.assertEquals(Registerpagetitle, "Create New Customer Account");
		

	}

	@Test
	public void TC_03_VerifyNavigate() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account' and text()='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account' ]")).click();
		String Registerpageurl = driver.getCurrentUrl();
		Assert.assertEquals(Registerpageurl, "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		String Loginpageurl = driver.getCurrentUrl();
		Assert.assertEquals(Loginpageurl, "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		String Registerpagetitle = driver.getTitle();
		Assert.assertEquals(Registerpagetitle, "Create New Customer Account");

	}
	
	@Test
	public void TC_04_Getpagesource() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account' and text()='My Account']")).click();
		
		String Pagelogin = driver.getPageSource();
		Assert.assertTrue(Pagelogin.contains("Login or Create an Account"));
		String Pageregister = driver.getPageSource();
		Assert.assertTrue(Pageregister.contains("Create an Account"));
		

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
