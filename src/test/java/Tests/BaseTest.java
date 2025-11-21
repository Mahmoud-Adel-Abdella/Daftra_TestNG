package Tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import Utilities.WebDriverListeners;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BaseTest {
    // Globals
    protected static WebDriver driver;
    protected SoftAssert softAssert;
    protected Faker faker;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // setup chromedriver
        WebDriverManager.chromedriver().setup();

        // 1) Create driver
        driver = new ChromeDriver();

        // 2) Decorate with listeners
        WebDriverListeners myListeners = new WebDriverListeners();
        driver = new EventFiringDecorator(myListeners).decorate(driver);

        // 3) common setup
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 4) test helpers
        faker = new Faker();
        softAssert = new SoftAssert();

        // 5) open page
        driver.get("https://waffarha.com/ar/%D8%A7%D9%86%D8%B4%D8%B7%D8%A9-%D9%88%D8%AA%D8%B1%D9%81%D9%8A%D9%87-c-8#");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        softAssert.assertAll();
        driver.quit();
        driver = null;
    }
}
