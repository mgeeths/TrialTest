package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	
	public LoginPageTest() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		BaseClass.launchBrowser();
		loginPage = new LoginPage();
		
	}
	
	@Test(priority =1)
	public void validateLoginPageTitle() {
		loginPage.goToWebsite();
		String title = loginPage.pageTitle();
		Assert.assertEquals(title, "OrangeHRM");
	}
	
	@Test(priority =2)
	public void validateLogo() {
		loginPage.goToWebsite();
		boolean flag = loginPage.logo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority =3)
	public  void validLogin() {
		loginPage.goToWebsite();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//return new DashboardPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
