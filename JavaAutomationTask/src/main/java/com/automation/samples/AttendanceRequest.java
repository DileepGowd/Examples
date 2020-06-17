package com.automation.samples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AttendanceRequest {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
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
			System.out.println("File dosen't exists  "+e.getMessage());
			username = "@xcubelabs.com"; // we can pass directly to darwinbox credentials..
			password = "xxx"; 
		}
		
		driver.findElement(By.id("UserLogin_username")).sendKeys(username);
		Thread.sleep(2000);
		
		driver.findElement(By.id("UserLogin_password")).sendKeys(password);
		Thread.sleep(2000);
		
		driver.findElement(By.id("login-submit")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("/html/body/div[2]/div/nav/div[1]/div/div[8]/div")).click();
		
		// Getting the yesterday date to get Id of attendance request button.
		Date dt = new Date();
		dt.setTime(dt.getTime()-24*3*60*60*1000);
		String date = dt.getTime()+"";
		String yesterDay = date.substring(0, 5);
		System.out.println(yesterDay);
		
		//clicking on attendance request button of yesterday.
		driver.findElement(By.xpath("//*[starts-with(@shift_date, '"+yesterDay+"')]")).click();
		
		driver.findElement(By.id("AttendanceRequestForm_message")).sendKeys("Please update the attendance.");
		
		//driver.findElement(By.xpath("//*[@id=\"add_request_btn\"]")).click();
		
		
	}
	
}