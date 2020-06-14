package com.hrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.util.Xls_Reader;

public class AddEmpPage extends BaseClass {
	Xls_Reader reader;
	// Page Factory

	@FindBy(id = "firstName")
	WebElement fNameField;

	@FindBy(id = "middleName")
	WebElement mNameField;

	@FindBy(id = "lastName")
	WebElement lNameField;

	@FindBy(id = "photofile")
	WebElement photoField;

	@FindBy(id = "chkLogin")
	WebElement chkLoginField;

	@FindBy(id = "user_name")
	WebElement uNameField;

	@FindBy(id = "user_password")
	WebElement pwdField;

	@FindBy(id = "re_password")
	WebElement confirmPwdField;

	@FindBy(id = "status")
	WebElement statusField;

	@FindBy(id = "btnSave")
	WebElement saveBtn;

	@FindBy(id = "menu_pim_addEmployee")
	WebElement addEmpTab;

	public AddEmpPage() {
		PageFactory.initElements(driver, this);
		reader = new Xls_Reader(
				"C://Users//browse//Automation//TrialOrangeHrm//src//main//java//com//hrm//qa//testdata//Test Data.xlsx");

	}

	// Actions

	public void addNewEmpFromExcelFile() throws InterruptedException {
		int rowCount = reader.getRowCount("NewUserDetails");
		System.out.println(rowCount);

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			System.out.println("New Emp on row no." +  rowNum);

			Thread.sleep(2000);
			fNameField.clear();
			fNameField.sendKeys(reader.getCellData("NewUserDetails", "FirstName", rowNum));
			System.out.println(fNameField.getText());

			lNameField.clear();
			lNameField.sendKeys(reader.getCellData("NewUserDetails", "LastName", rowNum));
			System.out.println(lNameField.getText());
			String path = reader.getCellData("NewUserDetails", "Path", rowNum);
			System.out.println(path);
			photoField.sendKeys(reader.getCellData("NewUserDetails", "Path", rowNum));
			Thread.sleep(3000);
			chkLoginField.click();
			uNameField.sendKeys(reader.getCellData("NewUserDetails", "UserName", rowNum));
			pwdField.sendKeys(reader.getCellData("NewUserDetails", "Password", rowNum));
			confirmPwdField.sendKeys(reader.getCellData("NewUserDetails", "Password", rowNum));
			saveBtn.click();
			Thread.sleep(2000);
			addEmpTab.click();

		}
	}
}
