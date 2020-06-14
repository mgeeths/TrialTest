package com.hrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hrm.qa.base.BaseClass;

public class AddUserPage extends BaseClass{
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	UsersPage usersPage;
	
	//Page Factory
	@FindBy(id="systemUser_userType")
	WebElement userRoleField;
	
	@FindBy(id="systemUser_employeeName_empName")
	WebElement empNameField;
	
	@FindBy(id="systemUser_userName")
	WebElement userNameField;
	
	@FindBy(id="systemUser_status")
	WebElement statusField;
	
	@FindBy(id="systemUser_password")
	WebElement pwdField;
	
	@FindBy(id="systemUser_confirmPassword")
	WebElement confirmPwdField;
	
	@FindBy(id="btnSave")
	WebElement saveBtn;
	
	public AddUserPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void enterAllInputFields() {
		userRoleField.clear();
		Select select1 = new Select(userRoleField);
		select1.selectByVisibleText("ESS");
		empNameField.clear();
		empNameField.sendKeys("Steffi Graf");
		userNameField.clear();
		userNameField.sendKeys("steffi.graf");
		statusField.clear();
		Select select2 = new Select(statusField);
		select2.selectByVisibleText("Enabled");
		pwdField.clear();
		pwdField.sendKeys("Test@123");
		confirmPwdField.clear();
		confirmPwdField.sendKeys("Test@123");
		
	}
	
	public void saveNewUserCreated() {
		saveBtn.click();
	}
}
