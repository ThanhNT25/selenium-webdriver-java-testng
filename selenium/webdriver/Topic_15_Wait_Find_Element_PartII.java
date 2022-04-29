package webdriver;

import java.io.File;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


public class Topic_15_Wait_Find_Element_PartII {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	String image1Name = "image001.jpg";
	String image2Name = "image002.jpg";
	String image3Name = "image003.jpg";
	
	String uploadFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
	String image1Path = uploadFolderPath + image1Name;
	String image2Path = uploadFolderPath + image2Name;
	String image3Path = uploadFolderPath + image3Name;
	

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver,10);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}
	
	@Test
	public void TC_02() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Thread.sleep(10000);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}
	
	@Test
	public void TC_03_Only_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}
	
	@Test
	public void TC_04_Only_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");
	}
	
		
	@Test
	public void TC_05_DatePiker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//wait cho date picker hien thi		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		WebElement selectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectDate.getText(),"No Selected Dates to display.");
		
		//wait de co the click chon lich
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='27']"))).click();
		
		//wait cho icon loading khong hien thi
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='RadCalendar']>div.raDiv")));
		
		selectDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		
		Assert.assertEquals(selectDate.getText(), "Wednesday, April 27, 2022");
		
		//wait cho ngay duoc select thanh cong(visible)
		WebElement todaySelected = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='27']")));
		Assert.assertTrue(todaySelected.isDisplayed());
	}
	
	@Test
	public void TC_06_UploadFiles() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait = new WebDriverWait(driver,30);
		
		By uploadFileLocator = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileLocator).sendKeys(image1Path +"\n" + image2Path +"\n" + image3Path);
		
		//wait cho loading xong file
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		//wait cho hien thi text thong bao thanh cong
		WebElement uploadText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(uploadText.isDisplayed());
		
		driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).click();
		
		String homePageWindowsID = driver.getWindowHandle();
		System.out.print("Tab A: " + homePageWindowsID);

		sleepInSecond(3);
		switchToWindowByID(homePageWindowsID);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='image001.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='image002.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='image003.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
	
		
		
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
