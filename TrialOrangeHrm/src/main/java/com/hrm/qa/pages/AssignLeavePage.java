package com.hrm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;

public class AssignLeavePage extends BaseClass{	
		//Page Factory
		
		@FindBy(id="assignleave_txtEmployee_empName")
		WebElement empNameField;
		
		@FindBy(id="leaveBalance_details_link")
		WebElement leaveBalLink;
		
		@FindBy(id="assignleave_txtComment")
		WebElement commentsBox;
		
		@FindBy(id="assignBtn")
		WebElement assignBtn;
		
		@FindBy(id="confirmCancelButton")
		WebElement cancelLeaveBtn;
		
		@FindBy(id="confirmOkButton")
		WebElement confirmLeaveBtn;
		
		@FindBy(id="menu_leave_viewLeaveList")
		WebElement leaveListLink;
		

	public AssignLeavePage() {
		PageFactory.initElements(driver, this);
		}
	
	public void enterEmpName() {
		empNameField.sendKeys(prop.getProperty("empName"));
	}
	
	public void selectLeaveType() {
		Select select = new Select(driver.findElement(By.id("assignleave_txtLeaveType")));
		select.selectByVisibleText("Vacation US");
	}
	
	public void chooseFromDate() {
		
		WebElement fromDateEle = driver.findElement(By.id("assignleave_txtFromDate"));
		driver.findElement(By.id("assignleave_txtFromDate")).click();
		JavascriptExecutor js =((JavascriptExecutor)driver);
		//js.executeScript("document.getElementById('assignleave_txtFromDate').removeAttribute('readonly')");
		fromDateEle.clear();
		js.executeScript("document.getElementById('assignleave_txtFromDate').setAttribute('value','2020-05-30');");
		//js.executeScript("arguments[0].setAttribute('value','2020-06-13');",fromDateEle);
		String fromDate = fromDateEle.getText();
		System.out.println(fromDate);
	} 
	
	public void enterFromDate() {
		WebElement fromDateEle = driver.findElement(By.id("assignleave_txtFromDate"));
		fromDateEle.clear();
		fromDateEle.sendKeys(prop.getProperty("leaveFromDate"));
		
	}
	
	public void enterToDate() {
		WebElement fromToEle = driver.findElement(By.id("assignleave_txtToDate"));
		fromToEle.clear();
		fromToEle.sendKeys(prop.getProperty("leaveToDate"));
		fromToEle.sendKeys(Keys.ENTER);
		
	}
	public void chooseToDate() {
		driver.findElement(By.id("assignleave_txtToDate")).click();
		JavascriptExecutor js =((JavascriptExecutor)driver);
		js.executeScript("document.getElementById('assignleave_txtToDate').setAttribute('value','2020-06-30')");

	}
	
	public void enterComments() {
		//@SuppressWarnings("deprecation")
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(leaveBalLink));
		commentsBox.sendKeys("Jasmine Morgan is going on leave in July sfhsfhgshsfbzcvz");
		System.out.println(commentsBox.getText());
	}
	
	public void clickAssignBtn() {
		assignBtn.click();
		//cancelLeaveBtn.click();
		confirmLeaveBtn.click();
		}
	
	public void successMsg() {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue( bodyText.contains("Successfully Assigned"),"Msg not displayed");
	}
	
	public LeaveListPage goToLeaveListPage() {
		//leaveListLink.click();
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", leaveListLink);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='toggle tiptip']")));
		return new LeaveListPage();
	}

	public void enterAllInputFields() {
		empNameField.sendKeys(prop.getProperty("empName"));
		Select select = new Select(driver.findElement(By.id("assignleave_txtLeaveType")));
		select.selectByVisibleText("Vacation US");
		WebElement fromDateEle = driver.findElement(By.id("assignleave_txtFromDate"));
		fromDateEle.clear();
		fromDateEle.sendKeys(prop.getProperty("leaveFromDate"));
		WebElement fromToEle = driver.findElement(By.id("assignleave_txtToDate"));
		fromToEle.clear();
		fromToEle.sendKeys(prop.getProperty("leaveToDate"));
		fromToEle.sendKeys(Keys.ENTER);
		commentsBox.sendKeys(prop.getProperty("empName") + " is going on leave from " + prop.getProperty("leaveFromDate") + " to " + prop.getProperty("leaveToDate"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
