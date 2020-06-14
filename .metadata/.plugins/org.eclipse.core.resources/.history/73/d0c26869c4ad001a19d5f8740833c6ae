package com.hrm.qa.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;

public class LeaveListPage extends BaseClass {

	// Page Factory
	@FindBy(xpath="//input[@id='calFromDate']")
	WebElement fromDateField;
	
	@FindBy(id="calToDate")
	WebElement toDateField;

	@FindBy(xpath = "//img[@class='ui-datepicker-trigger']")
	WebElement fromCalender;

	@FindBy(xpath = "(//img[@class='ui-datepicker-trigger'])[2]")
	WebElement toCalender;

	@FindBy(xpath = "//select[@data-handler='selectMonth']")
	WebElement selectMonth;

	@FindBy(xpath = "//select[@data-handler='selectYear']")
	WebElement selectYear;

	@FindBy(linkText = "prop.getProperty('filterFromDay')")
	WebElement selectDay;

	@FindBy(id = "leaveList_chkSearchFilter_checkboxgroup_allcheck")
	WebElement allCheckbox;

	@FindBy(xpath = "//input[(@id='leaveList_chkSearchFilter_2') and (@value='2')]")
	WebElement scheduledChkbox;

	@FindBy(xpath = "//input[@id='leaveList_chkSearchFilter_1']")
	WebElement pendingChkbox;

	@FindBy(xpath = "//input[@id='leaveList_chkSearchFilter_3']")
	WebElement takenChkbox;

	@FindBy(xpath = "//input[@id='leaveList_chkSearchFilter_0']")
	WebElement cancelledChkbox;

	@FindBy(xpath = "//input[@id='leaveList_chkSearchFilter_-1']")
	WebElement rejectedChkbox;

	@FindBy(id = "leaveList_txtEmployee_empName")
	WebElement empNameField;

	@FindBy(id="btnReset")
	WebElement resetBtn;
	
	@FindBy(id = "btnSearch")
	WebElement searchBtn;

	@FindBy(xpath = "//input[@id = 'btnSave']")
	WebElement saveBtn;

	@FindBy(xpath="//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']")
	List<WebElement> allRows;
	
	public LeaveListPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void enterFromDateFilter() {
		fromCalender.click();
		Select selectm = new Select(selectMonth);
		selectm.selectByVisibleText(prop.getProperty("filterFromMonth"));

		Select selecty = new Select(selectYear);
		selecty.selectByVisibleText(prop.getProperty("filterFromYear"));

		driver.findElement(By.linkText(prop.getProperty("filterFromDay"))).click();
		//fromDateField.sendKeys(Keys.ENTER);
		System.out.println(fromDateField.getAttribute("value"));
	}

	public void enterToDateFilter() {
		toCalender.click();
		Select selectm = new Select(selectMonth);
		selectm.selectByVisibleText(prop.getProperty("filterToMonth"));

		Select selecty = new Select(selectYear);
		selecty.selectByVisibleText(prop.getProperty("filterToYear"));

		driver.findElement(By.linkText(prop.getProperty("filterToDay"))).click();
		System.out.println(toDateField.getAttribute("value"));

	}

	public void enterEmpNameInFilter() throws InterruptedException {
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", allCheckbox);
		js.executeScript("arguments[0].click();", empNameField);

		empNameField.clear();
		empNameField.sendKeys(prop.getProperty("empName"));
		searchBtn.click();
		Thread.sleep(2000);

	}

	public boolean resultTableNameCol() {

		boolean flag = allRows.size() > 0;
		for (int i = 0; i < allRows.size(); i++) {
			List<WebElement> nameCol = allRows.get(i).findElements(By.xpath("./td"));
			String name = nameCol.get(1).getText();
			System.out.println(name);
			Assert.assertEquals(name, prop.getProperty("empName"));
		}
		return flag;

	}

