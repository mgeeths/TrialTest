package com.hrm.qa.testcases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LeaveListPage;
import com.hrm.qa.pages.LoginPage;

public class LeaveListPageTest extends BaseClass {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AssignLeavePage assignLeavePage;

	LeaveListPage leaveListPage;

	public LeaveListPageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		launchBrowser();

		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		assignLeavePage = new AssignLeavePage();
		leaveListPage = new LeaveListPage();
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod
	public void goToLeaveListPage() {
		dashboardPage.goToLeaveList();
		}
	
	@Test(priority=1)
	public void validateDateFilter() throws ParseException {
		leaveListPage.enterFromDateFilter();
		leaveListPage.enterToDateFilter();
		leaveListPage.leaveFilteredByFromDate();
	}


	@Test(priority=2)
	public void verifyEmpNameInResultTable() throws InterruptedException {
		leaveListPage.enterEmpNameInFilter();
		Assert.assertTrue(leaveListPage.resultTableNameCol());
		
	}
	
	@Test(priority=3)
	public void valiadatePendingStatus() {
		Assert.assertTrue(leaveListPage.pendingStatus());

	}

	@Test(priority=4)
	public void validateScheduledStatus() {
		Assert.assertTrue(leaveListPage.scheduledStatus());
	}

	@Test(priority=5)
	public void valiadateTakenStatus() {
		Assert.assertTrue(leaveListPage.takenStatus());

	}

	@Test(priority=6)
	public void valiadateAllStatus() {
		Assert.assertTrue(leaveListPage.allStatus());

	}

	@Test(priority=7)
	public void verifyCancelOneLeave() throws InterruptedException {
		leaveListPage.cancelOneAssignedLeave();
	}
	
	@Test(priority=8)
	public void validateCancelStatus() throws InterruptedException {
		//leaveListPage.enterEmpNameInFilter();
		Assert.assertTrue(leaveListPage.cancelStatus());
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
