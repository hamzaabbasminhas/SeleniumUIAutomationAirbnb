package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PropertyDetailsPage {

	public WebDriver driver;

	By show_amenities = By.xpath(
			"/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[2]/div[3]/a[1]");
	By pool_amenity = By.xpath("//div[@class='_vzrbjl'][contains(text(),'Pool')]");

	By price_of_property = By.xpath("//div[@class='_fhph4u']//div[1]//div[1]//div[1]//div[1]//div[@class='_3gn0lkf']//div[@class='_tmwq9g']//div[@class='_1bbeetd']//div[@class='_ls0e43']//div[@class='_l2ulkt8']//div[1]//span//span[@class='_1p7iugi']");
									 	
	By pointer_on_calendar = By.xpath("//span[@class='_1nq36y92']");
	
	public PropertyDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement poolAmenity() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pool_amenity));
		return driver.findElement(pool_amenity);
	}

	public WebElement show_amenties() {
		return driver.findElement(show_amenities);
	}
	
	public WebElement priceOfProperty()
	{
		return driver.findElement(price_of_property);
	}
	
	public List<WebElement> pointerOnCalendar()
	{
		return driver.findElements(pointer_on_calendar);
	}

}
