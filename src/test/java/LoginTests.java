import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {


    @Test
    public void loginValidEmailPassword() {
        //navigateToSite(); //WE DON NOT NEED IT ANY MORE BECAUSE OF THE PARAMETER IN XML FILE
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //STEP-5
        //We change the wait, so it will wait until the element is visible
        //WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img[class='avatar']")));

        //Assertion expect vs actual
        Assert.assertTrue(avatarIcon.isDisplayed());

    }
    //FLUENT WAIT EXAMPLE
   /* @Test
    public void loginValidEmailPasswordFluentWait() {
        //navigateToSite(); //WE DON NOT NEED IT ANYMORE BECAUSE OF THE PARAMETER IN XML FILE
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //FLUENT WAIT / it needs to be cast down into  web element
        WebElement avatarIcon = (WebElement) fluentWait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img[class='avatar']")));

        //Assertion expect vs actual
        Assert.assertTrue(avatarIcon.isDisplayed());

    }*/



    @Test
    public void wrongPassword() {
        //navigateToSite();
        provideEmail("montano-patino@testpro.io");
        providePassword("testPro123");
        loginButton();

        //STEP-5 ASSERTIONS able to capture
        //this will fail because the app will not log in and will not be able to capture the icon
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));

        //Assertion
        Assert.assertTrue(avatarIcon.isDisplayed());


    }
    //LoginNegativeTestData
    @Test (dataProvider = "LoginNegativeTestData")
    public void LoginNegativeTestData(String email1, String password1) throws InterruptedException {
        provideEmail(email1);//provideEmail->this method comes from BaseTest
        providePassword(password1);//providePassword->this method comes from BaseTest
        loginButton();//this method comes from BaseTest
        Thread.sleep(2000);

        String url= "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

}