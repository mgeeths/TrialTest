package com.hrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.qa.base.BaseClass;

public class UsersPage extends BaseClass{

	//Page Factory
	@FindBy(id="btnAdd")
	WebElement addBtn;
	
	
	public UsersPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public AddUserPage goToAddUserPage() {
		addBtn.click();
		return new AddUserPage();
	}

}
