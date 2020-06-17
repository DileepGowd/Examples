package com.automation.samples;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.automation.Util.ReadExcel;

public class ValidateWebTable {

	static WebDriver driver;
	
	@Test
	public void validateWebTable() {
		System.setProperty("webdriver.chrome.driver", "/home/purpletalk/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://purpletalk.darwinbox.in");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);
		FileInputStream file = null;
		try {
			file = new FileInputStream("data.xlsx");
		} catch (FileNotFoundException e) {
			System.out.println("File not exist..");
			e.printStackTrace();
		}
		XSSFWorkbook wb = null;
		Map<String, String> map = ReadExcel.readExcelFile();
		String username = map.get("username");
		String password = map.get("password");
		XSSFSheet sheet2 = null;
		try {
			wb = new XSSFWorkbook(file);
			sheet2 = wb.getSheetAt(1);
		} catch (IOException e) {
			System.out.println("File dosen't exists  " + e.getMessage());
		}

		String date = sheet2.getRow(1).getCell(0).getStringCellValue();
		String status = sheet2.getRow(1).getCell(1).getStringCellValue();

		driver.findElement(By.id("UserLogin_username")).sendKeys(username);
		
		driver.findElement(By.id("UserLogin_password")).sendKeys(password);
		
		driver.findElement(By.id("login-submit")).click();

		driver.findElement(By.xpath("/html/body/div[2]/div/nav/div[1]/div/div[8]/div")).click();

		List<WebElement> thead = driver.findElements(By.xpath("/html/body/div[11]/table/thead/tr/th"));

		List<WebElement> tableRow = driver.findElements(By.xpath("//*[@id=\"attendance_log\"]/tbody/tr"));

		for (int i = 1; i <= tableRow.size(); i++) {
			for (int j = 1; j <= thead.size(); j++) {

				WebElement element = driver
						.findElement(By.xpath("//*[@id=\"attendance_log\"]/tbody/tr[" + i + "]/td[" + j + "]"));
				if (date.equals(element.getText())) {
					int value = j+5;
					WebElement statusElement = driver
							.findElement(By.xpath("//*[@id=\"attendance_log\"]/tbody/tr[" + i + "]/td[" + value + "]"));
					System.out.println(status + " <-> " + statusElement.getText());
					assertEquals(status, statusElement.getText());
				}
			}
		}
	}
	
}
