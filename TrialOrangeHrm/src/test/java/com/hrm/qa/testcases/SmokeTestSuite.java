package com.hrm.qa.testcases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AddEmpPage;
import com.hrm.qa.pages.AddUserPage;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.ConfigureNewHolidays;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.EmpListPage;
import com.hrm.qa.pages.HolidaysPage;
import com.hrm.qa.pages.LeaveListPage;
import com.hrm.qa.pages.LoginPage;
import com.hrm.qa.pages.UsersPage;

public class SmokeTestSuite extends BaseClass {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AddUserPage addUserPage;
	AddEmpPage addEmpPage;
	AssignLeavePage assignLeavePage;
	ConfigureNewHolidays configureNewHolidays;
	EmpListPage empListPage;
	HolidaysPage holidaysPage;
	LeaveListPage leaveListPage;
	UsersPage usersPage;

	public SmokeTestSuite() {
		super();
	}	
	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage =new LoginPage();
		dashboardPage =new DashboardPage() ;
		addUserPage = new AddUserPage();
		addEmpPage = new AddEmpPage();
		assignLeavePage = new AssignLeavePage();
		configureNewHolidays = new ConfigureNewHolidays();
		empListPage = new EmpListPage();
		holidaysPage = new HolidaysPage();
		leaveListPage = new LeaveListPage();
		usersPage = new UsersPage();
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@BeforeMethod
	public void goToDashboardPage() {
		dashboardPage.goToDashboardPage();
	}
	
	@Test(priority=1)
	public void createNewUsers() throws InterruptedException {
		dashboardPage.goToEmpListPage();
		empListPage.goToAddNewEmpPage();
		addEmpPage.addNewEmpFromExcelFile();
	}
	
	@Test(priority=2)
	public void assignLeave() throws InterruptedException {
		dashboardPage.goToAssignLeave();
		assignLeavePage.enterAllInputFieldsFromXcelFile();
	}
	
	@Test(priority=3)
	public void verifyDateFiltersInLeaveListPage() throws InterruptedException, ParseException {
		dashboardPage.goToLeaveList();
		leaveListPage.enterFromDateFilter();
		leaveListPage.enterToDateFilter();
		leaveListPage.leaveFilteredByFromDate();
	}
	
	@Test(priority=4)
	public void verifyEmpNameFilterInLeaveListPage() throws InterruptedException {
		dashboardPage.goToLeaveList();
		leaveListPage.enterEmpNameInFilter();
		Assert.assertTrue(leaveListPage.resultTableNameCol());
	}
	
	
	@Test(priority=5)
	public void valiadatePendingStatus() {
		dashboardPage.goToLeaveList();
		Assert.assertTrue(leaveListPage.pendingStatus());

	}

	@Test(priority=6)
	public void validateScheduledStatus() {
		dashboardPage.goToLeaveList();
		Assert.assertTrue(leaveListPage.scheduledStatus());
	}

	@Test(priority=7)
	public void valiadateTakenStatus() {
		dashboardPage.goToLeaveList();
		Assert.assertTrue(leaveListPage.takenStatus());

	}

	@Test(priority=8)
	public void valiadateAllStatus() {
		dashboardPage.goToLeaveList();
		Assert.assertTrue(leaveListPage.allStatus());

	}

	@Test(priority=9)
	public void verifyCancelLeave() throws InterruptedException {
		dashboardPage.goToLeaveList();
		leaveListPage.cancelOneAssignedLeave();
	}
	
	@Test(priority=10)
	public void validateCancelStatus() throws InterruptedException {
		dashboardPage.goToLeaveList();
		Assert.assertTrue(leaveListPage.cancelStatus());
	}
	
	//@AfterMethod
	public void goBackToDashboardPage() throws InterruptedException {
		Thread.sleep(2000);
		dashboardPage.goToDashboardPage();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
