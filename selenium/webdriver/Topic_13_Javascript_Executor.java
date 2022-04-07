package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String emailAddress = "thanhtest" +getRandomNumber()+"@gmail.com";
	Alert alert;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//interface không cho new, ép kiểu
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Techpanda() {
		
		navigateToUrlByJS("http://live.techpanda.org");
		sleepInSecond(5);
		
		String domain = (String)jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.techpanda.org");
		
		String url = (String)jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.techpanda.org/");
		
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		sleepInSecond(3);
		
		String shoppingCartText = getInnerText();
		Assert.assertTrue(shoppingCartText.contains("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");

		scrollToElementOnDown("//input[@id='newsletter']");
		
		sendkeyToElementByJS("//input[@id='newsletter']","abc124@gmail.com");
		
		clickToElementByJS("//button[@title='Subscribe']");
		
		String subcribeText = getInnerText();
		Assert.assertTrue(subcribeText.contains("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		String domainpage = (String)jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domainpage, "demo.guru99.com");	
		
	}
	
	@Test
	public void TC_02_Html5(){
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		By firstName = By.id("user_first_name");
		By email = By.id("user_email");
		By createButton = By.xpath("//button[contains(text(),'Tạo tài khoản mới')]");
		
		clickToElementByJS1(createButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessageBy(firstName), "Please fill out this field.");
		
		driver.findElement(firstName).sendKeys("Automation");
		clickToElementByJS1(createButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessageBy(email), "Please fill out this field.");
		
		driver.findElement(email).sendKeys("1234@567@567");
		clickToElementByJS1(createButton);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessageBy(email), "A part following '@' should not contain the symbol '@'.");
		
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(emailAddress);
		clickToElementByJS1(createButton);
		sleepInSecond(3);
		//Assert.assertEquals(getElementValidationMessageBy(email), "Please fill out this fiels.");
		
		
	}
	
	@Test
	public void TC_03_Register() {
		driver.get("http://demo.guru99.com/v4");
		By username = By.name("uid");
		By pass =  By.name("password");
		By buttonlogin = By.name("btnLogin");
		
		driver.findElement(username).sendKeys("mngr397806");
		driver.findElement(pass).sendKeys("Abuteda");
		driver.findElement(buttonlogin).click();

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInSecond(3);
		
		String nameCustomer = "Thanh";
		String dob = "01/01/1992";
		String adress = "Thanh Trì";
		String city = "Ha Noi";
		String state = "Thanh Tri";
		String pin = "123456";
		String mobileNumber = "0568794545";
		String email = "nguyenthithanh555@gmail.com";
		String password = "13456a@";
		
		By nametextbox = By.xpath("//input[@name='name']");
		By gender = By.xpath("//input[@name='rad1' and @value= 'm']");
		By dobtextbox = By.xpath("//input[@id='dob']");
		By addresstextbox = By.xpath("//textarea[@name='addr']");
		By citytextbox = By.xpath("//input[@name='city']");
		By statetextbox = By.xpath("//input[@name='state']");
		By pintextbox = By.xpath("//input[@name='pinno']");
		By phonetextbox = By.xpath("//input[@name='telephoneno']");
		By emailtextbox = By.xpath("//input[@name='emailid']");
		By passwordtextbox = By.xpath("//input[@name='password']");
		
		driver.findElement(nametextbox).sendKeys(nameCustomer);
		driver.findElement(gender).click();
		removeAttributeInDOM("//input[@name='dob']","type");
		driver.findElement(dobtextbox).sendKeys(dob);
		driver.findElement(addresstextbox).sendKeys(adress);
		driver.findElement(citytextbox).sendKeys(city);
		driver.findElement(statetextbox).sendKeys(state);
		driver.findElement(pintextbox).sendKeys(pin);
		driver.findElement(phonetextbox).sendKeys(mobileNumber);
		driver.findElement(emailtextbox).sendKeys(emailAddress);
		driver.findElement(passwordtextbox).sendKeys(password);
		
		//click button submit
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), ("Customer Registered Successfully!!!"));
	}


	public void clickToElementByJS1(By bylocator) {
		WebElement element = driver.findElement(bylocator);
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
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}
	
	public String getElementValidationMessageBy(By bylocator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(bylocator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99);
	}
}
