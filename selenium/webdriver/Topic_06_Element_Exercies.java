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
		WebElement Email = driver.findElement(By.xpath("//div[@class='container']//input[@id='mail']"));
		if (Email.isDisplayed()) {
			System.out.println("Email Element is displayed");
			Email.sendKeys("Automation Testing");
		} 
		else {
			System.out.println("Email Element is not displayed");
		}
		
		WebElement Age = driver.findElement(By.xpath("//div[@class='container']//input[@id='under_18']"));
		if(Age.isDisplayed()) {
			System.out.println("Age Element is displayed");
			Age.click();
		}
		else {
			System.out.println("Age Element is not displayed");
		}
		
		Boolean Education = driver.findElement(By.xpath("//div[@class='container']//label[@for='edu' and text()='Education:' ]")).isDisplayed();
		System.out.println("Education is display");
		Boolean User5 = driver.findElement(By.xpath("//div[@class='figcaption']/h5[(text()='Name: User5')]")).isDisplayed();
		if(User5==true) {
			System.out.println(" User5 is display");
		}
		else{
			System.out.println(" User5 is not display");
		}

		
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
		
		
		
		WebElement PWdisable = driver.findElement(By.xpath("//div[@class='container']//input[@id='disable_password']"));
		if(PWdisable.isEnabled())
			System.out.println("Element is enable");
		else{
			System.out.println("Element is disable");
		}
		
		
		WebElement Radiobuttondisable = driver.findElement(By.xpath("//div[@class='container']//input[@id='radio-disabled']"));
		if(Radiobuttondisable.isEnabled())
			System.out.println("Element is enable");
		else{
			System.out.println("Element is disable");
		}
		
		WebElement Biodisable = driver.findElement(By.xpath("//div[@class='container']//textarea[@id='bio']"));
		if(Biodisable.isEnabled())
			System.out.println("Element is enable");
		else{
			System.out.println("Element is disable");
		}
		
		WebElement Job3disable = driver.findElement(By.xpath("//div[@class='container']//select[@id='job3']"));
		if(Job3disable.isEnabled())
			System.out.println("Element is enable");
		else{
			System.out.println("Element is disable");
		}
		
		WebElement Checkboxdisable = driver.findElement(By.xpath("//div[@class='container']//input[@id='check-disbaled']"));
		if(Checkboxdisable.isEnabled())
			System.out.println("Element is enable");
		else{
			System.out.println("Element is disable");
		}
		
		WebElement Slider2disable = driver.findElement(By.xpath("//div[@class='container']//input[@id='slider-2']"));
		if(Slider2disable.isEnabled())
			System.out.println("Element is enable");
		else{
			System.out.println("Element is disable");
		}		
		
	}
	
	@Test
	public void TC_03_Element_Isselected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	    
	    WebElement Radiobt = driver.findElement(By.xpath("//div[@class='container']//input[@id='under_18']"));
	    Radiobt.click();
	   
	    sleepInSecond(3);
	    
	    WebElement checkbox = driver.findElement(By.xpath("//div[@class='container']//input[@id='java']"));
	    checkbox.click();
	    
	    //check element was selected
	    if(Radiobt.isSelected()) {
	    	System.out.println(" radio button Element is selected");
	    }
	    else {
	    	System.out.println( " radio button Element is de-selected");
	    }
	    
	    if(checkbox.isSelected()) {
	    	System.out.println(" checkbox Element is selected");
	    }
	    else {
	    	System.out.println( " checkbox Element is de-selected");
	    }
	    
	    //uncheck checkbox
	    checkbox.click();
	    sleepInSecond(3);
	    
	    //check checkbox was uncheck
	    Boolean uncheckbox = driver.findElement(By.xpath("//div[@class='container']//input[@id='java']")).isSelected();
	    if(uncheckbox==true) {
	    	System.out.println("Java Element is checked");
	    }
	    else {
	    	System.out.println( "Java Element is unchecked");
	    }
	    
	}
	
	@Test
	public void TC_04_Register_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
	    
	    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nguyenthithanh0894@gmail.com");
	    driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("Thanh");
	    WebElement PW = driver.findElement(By.xpath("//input[@id='new_password']"));
	    PW.click();
	    Boolean lowrcaserequire = driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='lowercase-char']")).isDisplayed();
	    Boolean uppercaserequire = driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='uppercase-char']")).isDisplayed();
	    Boolean numberrequire = driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='number-char']")).isDisplayed();
	    Boolean specialcompleterequire = driver.findElement(By.xpath("//div[@class='lastGroup size1of2']//li[@class='special-char']")).isDisplayed();
	    Boolean enoughcharacter = driver.findElement(By.xpath("//div[@class='lastGroup size1of2']//li[@class='8-char']")).isDisplayed();

	    if(lowrcaserequire == true) {
	    	System.out.println(" lowrcaserequire Elemnt is display");
	    }
	    else {
	    	System.out.println("lowrcaserequire Elemnt is not display");
	    }	
	    
	    if(uppercaserequire == true) {
	    	System.out.println(" uppercaserequire Elemnt is display");
	    }
	    else {
	    	System.out.println("uppercaserequire Elemnt is not display");
	    }	
	    if(numberrequire == true) {
	    	System.out.println(" numberrequire Elemnt is display");
	    }
	    else {
	    	System.out.println("numberrequire Elemnt is not display");
	    }	
	    if(specialcompleterequire == true) {
	    	System.out.println(" specialcompleterequire Elemnt is display");
	    }
	    else {
	    	System.out.println("specialcompleterequire Elemnt is not display");
	    }
	    if(enoughcharacter == true) {
	    	System.out.println(" 8 characters minimum Elemnt is display");
	    }
	    else {
	    	System.out.println("8 characters minimum Elemnt is not display");
	    }
	    
	    PW.sendKeys("a");   
	    
	    WebElement lowrcasecoplete = driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='lowercase-char completed']"));
	    if(lowrcasecoplete.isDisplayed()) {
	    	System.out.println(" lowrcasecoplete Elemnt is display");
	    }
	    else {
	    	System.out.println("lowrcasecoplete Elemnt is not display");
	    }		
	    
	    
	    PW.sendKeys("A");
	    WebElement uppercasecomplete = driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='uppercase-char completed']"));
	    if(uppercasecomplete.isDisplayed()) {
	    	System.out.println(" uppercasecomplete Elemnt is display");
	    }
	    else {
	    	System.out.println("uppercasecomplete Elemnt is not display");
	    }		
	    
	    
	    PW.sendKeys("0");
	    WebElement numbercomplete = driver.findElement(By.xpath("//div[@class='group size1of2']//li[@class='number-char completed']"));
	    if(uppercasecomplete.isDisplayed()) {
	    	System.out.println(" numbercomplete Elemnt is display");
	    }
	    else {
	    	System.out.println("numbercomplete Elemnt is not display");
	    }	
	    
	    		
	    PW.sendKeys("@");
	    WebElement specialcomplete = driver.findElement(By.xpath("//div[@class='lastGroup size1of2']//li[@class='special-char completed']"));
	    if(specialcomplete.isDisplayed()) {
	    	System.out.println(" specialcomplete Elemnt is display");
	    }
	    else {
	    	System.out.println("specialcomplete Elemnt is not display");
	    }	
	    		
	    PW.sendKeys("1234");
	    String PWformatvalid = driver.findElement(By.xpath("//div[@class='lastUnit size1of1']//div[@class='c-mediaBody--centered']")).getText();
	    Assert.assertTrue(PWformatvalid.contains("Your password is secure and you're all set!"));
	    	    
	    PW.clear();
	    PW.sendKeys("123");
//	    WebElement bttsigup = driver.findElement(By.xpath("//fieldset[@class='!margin-bottom--lv2']//button[@id='create-account']"));
//	    String attribute = driver.findElement(By.xpath("//fieldset[@class='!margin-bottom--lv2']//button[@id='create-account']")).getAttribute("disable");
//	    System.out.println(attribute);
//	    
	    Boolean bttsigup = driver.findElement(By.xpath("//fieldset[@class='!margin-bottom--lv2']//button[@id='create-account']")).isEnabled();
	    if(bttsigup==true) {
	    	System.out.println("Button Sigup is enable");
	    }
	    else {
	    	System.out.println("Button Sigup is disable");
	    }
	    		
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
