package com.hrm.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.util.Xls_Reader;

public class EmpListPage extends BaseClass {
	Xls_Reader reader;
	// Page Factory
	@FindBy(id = "btnAdd")
	WebElement addEmpBtn;

	@FindBy(name = "empsearch[employee_name][empName]")
	WebElement empNameFieldInSearch;

	@FindBy(id = "searchBtn")
	WebElement searchBtn;

	@FindBy(xpath = "//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']")
	List<WebElement> allRows;

	@FindBy(xpath = "//table[@id='resultTable']//td[3]/a")
	WebElement fNameLink;

	@FindBy(xpath = "//input[starts-with(@id='ohrmList_chkSelectRecord')]")
	WebElement allUserCheckBox;

	@FindBy(id = "btnDelete")
	WebElement deleteBtn;

	@FindBy(id = "dialogDeleteBtn")
	WebElement confirmDeleteBtn;

	public EmpListPage() {
		PageFactory.initElements(driver, this);
		reader = new Xls_Reader(
				"C://Users//browse//Automation//TrialOrangeHrm//src//main//java//com//hrm//qa//testdata//Test Data.xlsx");

	}

	// Actions
	public AddEmpPage goToAddNewEmpPage() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", addEmpBtn);
		// addEmpBtn.click();
		return new AddEmpPage();
	}

	public boolean checkNameInResultTable() throws InterruptedException {
		empNameFieldInSearch.clear();
		boolean flag = false;
		int rowCount = reader.getRowCount("NewUserDetails");
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE; 
			empNameFieldInSearch.sendKeys(del);
			Thread.sleep(3);
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].value = '';", empNameFieldInSearch);
			
			empNameFieldInSearch.clear();
			empNameFieldInSearch.click();
			String empName = reader.getCellData("NewUserDetails", "FullName", rowNum);
			empNameFieldInSearch.sendKeys(empName);
			searchBtn.click();
			String fName = reader.getCellData("NewUserDetails", "FirstName", rowNum);
			if (fNameLink.getText().equals(fName)) {
				flag = true;
			}
		}
		return flag;
	}

	public void deleteEmp() {
		int rowCount = reader.getRowCount("NewUserDetails");
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			for (WebElement eachRow : allRows) {
				List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
				String nameCol = allCols.get(2).findElement(By.tagName("a")).getText();
				String fName = reader.getCellData("NewUserDetails", "FirstName", rowNum);
				if (nameCol.contains(fName)) {
					driver.findElement(By.xpath("./input[starts-with(@id='ohrmList_chkSelectRecord')]")).click();
					;

				}
				deleteBtn.click();
				confirmDeleteBtn.click();
			}

		}
	}

	public void deleteOneEmp() throws InterruptedException {
		for (WebElement eachRow : allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String nameCol = allCols.get(2).findElement(By.tagName("a")).getText();
			if (nameCol.equals("Steven")) {
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript("arguments[0].scrollIntoView(true);", allCols.get(2).findElement(By.tagName("a")));
				Thread.sleep(2000);
				allCols.get(0).findElement(By.xpath("./input[starts-with(@id,'ohrmList_chkSelectRecord')]")).click();
				Thread.sleep(3000);
			}
		}
	}
}
