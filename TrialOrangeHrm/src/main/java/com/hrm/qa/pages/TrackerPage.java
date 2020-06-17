package com.hrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.hrm.qa.base.BaseClass;

public class TrackerPage extends BaseClass{
	
	//Page Factory
	@FindBy(id="btnAdd")
	WebElement addBtn;
	
	@FindBy(id="addPerformanceTracker_tracker_name")
	WebElement trackerNameField;
	
	@FindBy(id="addPerformanceTracker_employeeName_empName")
	WebElement empNameField;
	
	@FindBy(id="addPerformanceTracker_availableEmp")
	WebElement availableEmpNames;
	
	@FindBy(id="btnAssignEmployee")
	WebElement addReviewersBtn;
	
	@FindBy(id="btnSave")
	WebElement saveBtn;
	
	
	public TrackerPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addNewTracker(String tName, String eName, String revName) {
		addBtn.click();
		trackerNameField.clear();
		trackerNameField.click();
		trackerNameField.sendKeys(tName);
		empNameField.clear();
		empNameField.click();
		empNameField.sendKeys(eName);
		Select select = new Select(availableEmpNames);
		select.selectByVisibleText(revName);
		//saveBtn.click();
	}
}
