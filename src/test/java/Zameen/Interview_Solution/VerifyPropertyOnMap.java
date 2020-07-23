package Zameen.Interview_Solution;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.base;
import pageObjects.PropertyDetailsPage;

public class VerifyPropertyOnMap extends base {
	public WebDriver driver;

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		log.info("Driver Initialized");
	}

	@Test(dataProvider = "getData")
	public void verifyPropertyOnMap(String location, String checkinDate, String checkoutDate) {

		searchForProperties(location, checkinDate, checkoutDate);

		PropertyDetailsPage prop_details = new PropertyDetailsPage(driver);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Actions builder = new Actions(driver);
		builder.moveToElement(prop_details.priceOfProperty()).perform();
		log.info("Hover over the first property");
		
		String price = prop_details.priceOfProperty().getText();
		log.info("Checking price of property");
		
		price = price.replaceAll("\\s+", "");

		int a = 1;
		if (price.contains("Discountedprice")) {
			price.replaceAll("Discountedprice", "");
			a = 2;
		}
		String splitExpected[] = price.split(":", 3);

		int size = prop_details.pointerOnCalendar().size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			String text = prop_details.pointerOnCalendar().get(i).getText();
			
			System.out.println(text);
			System.out.println(splitExpected[a]);
			if (text.equals(splitExpected[a])) {
				Assert.assertTrue(true);
				log.info("Verifying property appears on calendar");
				break;
			}
			count++;
		}

		if (count == size) {
			Assert.assertTrue(false);
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

}
