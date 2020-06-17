package com.automation.samples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MouseActions {

	WebDriver driver;

	@BeforeClass
	public void open() {
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2); 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.chrome.driver", "/home/purpletalk/Downloads/chromedriver");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.myntra.com");
		driver.manage().window().maximize();
	}

	@Test(enabled=true)
	public void mouseOver() {
		Actions act = new Actions(driver);

		WebElement menShoping = driver.findElement(By.xpath("//a[@class='desktop-main' and contains(text(),'Men')]"));

		act.moveToElement(menShoping).perform();

		driver.findElement(By.xpath("//a[@href='/men-casual-shoes']")).click();

	}

	@Test(enabled=true)
	public void doubleClick() {
		
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='common-checkboxIndicator']"));
		
		System.out.println("Size : "+elements.size());
		
		new Actions(driver).doubleClick(elements.get(2)).perform();
		
	}
	
	@Test(enabled=false)
	public void rightClick() {
		
		WebElement woodland = driver
				.findElement(By.xpath("//img[@title='LOCOMOTIVE Men White Sneakers']"));

		new Actions(driver).contextClick(woodland).perform();
	}
	
}
