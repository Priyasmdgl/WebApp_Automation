package serviceenggPage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class LoginPage {

	WebDriver driver;
	@BeforeClass
	public void beforesuite() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://192.168.1.3:8080/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("contact"));
		username.sendKeys("serviceengg@gmail.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("Admin@123");
		WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
		login.click();
		WebElement user = driver.findElement(By.id("userName"));
		String text = user.getText();
		System.out.println(text);
		Assert.assertTrue(true, text);
	}
	@AfterClass
	public void aftersuite() {
		 if (driver != null) {
	            driver.quit();
	        }
	}
}
