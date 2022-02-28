package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea_Exercies {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test
	public void TC_01_Add_Customer() {
		driver.get("http://demo.guru99.com/v4");
		
		//Login
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr389336");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("AqupuhE");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		//verify Homepage
		String urlhomepage = driver.getCurrentUrl();
		Assert.assertEquals(urlhomepage, "https://demo.guru99.com/v4/manager/Managerhomepage.php");
		
		//click button new customer
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();
		
		String nameCustomer = "Thanh";
		String dob = "01/01/1992";
		String adress = "Thanh Trì\n Hà Nội";
		String city = "Ha Noi";
		String state = "Thanh Tri";
		String pin = "123456";
		String mobileNumber = "0568794545";
		String email = "nguyenthithanh555@gmail.com";
		String password = "13456a@";
		
		By nametextbox = By.xpath("//input[@name='name']");
		By gender = By.xpath("//input[@name='rad1' and @value= 'm']");
		By dobtextbox = By.xpath("//input[@id='dob']");
		By citytextbox = By.xpath("//input[@name='city']");
		By statetextbox = By.xpath("//input[@name='state']");
		By pintextbox = By.xpath("//input[@name='pinno']");
		By phonetextbox = By.xpath("//input[@name='telephoneno']");
		By emailtextbox = By.xpath("//input[@name='emailid']");
		By passwordtextbox = By.xpath("//input[@name='password']");
		
		driver.findElement(nametextbox).sendKeys(nameCustomer);
		driver.findElement(gender).click();
		driver.findElement(dobtextbox).sendKeys(dob);
		driver.findElement(citytextbox).sendKeys(city);
		driver.findElement(statetextbox).sendKeys(state);
		driver.findElement(pintextbox).sendKeys(pin);
		driver.findElement(phonetextbox).sendKeys(mobileNumber);
		driver.findElement(emailtextbox).sendKeys(email);
		driver.findElement(passwordtextbox).sendKeys(password);
		
		//click button submit
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		String customeridget = driver.findElement(By.xpath("//table[@id='customer']//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customeridget);
		
		//verify data
		By customeridby = By.xpath("//table[@id='customer']//td[text()='Customer ID']/following-sibling::td");
		By customernameby = By.xpath("//table[@id='customer']//td[text()='Customer Name']/following-sibling::td");
		By dobby = By.xpath("//table[@id='customer']//td[text()='Birthdate']/following-sibling::td");
		By addressby = By.xpath("//table[@id='customer']//td[text()='Address']/following-sibling::td");
		By cityby = By.xpath("//table[@id='customer']//td[text()='City']/following-sibling::td");
		By stateby = By.xpath("//table[@id='customer']//td[text()='State']/following-sibling::td");
		By pinby = By.xpath("//table[@id='customer']//td[text()='Pin']/following-sibling::td");
		By phoneby = By.xpath("//table[@id='customer']//td[text()='Mobile No.']/following-sibling::td");
		By emailby = By.xpath("//table[@id='customer']//td[text()='Email']/following-sibling::td");
		
		Assert.assertEquals(driver.findElement(customernameby).getText(), nameCustomer);
		Assert.assertEquals(driver.findElement(dobby).getText(), dob);
		Assert.assertEquals(driver.findElement(addressby).getText(), adress);
		Assert.assertEquals(driver.findElement(cityby).getText(), city);
		Assert.assertEquals(driver.findElement(stateby).getText(), state);
		Assert.assertEquals(driver.findElement(pinby).getText(), pin);
		Assert.assertEquals(driver.findElement(phoneby).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(emailby).getText(), email);
		
		//click button edit customer
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customeridget);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
			
	}

	@Test
	public void TC_02_Add_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// Login
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		// Open add employee
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");

		// Add employee
		By firstNametextbox = By.id("firstName");
		By lastNametextbox = By.id("lastName");

		String firstName, lastName, editfirstName, editlastName;
		firstName = "Nguyen";
		lastName = "Thanh" ;
		editfirstName = "Nguyen1";
		editlastName = "Thanh1";

		driver.findElement(firstNametextbox).sendKeys(firstName);
		driver.findElement(lastNametextbox).sendKeys(lastName);
		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		System.out.println(employeeID);

		// Click button save
		driver.findElement(By.id("btnSave")).click();

		// Verify data
		By firstNametextboxby = By.id("personal_txtEmpFirstName");
		By lastNametextboxby = By.id("personal_txtEmpLastName");
		By employeeidby = By.id("personal_txtEmployeeId");
		
		//verify firstname, lastname, employeeid is disable
		Assert.assertFalse(driver.findElement(firstNametextboxby).isEnabled());
		Assert.assertFalse(driver.findElement(lastNametextboxby).isEnabled());		
		Assert.assertFalse(driver.findElement(employeeidby).isEnabled());
		
		//verify data firstname, lastname, employeeid correct with form add employee
		Assert.assertEquals(driver.findElement(firstNametextboxby).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(lastNametextboxby).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(employeeidby).getAttribute("value"),employeeID);
		
		//click button edit
		driver.findElement(By.id("btnSave")).click();
		
		//verify firstname, lastname, employeeid is enable
		Assert.assertTrue(driver.findElement(firstNametextboxby).isEnabled());
		Assert.assertTrue(driver.findElement(lastNametextboxby).isEnabled());		
		Assert.assertTrue(driver.findElement(employeeidby).isEnabled());
		
		//clear data
		driver.findElement(firstNametextboxby).clear();
		driver.findElement(lastNametextboxby).clear();
		
		//Edit data
		driver.findElement(firstNametextboxby).sendKeys(editfirstName);
		driver.findElement(lastNametextboxby).sendKeys(editlastName);
		
		//click button save
		driver.findElement(By.id("btnSave")).click();
		
		//verify firstname, lastname, employeeid is disable
		Assert.assertFalse(driver.findElement(firstNametextboxby).isEnabled());
		Assert.assertFalse(driver.findElement(lastNametextboxby).isEnabled());		
		Assert.assertFalse(driver.findElement(employeeidby).isEnabled());
		
		//verify data firstname, lastname, employeeid correct with form add employee
		Assert.assertEquals(driver.findElement(firstNametextboxby).getAttribute("value"),editfirstName);
		Assert.assertEquals(driver.findElement(lastNametextboxby).getAttribute("value"),editlastName);
		
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
