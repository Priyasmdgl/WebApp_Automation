package serviceenggPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NotificationIcon extends LoginPage {
@Test
	public void bellicon() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement bell = driver.findElement(By.className("bell-icon"));
		bell.click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		WebElement title = driver.findElement(By.xpath("//h4[text()='Alarms']"));
		String text = title.getText();
		Assert.assertEquals(text, "Alarms");
	}
}
