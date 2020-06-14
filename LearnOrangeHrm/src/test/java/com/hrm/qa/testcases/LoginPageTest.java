package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.baseclass.BaseClass;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	LoginPage loginPage = new LoginPage();
	DashboardPage dashboardPage;
	
	public LoginPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();

	}
	
	@Test(priority = 1)
	public void testPageTitle() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, "OrangeHRM");
	}
	
	@Test(priority = 2)
	 public void testPageLogo(){
	 boolean b = loginPage.verifyLogo();
	 Assert.assertTrue(b);
	 }
	 
	@Test(priority = 3)
	public void testValidLogin() {
		dashboardPage = loginPage.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
