package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_15_Wait_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;	
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver,10);
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		//c1 wait pagw ready
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
		
		//c2 wait cho loading icon bien mat (invisible) + ket hop set implicit co gia tri (xxs)
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.loadmask"))));
		
		// implicit = 0
//		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
				
	}
	
	@Test
	public void TC_02() {
		driver.get("https://blog.testproject.io/");
		
		// Handle popup
		if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		
		// Hover mouse to search textbox
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> firstAllPostTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement postTitle :  firstAllPostTitle) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
			
		}
		
		
		
		
		//c2 wait cho loading icon bien mat (invisible) + ket hop set implicit co gia tri (xxs)
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.loadmask"))));
		
		// implicit = 0
//		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
				
	}
	
	//Only JQuery
	public boolean isJQueryLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor =(JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver dirver) {
				return(Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	//JQuery + Javascript
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor =(JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver dirver) {
				try {
				return(Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active ===0);");
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver dirver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	//JQuery + Element 
		public boolean isJQueryandAjaxIconLoadedSuccess() {
			explicitWait = new WebDriverWait(driver, 30);
			jsExecutor =(JavascriptExecutor) driver;
			
			ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver dirver) {
					try {
					return((Long) jsExecutor.executeScript("return jQuery.active") ==0);
					} catch (Exception e) {
						return true;
					}
				}
			};
			ExpectedCondition<Boolean> ajaxIconLoading = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver dirver) {
					return jsExecutor.executeScript("return $('div.spinner').hidden").toString().equals("underfined");
				}
			};
			return explicitWait.until(jQueryLoad) && explicitWait.until(ajaxIconLoading);
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
