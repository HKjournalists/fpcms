package com.fpcms.common.selenium;

import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DLDSeleniumTest {

	
	@Test
	public void test_firefox() {
		 // The Firefox driver supports javascript 
        WebDriver driver = new FirefoxDriver();
        
        // Go to the Google Suggest home page
        driver.get("http://www.google.com/webhp?complete=1&hl=en");
        
        // Enter the query string "Cheese"
        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Cheese");

        // Sleep until the div we want is visible or 5 seconds is over
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            WebElement resultsDiv = driver.findElement(By.className("gssb_e"));

            // If results have been returned, the results are displayed in a drop down.
            if (resultsDiv.isDisplayed()) {
              break;
            }
        }

        // And now list the suggestions
        List<WebElement> allSuggestions = driver.findElements(By.xpath("//td[@class='gssb_a gbqfsf']"));
        
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
        }

        driver.quit();
	}
	
	@Test
	public void test() throws Exception {
		 // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        //WebDriver driver = new HtmlUnitDriver();
		
//		File file = new File("C:/Program Files/Internet Explorer/iexplore.exe");
//		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//        WebDriver driver = new InternetExplorerDriver();
		// 
//		File file =new File("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
//		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//		WebDriver driver = new ChromeDriver();
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
		driver.get("http://www.google.com");

        // And now use this to visit Google
//        driver.get("http://www.google.com");
        driver.get("http://www.baidu.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
	}
	
	@Test
	public void test_dld() {
		 // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
//		WebDriver driver = new HtmlUnitDriver();
//		File file = new File("C:/Program Files/Internet Explorer/iexplore.exe");
//		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		WebDriver driver = new FirefoxDriver();
//		WebDriver driver = new HtmlUnitDriver();
		
        login(driver);

        long end = System.currentTimeMillis() + 5000;
        By linkText = By.linkText("View Timetable");
		while (System.currentTimeMillis() < end) {
        	WebElement webElement = driver.findElement(linkText);
        	
            // If results have been returned, the results are displayed in a drop down.
            if (webElement.isDisplayed()) {
            	webElement.click();
            	break;
            }
        }
        
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
	}

	private void login(WebDriver driver) {
		// And now use this to visit Google
        driver.get("http://acorn.utoronto.ca/");

        // Find the text input element by its name
        WebElement userElement = driver.findElement(By.name("user"));
        WebElement passwordElement = driver.findElement(By.name("pass"));
        WebElement loginElement = driver.findElement(By.name("login"));

        // Enter something to search for
        userElement.sendKeys("zengkail");
        passwordElement.sendKeys("Kelly317");

        // Now submit the form. WebDriver will find the form for us from the element
        loginElement.submit();
	}
	
}
