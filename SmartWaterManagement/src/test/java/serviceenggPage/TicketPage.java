package serviceenggPage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TicketPage extends LoginPage{
@Test(enabled=false)
	public void ticketstatus() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://192.168.1.3:8080/tickets");
		WebElement status = driver.findElement(By.className("status-row"));
		if(status.isDisplayed()) {
			System.out.println(status.getText());
		}
	}
@Test
public void edit() {
	driver.navigate().to("http://192.168.1.3:8080/tickets");
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	WebElement edit = driver.findElement(By.xpath("//i[@class='fas fa-edit']"));
	edit.click();
	WebElement data = driver.findElement(By.xpath("//div[@class='card-body-read-only']"));
	Assert.assertTrue(data.isDisplayed());
	WebElement ticket = driver.findElement(By.id("ticket_Status"));
	Select select = new Select(ticket);
	select.selectByVisibleText("In-Progress");
	WebElement actiontaken = driver.findElement(By.id("action_taken"));
	actiontaken.clear();
	actiontaken.sendKeys("demo");
	WebElement remarks = driver.findElement(By.id("remarks_follow_up"));
	remarks.clear();
	remarks.sendKeys("demo");
	WebElement submit = driver.findElement(By.id("submitButton"));
	submit.click();
}
}
