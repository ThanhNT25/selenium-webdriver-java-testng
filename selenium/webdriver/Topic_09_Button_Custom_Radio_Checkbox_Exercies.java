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
	String osName = System.getProperty("os.name");
	

	@BeforeClass
	public void beforeClass() {
//		if(osName.startsWith("Windows")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		} else if(osName.startsWith("Mac")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
//		} else {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		}
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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		By buttonDangNhap = By.xpath("//button[@class='fhs-btn-login']");
		
		//verify button đăng nhập disable
		Assert.assertFalse(driver.findElement(buttonDangNhap).isEnabled());
		
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0349461320");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		
		//verify button đăng nhập enable và có màu đỏ
		Assert.assertTrue(driver.findElement(buttonDangNhap).isEnabled());
		String buttonloginBackgroundColorrgba = driver.findElement(buttonDangNhap).getCssValue("background-color");
		System.out.println("rgba color" +buttonloginBackgroundColorrgba);

		//Convert qua Hexa
		String backgroundbuttonlogincolorhexa = Color.fromString(buttonloginBackgroundColorrgba).asHex();
		Assert.assertEquals(backgroundbuttonlogincolorhexa.toUpperCase(), "#C92127");
		
		String backgroundbuttonlogincolorrgb = Color.fromString(buttonloginBackgroundColorrgba).asRgb();
		Assert.assertEquals(backgroundbuttonlogincolorrgb, "rgb(201, 33, 39)");
		
		//refresh page
		driver.navigate().refresh();
		driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();
		
		//remove disable attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(buttonDangNhap));
		sleepInSecond(2);
		
		driver.findElement(buttonDangNhap).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");

			
	}

	@Test
	public void TC_02_Default_Checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement checkboxDual = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));	
		//By checkboxDual = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		
		//kiem tra chua duoc chon va thuc hien chon
		if(!checkboxDual.isSelected()) {
			//driver.findElement(checkboxDual).click();
			jsExecutor.executeScript("arguments[0].click();", checkboxDual);
			sleepInSecond(2);
		}
		
		//verify checkbox da duoc chon
		Assert.assertTrue(checkboxDual.isSelected());
				
		//kiem tra da chon va thuc hien bo chon
		if(checkboxDual.isSelected()) {
			//driver.findElement(checkboxDual).click();
			jsExecutor.executeScript("arguments[0].click();", checkboxDual);
			sleepInSecond(2);
		}
		
		//verify checkbox da uncheck
		Assert.assertFalse(checkboxDual.isSelected());
		
	}
	
	@Test
	public void TC_03_Default_Radio() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		//By radiobutton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
	
		WebElement radiobutton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));	

		jsExecutor.executeScript("arguments[0].click();", radiobutton);
		sleepInSecond(2);
		Assert.assertTrue((radiobutton).isSelected());
				
	}
	
	@Test
	public void TC_04_Default_Checkbox_ham() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		//WebElement checkboxDual = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));	
		//By checkboxDual = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		
		//select
		//checkToCheckbox(checkboxDual);
		
		//verify checkbox da duoc chon
		//Assert.assertTrue(driver.findElement(checkboxDual).isSelected());
				
		//uncheck
		//uncheckToCheckbox(checkboxDual);
		
		//verify checkbox da uncheck
		//Assert.assertFalse(driver.findElement(checkboxDual).isSelected());
		
	}
	
	@Test
	public void TC_05_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxs = driver.findElements(By.cssSelector("input[type='checkbox']"));
		//action
		for(WebElement checkbox : checkboxs) {
			if(!checkbox.isSelected()) {
				checkbox.click();
				//sleepInSecond(1);
			}
			
		}
		
		//verify
		for(WebElement checkbox : checkboxs) {
			Assert.assertTrue(checkbox.isSelected());
		
		}
		
		for(WebElement checkbox : checkboxs) {
			if(checkbox.isSelected()) {
				checkbox.click();
				//sleepInSecond(1);
			}
			
		}
		
		//verify
		for(WebElement checkbox : checkboxs) {
			Assert.assertFalse(checkbox.isSelected());
		
		}
		
	}

	public void checkToCheckbox (By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();		}
	}
	
	public void uncheckToCheckbox (By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public boolean elementIsSelected (By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean elementIsEnable (By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}
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
