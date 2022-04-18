package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Upload_Files {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
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
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Upload_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileLocator = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileLocator).sendKeys(image1Path);
		driver.findElement(uploadFileLocator).sendKeys(image2Path);
		driver.findElement(uploadFileLocator).sendKeys(image3Path);
		sleepInSecond(3);
		
		//verify file load sucessful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image1Name+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image2Name+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image3Name+"']")).isDisplayed());
		
		//click to button upload each file
		List<WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement button : uploadButton) {
			button.click();
			
		}
		
		//verify upload file successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title ='" +image1Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title ='" +image2Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title ='" +image3Name +"']")).isDisplayed());
	}

	@Test
	public void TC_02_Multiplw_Upload_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileLocator = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileLocator).sendKeys(image1Path +"\n" + image2Path +"\n" + image3Path);
	
		sleepInSecond(3);
		
		//verify file load sucessful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image1Name+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image2Name+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image3Name+"']")).isDisplayed());
		
		//click to button upload each file
		List<WebElement> uploadButton = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement button : uploadButton) {
			button.click();
			
		}
		
		//verify upload file successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title ='" +image1Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title ='" +image2Name +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title ='" +image3Name +"']")).isDisplayed());
		
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
