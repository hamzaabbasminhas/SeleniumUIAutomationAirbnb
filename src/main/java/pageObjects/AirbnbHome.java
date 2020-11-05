package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AirbnbHome {
	
	public WebDriver driver;
	
	By location=By.xpath("//input[@id='bigsearch-query-detached-query']");
    By guests = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/header[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[5]/div[1]");
    By date = By.xpath("//div[@class='_1eu9zfzy']");
    By adults = By.xpath("//div[@id='stepper-adults']//button[2]");
    By children = By.xpath("//div[@id='stepper-children']//button[2]");
    By search = By.xpath("//button[@class='_1mzhry13']");
	
	
	
	
	
	public AirbnbHome(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		
	}


	public WebElement locationField()
	{
		return driver.findElement(location);
	}
	
	public List<WebElement> CalendarDate()
	{
		return driver.findElements(date);
	}
	public WebElement guestsField()
	{
		return driver.findElement(guests);
	}
	
	public WebElement adultsButton()
	{
		return driver.findElement(adults);
		
	}
	
	public WebElement childrenButton()
	{
		return driver.findElement(children);
	}
	
	public WebElement searchButton()
	{
		return driver.findElement(search);
	}
	
	

}
