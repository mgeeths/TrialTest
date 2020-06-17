package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@BeforeMethod
	public void navToAddUsersPage() {
		usersPage.goToAddUserPage();
	}

	@Test
	public void verifyAddUserPageurl() {
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/admin/saveSystemUser");
	}
	
	@Test
	public void enterAllInputFields() {
		addUserPage.enterAllInputFields();
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
