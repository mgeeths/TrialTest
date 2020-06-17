package com.hrm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.util.Xls_Reader;

public class AssignLeavePage extends BaseClass {
	Xls_Reader xlsReader;
	// Page Factory

	@FindBy(id = "assignleave_txtEmployee_empName")
	WebElement empNameField;

	@FindBy(id = "assignleave_txtLeaveType")
	WebElement leaveType;

	@FindBy(id = "assignleave_txtFromDate")
	WebElement fromDateEle;

	@FindBy(id = "assignleave_txtToDate")
	WebElement toDateEle;

	@FindBy(id = "assignleave_duration_duration")
	WebElement durationField;

	@FindBy(id = "assignleave_duration_ampm")
	WebElement ampmDuration;

	@FindBy(id = "assignleave_duration_time_from")
	WebElement specificFromTime;

	@FindBy(id = "assignleave_duration_time_to")
	WebElement specificToTime;

	@FindBy(id = "leaveBalance_details_link")
	WebElement leaveBalLink;

	@FindBy(id = "assignleave_txtComment")
	WebElement commentsBox;

	@FindBy(id = "assignBtn")
	WebElement assignBtn;

	@FindBy(id = "confirmCancelButton")
	WebElement cancelLeaveBtn;

	@FindBy(id = "confirmOkButton")
	WebElement confirmLeaveBtn;

	@FindBy(id = "menu_leave_viewLeaveList")
	WebElement leaveListLink;

	public AssignLeavePage() {
		PageFactory.initElements(driver, this);
		xlsReader = new Xls_Reader(
				"C://Users//browse//Automation//TrialOrangeHrm//src//main//java//com//hrm//qa//testdata//Test Data.xlsx");

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
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// js.executeScript("document.getElementById('assignleave_txtFromDate').removeAttribute('readonly')");
		fromDateEle.clear();
		js.executeScript("document.getElementById('assignleave_txtFromDate').setAttribute('value','2020-05-30');");
		// js.executeScript("arguments[0].setAttribute('value','2020-06-13');",fromDateEle);
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
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('assignleave_txtToDate').setAttribute('value','2020-06-30')");

	}

	public void enterComments() {
		// @SuppressWarnings("deprecation")
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(leaveBalLink));
		commentsBox.sendKeys("Jasmine Morgan is going on leave in July sfhsfhgshsfbzcvz");
		System.out.println(commentsBox.getText());
	}

	public void clickAssignBtn() {
		assignBtn.click();
		// cancelLeaveBtn.click();
		confirmLeaveBtn.click();
	}

	public void successMsg() {
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(bodyText.contains("Successfully Assigned"), "Msg not displayed");
	}

	public LeaveListPage goToLeaveListPage() {
		// leaveListLink.click();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", leaveListLink);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='toggle tiptip']")));
		return new LeaveListPage();
	}

	public void enterAllInputFields() {
		empNameField.sendKeys(prop.getProperty("empName"));
		Select select = new Select(leaveType);
		select.selectByVisibleText("Vacation US");

		fromDateEle.clear();
		fromDateEle.sendKeys(prop.getProperty("leaveFromDate"));

		toDateEle.clear();
		toDateEle.sendKeys(prop.getProperty("leaveToDate"));
		toDateEle.sendKeys(Keys.ENTER);
		commentsBox.sendKeys(prop.getProperty("empName") + " is going on leave from "
				+ prop.getProperty("leaveFromDate") + " to " + prop.getProperty("leaveToDate"));

	}

	public void assignHalfDayLeave() {
		String empName = xlsReader.getCellData("NewUserDetails", "FullName", 5);
		//empNameField.clear();
		empNameField.click();
		empNameField.sendKeys(empName);
		Select select = new Select(leaveType);
		select.selectByVisibleText("Maternity US");

		fromDateEle.clear();
		String halfDate= xlsReader.getCellData("AssignLeaveDetails", "StringLeaveFromDate", 10);
		fromDateEle.sendKeys(halfDate);
		fromDateEle.sendKeys(Keys.ENTER);
		durationField.click();
		Select select1 = new Select(durationField);
		select1.selectByVisibleText("Half Day");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfAllElements(ampmDuration));
		ampmDuration.click();
		Select select2 = new Select(ampmDuration);
		select2.selectByVisibleText("Afternoon");

		commentsBox.sendKeys(empName + " is going on half day leave on " + halfDate);
		assignBtn.click();

	}

	public void assignSpecificTimeLeave() throws InterruptedException {
		String empName = xlsReader.getCellData("NewUserDetails", "FullName", 5);
		//empNameField.clear();
		empNameField.click();
		empNameField.sendKeys(empName);
		Select select = new Select(leaveType);
		select.selectByVisibleText("FMLA US");
		fromDateEle.clear();
		String halfDate= xlsReader.getCellData("AssignLeaveDetails", "StringLeaveFromDate", 8);
		fromDateEle.sendKeys(halfDate);
		fromDateEle.sendKeys(Keys.ENTER);
		durationField.click();
		Select select1 = new Select(durationField);
		select1.selectByVisibleText("Specify Time");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait1.until(ExpectedConditions.visibilityOfAllElements(specificFromTime));
		Select select2 = new Select(specificFromTime);
		select2.selectByVisibleText("09:30");
		Select select3 = new Select(specificToTime);
		select3.selectByVisibleText("13:00");
		Thread.sleep(3000);
		commentsBox.sendKeys(empName + " is going on specific time leave on  " + halfDate);
		assignBtn.click();

	}

	public void enterAllInputFieldsFromXcelFile() throws InterruptedException {
		int rowCount = xlsReader.getRowCount("AssignLeaveDetails");
		System.out.println(rowCount);
		
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			System.out.println("Emp on row no." +  rowNum);
			String empName = xlsReader.getCellData("AssignLeaveDetails", "EmployeeName", rowNum);
			System.out.println(empName);
			empNameField.clear();
			empNameField.sendKeys(empName);

			String typeOfLeave = xlsReader.getCellData("AssignLeaveDetails", "TypeOfLeave", rowNum);
			System.out.println(typeOfLeave);
			Select select = new Select(leaveType);
			select.selectByVisibleText(typeOfLeave);

			fromDateEle.clear();

			String leaveFromDate = xlsReader.getCellData("AssignLeaveDetails", "StringLeaveFromDate", rowNum);
			System.out.println(leaveFromDate);
			fromDateEle.clear();
			fromDateEle.sendKeys(leaveFromDate);

			toDateEle.clear();
			String leaveToDate = xlsReader.getCellData("AssignLeaveDetails", "StringLeaveToDate", rowNum);
			System.out.println(leaveToDate);
			toDateEle.clear();
			toDateEle.sendKeys(leaveToDate);
			toDateEle.sendKeys(Keys.ENTER);
			commentsBox.clear();
			commentsBox.sendKeys(empName + " is going on leave from "
					+ leaveFromDate + " to " + leaveToDate);

			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].click();", assignBtn);
			//assignBtn.click();
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("confirmOkButton")));
			js.executeScript("arguments[0].click();", confirmLeaveBtn);
			Thread.sleep(2000);
			//confirmLeaveBtn.click();

			String bodyText = driver.findElement(By.tagName("body")).getText();
			Assert.assertTrue(bodyText.contains("Successfully Assigned"), "Msg not displayed");
			
			Thread.sleep(2000);

		}
	}
}
