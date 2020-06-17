package com.automation.samples;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.automation.Util.ReadExcel;

public class ScrollToFindElement {

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

		WebElement element = driver.findElement(By.xpath("//*[@id=\"5eb02a9d06a95\"]/span/img"));

		int headrVerticalSize = 0;

		Actions actions = new Actions(driver);

		actions.moveToElement(element, 0, -1 * headrVerticalSize).perform();

	}

}
