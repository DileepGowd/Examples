package com.automation.website;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.Base;

public class NaukriWebSite {

	private WebDriver driver;

	@Before
	public void login() {
		driver = Base.getDriverForSite("https://www.naukri.com");
	}

	// Shifting from one window to another window
	@Test
	public void searchJob() {

		driver.findElement(By.id("qsb-keyword-sugg")).sendKeys("java Developer");

		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();

		String mainWeb = driver.getWindowHandle();

		List<WebElement> jobs = driver.findElements(By.xpath("//article/div/div/a[contains(text(),'Java')]"));
		
		jobs.get(0).click();

		Set<String> allWindows = driver.getWindowHandles();
		
		for (String child : allWindows) {
			if (!mainWeb.equals(child)) {
				driver.switchTo().window(child);
				System.out.println(driver.switchTo().window(child).getTitle());
			}
		}
		
		driver.findElement(By.id("logToApp")).click();
		
	}

}
