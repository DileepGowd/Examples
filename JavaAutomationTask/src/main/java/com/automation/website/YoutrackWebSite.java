package com.automation.website;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.Base;
import com.automation.Util.ReadExcel;

public class YoutrackWebSite {

	static WebDriver driver;

	String newIssue = "Testing";
	static String siteUrl = "https://youtrack.xcubelabs.com";

	@BeforeTest
	public static void open() {
		driver = Base.getDriverForSite(siteUrl);
		String username = ReadExcel.readExcelFile().get("username");
		String password = ReadExcel.readExcelFile().get("password");

		driver.findElement(By.id("username")).sendKeys(username);

		driver.findElement(By.id("password")).sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test(enabled = false)
	public void createIssue() {

		driver.findElement(By.xpath("//span[contains(text(),'Create Issue')]")).click();

		driver.findElement(By.xpath("//textarea[@class='jt-input ring-input edit-issue-form__i__summary']"))
				.sendKeys(newIssue);

		driver.findElement(
				By.xpath("//textarea[@class='jt-input ring-input edit-issue-form__i__description username-suggest']"))
				.sendKeys("Testing From Selenium");

		driver.findElement(By.xpath("//button[contains(text(),'Create Issue')]")).click();

		String createdIssue = driver.findElement(By.xpath("//span[contains(text(),'" + newIssue + "')]")).getText();

		assertEquals(createdIssue, newIssue);
	}

	@Test(enabled=true)
	public void deleteIssue() {
		boolean deleteFlag = false;
		String url = driver.getCurrentUrl();

		if (!url.contains("Dashboard")) {
			System.out.println("Url not contains dashboard...");
			driver.findElement(By.xpath("//span[contains(text(), 'Dashboard')]")).click();
			driver.findElement(By.xpath("//div[@class='widget__control']")).click();
		}
		List<WebElement> listOfIssues = driver
				.findElements(By.xpath("//a[@class='ring-link youtrack-issues__issue-summary']"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		listOfIssues = wait.until(ExpectedConditions.visibilityOfAllElements(listOfIssues));

		String parentWindow = driver.getWindowHandle();

		for (WebElement issue : listOfIssues) {
			String deleteIssue = issue.getText();
			if (deleteIssue.equals(newIssue)) {
				issue.click();
				deleteFlag = true;
			}
		}
		if (deleteFlag) {
			Set<String> childWindows = driver.getWindowHandles();

			for (String child : childWindows) {
				if (!child.equals(parentWindow)) {
					driver.switchTo().window(child);
				}
			}

			driver.findElement(By.id("id_l.I.tb.deleteIssueLink")).click();

			driver.switchTo().alert().accept();

			if (!url.contains("Dashboard")) {
				System.out.println("Url not contains dashboard...");
				driver.findElement(By.xpath("//span[contains(text(), 'Dashboard')]")).click();
			}

			List<WebElement> list = driver
					.findElements(By.xpath("//a[@class='ring-link youtrack-issues__issue-summary']"));

			List<String> issues = new ArrayList<>();

			list.forEach(issue -> issues.add(issue.getText()));
			if (list.contains(newIssue)) {
				System.out.println(newIssue + " is deleted...");
			}
			Assert.assertEquals(list.contains(newIssue), false);
		}
	}
}
