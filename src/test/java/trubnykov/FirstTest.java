package trubnykov;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {

    private WebDriver driver;

    @BeforeClass // Runs this method before the first test method in the current class is invoked
    public void setUp() {
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
    }

    @Test // Marking this method as part of the test
    public void gotoGoogleSearchFirstResult() {
        // Open Google
        driver.get("http://www.google.com");

        // Search for "automation"
        WebElement searchBox;
        searchBox = driver.findElement(By.id("lst-ib"));
        searchBox.sendKeys("automation");
        searchBox.submit();

        // Wait until the google page shows the result
        WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

        // Open the first link on search results page.
        String first_link = findElements.get(0).getAttribute("href");
        driver.navigate().to(first_link);

        // Get text from header of the page
        String header = driver.findElement(By.id("firstHeading")).getText();
        // Verify that header equals "Automation"
        Assert.assertEquals(header, "Automation");
    }

    @AfterClass // Runs this method after all the test methods in the current class have been run
    public void tearDown() {
        // Close all browser windows and safely end the session
        driver.quit();
    }
}