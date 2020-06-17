package com.automation.samples;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.automation.Util.ReadExcel;

public class ScrollToEndOfPage {

		static WebDriver driver;

		public static void main(String[] args) {
			System.setProperty("webdriver.chrome.driver", "/home/purpletalk/Downloads/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.get("https://purpletalk.darwinbox.in");
			driver.manage().window().maximize();
			Map<String, String> map = ReadExcel.readExcelFile();
			String username = map.get("username");
			String password = map.get("password");

			driver.findElement(By.id("UserLogin_username")).sendKeys(username);
			
			driver.findElement(By.id("UserLogin_password")).sendKeys(password);
			
			driver.findElement(By.id("login-submit")).click();

			driver.findElement(By.xpath("/html/body/div[2]/div/nav/div[1]/div/div[8]/div")).click();
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scrollBy(0,document.body.scrollHeight)", "");
			
		}
	
}
