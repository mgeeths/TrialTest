package TrialPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicFunctions {
	
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/browse/Downloads/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
		BasicFunctions bf = new BasicFunctions();
		bf.login("Admin", "admin123");
		//bf.forgotPassword("Admin");
		//bf.about();
		//bf.goToUser();
		//bf.addUser("Fiona Grace", "fgrace", "Tester1");
		bf.goToJobTab();
		//bf.addWorkShift("AfterNoon Shift");
		//bf.verifyWorkShiftAdded("PMM Shift");
		//bf.verifyTimeInShiftsAdded("AfterNoon Shift");
		bf.verifyCheckboxSelection("Twilight");
	}

    public void login(String uname, String pass) {
	   driver.findElement(By.id("txtUsername")).sendKeys(uname);;
	   driver.findElement(By.id("txtPassword")).sendKeys(pass);
	   driver.findElement(By.name("Submit")).click();
	   driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
	   String user = driver.findElement(By.linkText("Welcome Admin")).getText();
	   System.out.println(user);
	   
	   if(user.equals("Welcome Admin")) {
		   System.out.println("Correct user " + user +" logged in");
	   }
	   else {
			   System.out.println("Incorrect user " + user + "logged in");
		   }
	       
	   //driver.quit();
	}
    
    public void forgotPassword(String uname) {
    	driver.findElement(By.linkText("Forgot your password?")).click();
    	driver.findElement(By.xpath("//input[@type='text']")).sendKeys(uname);
    	driver.findElement(By.id("btnSearchValues")).click();
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	String msg = driver.findElement(By.xpath("//div[@class= 'message warning fadable']")).getText();
    	System.out.println(msg);
    	Alert alert = driver.switchTo().alert();
    	String msgDisplayed = alert.getText();
    	System.out.println(msgDisplayed);
    }
    
    
    public void about() {
    	
    driver.findElement(By.linkText("Welcome Admin")).click();
    WebDriverWait wait = new WebDriverWait(driver,5);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='aboutDisplayLink']"))).click();
    //element.click();
    String aboutBox = driver.findElement(By.id("frmSelectEmployees")).getText();
    System.out.println(aboutBox);
    	
    	
    }
    
    
    public void goToUser() throws InterruptedException {
    	Actions actions = new Actions(driver);
    	actions.moveToElement(driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']"))).build().perform();
    	actions.moveToElement(driver.findElement(By.id("menu_admin_UserManagement"))).build().perform();
    	driver.findElement(By.linkText("Users")).click();
    	Thread.sleep(3000);
    	
    	}
    
    public void addUser(String user, String username, String pwd) {
    	
    	driver.findElement(By.xpath("//input[@name='btnAdd']")).click();
    	Select select = new Select(driver.findElement(By.id("systemUser_userType")));
    	select.selectByVisibleText("Admin");
    	driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']")).sendKeys(user);
    	
    	driver.findElement(By.xpath("//input[@id='systemUser_userName']")).sendKeys(username);
    	Select statusSelect = new Select(driver.findElement(By.xpath("//select[@id='systemUser_status']")));
    	statusSelect.selectByIndex(0);
    	driver.findElement(By.xpath("//input[@id='systemUser_password']")).sendKeys(pwd);
    	driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']")).sendKeys(pwd);
    	WebElement element = driver.findElement(By.xpath("//input[@id='btnSave']"));
    	JavascriptExecutor js = ((JavascriptExecutor)driver);
    	js.executeScript("arg[0].click();", element);
    	
    }
    
    public void goToJobTab() throws InterruptedException {
    	Actions actions = new Actions(driver);
    	actions.moveToElement(driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']"))).build().perform();
    	actions.moveToElement(driver.findElement(By.linkText("Job"))).build().perform();
    	//actions.moveToElement(driver.findElement(By.linkText("Work Shifts"))).build().perform();
    	driver.findElement(By.linkText("Work Shifts")).click();
    	Thread.sleep(3000);
    	
    	}
    
    public void addWorkShift(String shiftName) {
    	
    	driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
    	driver.findElement(By.id("workShift_name")).sendKeys(shiftName);
    	Select selectFrom = new Select(driver.findElement(By.id("workShift_workHours_from")));
    	selectFrom.selectByVisibleText("06:30");
    	Select selectTo = new Select(driver.findElement(By.id("workShift_workHours_to")));
    	selectTo.selectByVisibleText("12:00");
    	//Thread.sleep(3000);
    	Select selectEmp = new Select(driver.findElement(By.id("workShift_availableEmp")));
    	selectEmp.selectByValue("1");
    	selectEmp.selectByValue("6");
    	driver.findElement(By.xpath("//a[@id='btnAssignEmployee']")).click();
    	driver.findElement(By.id("btnSave")).click();
    	
    }
    
    public void verifyWorkShiftAdded(String shiftName) {
    	Boolean linkName = driver.findElement(By.linkText(shiftName)).isDisplayed();
    	
    	if (linkName) {
    		System.out.println(shiftName + " Shift got added");
    	
    	}
    	
   	}
    
    public void verifyTimeInShiftsAdded(String shiftName) {
    	List<WebElement> lineItems = driver.findElements(By.xpath("//table[@id='resultTable']//tbody//tr[@class='odd']|//table[@id='resultTable']//tbody//tr[@class='even']"));
    	
    	for(int i=0; i<=lineItems.size();i++) {
    		String lineName = lineItems.get(i).findElement(By.tagName("a")).getText();
    		System.out.println(lineName);
    		if(lineName.equals(shiftName)) {
    			List <WebElement> allCols = lineItems.get(i).findElements(By.xpath("./td"));
    			allCols.get(0).findElement(By.xpath("./input[@type='checkbox']")).click();
    			String startTime = allCols.get(2).getText();
    			String endTime = allCols.get(3).getText();
    			String hoursPerDay = allCols.get(4).getText();
    			System.out.println("Start time is "+ startTime);
    			System.out.println("End time is "+ endTime);
    			System.out.println("Hours Per Day is "+ hoursPerDay);
    			
    			break;
    		}
    		
    	}
    }
    
    public void verifyCheckboxSelection(String shiftName) {
    	
    	List<WebElement> lineItems = driver.findElements(By.xpath("//table[@id='resultTable']//tbody//tr[@class='odd']|//table[@id='resultTable']//tbody//tr[@class='even']"));
    	for(int l=0; l<=lineItems.size(); l++) {
    		String lineName = lineItems.get(l).findElement(By.tagName("a")).getText();
    		if(lineName.equals(shiftName)){
    			System.out.println(shiftName);
    			lineItems.get(l).findElement(By.xpath("./td/input[@type='checkbox']")).click();
    	}
    
    
    }
    }
}
    

