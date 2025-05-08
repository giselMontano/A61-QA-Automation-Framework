import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {


    @Test
    public void loginEmptyEmailPassword() {
        navigateToSite();
        inputEmail("gisel.montano-patino@testpro.io");
        inputPassword("TestPro123");
        loginButton();

        //STEP-5
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));

        //Assertion expect vs actual
        Assert.assertTrue(avatarIcon.isDisplayed());

    }

    @Test
    public void wrongPassword() {
        navigateToSite();
        inputEmail("gisel.montano-patino@testpro.io");
        inputPassword("testPro123");
        loginButton();

        //STEP-5 ASSERTIONS
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));

        //Assertion
        Assert.assertTrue(avatarIcon.isDisplayed());


    }
}