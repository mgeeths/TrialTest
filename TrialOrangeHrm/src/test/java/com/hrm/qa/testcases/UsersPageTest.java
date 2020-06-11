package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;
import com.hrm.qa.pages.UsersPage;

public class UsersPageTest extends BaseClass{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	UsersPage usersPage;
	
	
	public UsersPageTest() {
		super();
	}	
	
	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage  = new LoginPage();
		dashboardPage = new  DashboardPage();
		usersPage  = new UsersPage();
		}
	
	@BeforeMethod
	public void loginToAppln() {
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyGoToUsersPage() {
		dashboardPage.goToUsersPage();
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers");
	}
	
	@AfterMethod
	public void goBackToDashboardPage() {
		dashboardPage.goToDashboardPage();
		}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
