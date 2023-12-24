package magento;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class magentoTestCases {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String[] firstNames = { "sara", "Ahmad", "Lolo", "Soso" };
	String[] lastNames = { "Sameer", "Reda", "Abood", "salem", "dani" };

	int randFirst = rand.nextInt(firstNames.length);
	int randLast = rand.nextInt(lastNames.length);
	int randEmailID = rand.nextInt(9999);
	String firstUserName = firstNames[randFirst];
	String lastUserName = lastNames[randLast];
	String email = firstUserName + lastUserName + randEmailID + "@gmail.com";
	String logOut="https://magento.softwaretestingboard.com/customer/account/logout/";
	String logIn="https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";

	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.get("https://magento.softwaretestingboard.com/");

	}

	@Test(priority = 1)
	public void signUp() throws InterruptedException {

		driver.findElement(By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstUserName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastUserName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("dalia1@@@");
		driver.findElement(By.xpath("//input[@id='password-confirmation']")).sendKeys("dalia1@@@");
		driver.findElement(By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")).click();
		Thread.sleep(2000);
		WebElement successMsg =driver.findElement(By.className("message-success"));
		Assert.assertEquals(successMsg.getText(), "Thank you for registering with Main Website Store.");
		

	}
	
	@Test(priority = 2)
	public void logOut() {
		driver.get(logOut);
		WebElement outMsg=driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
		String logOutMsg= outMsg.getText();
		SoftAssert softassert= new SoftAssert();
		softassert.assertEquals(logOutMsg.contains("signed out"), true);
		softassert.assertEquals(logOutMsg, "You are signed out");
		softassert.assertAll();
		
		
	}

	@Test(priority = 3, enabled = false)
	public void signIn() throws InterruptedException {
		
		driver.get(logIn);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys("dalia1@@@");
		driver.findElement(By.id("send2")).click();
		WebElement welcomeMsg= driver.findElement(By.xpath("(//ul[@class='header links'])[1]"));
		Assert.assertEquals(welcomeMsg.getText().contains("Welcome"), true, "this is to check the welocome msg");

	}

	@AfterTest
	public void after() {
	}

}
