package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    //INITIALIZING
    public BasePage (WebDriver GivenDriver){
        driver= GivenDriver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        actions=new Actions(driver);


    }

    //THIS PART WILL WAIT FOR ELEMENTS TO BE VISIBLE
    public WebElement findElementWait(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
