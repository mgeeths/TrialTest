package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.EmpListPage;
import com.hrm.qa.pages.LoginPage;

public class EmpListPageTest extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	EmpListPage empListPage;

	public EmpListPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		empListPage = new EmpListPage();

		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		// dashboardPage.goToDashboardPage();

	}

	@BeforeMethod
	public void goToDashboard() {
		dashboardPage.goToDashboardPage();
		dashboardPage.goToEmpListPage();
	}
	
	@Test
	public void verifyEmpListPageUrl() {
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList");
	}
	
	@AfterMethod
	public void goBackToDashboad() {
		dashboardPage.goToDashboardPage();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
