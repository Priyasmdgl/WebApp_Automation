package serviceenggPage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportPage extends LoginPage{

	@Test
	public void reports() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://192.168.1.3:8080/reports");
		WebElement title = driver.findElement(By.xpath("//h4[text()='Location Filter']"));
		Assert.assertTrue(title.isDisplayed());
		String text = title.getText();
		System.out.println(text);
		Assert.assertEquals(text, "Location Filter");
	}
	@Test
	public void water_report() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement district = driver.findElement(By.id("districtDropdown"));
		Select selectdistrict = new Select(district);
		selectdistrict.selectByVisibleText("Chennai");
		WebElement block = driver.findElement(By.id("blockDropdown"));
		Select selectblock = new Select(block);
		selectblock.selectByVisibleText("Ambattur");
		WebElement panchayat = driver.findElement(By.id("panchayatDropdown"));
		Select selectpanchayat = new Select(panchayat);
		selectpanchayat.selectByVisibleText("Vellacheri");
		WebElement habitation = driver.findElement(By.id("habitationDropdown"));
		Select selecthabitation = new Select(habitation);
		selecthabitation.selectByVisibleText("Vellacheri");
		Thread.sleep(1000);
		WebElement fromDate = driver.findElement(By.id("fromDate"));
		fromDate.sendKeys("15-12-2024");
		String fromDateValue = fromDate.getAttribute("value");
		System.out.println("From Date Value: " + fromDateValue);
		if (fromDateValue.isEmpty()) {
		    System.out.println("No date selected for From Date.");
		} else {
		    System.out.println("The From Date is: " + fromDateValue);
		}
		 WebElement todate = driver.findElement(By.id("toDate")); 
		 todate.sendKeys("29-12-2024");
		 Alert alert = driver.switchTo().alert();
		 Thread.sleep(5000);
		 alert.accept();
		 todate.sendKeys("2024-12-20");
		 String toDateValue = todate.getAttribute("value");
		 System.out.println("to Date Value: " + toDateValue);
		 if (toDateValue.isEmpty()) {
			    System.out.println("No date selected for to Date.");
			} else {
			    System.out.println("The to Date is: " + toDateValue);
		 if(toDateValue.compareTo(fromDateValue) < 0) {
		  System.out.println("To Date cannot be earlier than From Date."); 
		  }
		  WebElement reporttype = driver.findElement(By.id("motorType")); 
		  Select selectreport = new Select(reporttype);
		 selectreport.selectByVisibleText("Average Water Level Report"); 
		 WebElement search = driver.findElement(By.xpath("//button[@class='btn-search']"));
		 search.click();	
	}
}
}
