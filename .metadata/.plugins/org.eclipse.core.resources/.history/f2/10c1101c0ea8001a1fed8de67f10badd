package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;

public class DashboardPageTest extends BaseClass{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AssignLeavePage assignLeavePage;
	
	DashboardPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		
	}
	//@Test
	public void validateUser() {
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String currentUserName = dashboardPage.currentUser();
		Assert.assertEquals(currentUserName, "Welcome Admin");
	}
	//@Test
	public void clickAssignLeaveLink() {
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		assignLeavePage = dashboardPage.goToAssignLeave();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave");
	}
	
	@Test
	public void navigateToHolidaysPage() {
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		dashboardPage.goToHoildaysPage();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://opensource-demo.orangehrmlive.com/index.php/leave/viewHolidayList");
 	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
