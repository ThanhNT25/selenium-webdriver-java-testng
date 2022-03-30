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


public class Topic_11_Popup_Exercies {
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
	public void TC_01_Popup_I() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopup = By.cssSelector("div#modal-login-v1");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());	
					
	}
	
	@Test
	public void TC_02_Popup_II() {
		driver.get("https://jtexpress.vn/");
		
		By sliderPopup = By.cssSelector("div#home-modal-slider");
		String billcode = "977587575";
		Assert.assertTrue(driver.findElement(sliderPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.close")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(sliderPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#billcodes")).sendKeys(billcode);
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5")).getText().contains(billcode));
					
	}

	@Test
	public void TC_03_Random_Popup_In_Dom_I() {
		driver.get("https://vnk.edu.vn/");
		
		if(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
		}
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());
					
	}
	
	@Test
	public void TC_04_Random_Popup_In_Dom_Kmplayer() {
		driver.get("http://www.kmplayer.com/");
		
		if(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed()) {
			//Close popup
			
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());
		}
		driver.findElement(By.xpath("//a[contains(text(),'MOVIEBLOC')]")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("section.main_top_banner")).isDisplayed());
					
	}
	
	@Test
	public void TC_05_Random_Popup_Not_In_Dom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		
		
		if(popupContent.size() > 0) {
			System.out.println("case1 popup hien thi thi co the taho tac voi popup roi close");
			
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Jonh");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("Jonhjonh@gmail.net");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987657874");
			sleepInSecond(2);
			
			//close popup
			//driver.findElement(By.cssSelector("button#close-popup")).click();
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
			sleepInSecond(2);
			
			//verify popup khong hien thi;
			Assert.assertEquals(driver.findElements(By.cssSelector("div.popup-content")).size(), 0);
		}else {
			//neu setting cho app vao ngay nao do khong hien thi popup
			//sau thoi gian thi setting khong hien thi popup nua
			//ngay tu dau khong co rong DOM
			System.out.println("case 2 neu popup khong hien thi qua step tiep theo luon");
		}
		driver.findElement(By.xpath("//a[(text()='Tất cả khóa học')]")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input#search-courses")).isDisplayed());
					
	}
	
	@Test
	public void TC_07_Random_Popup_Not_In_Dom_Shopee() {
		driver.get("https://shopee.vn/");
		
List<WebElement> popupContent = driver.findElements(By.cssSelector("div.home-popup__content"));
		
		String keyword = "Macbook Pro";
		if(popupContent.size() > 0) {
			System.out.println("case1 popup hien thi thi co the taho tac voi popup roi close");
			
			//close popup
			//driver.findElement(By.cssSelector("button#close-popup")).click();
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.shopee-popup__close-btn")));
			sleepInSecond(2);
			
			//verify popup khong hien thi;
			Assert.assertEquals(driver.findElements(By.cssSelector("div.popup-content")).size(), 0);
		}else {
			//neu setting cho app vao ngay nao do khong hien thi popup
			//sau thoi gian thi setting khong hien thi popup nua
			//ngay tu dau khong co rong DOM
			System.out.println("case 2 neu popup khong hien thi qua step tiep theo luon");
		}
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys(keyword);
		//driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")));
		
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
