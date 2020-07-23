package Zameen.Interview_Solution;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.base;
import pageObjects.Properties;
import pageObjects.PropertyDetailsPage;

public class VerifyDetailPageFilters extends base {

	public WebDriver driver;

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver Initialized");
	}

	@Test(dataProvider = "getData")
	public void verifyPageFilters(String location, String checkinDate, String checkoutDate) {

		searchForProperties(location, checkinDate, checkoutDate);

		Properties prop = new Properties(driver);

		prop.moreFilterButton().click();
		log.info("Clicking on more filter button");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 5; i++) {
			prop.roomFilterCheckbox().click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		log.info("Selecting 5 Rooms filter");

		prop.poolFilterCheckbox().click();

		log.info("Selecting Pool filter");

		prop.showButton().click();

		log.info("Click on show button");

		String expected = prop.getNumOfGuest().getText();
		String splitExpected[] = expected.split(" · ", 3);
		String furtherSplitExpected[] = splitExpected[1].split(" ", 2);

		int expected1 = Integer.parseInt(furtherSplitExpected[0]);

		boolean check = false;
		if (expected1 >= 5) {
			check = true;
			Assert.assertTrue(check);
			log.info(
					"Verifying that the properties displayed on the first page have at least the number of selected bedroom");
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.propertyToVerify().click();
		log.info("Opening detail page of a property");

		String parent = driver.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will return
		// Set of St//rings
		Set<String> s1 = driver.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();
		I1.next();
		driver.switchTo().window(I1.next());

		PropertyDetailsPage prop_details = new PropertyDetailsPage(driver);
		prop_details.show_amenties().click();
		log.info("Opening all amenities");

		String actual = prop_details.poolAmenity().getText();
		expected = "Pool\nPrivate or shared";

		Assert.assertEquals(actual, expected);
		;
		log.info("Verifying Pool is displayed");
	}

	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Close Browser");
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][3];

		data[0][0] = "Rome,Italy";

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 7);
		String startDate = dateFormat.format(cal.getTime());

		data[0][1] = startDate;

		cal.add(Calendar.DATE, 7);
		String endDate = dateFormat.format(cal.getTime());

		data[0][2] = endDate;

		return data;

	}

}
