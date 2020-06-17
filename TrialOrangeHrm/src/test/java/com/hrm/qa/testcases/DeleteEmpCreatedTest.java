package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.DeleteEmp;
import com.hrm.qa.pages.EmpListPage;
import com.hrm.qa.pages.LoginPage;

public class DeleteEmpCreatedTest extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	EmpListPage empListPage;
	DeleteEmp deleteEmp;

	public DeleteEmpCreatedTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		empListPage = new EmpListPage();
		deleteEmp = new DeleteEmp();

		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod
	public void navToEmpListPage() {
		dashboardPage.goToEmpListPage();
	}

	@Test(priority = 1)
	public void verifyEmpListPageUrl() {
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList");
	}

	@Test(priority = 2)
	public void deleteNewEmpCreated() throws InterruptedException {
		deleteEmp.deleteEmp();
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
