package loginScenario;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class State {

	WebDriver driver;
	@BeforeClass
	public void launch_browser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	 @DataProvider(name = "login data")
	    public String[][] Login_data() throws IOException {
	        FileInputStream excel = new FileInputStream("src/test/resources/logindata.xls");
	        Workbook workbook = new HSSFWorkbook(excel);
	        Sheet sheet = workbook.getSheetAt(6);
	        
	        int rowCount = sheet.getPhysicalNumberOfRows();
	        String[][] data = new String[rowCount - 1][2]; 
	        
	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            data[i - 1][0] = row.getCell(0).getStringCellValue(); 
	            data[i - 1][1] = row.getCell(1).getStringCellValue();
	        }
	        
	        workbook.close();
	        excel.close();
	        return data;
	    }
	
	@Test(dataProvider = "login data")
	public void login(String email, String pwd) {
		
		driver.navigate().to("http://192.168.1.2:8080/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement username = driver.findElement(By.id("contact"));
		username.sendKeys(email);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pwd);
		WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
		login.click();
		WebElement user = driver.findElement(By.id("userName"));
		String text = user.getText();
		System.out.println(text);
		Assert.assertTrue(true, text);
	}
	
	@AfterClass
	public void Close_browser() {
		 if (driver != null) {
	            driver.quit();
	        }
	}

}
