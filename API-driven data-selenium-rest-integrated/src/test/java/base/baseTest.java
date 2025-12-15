package base;

import utils.configReader;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class baseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setup() {

		RestAssured.baseURI = configReader.getProperty("apiBaseUrl");

		String browser = configReader.getProperty("browser").toLowerCase();
		boolean headless = Boolean.parseBoolean(configReader.getProperty("headless"));

		switch (browser) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			if (headless)
				chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser specified in config.properties: " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(configReader.getProperty("apiBaseUrl"));
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}