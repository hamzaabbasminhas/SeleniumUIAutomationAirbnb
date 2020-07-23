package Zameen.Interview_Solution;

import org.testng.annotations.Test;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.base;
import pageObjects.AirbnbHome;
import pageObjects.Properties;

public class VerifySearchCriteria extends base {
	public WebDriver driver;

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver Initialized");
	}

	@Test(dataProvider = "getData")
	public void verifySearchCriteria(String location, String checkinDate, String checkoutDate) {

		searchForProperties(location, checkinDate, checkoutDate);

		Properties prop = new Properties(driver);
		// Verify all applied filters are correct
		String actual = prop.getLocationFilter().getText();

		String expected = "Location\n" + "Rome";
		Assert.assertEquals(actual, expected);
		log.info("Verifying Location filter");

		String arr[] = checkinDate.split("/", 3);
		String arr1[] = checkoutDate.split("/", 3);

		String startMonth = convertMonth(arr[0]);
		String endMonth = convertMonth(arr1[0]);

		String startDate = convertDate(arr[1]);
		String endDate = convertDate(arr1[1]);

		actual = prop.getdateFilter().getText();
		expected = "Check in / Check out\n" + startMonth + " " + startDate + " - " + endMonth + " " + endDate;

		Assert.assertEquals(actual, expected);
		log.info("Verifying Date Filter");

		actual = prop.getGuestFilter().getText();
		expected = "Guests\n3 guests";

		Assert.assertEquals(actual, expected);
		log.info("Verifying Guests Filter");

		// Verifying properties first page can accommodate at least the selected number
		// of guests
		expected = prop.getNumOfGuest().getText();
		String splitExpected[] = expected.split(" · ", 2);
		String furtherSplitExpected[] = splitExpected[0].split(" ", 2);

		int expected1 = Integer.parseInt(furtherSplitExpected[0]);

		boolean check = false;
		if (expected1 >= 3) {
			check = true;
			Assert.assertTrue(check);
			log.info("Verifying properties on first page can accommodate at least the selected number of guests");
		}
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

	public String convertMonth(String date) {

		if (date.equals("01")) {
			date = "Jan";
		}

		else if (date.equals("02")) {
			date = "Feb";
		}

		else if (date.equals("03")) {
			date = "Mar";
		}

		else if (date.equals("04")) {
			date = "Apr";
		}

		else if (date.equals("05")) {
			date = "May";
		}

		else if (date.equals("06")) {
			date = "Jun";
		}

		else if (date.equals("07")) {
			date = "Jul";
		}

		else if (date.equals("08")) {
			date = "Aug";
		}

		else if (date.equals("09")) {
			date = "Sep";
		}

		else if (date.equals("10")) {
			date = "Oct";
		}

		else if (date.equals("11")) {
			date = "Nov";
		}

		else if (date.equals("12")) {
			date = "Dec";
		}

		return date;
	}

	public String convertDate(String date) {
		if (date.equals("01")) {
			date = "1";
		}

		else if (date.equals("02")) {
			date = "2";
		}

		else if (date.equals("03")) {
			date = "3";
		}

		else if (date.equals("04")) {
			date = "4";
		}

		else if (date.equals("05")) {
			date = "5";
		}

		else if (date.equals("06")) {
			date = "6";
		}

		else if (date.equals("07")) {
			date = "7";
		}

		else if (date.equals("08")) {
			date = "8";
		}

		else if (date.equals("09")) {
			date = "9";
		}

		return date;
	}
}
