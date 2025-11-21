package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    //Globals
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        actions = new Actions(driver);
//        PageFactory.initElements(driver, this);
    }

    public void write(By locator , String text){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public String read(By locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public double readDigits(By locator){
        return Double.parseDouble(read(locator).replaceAll("[^0-9]" , ""));
    }

    public void click(By locator){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        actions.moveToElement(element).perform();
        element.click();
    }

    public void waitClick(By locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        click(locator);
    }

    public void radioRandomSelect(By locator){
        List<WebElement> radios = driver.findElements(locator);

        Random random = new Random();
        int randomIndex = random.nextInt(radios.size());

        WebElement selector = radios.get(randomIndex);
        selector.click();
    }

    public void dropdownRandomSelect(By locator){
        WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select selects = new Select(element);

        List<WebElement> options = selects.getOptions();

        Random random = new Random();
        int randomIndex = random.nextInt(options.size());

        selects.selectByIndex(randomIndex);
    }

    public String getAttribute (By locator , String attribute){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getAttribute(attribute);
    }

    public void navigateTo (By locator , String url){
        driver.get(url);
    }
}
