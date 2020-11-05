package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Properties {

	public WebDriver driver;

	By location_filter = By.xpath("//header/div[1]/div[2]/div[1]/div[1]/button[1]/div[1]");
	By date_filter = By.xpath(
			"/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[2]/div[1]/div[1]/button[2]/div[1]");
	By guest_filter = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[2]/div[1]/div[1]/button[3]/div[1]");
	By num_of_guests = By.xpath(
			"//body[@class='with-new-header']/div/div/div/div/div[@class='_16grqhk']/main[@id='site-content']/div[@class='content-container']/div[@id='ExploreLayoutController']/div[@class='_e296pg']/div[@class='_8h8epe3']/div[@class='_twmmpk']/div[@class='_19qnt1y']/div[@class='_1gw6tte']/div[@class='_uhpzdny']/div/div/div/div/div/div[@class='_fhph4u']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]");

	By more_filters_button = By.xpath("//body/div[4]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/div[2]/div[1]/div[5]/button[1]");
	By room_filter_checkBox = By.xpath(
			"/html[1]/body[1]/div[11]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/button[2]/span[1]");
	By pool_filter_checkBox = By.xpath("//div[6]//div[1]//div[1]//div[4]//label[1]//span[1]//span[1]");
	By show_button = By.xpath("//button[@class='_m095vcq']");
	By property_to_verify = By.xpath("//body[@class='with-new-header']/div/div/div/div/div[@class='_16grqhk']/main[@id='site-content']/div[@class='content-container']/div[@id='ExploreLayoutController']/div[@class='_e296pg']/div[@class='_8h8epe3']/div[@class='_twmmpk']/div[@class='_19qnt1y']/div[@class='_1gw6tte']/div[@class='_uhpzdny']/div/div/div/div/div/div[@class='_fhph4u']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]");

	public Properties(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLocationFilter() {
		return driver.findElement(location_filter);
	}

	public WebElement getdateFilter() {
		return driver.findElement(date_filter);
	}

	public WebElement getGuestFilter() {
		return driver.findElement(guest_filter);
	}

	public WebElement getNumOfGuest() {
		return driver.findElement(num_of_guests);
	}

	public WebElement moreFilterButton() {
		return driver.findElement(more_filters_button);
	}

	public WebElement roomFilterCheckbox() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(room_filter_checkBox));
		return driver.findElement(room_filter_checkBox);
	}

	public WebElement poolFilterCheckbox() {
		return driver.findElement(pool_filter_checkBox);
	}

	public WebElement showButton() {
		return driver.findElement(show_button);
	}

	public WebElement propertyToVerify() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(property_to_verify));
		return driver.findElement(property_to_verify);
	}

}
