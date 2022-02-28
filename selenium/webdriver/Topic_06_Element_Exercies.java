package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_06_Element_Exercies {
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
	public void TC_01_Element_Isdisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
//	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']")).isDisplayed());
	    Boolean Email = driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']")).isDisplayed();
	    System.out.println(Email+" Email Element is displayed");
	    
	    Boolean Age = driver.findElement(By.xpath("//div[@class='container']//input[@id='under_18']")).isDisplayed();
	    System.out.println(Age+ " Age Element is displayed");
	    
	    Boolean Education = driver.findElement(By.xpath("//div[@class='container']//label[@for='edu' and text()='Education:' ]")).isDisplayed();
	    System.out.println(Education+ " Education Element is displayed");
		
//		WebElement menuOption = driver.findElement(By.xpath(".//div[contains(text(),'Music')]"));
//    	//Mouse hover menuOption 'Music'
//    	actions.moveToElement(menuOption).perform();

	}
	
	@Test
	public void TC_02_Element_Isenabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Boolean Emailenable = driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']")).isEnabled();
	    System.out.println(Emailenable+ " is enable");
	    
	    Boolean Radiobttenable = driver.findElement(By.xpath("//div[@class='container']//input[@id='under_18']")).isEnabled();
	    System.out.println(Radiobttenable+ " is enable");
	    
	    Boolean Eduenable = driver.findElement(By.xpath("//div[@class='container']//textarea[@id='edu']")).isEnabled();
	    System.out.println(Eduenable+ " is enable");
	    
	    Boolean selectjob1enable = driver.findElement(By.xpath("//div[@class='container']//select[@id='job1']")).isEnabled();
	    System.out.println(selectjob1enable+ " is enable");
	    
	    Boolean selectjob2enable = driver.findElement(By.xpath("//div[@class='container']//select[@id='job2']")).isEnabled();
	    System.out.println(selectjob2enable+ " is enable");
	    
	    Boolean checkboxenable = driver.findElement(By.xpath("//div[@class='container']//input[@id='development']")).isEnabled();
	    System.out.println(checkboxenable+ " is enable");
	    
	    Boolean Slider1enable = driver.findElement(By.xpath("//div[@class='container']//input[@id='slider-1']")).isEnabled();
	    System.out.println(Slider1enable+ " is enable");
		
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//input[@id='disable_password']")).isEnabled());
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//input[@id='radio-disabled']")).isEnabled());
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//textarea[@id='bio']")).isEnabled());
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//select[@id='job3']")).isEnabled());
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//input[@id='check-disbaled']")).isEnabled());
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//input[@id='slider-2']")).isEnabled());
		
	}
	
	@Test
	public void TC_03_Element_Isselected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//div[@class='container']//input[@id='under_18']")).click();
		Boolean Radiobt = driver.findElement(By.xpath("//div[@class='container']//input[@id='under_18']")).isSelected();
		System.out.println(Radiobt+ " is selected");
	    sleepInSecond(3);
	    driver.findElement(By.xpath("//div[@class='container']//input[@id='java']")).click();
	    Boolean checkbox = driver.findElement(By.xpath("//div[@class='container']//input[@id='java']")).isSelected();
		System.out.println(checkbox+ " is selected");
	    //uncheck checkbox
	    driver.findElement(By.xpath("//div[@class='container']//input[@id='java']")).click();	     
	    //check checkbox was uncheck
	    Assert.assertFalse(driver.findElement(By.xpath("//div[@class='container']//input[@id='slider-2']")).isSelected());
	    
	}
	
	@Test
	public void TC_04_Register_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
	    
	    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenthithanh0894@gmail.com");
	    driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("Thanh");
	    WebElement PW = driver.findElement(By.xpath("//input[@id='new_password']"));
	    PW.click();
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='lowercase-char']")).isDisplayed());
	    System.out.println("Element is diplay");
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='uppercase-char']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='number-char']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lastGroup size1of2']//li[@class='special-char']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lastGroup size1of2']//li[@class='8-char']")).isDisplayed());
	    
	    PW.sendKeys("aA0@");   
	    
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='lowercase-char completed']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='uppercase-char completed']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='number-char completed']")).isDisplayed());
	    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lastGroup size1of2']//li[@class='special-char completed']")).isDisplayed());	    
	    		
	    PW.sendKeys("1234");
	    String PWformatvalid = driver.findElement(By.xpath("//div[@class='lastUnit size1of1']//div[@class='c-mediaBody--centered']")).getText();
	    Assert.assertTrue(PWformatvalid.contains("Your password is secure and you're all set!"));
	    	    
	    PW.clear();
	    PW.sendKeys("123");
	    
	    Assert.assertFalse(driver.findElement(By.xpath("//fieldset[@class='!margin-bottom--lv2']//button[@id='create-account']")).isEnabled());
	    		
	    driver.findElement(By.xpath("//div[@class='margin-bottom--lv4']//input[@id='marketing_newsletter']")).click();
	    Boolean checkbox = driver.findElement(By.xpath("//div[@class='margin-bottom--lv4']//input[@id='marketing_newsletter']")).isSelected();
	    System.out.println(checkbox+ " checkbox is checked");	   
	  
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
