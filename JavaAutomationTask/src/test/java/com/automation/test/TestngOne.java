package com.automation.test;

import org.testng.Reporter;
import org.testng.annotations.Test;


public class TestngOne {
	
	@Test(priority=5)
	public void testFirst() {
		System.out.println("test fisrt method..");
		Reporter.log("test first method..");
	}

	@Test(priority=10)
	public void testSecond() {
		System.out.println("test second method..");
		Reporter.log("test second method..");
	}
	
}
