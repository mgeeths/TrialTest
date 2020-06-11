package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AddUserPage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;
import com.hrm.qa.pages.UsersPage;

public class AddUserPageTest extends BaseClass {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	UsersPage usersPage;
	AddUserPage addUserPage;

	public AddUserPageTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		usersPage = new UsersPage();
		addUserPage = new AddUserPage();

	}

	@BeforeMethod
	public void loginToAppln() {
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		dashboardPage.goToUsersPage();
	}

	@Test
	public void verifyAddUserPageurl() {
		usersPage.goToAddUserPage();
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/admin/saveSystemUser");
	}

	@AfterMethod
	public void goBackToDashboardPage() {
		dashboardPage.goToDashboardPage();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
