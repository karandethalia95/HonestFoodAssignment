package testPages;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.browserFactory;
import junit.framework.Assert;
import pages.clubKitchenHomePage;
import pages.mamacitaPage;

public class mamacitaTest extends browserFactory {
	
	browserFactory browFac;
	clubKitchenHomePage clubKitchenHomePageObject;
	mamacitaPage mamPage;
	
	@BeforeMethod
	public void setup() {
		browFac = new browserFactory();
	}
	
	@Test
	@Parameters("browser")
	public void clickMamacitaTest(String browserName) throws InterruptedException {
		browFac.initialization(browserName);
		clubKitchenHomePageObject = new clubKitchenHomePage();
		String title = clubKitchenHomePageObject.validateHomePageTitle();
		System.out.println(title);
		Assert.assertEquals(title,"Clubkitchen- Dein Lieferservice für die besten Onlinerestaurants | Clubkitchen");
		clubKitchenHomePageObject.clickMamacita();
		
		mamPage = new mamacitaPage();
		mamPage.enterAdress();
		Thread.sleep(5000);
		mamPage.selectProducts();
		
		
	}
	
	@AfterMethod
	public void quit() {
		System.out.println("After method");
		//driver.quit();
	}

}