	// returns if all the rows has the status 'scheduled' only
	public boolean scheduledStatus() {
		fromDateField.clear();
		toDateField.clear();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (!scheduledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", scheduledChkbox);
		}
		if (pendingChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", pendingChkbox);
		}
		if (allCheckbox.isSelected()) {
			js.executeScript("arguments[0].click();", allCheckbox);
		}
		if (takenChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", takenChkbox);
		}
		if (cancelledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", cancelledChkbox);
		}
		if (rejectedChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", rejectedChkbox);
		}
		empNameField.clear();
		searchBtn.click();
	
		boolean flag = allRows.size() > 0;
		for (int i = 0; i < allRows.size(); i++) {
			List<WebElement> statusCol = allRows.get(i).findElements(By.xpath("./td"));
			String status;
			status = statusCol.get(5).findElement(By.xpath("./a")).getText();
			System.out.println(status);
			// Assert.assertTrue(status.contains("Scheduled"));
			if (!status.contains("Scheduled")) {
				flag = false;
			}
		}
		return flag;
	}

	// returns if all the rows has the status 'taken' only

	public boolean takenStatus() {
		fromDateField.clear();
		toDateField.clear();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (scheduledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", scheduledChkbox);
		}
		if (pendingChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", pendingChkbox);
		}
		if (allCheckbox.isSelected()) {
			js.executeScript("arguments[0].click();", allCheckbox);
		}
		if (! takenChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", takenChkbox);
		}
		if (cancelledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", cancelledChkbox);
		}
		if (rejectedChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", rejectedChkbox);
		}
		empNameField.clear();
		searchBtn.click();

		boolean flag = (allRows.size() > 0);
		for (WebElement thisRow : allRows) {
			List<WebElement> allColumns = thisRow.findElements(By.xpath("./td"));
			String status = allColumns.get(5).findElement(By.xpath("./a")).getText();
			System.out.println(status);
			if (!status.contains("Taken")) {
				flag = false;
			}

		}
		return flag;
	}

