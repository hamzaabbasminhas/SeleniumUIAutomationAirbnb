package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObjects.AirbnbHome;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base {

	public WebDriver driver;
	public Properties prop;
	private static Logger log = LogManager.getLogger(base.class.getName());

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();

		String base_path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(base_path + "//src//main//java//Resources//data.properties");

		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", base_path + "//src//main//java//Resources//chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			// execute in chrome driver

		} else if (browserName.equals("firefox")) {

			// firefox code

		} else if (browserName.equals("IE")) {

			// IE code
		}

		// Implicit wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;

	}
	
	public void searchForProperties(String location, String checkinDate, String checkoutDate) {

		// creating object to that class and invoke methods of it
		driver.get(prop.getProperty("url"));
		log.info("Open bowser and go to " + prop.getProperty("url"));

		// creating object of AirbnbHome class to get access to web elements present on
		// the page
		AirbnbHome home = new AirbnbHome(driver);

		home.locationField().sendKeys(location);
		home.locationField().sendKeys(Keys.ENTER);
		log.info("Select " + location + " as a location");

		// Calculate and select Check in date
		String arr[] = checkinDate.split("/", 3);

		int count = home.CalendarDate().size();

		for (int i = 0; i < count; i++) {
			String text = home.CalendarDate().get(i).getText();
			if (text.equals(arr[1])) {
				home.CalendarDate().get(i).click();
				break;

			}

		}
		
		log.info("Entering checkin Date");

		// Calculate and select Check out date
		String arr1[] = checkoutDate.split("/",3);
	
		int end = Integer.parseInt(arr[1]);
		end = end + 31;
		
		int dateEnd = Integer.parseInt(arr1[1]);
		
		if(dateEnd < 10 )
		{
			dateEnd = dateEnd + 10;
		}
		for (int i = end ; i < count; i++) {
			
			String text =home.CalendarDate().get(i).getText();
			String text2 = text;
			int text1 = Integer.parseInt(text2);
			if(text1<10)
				text1 = text1 +10;
			
		if (text1 == dateEnd) {
			home.CalendarDate().get(i).click();
				break;

			}

		}
		
		log.info("Entering Checkout Date");
		
		home.guestsField().click();

		home.adultsButton().click();
		home.adultsButton().click();

		home.childrenButton().click();

		log.info("Select Number of guests as 2 adults and 1 child");

		home.searchButton().click();
		log.info("Search for properties");

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		log.info("Capturing Screenshot");
		return destinationFile;

	}

}
