package com.automation.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.Base;
import com.automation.Util.ReadExcel;

public class ModelHandle {

	static WebDriver driver;

	@BeforeClass
	public static void login() {
		driver = Base.getDriverForSite("https://purpletalk.darwinbox.in");

		String username = ReadExcel.readExcelFile().get("username");
		String password = ReadExcel.readExcelFile().get("password");

		driver.findElement(By.id("UserLogin_username")).sendKeys(username);

		driver.findElement(By.id("UserLogin_password")).sendKeys(password);

		driver.findElement(By.id("login-submit")).click();
	}

	@Test
	public void modelHandle() {

		List<WebElement> attendance = driver
				.findElements(By.xpath("//div[@class='single-menu-item']/a/p[contains(text(),'Attendance')]"));

		attendance.get(1).click();

		driver.findElement(By.id("attendance_request")).click();

		driver.switchTo().activeElement();
		
		driver.findElement(By.xpath("//div[@class='modalpop attendance-request']/div/div/input[@class='search']")).click();

		driver.findElement(
				By.xpath("//div[@class='menu transition visible']/div[contains(text(),'Attendance Request')]")).click();

		driver.findElement(By.id("AttendanceRequestForm_message")).sendKeys("Please update the attendance...");

	}

}
