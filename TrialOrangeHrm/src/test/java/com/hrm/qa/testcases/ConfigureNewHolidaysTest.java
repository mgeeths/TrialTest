package com.hrm.qa.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.ConfigureNewHolidays;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.HolidaysPage;
import com.hrm.qa.pages.LoginPage;

public class ConfigureNewHolidaysTest extends BaseClass {
	DashboardPage dashboardPage;
	LoginPage loginPage;
	HolidaysPage holidaysPage;
	ConfigureNewHolidays configureHolidays;

	public ConfigureNewHolidaysTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		holidaysPage = new HolidaysPage();
		configureHolidays = new ConfigureNewHolidays();
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@BeforeMethod
	public void navToConfigureHolidays() {
		dashboardPage.goToHoildaysPage();
	}

	@Test(priority=1)
	@Parameters({"holidayName","holidayDate"})
	public void verifyCreateNewHolidayInTable(String holidayName, String holidayDate) throws InterruptedException {
		holidaysPage.goToConfigureNewHolidayPage();
		configureHolidays.createNewHoliday(holidayName,holidayDate);
		Thread.sleep(2000);
		configureHolidays.resultTableData(holidayName,holidayDate);
	}
	/*The function returns two string variables. The assertions of these should be done in this 
	 * function. To access these values, use .get method. Before asserting the values, the total 
	 * number of values returned has to be asserted (to make sure that there are two items 
	 * returned by the edit function. Otherwise, it will lead to null point exception. ie If for
	 * some reason, the called function (editHoliday) does not return two values, then in this 
	 * function, nothing is verified and the assertTrue will give a null point exception and we
	 * won't know where the error happened. With the verificaton of the size of the list, the 
	 * assertion will fail and we will know clearly that the function did not return two items.   
	 */

	@Test(priority=2)
	@Parameters({"holidayName"})
	public void verifyEditHoiliday(String holidayName) throws InterruptedException {
		
		List<String> resultTable = configureHolidays.editHoliday(holidayName);
		Assert.assertEquals(resultTable.size(),2);
		Assert.assertEquals(resultTable.get(0), "Half Day");
		Assert.assertEquals(resultTable.get(1), "No");

	}
	
	@Test(priority=3)
	@Parameters({"holidayName"})
	public void verifyDeleteHolidayCreated(String holidayName) {
		configureHolidays.deleteHolidayCreated(holidayName);
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
