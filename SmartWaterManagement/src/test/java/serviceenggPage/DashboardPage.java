package serviceenggPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardPage extends LoginPage {

    @Test(enabled=false)
	public void viewallcontroller() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement viewall = driver.findElement(By.id("toggle-button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", viewall);
		viewall.click();
		List<WebElement> grid = driver.findElements(By.id("district-card-grid"));
		for (WebElement webElement : grid) {
			if(webElement.isDisplayed()) {
				String districts = webElement.getText();
				System.out.println(districts);
				if (districts.contains("Chennai")) {
	                boolean isChennaiPresent = true;
	                System.out.println("Chennai is present");
	                break; // Exit loop once Chennai is found
	            }else {
	            	System.out.println("Not present");
	            }
			}
		}
		
}
    @Test(enabled=false)
    public void exceldownload() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement excel = driver.findElement(By.xpath("//*[@id=\"export-to-excel\"]/i"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", excel);
    	excel.click();
    	File location = new File("C:\\Users\\HP\\Downloads");
    	File[] allfiles= location.listFiles();
    	for (File file : allfiles) {
    		if(file.getName().equals("Unconfirmed 189693.crdownload")) {
    			System.out.println("Excel File is downloaded");
    			break;
    		}else {
    			System.out.println("File not downloaded");
    		}
    }
}
    @Test(enabled=false)
    public void pdfdownload() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement pdf = driver.findElement(By.xpath("//*[@id=\"export-to-pdf\"]/i"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", pdf);
    	pdf.click();
    	File locations = new File("C:\\Users\\HP\\Downloads");
    	File[] allfile = locations.listFiles();
    	for (File file : allfile) {
			if(file.getName().equals("ohtData.pdf.crdownload")) {
				System.out.println("Pdf File is downloaded");
			}else {
				System.out.println("Not downloaded");
				break;
			}
		}
    }
    @Test(enabled=false)
    public void search() {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement search = driver.findElement(By.xpath("//*[@id=\"commonTable_filter\"]/label/input"));
    	search.sendKeys("Dindigul");
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("commonTable")));
    	WebElement table = driver.findElement(By.id("commonTable"));
    	 List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));// Locate all rows in the table body
          for (WebElement row : rows) {
              List<WebElement> columns = row.findElements(By.tagName("td")); // Get all columns (cells) in the row
              
              for (WebElement column : columns) {
                  String columnText = column.getText();
                  System.out.print(columnText + "\t"); // Print each column value
              }
              System.out.println(); // Move to the next line after each row
          }
          for (WebElement row : rows) {
              if (row.getText().contains("Dindigul")) {
                  boolean isDistrict_NamePresent = true;
                  System.out.println("District is present");
                  break;
              }else {
            	  System.out.println("Not present");
              }
          }
    }
    @Test
    public void filter() throws IOException {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement filter = driver.findElement(By.xpath("//button[@class='filter-icon fa fa-filter']"));
    	filter.click();
    	WebElement district = driver.findElement(By.id("districtDropdown1"));
    	Select selectdistrict = new Select(district);
    	selectdistrict.selectByVisibleText("Chennai");
    	WebElement block = driver.findElement(By.id("blockDropdown1"));
    	Select selectblock = new Select(block);
    	selectblock.selectByVisibleText("Ambattur");
    	WebElement panchayat = driver.findElement(By.id("panchayatDropdown1"));
    	Select selectpanchayat = new Select(panchayat);
    	selectpanchayat.selectByVisibleText("Vellacheri");
    	WebElement habitation = driver.findElement(By.id("habitationDropdown1"));
    	Select selecthabitation = new Select(habitation);
    	selecthabitation.selectByVisibleText("Vellacheri");
    	WebElement apply = driver.findElement(By.id("submitButton-1"));
    	apply.click();
    	WebElement result = driver.findElement(By.xpath("//div[@class='col-md-8 line-chart-column']"));
    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", result);
	    js.executeScript("window.scrollBy(0, 500);");
    	TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourcefile= screenshot.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File("C:\\Users\\HP\\Downloads\\Average_water_level.png");
		FileUtils.copyFile(sourcefile, destinationfile);
    }
}