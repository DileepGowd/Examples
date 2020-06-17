package com.automation.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.Base;

public class FlipkartSiteTest {

	WebDriver driver;

	@BeforeTest
	public void openWebSite() {
		driver = Base.getDriverForSite("https://www.flipkart.com");
	}

	@AfterTest
	public void closeSite() {
		driver.close();
	}

	@Test
	public void searchProduct() {
		try {
			driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		} catch (Exception enfe) {
			System.out.println("popup element not found : " + enfe.getMessage());
		}
		driver.findElement(By.xpath("//input[@type='text' and @title='Search for products, brands and more']"))
				.sendKeys("coronavirus mask");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		List<WebElement> list = driver.findElements(By.cssSelector("div > a._1Vfi6u > div > div._1vC4OE"));

		int quantity = list.size();

		int camaValues = 0;

		System.out.println("coronavirus mask total products -> " + quantity);
		Reporter.log("coronavirus mask total products -> " + quantity);

		int priceForAllProducts = 0;

		for (WebElement element : list) {
			String priceStr = element.getText().substring(1);
			if (priceStr.contains(",")) {
				camaValues++;
			} else {
				System.out.println("Price for one Product - > " + priceStr);
				int price = Integer.parseInt(priceStr);
				priceForAllProducts += price;
			}
		}

		if (camaValues > 0)
			quantity = quantity - camaValues;

		int average = priceForAllProducts / quantity;

		System.out.println("Average price : " + average);
		Reporter.log("Average price of flipkart : " + average);

		Assert.assertEquals(average, 450);
	}

}
