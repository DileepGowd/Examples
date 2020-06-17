package com.automation.website;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.automation.Base;

public class AmazonWindowHandle {

	private WebDriver driver;

	@Before
	public void goToSite() {
		driver = Base.getDriverForSite("https://www.amazon.in");
	}

	@Test
	public void searchProduct() {

		WebElement category = driver.findElement(By.id("searchDropdownBox"));

		category.click();
		
		Select elements = new Select(category);

		Actions action = new Actions(driver);
		
		List<WebElement> list = elements.getOptions();

		for (WebElement ele : list) {
			String electronics = ele.getText();
			if (electronics.equals("Electronics")) {
				action.moveToElement(ele);
				action.click();
			}
		}

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nokia 9");

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Nokia 9 PureView')]")).click();

		String parentWindow = driver.getWindowHandle();

		Set<String> childWindows = driver.getWindowHandles();

		for (String child : childWindows) {
			if (!child.equals(parentWindow)) {
				driver.switchTo().window(child);
			}
		}
		
		driver.findElement(By.id("wishListMainButton-announce")).click();

	}

}
