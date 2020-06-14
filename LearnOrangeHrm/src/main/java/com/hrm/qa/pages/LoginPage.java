package com.hrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.qa.baseclass.BaseClass;

public class LoginPage extends BaseClass{
	
	//Page Factory
	@FindBy(name="txtUsername")
	WebElement username;
	
	@FindBy(id="divPassword")
	WebElement password;
	
	@FindBy(id="btnLogin")
	WebElement loginBtn;
	
	@FindBy(xpath = "//img[@src=\"/webres_5ebd1457b45137.49759927/themes/default/images/login/logo.png\"]")
	WebElement logo;
	
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
		
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyLogo() {
		return logo.isDisplayed();
		}
	
	public DashboardPage validLogin(String uname, String pwd) {
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginBtn.click();
		return new DashboardPage();
	}
}
