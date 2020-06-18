package com.hrm.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;

public class ConfigureNewHolidays extends BaseClass {

	// PageFactory
	@FindBy(id = "menu_leave_Configure")
	WebElement configureTab;

	@FindBy(linkText = "Holidays")
	WebElement holidaysLink;

	@FindBy(id = "holiday_description")
	WebElement newHolidayName;

	@FindBy(id = "holiday_date")
	WebElement holidayDatePicker;

	@FindBy(id = "holiday_recurring")
	WebElement recurringChkbox;

	@FindBy(id = "saveBtn")
	WebElement saveBtn;

	@FindBy(xpath = "//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']")
	List<WebElement> allRows;

	@FindBy(id = "holiday_length")
	WebElement selectLengthField;

	@FindBy(id = "btnDelete")
	WebElement deleteBtn;

	@FindBy(id = "dialogDeleteBtn")
	WebElement confirmDeleteBtn;

	public ConfigureNewHolidays() {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public void createNewHoliday(String holidayName, String holidayDate) throws InterruptedException {
		newHolidayName.sendKeys(holidayName);
		holidayDatePicker.clear();
		holidayDatePicker.sendKeys(holidayDate);
		holidayDatePicker.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		String date = holidayDatePicker.getText();
		System.out.println(date);
		recurringChkbox.click();
		saveBtn.click();
		Thread.sleep(2000);
	}

	public void resultTableData(String holidayName, String holidayDate) {
		// boolean flag = false;
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String name = allCols.get(1).getText();
			System.out.println(name);
			if (name.equals(holidayName)) {
				String holiDate = allCols.get(2).getText();
				String lengthDay = allCols.get(3).getText();
				String repeatiton = allCols.get(4).getText();
				System.out.println(holiDate);
				System.out.println(lengthDay);
				System.out.println(repeatiton);
				Assert.assertTrue(holiDate.equals(holidayDate), "Date entered is " + holiDate);
				Assert.assertTrue(lengthDay.equals("Full Day"), "Day entered is " + lengthDay);
				Assert.assertTrue(repeatiton.equals("Yes"), "Repetition entered is " + repeatiton);
				break;
			}

		}
	}

	/*
	 * editHoliday function will return two string values. So to return them,
	 * declare a List<String> variable results. This declaration is done before the
	 * second for loop, as the list will be created only there.
	 */
	public List<String> editHoliday(String holidayName) throws InterruptedException {
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String name = allCols.get(1).getText();
			if (name.equals(holidayName)) {
				WebElement holidayLink = allCols.get(1).findElement(By.tagName("a"));
				Thread.sleep(2000);
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript("arguments[0].click();", holidayLink);
				String url = driver.getCurrentUrl();
				Assert.assertTrue(url
						.contains("https://opensource-demo.orangehrmlive.com/index.php/leave/defineHoliday?hdnEditId"));
				recurringChkbox.click();
				Select select = new Select(selectLengthField);
				select.selectByVisibleText("Half Day");
				saveBtn.click();
				break;
			}
		}
		List<String> results = new ArrayList<>();
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String name = allCols.get(1).getText();
			if (name.equals(holidayName)) {
				String lengthOfHoliday = allCols.get(3).getText();
				results.add(lengthOfHoliday);
				String repetition = allCols.get(4).getText();
				results.add(repetition);

			}
		}
		return results;
	}

	public void deleteHolidayCreated(String holidayName) {
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String nameLink = allCols.get(1).getText();
			if (nameLink.equals(holidayName)) {
				WebElement box = allCols.get(0).findElement(By.xpath("./input[@type='checkbox']"));
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript("arguments[0].scrollIntoView(true);", box);
				box.click();
				deleteBtn.click();
				confirmDeleteBtn.click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Successfully Deleted"));
				break;
			}

		
		}
	}
	
	
}
