package webdriver;

import java.util.List;
import java.util.Set;
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

public class Topic_11_Windows_Tab {
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
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String homePageWindowsID = driver.getWindowHandle();
		System.out.print("Tab A: " + homePageWindowsID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		System.out.println(driver.getCurrentUrl());
		switchToWindowByID(homePageWindowsID);

		// sau khi switch qua
		System.out.println("url cua page B " + driver.getCurrentUrl());

		driver.switchTo().window(homePageWindowsID);

	}

	@Test
	public void TC_02_Iframe_Index() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String homePageWindowsID = driver.getWindowHandle();
		System.out.print("Tab A: " + homePageWindowsID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Google");
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Facebook");
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		closeAllWindowsWithoutParent(homePageWindowsID);
		sleepInSecond(5);

	}

	@Test
	public void TC_03_Iframe_Index() {
		driver.get("https://kyna.vn/");
		String homePageWindowsID = driver.getWindowHandle();
		System.out.print("Tab A: " + homePageWindowsID);

		//clickToElementByJS("//div[@class='hotline']//a[@href='https://www.facebook.com/kyna.vn']");
		clickToElementByJS("//div[@class='hotline']//a[contains(@href,'facebook.com')]");
		sleepInSecond(3);
		switchToWindowByTitle("(1) Kyna.vn | Facebook");
		System.out.println("title of page: " + driver.getTitle());
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		//clickToElementByJS("//div[@class='hotline']//a[@href='https://www.youtube.com/user/kynavn']");
		clickToElementByJS("//div[@class='hotline']//a[contains(@href,'youtube.com')]");

		sleepInSecond(3);
		switchToWindowByTitle("Kyna.vn - YouTube");
		System.out.println("title of page: " + driver.getTitle());
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		closeAllWindowsWithoutParent(homePageWindowsID);
		sleepInSecond(5);


	}

	@Test
	public void TC_04_Iframe_Techpanda() {
		driver.get("http://live.techpanda.org/");
		String homePageWindowsID = driver.getWindowHandle();
		System.out.print("Tab A: " + homePageWindowsID);

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span[text()='The product Sony Xperia has been added to comparison list.']")).getText(),
				"The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span[text()='The product Samsung Galaxy has been added to comparison list.']")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//div[@class='actions']//span[text()='Compare']")).click();
		switchToWindowByID(homePageWindowsID);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowsWithoutParent(homePageWindowsID);
		driver.findElement(By.xpath("//div[@class='actions']//a[text()='Clear All']")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).getText(),"The comparison list was cleared.");

	}
	
	@Test
	public void TC_05_Iframe_Cambrigde() {
		driver.get("https://dictionary.cambridge.org/vi/");
		String homePageWindowsID = driver.getWindowHandle();
		System.out.print("Tab A: " + homePageWindowsID);

		driver.findElement(By.cssSelector("span.cdo-login-button")).click();
		switchToWindowByID(homePageWindowsID);
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='gigya-layout-cell responsive with-site-login']//span[@data-bound-to='loginID'])[2]")).getText(),
				"This field is required");
		
		Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='gigya-layout-cell responsive with-site-login']//span[@data-bound-to='password'])[3]")).getText(),
				"This field is required");
		
		driver.findElement(By.xpath("//input[@name='username' and @placeholder='Email *' ]")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@name='password' and @placeholder='Password *' ]")).sendKeys("Automation000***");
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		sleepInSecond(10);
		switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		Assert.assertEquals(driver.findElement(By.cssSelector("span.cdo-username")).getText(), "Automation FC");

	}

	public void switchToWindowByID(String currentWindowID) {
		// lay het tat ca cac ID dang co ra
		Set<String> allWindowIDs = driver.getWindowHandles();

		// dung 1 bien tam de duyen qua cac phan tu trong set<string>
		for (String id : allWindowIDs) {
			// neu nhu id nao khac voi id cua home page
			if (!id.equals(currentWindowID)) {
				// switch qua id cua tab do
				driver.switchTo().window(id);
			}
		}
	}

	public void switchToWindowByTitle(String expectedTitle) {
		// lay het tat ca cac id dang co
		Set<String> allWindowIDs = driver.getWindowHandles();

		// dung 1 bien tam de duye qa cacs phan tu trong Set<string>
		for (String id : allWindowIDs) {
			// switch vao truong moi kiem tra dieu kien sau
			driver.switchTo().window(id);

			// lay cai title cua page do ra
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		return false;
	}

	public void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
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
