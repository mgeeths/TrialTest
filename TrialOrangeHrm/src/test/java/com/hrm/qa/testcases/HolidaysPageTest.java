package com.hrm.qa.testcases;

import java.text.ParseException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.HolidaysPage;
import com.hrm.qa.pages.LoginPage;

public class HolidaysPageTest extends BaseClass {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	HolidaysPage holidaysPage;

	public HolidaysPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		holidaysPage = new HolidaysPage();
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		dashboardPage.goToHoildaysPage();
	}
	
	//@Test
	public void verifyHideFiltersToggleBtn() {
		holidaysPage.toggleButtonHideAndShowFilter();
	}
	
	//@Test
	public void verifyDatesFilterEntered() throws InterruptedException {
		List<String> DateValues = holidaysPage.enterDateFields();
		Assert.assertEquals(holidaysPage.enterDateFields().size(),2);
		Assert.assertEquals(DateValues.get(0), "2020-02-01");
		Assert.assertEquals(DateValues.get(1), "2020-12-31");
		}
	
	//@Test
	public void verifyResultsTablePerFilterDates() throws ParseException {
		Assert.assertTrue(holidaysPage.filterHolidaysByDate());
	}
	
	//@Test
	public void verifyGoToConfigureNewHolidaysPage() {
		holidaysPage.goToConfigureNewHolidayPage();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://opensource-demo.orangehrmlive.com/index.php/leave/viewHolidayList");
	}
	
	//@Test
	public void verifyCancelDeleteHoliday() throws InterruptedException {
		//holidaysPage.goToConfigureNewHolidayPage();
		holidaysPage.CancelDeleteHoliday();

	}
	
	@Test
	public void verifyDeleteHoliday() throws InterruptedException {
		//holidaysPage.goToConfigureNewHolidayPage();
		holidaysPage.DeleteHoliday();

	}
	
	@AfterMethod
	public void logoutofAppln() throws InterruptedException {
		dashboardPage.goToDashboardPage();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
