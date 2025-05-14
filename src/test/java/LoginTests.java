import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {


    @Test
    public void loginValidEmailPassword() {
        navigateToSite();
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //STEP-5
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));

        //Assertion expect vs actual
        Assert.assertTrue(avatarIcon.isDisplayed());

    }

    @Test
    public void wrongPassword() {
        navigateToSite();
        provideEmail("montano-patino@testpro.io");
        providePassword("testPro123");
        loginButton();

        //STEP-5 ASSERTIONS
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));

        //Assertion
        Assert.assertTrue(avatarIcon.isDisplayed());


    }
}