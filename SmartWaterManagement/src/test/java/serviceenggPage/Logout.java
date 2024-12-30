package serviceenggPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Logout extends LoginPage{
@Test
	public void logout() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement profileicon = driver.findElement(By.id("profileBtn"));
		profileicon.click();
		WebElement signout = driver.findElement(By.id("signOutLink"));
		signout.click();
		WebElement logout = driver.findElement(By.xpath("//h4[text()='You are Logged Out']"));
		String text = logout.getText();
		System.out.println(text);
		Assert.assertEquals(text, "You are Logged Out");
		WebElement signin = driver.findElement(By.xpath("//button[text()='Sign In']"));
		signin.click();
		String url = driver.getCurrentUrl();
		WebElement msg = driver.findElement(By.xpath("//p[text()='Sign in to continue to SWMS']"));
		String login = msg.getText();
		Assert.assertEquals(login, "Sign in to continue to SWMS");
	}
}
