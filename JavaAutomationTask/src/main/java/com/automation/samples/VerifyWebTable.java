package com.automation.samples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyWebTable {

	static WebDriver driver;

	/*public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}*/
	
	public static void main(String[] args) {
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
		String username = "";
		String password = "";
		try {
			wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(0);
			Cell uname = sheet.getRow(1).getCell(0);
			uname.setCellType(Cell.CELL_TYPE_STRING);
			Cell pwd = sheet.getRow(1).getCell(1);
			pwd.setCellType(Cell.CELL_TYPE_STRING);
			username = uname.getStringCellValue();
			password = pwd.getStringCellValue();
		} catch (IOException e) {
			System.out.println("File dosen't exists  " + e.getMessage());
			username = "@xcubelabs.com"; // we can pass directly to darwinbox credentials..
			password = "xxx";
		}

		driver.findElement(By.xpath("//*[@id=\"UserLogin_username\"]")).sendKeys(username);

		driver.findElement(By.xpath("//*[@id=\"UserLogin_password\"]")).sendKeys(password);

		driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

		driver.findElement(By.xpath("/html/body/div[2]/div/nav/div[1]/div/div[8]/div")).click();

		List<WebElement> thead = driver.findElements(By.xpath("/html/body/div[11]/table/thead/tr/th"));

		List<WebElement> tableRow = driver.findElements(By.xpath("//*[@id=\"attendance_log\"]/tbody/tr"));

		System.out.println("Size : " + tableRow.size());

		for (int i = 1; i <= tableRow.size(); i++) {
			System.out.println("Element : " + i + " : ");
			for (int j = 1; j <= thead.size(); j++) {

				WebElement element = driver
						.findElement(By.xpath("//*[@id=\"attendance_log\"]/tbody/tr[" + i + "]/td[" + j + "]"));
				System.out.print(element.getText() + " ");
			}
			System.out.println();
		}
	}

}
