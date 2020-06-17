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

public class AmazonSiteTest {

	WebDriver driver;

	@BeforeTest
	public void openWebSite() {
		driver = Base.getDriverForSite("https://www.amazon.in");
	}

	@AfterTest
	public void closeSite() {
		driver.close();
	}
	
	@Test
	public void searchProduct() {
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("coronavirus mask");

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		
		int quantity = list.size();

		int camaValues = 0;

		System.out.println("coronavirus mask total products -> " + quantity);
		Reporter.log("coronavirus mask total products -> " + quantity);

		int priceForAllProducts = 0;

		for (WebElement element : list) {
			String priceStr = element.getText();
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

		System.out.println("Average price of amazon : " + average);
		Reporter.log("Average price of amazon : " + average);

		Assert.assertEquals(average, 450);
		
	}
	
}
