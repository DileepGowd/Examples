package com.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */

public class Base {

	static JavascriptExecutor js;
	static WebDriver driver;
	
	public static WebDriver getDriverForSite(String webSite) {
		System.setProperty("webdriver.chrome.driver", "/home/purpletalk/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(webSite);
		driver.manage().window().maximize();
		return driver;
	}
	
	/*@BeforeSuite
	public void createObjects() {
		js = (JavascriptExecutor) driver;
	}*/
	
}
