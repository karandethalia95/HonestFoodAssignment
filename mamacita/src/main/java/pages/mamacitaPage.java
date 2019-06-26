package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.browserFactory;

public class mamacitaPage extends browserFactory{
	
	@FindBy(xpath="//input[@id=\"address-input\"]")
	WebElement address;
	
	@FindBy(xpath="//input[@class='btn--honest blattgold--form-banner-submit']")
	WebElement submitAddress;
	
	@FindBy(xpath="//button[@id='topup-modal--close']")
	WebElement submitOrder;
	
	public mamacitaPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterAdress() {
		address.clear();
		address.sendKeys(prop.getProperty("address"));
		submitAddress.click();
	}
	
	public void selectProducts() throws InterruptedException {
		int totalProduct = Integer.parseInt(prop.getProperty("TotalProduct"));
		for(int i=0;i<totalProduct;i++)
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			String productName = prop.getProperty("product"+(i+1));
			//waiting for the product to get visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='"+productName+"']/button")));
			WebElement product = driver.findElement(By.xpath("//a[@title='"+productName+"']/button"));
			product.click();
			submitOrder.click();
			
			//waiting for item to be added in cart
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item--container items']/div["+(i+1)+"]/div[3]/span[@class='item--name']")));
			WebElement productDescCart = driver.findElement(By.xpath("//div[@class='item--container items']/div["+(i+1)+"]/div[3]/span[@class='item--name']"));
			//verifying item in the cart
			if(productDescCart.getText().equals(productName)) {
				System.out.println(productName +" added successfully to the cart");
			}
		}
	}

}
