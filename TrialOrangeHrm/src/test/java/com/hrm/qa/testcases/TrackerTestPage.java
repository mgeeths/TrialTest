package com.hrm.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AddEmpPage;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.ConfigureNewHolidays;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.EmpListPage;
import com.hrm.qa.pages.LoginPage;
import com.hrm.qa.pages.TrackerPage;

public class TrackerTestPage extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	AddEmpPage addEmpPage;
	AssignLeavePage assignLeavePage;
	ConfigureNewHolidays configureNewHolidays;
	EmpListPage empListPage;
	TrackerPage trackerPage;

	public TrackerTestPage() {
		super();
	}

	@BeforeClass
	public void setUp() {
		launchBrowser();

		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		addEmpPage = new AddEmpPage();
		assignLeavePage = new AssignLeavePage();
		configureNewHolidays = new ConfigureNewHolidays();
		empListPage = new EmpListPage();
		trackerPage = new TrackerPage();
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod
	public void goToPerformanceTab() {
		dashboardPage.goToPerformanceTab();
	}

	@Test(priority = 1)
	public void createNewUsers() throws InterruptedException {
		dashboardPage.goToEmpListPage();
		empListPage.goToAddNewEmpPage();
		addEmpPage.addNewEmpFromExcelFile();
	}

	public void createNewTaracker() {
		// trackerPage.addNewTracker(tName, eName, revName);
	}

	@AfterMethod
	public void goBackToDashboardPage() throws InterruptedException {
		Thread.sleep(2000);
		dashboardPage.goToDashboardPage();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
