package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.browserFactory;

public class clubKitchenHomePage extends browserFactory {
	
	@FindBy(xpath="//button[@class=\"agree-cookie\"]")
	WebElement agreeCookie;
	
	@FindBy(xpath="//div[@class='banner--content center right']//a[@class='banner--link' and @href='/speisekarte/mamacita/wallenstein/']")
	WebElement mamacitaButton;
	
	public clubKitchenHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public void clickMamacita() {
		agreeCookie.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(mamacitaButton).click().build().perform();
		System.out.println("Clickmamacita");
	}

}
