package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import utility.WebEventListener;

public class browserFactory {
	
	public static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	public static Properties prop;
	
	public browserFactory(){
		try {
			//ClassLoader classLoader =  Thread.currentThread().getContextClassLoader();
			FileInputStream input = new FileInputStream("./config.properties");
			prop = new Properties();
			prop.load(input);
			System.out.println("Loaded");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			}
		
	}
	
	public void initialization(String driverParm) {
		if(driverParm.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","./driver executables/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(driverParm.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "./driver executables/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
		
		else {
			System.out.println("No Drivers Found");
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

}
