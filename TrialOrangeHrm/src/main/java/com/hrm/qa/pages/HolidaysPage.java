package com.hrm.qa.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;

public class HolidaysPage extends BaseClass {
	// PageFactory

	@FindBy(xpath = "//a[contains(@class,'toggle')]")
	WebElement toggleBtn;

	@FindBy(xpath = "//input[@id='calFromDate']")
	WebElement fromDateField;

	@FindBy(xpath = "//input[@id='calToDate']")
	WebElement toDateField;

	@FindBy(id = "btnSearch")
	WebElement searchBtn;

	@FindBy(id = "btnAdd")
	WebElement addBtn;

	@FindBy(id = "btnDelete")
	WebElement deleteBtn;

	@FindBy(xpath = "//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']")
	List<WebElement> allRows;

	@FindBy(xpath = "//input[@class='btn reset']")
	WebElement cancelDelete;

	@FindBy(xpath = "//input[@id='dialogDeleteBtn']")
	WebElement okDeleteBtn;

	public HolidaysPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void toggleButtonHideAndShowFilter() {
		toggleBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOfAllElements(fromDateField, toDateField, searchBtn));
		Assert.assertFalse(searchBtn.isDisplayed());
		toggleBtn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSearch")));
		Assert.assertTrue(searchBtn.isDisplayed());
	}

	public List<String> enterDateFields() throws InterruptedException {
		List<String> dateFilterValues = new ArrayList<>();
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('calFromDate').setAttribute('value','2020-02-01')");
		Thread.sleep(2000);
		String FromDate = fromDateField.getAttribute("value");
		dateFilterValues.add(FromDate);
		System.out.println(FromDate);
		Thread.sleep(2000);
		String ToDate = toDateField.getAttribute("value");
		dateFilterValues.add(ToDate);
		System.out.println(ToDate);
		return dateFilterValues;
	}
	
	/*To work with Date objects, import Date and DateFormate from java.test
	 * DateFormat takes two parameters : the format in which your date is and the language
	 * Get the text ie. the string format of the date value from your appln 
	 * To convert that string to Date obj, use format.parse method. Then use that 
	 * dateObj to check if it is within a date range by using the before and after method.
	 * Here, the if condition checks if the given date is before the fromDate OR if it is 
	 * after the toDate. If either of the condition is true then the date does not fit
	 * within the range and so the flag is set to False.
	 */
	
	public boolean filterHolidaysByDate() throws ParseException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('calFromDate').setAttribute('value','2020-02-05')");
		js.executeScript("document.getElementById('calToDate').setAttribute('value','2020-11-30')");
		searchBtn.click();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		boolean flag = false;
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			Date dateCol = format.parse(allCols.get(2).getText());
			Date fromDate = format.parse(prop.getProperty("holidayFilterFromDate"));
			Date toDate = format.parse(prop.getProperty("holidayFilterToDate"));

			if(!(dateCol.before(fromDate) || dateCol.after(toDate))){
				flag = true;
			}
		}
		return flag; 
	}

	public ConfigureNewHolidays goToConfigureNewHolidayPage() {
		addBtn.click();
		return new ConfigureNewHolidays();
	}

	public void CancelDeleteHoliday() throws InterruptedException {
		Thread.sleep(2000);
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String name = allCols.get(1).getText();
			if (name.equals(prop.getProperty("deleteHolidayName"))) {
				allCols.get(0).findElement(By.xpath("./input[@type='checkbox']")).click();
				break;
			}
		}
		deleteBtn.click();
		cancelDelete.click();
		Thread.sleep(2000);
	}

	public void DeleteHoliday() throws InterruptedException {
		Thread.sleep(2000);
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String name = allCols.get(1).getText();
			if (name.equals(prop.getProperty("deleteHolidayName"))) {
				allCols.get(0).findElement(By.xpath("./input[@type='checkbox']")).click();
				break;
			}
		}
		deleteBtn.click();
		okDeleteBtn.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Successfully Deleted"));

	}
}
