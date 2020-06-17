package com.automation.test;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestngTwo {

	@Test(priority=4)
	public void testFirst() {
		System.out.println("test fisrt method..");
		Reporter.log("test first method..");
	}

	@Test(priority=4)
	public void testSecond() {
		System.out.println("test second method..");
		Reporter.log("test second method..");
	}
	
	@Test(priority=3)
	public void testThird() {
		System.out.println("test third method..");
		Reporter.log("test third method..");
	}

	@Test(priority=2)
	public void testFourth() {
		System.out.println("test fourth method..");
		Reporter.log("test fourth method..");
	}
	
	@Test(priority=1)
	public void testFive() {
		System.out.println("test five method..");
		Reporter.log("test five method..");
	}

	@Test(priority=0)
	public void testSix() {
		System.out.println("test six method..");
		Reporter.log("test six method..");
	}
	
}
