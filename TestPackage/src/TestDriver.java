
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDriver {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.gecko.driver", "C:/Users/browse/Downloads/geckodriver-v0.26.0-win64/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
	}
}