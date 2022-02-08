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



public class Topic_04_Xpath_Css_Exercies_Register {
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
		//driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Registerwithemtydata() {
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// Click button DANG KY
		driver.findElement(By.xpath("//div[@class='field_btn']/button[@class='btn_pink_sm fs16' and @type='submit']")).click();
		// Verify error message
		String txtfirsnameerror = driver.findElement(By.cssSelector("label#txtFirstname-error")).getText();
		Assert.assertEquals(txtfirsnameerror, "Vui lòng nhập họ tên");
		
		String txtemailerror = driver.findElement(By.cssSelector("label#txtEmail-error")).getText();
		Assert.assertEquals(txtemailerror, "Vui lòng nhập email");
		
		String txtcemailerror = driver.findElement(By.cssSelector("label#txtCEmail-error")).getText();
		Assert.assertEquals(txtcemailerror, "Vui lòng nhập lại địa chỉ email");
		
		String txtpasswderror = driver.findElement(By.cssSelector("label#txtPassword-error")).getText();
		Assert.assertEquals(txtpasswderror, "Vui lòng nhập mật khẩu");
		
		String txtcpasswderror = driver.findElement(By.cssSelector("label#txtCPassword-error")).getText();
		Assert.assertEquals(txtcpasswderror, "Vui lòng nhập lại mật khẩu");
		
		String txtphoneerror = driver.findElement(By.cssSelector("label#txtPhone-error")).getText();
		Assert.assertEquals(txtphoneerror, "Vui lòng nhập số điện thoại.");
		
	}

	
	
	@Test
	public void TC_02_Register_invalid_email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Fristname input data valid 
		driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyen Thanh");
		// input email data valid 
		driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("123@456@789");
		// input Cemail data valid 
		driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("123@456@789");
		// input password data valid 
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("12345678Aa@");
		// input Cpassword data valid 
		driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("12345678Aa@");
		// input phone data valid 
		driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0346780987");
		// Click button DANG KY
		driver.findElement(By.xpath("//div[@class='field_btn']/button[@class='btn_pink_sm fs16' and @type='submit']")).click();
		//verify error mgs
		String txtemailerror = driver.findElement(By.cssSelector("label#txtEmail-error")).getText();
		Assert.assertEquals(txtemailerror, "Vui lòng nhập email hợp lệ");
		//verify error mgs
		String txtcemailerror = driver.findElement(By.cssSelector("label#txtCEmail-error")).getText();
		Assert.assertEquals(txtcemailerror, "Email nhập lại không đúng");
	
	}
	
	
	@Test
	public void TC_03_Register_incorrect_confirmemail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Fristname input data valid 
		driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyen Thanh");
		// input email data valid 
		driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input Cemail data valid 
		driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("123@456@789");
		// input password data valid 
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("12345678Aa@");
		// input Cpassword data valid 
		driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("12345678Aa@");
		// input phone data valid 
		driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0346780987");
		// Click button DANG KY
		driver.findElement(By.xpath("//div[@class='field_btn']/button[@class='btn_pink_sm fs16' and @type='submit']")).click();
		//verify error mgs
		String txtcemailerror = driver.findElement(By.cssSelector("label#txtCEmail-error")).getText();
		Assert.assertEquals(txtcemailerror, "Email nhập lại không đúng");
	
	}
	
	@Test
	public void TC_04_Register_password_lessthan_6characters() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Fristname input data valid 
		driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyen Thanh");
		// input email data valid 
		driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input Cemail data valid 
		driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input password data valid 
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123");
		// input Cpassword data valid 
		driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123");
		// input phone data valid 
		driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0346780987");
		// Click button DANG KY
		driver.findElement(By.xpath("//div[@class='field_btn']/button[@class='btn_pink_sm fs16' and @type='submit']")).click();
		//verify error mgs
		String txtpasswderror = driver.findElement(By.cssSelector("label#txtPassword-error")).getText();
		Assert.assertEquals(txtpasswderror, "Mật khẩu phải có ít nhất 6 ký tự");
		
		String txtcpasswderror = driver.findElement(By.cssSelector("label#txtCPassword-error")).getText();
		Assert.assertEquals(txtcpasswderror, "Mật khẩu phải có ít nhất 6 ký tự");
	
	}
	
	@Test
	public void TC_05_Register_confirm_password_incorrect() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Fristname input data valid 
		driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyen Thanh");
		// input email data valid 
		driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input Cemail data valid 
		driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input password data valid 
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
		// input Cpassword data valid 
		driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123457");
		// input phone data valid 
		driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("0346780987");
		// Click button DANG KY
		driver.findElement(By.xpath("//div[@class='field_btn']/button[@class='btn_pink_sm fs16' and @type='submit']")).click();
		//verify error mgs
		String txtcpasswderror = driver.findElement(By.cssSelector("label#txtCPassword-error")).getText();
		Assert.assertEquals(txtcpasswderror, "Mật khẩu bạn nhập không khớp");
	
	}
	
	
	@Test
	public void TC_06_Register_phone_invalid() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Fristname input data valid 
		driver.findElement(By.cssSelector("input#txtFirstname")).sendKeys("Nguyen Thanh");
		// input email data valid 
		driver.findElement(By.cssSelector("input#txtEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input Cemail data valid 
		driver.findElement(By.cssSelector("input#txtCEmail")).sendKeys("nguyenthithanh0894@gmail.com");
		// input password data valid 
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("123456");
		// input Cpassword data valid 
		driver.findElement(By.cssSelector("input#txtCPassword")).sendKeys("123456");
		// input phone data valid 
		driver.findElement(By.cssSelector("input#txtPhone")).sendKeys("03467809");
		// Click button DANG KY
		driver.findElement(By.xpath("//div[@class='field_btn']/button[@class='btn_pink_sm fs16' and @type='submit']")).click();
		//verify error mgs
		String txtphoneerror = driver.findElement(By.cssSelector("label#txtPhone-error")).getText();
		Assert.assertEquals(txtphoneerror, "Số điện thoại phải từ 10-11 số.");
	
	}

//	@Test
//	public void TC_01_ValidateCurrentUrl() {
//		// Login Page Url matching
//		String loginPageUrl = driver.getCurrentUrl();
//		Assert.assertEquals(loginPageUrl, "https://alada.vn/tai-khoan/dang-ky.html");
//	}

//	@Test
//	public void TC_02_ValidatePageTitle() {
//		// Login Page title
//		String loginPageTitle = driver.getTitle();
//		Assert.assertEquals(loginPageTitle, "Facebook – log in or sign up");
//	}
//
//	@Test
//	public void TC_03_LoginFormDisplayed() {
//		// Login form displayed
//		Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
//	}

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
