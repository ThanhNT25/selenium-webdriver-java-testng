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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


public class Topic_11_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions action;
	Alert alert;
	

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
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		
		//switch to iframe A->B
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		
		//B verify so luong like tren facebook
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "167K likes");
		
		//B->A
		driver.switchTo().defaultContent();
		
		//A->C
		driver.switchTo().frame("cs_chat_iframe");
		
		//C click vao chat de bat popup
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("test");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("test phone");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Iframe");
		
		//C->A
		driver.switchTo().defaultContent();
		String keyword = "Excel";
		
		//A nhap va search
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		//verify course name chua tu khoa vua nhap
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for(WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}
	}
	
	
	@Test
	public void TC_02_Iframe() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		//switch to iframe A->B
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
				
		//B verify so luong like tren facebook
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());
		
	
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
