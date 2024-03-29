package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
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
	
	@BeforeClass
	public void setUp() {
		launchBrowser();
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
		
		boolean flag = loginPage.logo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority =3)
	public  void validLogin() {
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
