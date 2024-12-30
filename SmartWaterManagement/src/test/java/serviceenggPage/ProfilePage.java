package serviceenggPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfilePage extends LoginPage {
@Test
	public void profile() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement profileicon = driver.findElement(By.id("profileBtn"));
		profileicon.click();
		WebElement userprofile = driver.findElement(By.id("profileLink"));
		userprofile.click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		Thread.sleep(1000);
		WebElement user = driver.findElement(By.xpath("/html/body/div[2]/main/div/div[1]/h2"));
		String name = user.getText();
		System.out.println(name);
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\HP\\Downloads\\ProfilePage.png");
	    FileUtils.copyFile(source, destination);
	    WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
	    submit.click();
	    WebElement popup = driver.findElement(By.className("popup-message"));
	    String text = popup.getText();
	    System.out.println(text);
	    Assert.assertEquals(text, "User Profile Updated Successfully");
				
	}
}