	public boolean pendingStatus() {
		fromDateField.clear();
		toDateField.clear();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (scheduledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", scheduledChkbox);
		}
		if (! pendingChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", pendingChkbox);
		}
		if (allCheckbox.isSelected()) {
			js.executeScript("arguments[0].click();", allCheckbox);
		}
		if (takenChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", takenChkbox);
		}
		if (cancelledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", cancelledChkbox);
		}
		if (rejectedChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", rejectedChkbox);
		}
		empNameField.clear();
		searchBtn.click();

		boolean flag = (allRows.size() > 0);
		for (WebElement thisRow : allRows) {
			List<WebElement> allColumns = thisRow.findElements(By.xpath("./td"));
			String status = allColumns.get(5).findElement(By.xpath("./a")).getText();
			System.out.println(status);
			if (!status.contains("Pending")) {
				flag = false;
			}

		}
		return flag;
	}

	
	public boolean allStatus() {
		fromDateField.clear();
		toDateField.clear();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (scheduledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", scheduledChkbox);
		}
		if (pendingChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", pendingChkbox);
		}
		if (! allCheckbox.isSelected()) {
			js.executeScript("arguments[0].click();", allCheckbox);
		}
		if (takenChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", takenChkbox);
		}
		if (cancelledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", cancelledChkbox);
		}
		if (rejectedChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", rejectedChkbox);
		}

		searchBtn.click();

		boolean flag = (allRows.size() > 0);
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String status = allCols.get(5).findElement(By.xpath("./a")).getText();
			System.out.println(status);
			if (!(status.contains("Scheduled") || status.contains("Taken") || status.contains("Cancelled")
					|| status.contains("Pending"))) {
				flag = false;
			}
		}
		return flag;
	}

	/*
	 * If a function returns something, then its data type should be mentioned in
	 * the function defn. So, when we want to assert something in the testPage, the
	 * called function should return that value to be asserted. The logic should be,
	 * to make sure what the function should return(for it to be asserted correctly,
	 * especially when the function involves a for loop or if then st. In this
	 * function, the flag is set to False to begin with. This will make the function
	 * return false if there are no rows in the search result. As, there should be
	 * atleast one row , otherwise, the function should return false in order to
	 * fail it. Then, check if the conditions in the if st are satisfied, in order
	 * to go into the steps to be executed. Once it is executed successfully, the
	 * flag is set to true. Then it goes to the next item in the for loop. So, the
	 * flag remains True as long as each item is successfully executed. Even if one
	 * item is not executed,(because the if cond is not met), the flag will be set
	 * to flase and the function will return false. Hence failure. i.e. This
	 * function will return true only if all the line items are successfully
	 * executed.
	 * 
	 * 
	 */
	public boolean cancelStatus() throws InterruptedException {
		fromDateField.clear();
		toDateField.clear();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (scheduledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", scheduledChkbox);
		}
		if (pendingChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", pendingChkbox);
		}
		if (allCheckbox.isSelected()) {
			js.executeScript("arguments[0].click();", allCheckbox);
		}
		if (takenChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", takenChkbox);
		}
		if (! cancelledChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", cancelledChkbox);
		}
		if (rejectedChkbox.isSelected()) {
			js.executeScript("arguments[0].click();", rejectedChkbox);
		}
		empNameField.clear();
		searchBtn.click();

		boolean flag = allRows.size()>0;
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String status = allCols.get(5).findElement(By.tagName("a")).getText();
			if (!status.contains("Cancelled")) {
				flag = false;
			}
		}

		return flag;
	}

	public void cancelOneAssignedLeave() throws InterruptedException {
		fromDateField.clear();
		toDateField.clear();
		empNameField.click();
		empNameField.clear();
		empNameField.sendKeys(prop.getProperty("empName"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", allCheckbox);
		js.executeScript("arguments[0].click();", searchBtn);

		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			// String type = allCols.get(2).getText();
			String status = allCols.get(5).getText();
			if (!status.contains("Cancelled")) {
				Select select = new Select(
						allCols.get(7).findElement(By.xpath("./select[starts-with(@id,'select_leave_action')]")));
				select.selectByVisibleText("Cancel");
				saveBtn.click();
				Thread.sleep(2000);
				String bodyText = driver.findElement(By.tagName("body")).getText();
				Assert.assertTrue(bodyText.contains("Successfully Updated"), "Successfully Updated msg not displayed");
				Thread.sleep(2000);
				break;
			}
		}
	}

	public void cancelManyLeaveAssigned() throws InterruptedException {
		empNameField.click();
		empNameField.clear();
		empNameField.sendKeys(prop.getProperty("empName"));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", allCheckbox);
		js.executeScript("arguments[0].click();", searchBtn);

		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String status = allCols.get(5).getText();
			if (!status.contains("Cancelled")) {
				Select select = new Select(
						allCols.get(7).findElement(By.xpath("./select[starts-with(@id,'select_leave_action')]")));
				select.selectByVisibleText("Cancel");
			}
		}
		saveBtn.click();
		Thread.sleep(2000);
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(bodyText.contains("Successfully Updated"), "Successfully Updated msg not displayed");
		Thread.sleep(2000);
	}
	
	public boolean leaveFilteredByFromDate() throws ParseException {
		
		allCheckbox.click();
		empNameField.sendKeys(prop.getProperty("empName"));
		searchBtn.click();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date fromDateFilter = format.parse(fromDateField.getAttribute("value"));
		Date toDateFilter = format.parse(toDateField.getAttribute("value"));
	
		boolean flag = allRows.size()>0;
		for(WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			Date dateCol = format.parse(allCols.get(0).findElement(By.tagName("a")).getText());
			
			System.out.println(dateCol);
			if(dateCol.before(fromDateFilter) || dateCol.after(toDateFilter)) {
				flag = false;
			}
		}
		return flag;
	}
}
