package serviceenggPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LivedataPage extends LoginPage {
	@Test(priority=0)
	public void livedata() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://192.168.1.2:8080/liveData");
		Thread.sleep(1000);
		List<WebElement> status = driver.findElements(By.xpath("//div[@class='status-row']"));
		for (WebElement webElement : status) {
			if(webElement.isDisplayed()) {
				String result = webElement.getText();
				System.out.println(result);
			}
		}
	}

	@Test(priority=1,enabled=false)
	public void filter() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='filter-icon-btn']")));
		WebElement filter = driver.findElement(By.xpath("//button[@class='filter-icon-btn']"));
		filter.click();
		WebElement district = driver.findElement(By.id("districtDropdown"));
		Select selectdistrict = new Select(district);
		selectdistrict.selectByVisibleText("Dindigul");
		WebElement block = driver.findElement(By.id("blockDropdown"));
		Select selectblock = new Select(block);
		selectblock.selectByVisibleText("Dindigul");
		WebElement panchayat = driver.findElement(By.id("panchayatDropdown"));
		Select selectpanchayat = new Select(panchayat);
		selectpanchayat.selectByVisibleText("Chettinayakanpatti");
		WebElement habitation = driver.findElement(By.id("habitationDropdown"));
		Select selecthabitation = new Select(habitation);
		selecthabitation.selectByVisibleText("Chettinayakanpatti");
		WebElement apply = driver.findElement(By.id("submitButton"));
		apply.click();
		Thread.sleep(1000);
		WebElement chart = driver.findElement(By.id("waterLineChart"));
		Assert.assertTrue(chart.isDisplayed());
		if(chart.isDisplayed()) {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			File destination = new File("C:\\Users\\HP\\Downloads\\Livedata_Average_water_level.png");
			FileUtils.copyFile(source, destination);
		}else {
			System.out.println("chart is not displayed");
		}
	}
	@Test
	public void search() {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Chennai");
		WebElement table = driver.findElement(By.id("commonTable"));
		List<WebElement> heading = table.findElements(By.cssSelector("thead tr"));
		for (WebElement webElement : heading) {
			String title = webElement.getText();
			System.out.println(title + "\t");
		}
		List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));
		for (WebElement webElement : rows) {
			List<WebElement> columns = webElement.findElements(By.tagName("td"));
			for (WebElement webElement2 : rows) {
				String result = webElement2.getText();
				System.out.println(result + "\t");
			}
			System.out.println();
		}
		for (WebElement webElement : rows) {
			if(webElement.getText().contains("Chennai")) {
				System.out.println("Present");
			}else {
				System.out.println("Not present");
			}
		}
		
	}
}
