package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AddEmpPage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.EmpListPage;
import com.hrm.qa.pages.LoginPage;

public class AddEmpPageTest extends BaseClass{

	LoginPage loginPage;
	DashboardPage dashboardPage;
	EmpListPage empListPage;
	AddEmpPage addEmpPage;
	
	public AddEmpPageTest() {
		super();
	}
	
	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		empListPage = new EmpListPage();
		addEmpPage = new AddEmpPage();

		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@BeforeMethod
	public void navToAddNewEmpPage() {
		dashboardPage.goToEmpListPage();
		empListPage.goToAddNewEmpPage();
		
	}
	
	@Test(priority = 1)
	public void verifyPIMPageUrl() throws InterruptedException {
		Thread.sleep(2000);
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
	}
	
	@Test(priority = 2)
	public void createNewUsersFromExcelFile() throws InterruptedException {
		addEmpPage.addNewEmpFromExcelFile();
		Thread.sleep(2000);
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


