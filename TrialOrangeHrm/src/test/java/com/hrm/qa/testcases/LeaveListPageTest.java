package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LeaveListPage;
import com.hrm.qa.pages.LoginPage;

public class LeaveListPageTest extends BaseClass{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AssignLeavePage assignLeavePage;
	
	LeaveListPage leaveListPage;
	
	public LeaveListPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		assignLeavePage = new AssignLeavePage();
		leaveListPage = new LeaveListPage();
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		dashboardPage.goToAssignLeave();
		assignLeavePage.enterAllInputFields();
		assignLeavePage.clickAssignBtn();
		assignLeavePage.successMsg();
		assignLeavePage.goToLeaveListPage();
	}
	@Test
	public void enterEmpDetails() {
		leaveListPage.enterEmpDetails();
		String name =leaveListPage.resultTable();
		Assert.assertEquals(name, prop.getProperty("empName"));
	}
	
	
	//public void verifySearchName() {
		//String name =leaveListPage.resultTable();
		//Assert.assertEquals(name, prop.getProperty("empName"));
	//}
	@AfterMethod
	public void teraDown() {
		driver.quit();
	}
}
