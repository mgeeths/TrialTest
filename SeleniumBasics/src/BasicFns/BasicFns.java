package BasicFns;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.lang.Thread;

public class BasicFns {

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", "C:/Users/browse/Downloads/geckodriver-v0.26.0-win64/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\browse\\Downloads\\chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/");
		String title = driver.getTitle();
		System.out.println(title);
		
		/* Button elements:
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Add Element')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
		
		
		Find Links:
		driver.findElement(By.partialLinkText("Broken")).click();
		System.out.println(driver.getCurrentUrl());
		String page_url = "http://the-internet.herokuapp.com/broken_images";
		String curr_url = driver.getCurrentUrl();
		if (page_url.equals(curr_url)) {
			System.out.println("Correct ");
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.findElement(By.linkText("Dropdown")).click();
		Select select = new Select(driver.findElement(By.id("dropdown")));
		select.selectByVisibleText("Option 1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//Frames:
		driver.findElement(By.linkText("Frames")).click();
		driver.findElement(By.linkText("Nested Frames")).click();
		driver.switchTo().frame("frame-bottom");
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.tagName("body")));
	
		driver.quit();
		}
	}
