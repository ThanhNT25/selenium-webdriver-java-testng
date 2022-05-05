package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_15_Wait_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	private long sumTime = 30;
	private long pollTime =  1;
	

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
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentDriver = new FluentWait<WebDriver>(driver) ;
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// sau khi bam loading icon xuat hien va mat di sau 5s
		// icon loading bien mat hello text xuat hien
		
		fluentDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until( new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
			}
			
		});
	}
	
	@Test
	public void TC_02_Loading() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		fluentDriver = new FluentWait<WebDriver>(driver) ;
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// sau khi bam loading icon xuat hien va mat di sau 5s
		// icon loading bien mat hello text xuat hien
		
		fluentDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until( new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed();
			}
			
		});
		
	}
	
	@Test
	public void TC_03_Loading() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		fluentDriver = new FluentWait<WebDriver>(driver) ;
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		// sau khi bam loading icon xuat hien va mat di sau 5s
		// icon loading bien mat hello text xuat hien
		
		fluentDriver.withTimeout(Duration.ofSeconds(6))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class)
		.until( new Function<WebDriver, String>(){
			public String apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
				return text;
			}
			
		});
		
	}
	
	@Test
	public void TC_04_Loading_Display() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		fluentDriver = new FluentWait<WebDriver>(driver) ;
		
		clickToElement(By.cssSelector("//button[text()='Start']"));
		
		// sau khi bam loading icon xuat hien va mat di sau 5s
		// icon loading bien mat hello text xuat hien
		isElementDisplay(By.cssSelector("div#finish>h4"));
		
	}
	
	
	public WebElement findElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(sumTime))
				.pollingEvery(Duration.ofSeconds(pollTime))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	
	public void clickToElement(By locator) {
		WebElement element = findElement (locator);
		element.click();
	}
	
	public boolean isElementDisplay (By locator) {
		WebElement element = findElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(Duration.ofSeconds(sumTime))
				.pollingEvery(Duration.ofSeconds(pollTime))
				.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
						
			}
		});
		return isDisplayed;
		
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
