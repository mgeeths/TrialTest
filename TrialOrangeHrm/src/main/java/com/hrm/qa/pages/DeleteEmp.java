package com.hrm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.util.Xls_Reader;

public class DeleteEmp extends BaseClass {
	Xls_Reader reader;

	// Page Factory
	@FindBy(xpath = "//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']")
	List<WebElement> allRows;

	@FindBy(xpath = "//table[@id='resultTable']//td[2]/a")
	WebElement fNameLink;
	@FindBy(xpath = "//input[starts-with(@id='ohrmList_chkSelectRecord')]")
	WebElement allUserCheckBox;

	@FindBy(id = "btnDelete")
	WebElement deleteBtn;

	@FindBy(id = "dialogDeleteBtn")
	WebElement confirmDeleteBtn;

	public DeleteEmp() {
		PageFactory.initElements(driver, this);
		reader = new Xls_Reader(
				"C://Users//browse//Automation//TrialOrangeHrm//src//main//java//com//hrm//qa//testdata//Test Data.xlsx");

	}

	// Actions
	public void deleteEmp() throws InterruptedException {
		int rowCount = reader.getRowCount("NewUserDetails");
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			for (WebElement eachRow : allRows) {
				List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
				String nameCol = allCols.get(2).findElement(By.tagName("a")).getText();
				String fName = reader.getCellData("NewUserDetails", "FirstName", rowNum);
				if (nameCol.contains(fName)) {
					System.out.println(fName);
					JavascriptExecutor js = ((JavascriptExecutor) driver);
					js.executeScript("arguments[0].scrollIntoView(true);", allCols.get(2).findElement(By.tagName("a")));
					Thread.sleep(2000);
					allCols.get(0).findElement(By.xpath("./input[starts-with(@id,'ohrmList_chkSelectRecord')]"))
							.click();
				}
			}

		}
		deleteBtn.click();
		confirmDeleteBtn.click();
	}

}
