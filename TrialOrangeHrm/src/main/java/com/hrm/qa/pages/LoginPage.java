package com.hrm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.qa.base.BaseClass;

public class LoginPage extends BaseClass {
	//Page Factory or Object repository
	@FindBy (name="txtUsername")
	WebElement username;
	
	@FindBy(id="txtPassword")
	WebElement password;
	
	@FindBy(id="btnLogin")
	WebElement loginBtn;
	
	@FindBy(xpath = "//img[@src='/webres_5ebd1457b45137.49759927/themes/default/images/login/logo.png']")
	WebElement hrmLogo;
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void goToWebsite() {
		driver.get(prop.getProperty("url"));
	}
	
	public String pageTitle() {
		return driver.getTitle();
	}
	
	public boolean logo() {
		WebElement logoElement = driver.findElement(By.xpath("//img[@src='/webres_5ebd1457b45137.49759927/themes/default/images/login/logo.png']")
);
		System.out.println(logoElement.isDisplayed());
		return hrmLogo.isDisplayed();
	}
	
	public DashboardPage login(String uname, String pwd) {
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginBtn.click();
		return new DashboardPage();
	}
}
