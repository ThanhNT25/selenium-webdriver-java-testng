package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicWait;
	Actions action;
	Select select;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// khoi tao sau khi driver nay duoc sinh ra
		// JavascriptExecutor/
		jsExecutor = (JavascriptExecutor) driver;
		explicWait = new WebDriverWait(driver,30);
		action = new Actions(driver);
				

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01_Dropdown_List1() {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");
		driver.findElement(By.xpath("//input[@name='search_loc_submit']")).click();
		sleepInSecond(5);
		
		//32 result
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(),"33");
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		
		// verify tong so luong store name
		Assert.assertEquals(storeName.size(), 33);
		
		for(WebElement store : storeName) {
			System.out.println(store.getText());
		}
		
			
	}

	@Test
	public void TC_02_Dropdown_List2() {
		String firstName = "Nguyen";
		String lastName = "Thanh";
		String email = "nguyenthithanh0894+" +getRandomNumber()+ "@gmail.com";
		String date = "12";
		String month = "October";
		String year = "1994";
		String passWord = "123456";
		
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(date);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(passWord);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(passWord);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		//verify 
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
			
	}
	
	
	@Test
	public void TC_03_JQuery () {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "//ul[@id='number-menu']//div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "5");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");
		
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "//ul[@id='number-menu']//div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "3");
		
	}
	
	@Test
	public void TC_04_ReactJS () {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdownList("i.dropdown", "//div[@class='visible menu transition']//span[@class='text']", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']/div[@class='divider text']")).getText(), "Elliot Fu");
		
		selectItemInCustomDropdownList("i.dropdown", "//div[@class='visible menu transition']//span[@class='text']", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']/div[@class='divider text']")).getText(), "Justen Kitsune");
		
	}
	
	@Test
	public void TC_05_VueJS () {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "//ul[@class='dropdown-menu']//a", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "//ul[@class='dropdown-menu']//a", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
		
	}
	
	@Test
	public void TC_06_Angular () {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
	
		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "//div[@role='option']//span[@class='ng-option-label ng-star-inserted']", "Thành phố Hà Nội");
		//1.text không nằm trong HTML
//		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
//		Assert.assertEquals(actualText,"Thành phố Hà Nội");
		
		//2.
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(),"Thành phố Hà Nội");
		
		//3.
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("innerText"),"Thành phố Hà Nội");
		
		
		selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "//div[@role='option']//span[@class='ng-option-label ng-star-inserted']", "Huyện Thanh Trì");

		selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "//div[@role='option']//span[@class='ng-option-label ng-star-inserted']", "Xã Tam Hiệp");

		
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		
		selectItemInCustomDropdownList("li.dropdown-toggle", "//ul[@class='dropdown-menu']//a", "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
		
	}
	
	@Test
	public void TC_07_Editable () {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		enterItemInCustomDropdownList("//div[@id='default-place']/input", "//div[@id='default-place']//ul[@class='es-list']/li", "Smart");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']/input")).getAttribute("value"), "Smart");
		
		
	}
		
	public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
			//Stp 1 click vao 1 element cho no xo het ra cac item
		    explicWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentLocator)));
			driver.findElement(By.cssSelector(parentLocator)).click();
			
			//lay đến thẻ input để sendkey
			//driver.findElement(By.cssSelector(parentLocator)).sendKeys();
			sleepInSecond(2);
			
			//cho cac item load he ra thanh cong
			//locator chua het cac item
			//locator phai den node cuoi cung chua text
			explicWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
			
			//tim item can chon
			//lay tat ca cac elemnet sau đó duyệt qua từng item, nếu text bằng text mình mong muốn thì click vào
			List<WebElement> allItems =driver.findElements(By.xpath(childLocator));
			
			//duyệt qua từng item
			for(WebElement item : allItems) {
				String actualText = item.getText();
				System.out.println("Actual text = "+actualText);
				if(actualText.equals(expectedTextItem)) {
					//Nếu item nằm trong vung k nhìn thấy cần scroll
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(2);
					// click vào item cần tìm
					item.click();
					// thoát khỏi vòng lặp không tìm element nữa
					break;
					}
			}		
		
	}

	
	public void enterItemInCustomDropdownList(String parentXpath, String childXpath, String expectedTextItem) {
		driver.findElement(By.xpath(parentXpath)).clear();
		//Stp 1 click vao 1 element cho no xo het ra cac item		
		//lay đến thẻ input để sendkey
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedTextItem);
		sleepInSecond(2);
		
		//cho cac item load he ra thanh cong
		explicWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//tim item can chon
		//lay tat ca cac elemnet sau đó duyệt qua từng item, nếu text bằng text mình mong muốn thì click vào
		List<WebElement> allItems =driver.findElements(By.xpath(childXpath));
		
		//duyệt qua từng item
		for(WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual text = "+actualText);
			if(actualText.equals(expectedTextItem)) {
				//Nếu item nằm trong vung k nhìn thấy cần scroll
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				// click vào item cần tìm
				jsExecutor.executeScript("arguments[0].click();",item);
				// thoát khỏi vòng lặp không tìm element nữa
				break;
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
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99);
	}

	}
