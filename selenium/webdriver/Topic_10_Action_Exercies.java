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
//import com.sun.tools.javac.util.Context.Key;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;


public class Topic_10_Action_Exercies {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName =  System.getProperty("OS.name");
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
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		WebElement hoverText = driver.findElement(By.id("age"));
		action.moveToElement(hoverText).perform();
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover_To_Element() {
		driver.get("http://www.myntra.com/");
				
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
			
		
	}
	
	@Test
	public void TC_03_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		WebElement firstNumber = allNumber.get(0);
		WebElement secondNumber = allNumber.get(1);
		WebElement thirdNumber = allNumber.get(2);
		WebElement fourthNumber = allNumber.get(3);
		Actions moveItem = new Actions(driver);
		moveItem.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
		sleepInSecond(3);
		
		//verify element select successful
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(), 4);
		
	}
	@Test
	public void TC_04_Click_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
//		Keys controlKey;
//		if(osName.contains("Windows")) {
//			controlKey =  Keys.CONTROL;
//		}else {
//			controlKey =  Keys.COMMAND;
//		}
//		
//		action.keyDown(controlKey).perform();
//		action.click(allNumber.get(0)).click(allNumber.get(4)).click(allNumber.get(10)).perform();
//		action.keyUp(controlKey).perform();
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(allNumber.get(0)).click(allNumber.get(4)).click(allNumber.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		//verify element select successful
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(), 3);
				
	}
	
	@Test
	public void TC_05_Right_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		//verify
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	
	}
	
	@Test
	public void TC_06_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		sleepInSecond(3);
		
		//verify
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon.context-menu-icon-quit.context-menu-hover.context-menu-visible"))).perform();
		
		alert = driver.switchTo().alert();
		alert.accept();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon.context-menu-icon-quit")).isDisplayed());

	
	}
	
	@Test
	public void TC_07_Drap_Drop_Element() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
	
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(smallCircle,bigCircle).build().perform();
		//action.clickAndHold(smallCircle).moveToElement(bigCircle).release(bigCircle).perform();
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
		
	}
	

	public void clickJavascript (By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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
