
package BasicPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDriver {
    WebDriver driver;
    
    public TestDriver() {
    	System.setProperty("webdriver.gecko.driver", "C:/Users/browse/Downloads/geckodriver-v0.26.0-win64/geckodriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\browse\\Downloads\\chromedriver_win32/chromedriver.exe");
		this.driver = new ChromeDriver();
    }
    
	public void getTitle() {
		/*
		System.setProperty("webdriver.gecko.driver", "C:/Users/browse/Downloads/geckodriver-v0.26.0-win64/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\browse\\Downloads\\chromedriver_win32/chromedriver.exe");
		this.driver = new ChromeDriver();
		*/
		this.driver.get("https://www.google.com/");
		String title = this.driver.getTitle();
		System.out.println(title);
		String url = this.driver.getCurrentUrl();
		System.out.println(url);
		this.driver.quit();
	}
	
	public static void main(String[] args) {
		TestDriver t = new TestDriver();
		t.getTitle();
	}
}
