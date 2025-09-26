package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    By soundBar = By.xpath("//img[@alt='Sound bars']");
    By hoverOvr = By.cssSelector("[data-testid='play-btn']");

    //INITIALIZING
    public BasePage (WebDriver GivenDriver){
        driver= GivenDriver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        actions=new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    //THIS PART WILL WAIT FOR ELEMENTS TO BE VISIBLE
    public WebElement findElementWait(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement SoundBarPlay(){
        return  findElementWait(soundBar);

    }

    //---------HOVER OVER ACTION CLASS-----------
    //it needs to be WebElement because it returns a WEB ELEMENT
    public WebElement hoverOver() {

        WebElement playResume = driver.findElement(hoverOvr);
        //moveToElement--> hover over the element
        actions.moveToElement(playResume).perform();
        return wait.until(ExpectedConditions.visibilityOf(playResume));
    }
}
