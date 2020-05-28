package com.hrm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrm.qa.base.BaseClass;

public class LeaveListPage extends BaseClass {

	
	//Page Factory
	@FindBy(id="leaveList_chkSearchFilter_checkboxgroup_allcheck")
	WebElement allCheckbox;

	@FindBy(id="leaveList_txtEmployee_empName")
	WebElement empNameField;
	
	@FindBy(id="btnSearch")
	WebElement searchBtn;
	
	public LeaveListPage() {
		PageFactory.initElements(driver, this);
	}


	//Actions
	public void enterEmpDetails() {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebDriverWait wait = new WebDriverWait(driver);
		wait.until(ExpectedConditions.elementToBeClickable(allCheckbox));
		allCheckbox.click();
		empNameField.sendKeys(prop.getProperty("empName"));
		searchBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//this method will return null if the web element for rows is null.
	public String resultTable() {
		List<WebElement> allRows = driver.findElements(By.xpath("//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']"));
		for(int i=0; i<allRows.size(); i++) {
	    	List <WebElement> allCols = allRows.get(i).findElements(By.xpath("./td"));
			String name =allCols.get(1).findElement(By.xpath("./a")).getText();
			return name;
		}
		return null;
	}
	
}