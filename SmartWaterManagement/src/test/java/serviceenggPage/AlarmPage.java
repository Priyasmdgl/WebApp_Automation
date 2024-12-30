package serviceenggPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AlarmPage extends LoginPage{

	@Test
	public void alarmpage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://192.168.1.3:8080/alarms");
		WebElement table = driver.findElement(By.id("commonTable"));
		List<WebElement> heading = table.findElements(By.cssSelector("thead tr"));
		for (WebElement webElement : heading) {
			String title = webElement.getText();
			System.out.println(title + "\t");
		}
		List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
		for (WebElement webElement : rows) {
			List<WebElement> col = webElement.findElements(By.tagName("td"));
			for (WebElement webElement2 : rows) {
				String result = webElement2.getText();
				System.out.println(result + "\t");
			}
		}
	}
	@Test
	public void search() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Annur");
		WebElement table = driver.findElement(By.id("commonTable"));
		List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
		for (WebElement webElement : rows) {
			List<WebElement> col = webElement.findElements(By.tagName("td"));
			for (WebElement webElement2 : rows) {
				if(webElement2.getText().contains("Annur")) {
					System.out.println("Present");
				}else {
					System.out.println("Not present");
				}
				
			}
	}
}
	}
