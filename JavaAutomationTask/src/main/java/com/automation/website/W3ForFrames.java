package com.automation.website;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.Base;

public class W3ForFrames {

	private WebDriver driver;
	
	@Before
	public void openWebSite() {
		driver = Base.getDriverForSite("https://www.w3schools.com");
	}
	
	@Test
	public void searchFrame() {
		WebElement frame =  driver.findElement(By.xpath("//iframe[@src='/howto/tryhow_css_sidenav_ifr.htm']"));
		
		driver.switchTo().frame(frame);
		
		driver.findElement(By.xpath("//html/body/div/a[contains(text(),'Services')]")).click();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//nav[@id='mySidenav']/div/a[contains(text(),'Learn Sass')]")).click();
	}
	
}
