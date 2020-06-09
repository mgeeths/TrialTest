package com.hrm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrm.qa.base.BaseClass;

public class DashboardPage extends BaseClass {

	//Page Factory
	@FindBy(linkText="Welcome Admin")
	WebElement user;
	
	@FindBy(linkText="Logout")
	WebElement logout;
	
	@FindBy(xpath="//a[@id='menu_dashboard_index']")
	WebElement dashboardTab;
	
	@FindBy(xpath="//div[@class='quickLaunge']/a[@href='/index.php/leave/assignLeave']")
	WebElement assignLeaveLink;
	
	@FindBy(xpath="//div[@class='quickLaunge']/a[@href='/index.php/leave/viewLeaveList']")
	WebElement viewLeaveList;
	
	@FindBy(id="menu_leave_viewLeaveModule")
	WebElement leaveTab;
	
	@FindBy(id = "menu_leave_Configure")
	WebElement configureTab;

	@FindBy(linkText = "Holidays")
	WebElement holidaysLink;
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String currentUser() {
		String  name = user.getText();
		System.out.println(name);
		return name;
	}
	
	public void logoutOfAppln() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(user));
		user.click();
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		logout.click();
	}
	
	public DashboardPage goToDashboardPage() {
		dashboardTab.click();
		return new DashboardPage();
	}
	
	public  AssignLeavePage goToAssignLeave() {
		assignLeaveLink.click();
		//String currentPage = driver.getCurrentUrl();
		return new AssignLeavePage();
		
	}
	
	public void goToLeaveTab() {
		leaveTab.click();
	}
	
	public LeaveListPage goToLeaveList() {
		viewLeaveList.click();
		return new LeaveListPage();
	}
	
	public HolidaysPage goToHoildaysPage() {
		mouseOver(leaveTab);
		mouseOver(configureTab);
		mouseOver(holidaysLink);
		holidaysLink.click();
		return new HolidaysPage();
	}
}
