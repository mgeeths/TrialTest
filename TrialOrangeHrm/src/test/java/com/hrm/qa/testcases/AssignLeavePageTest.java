package com.hrm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;

public class AssignLeavePageTest extends BaseClass {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AssignLeavePage assignLeavePage;
	
	public AssignLeavePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		 launchBrowser();
		 loginPage = new LoginPage();
		 dashboardPage = new DashboardPage();
		 assignLeavePage = new AssignLeavePage();
		 
		 loginPage.goToWebsite();
		 loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		 dashboardPage.goToAssignLeave();
		 
	}
	/*
	@Test
	public void enterEmpName() {
		assignLeavePage.enterEmpName();
		assignLeavePage.selectLeaveType();
		assignLeavePage.enterFromDate();
		assignLeavePage.enterToDate();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assignLeavePage.enterComments();
		assignLeavePage.clickAssignBtn();
		assignLeavePage.successMsg();

	} */
	@Test 
	public void assignLeaveToEmp() {
		assignLeavePage.enterAllInputFields();
		assignLeavePage.clickAssignBtn();
		assignLeavePage.successMsg();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
