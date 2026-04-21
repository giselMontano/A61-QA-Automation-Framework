package StepDefinition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginStepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    //we comment this line because we  will be using this line of code often
    //@Given("I open browser")
    @Before
    public void i_open_browser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        

    }
    @After
    public void closeBrowser(){
        driver.quit();
    }

    @And("I open Login Page")
    public void iOpenLoginPage(){
        driver.get("https:/qa.koel.app/");
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
    wait.until(ExpectedConditions.visibilityOfElementLocated
            (By.cssSelector("input[type='email']"))).sendKeys((email));
}

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[type='password']"))).sendKeys((password));
    }

    @And("I submit")
    public void iSubmit() {
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.cssSelector("button[type='submit']"))).click();
    }


    @Then("I am logged in")
    public void iAmLoggedIn() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img[class='avatar']"))).isDisplayed());

    }

    @Then("I should not get logged in")
    public void iShouldNotGetLoggedIn() {
        String url = "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }
}