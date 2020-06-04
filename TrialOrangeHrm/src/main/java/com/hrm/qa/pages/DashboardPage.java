package com.hrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.qa.base.BaseClass;

public class DashboardPage extends BaseClass {

	//Page Factory
	@FindBy(id="welcome")
	WebElement user;
	
	@FindBy(xpath="//div[@class='quickLaunge']/a[@href='/index.php/leave/assignLeave']")
	WebElement assignLeaveLink;
	
	@FindBy(xpath="//div[@class='quickLaunge']/a[@href='/index.php/leave/viewLeaveList']")
	WebElement viewLeaveList;
	
	@FindBy(id="menu_leave_viewLeaveModule")
	WebElement leaveTab;
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String currentUser() {
		String  name = user.getText();
		System.out.println(name);
		return name;
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
}
