package com.hrm.qa.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hrm.qa.util.TestUtil;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\browse\\Automation\\LearnOrangeHrm\\src\\main\\java\\com\\hrm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}else {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\browse\\Downloads\\geckodriver-v0.26.0-win64.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
	}
}
	
