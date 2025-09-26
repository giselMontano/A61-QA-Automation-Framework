import PageFactory.LoginPageFactory;
import Pages.HomePage;
import Pages.LoginPage;
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
    @Test(dataProvider = "LoginNegativeTestData")
    public void LoginNegativeTestData(String email1, String password1) throws InterruptedException {
        provideEmail(email1);//provideEmail->this method comes from BaseTest
        providePassword(password1);//providePassword->this method comes from BaseTest
        loginButton();//this method comes from BaseTest
        Thread.sleep(2000);

        String url = "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }


    //**********************  LOG IN [POM]  *************************************
    //Login test using Page ObjectModel
    @Test
    public void positiveLoginTestPOM() {
        //WE NEED TO CREATE THE CONSTRUCTORS OF EACH CLASS TO BE ABLE TO USE IT

        //Objects
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //Steps
        //this is good to do negative tests as well
        /*loginPage.provideEmail("gisel.montano-patino@testpro.io");
        loginPage.providePassword("TestPro123");
        loginPage.clickLogin();*/

        //this will run only with the credentials that has been set up in LoginPage
        loginPage.loginSetUpCredentials();
        //Expected
        //HomePage this contains the avatar locator
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());

    }


    //************ This is [POM] APPROACH to test different cases with DATA PROVIDER ********************
    @Test(dataProvider = "LoginNegativeTestData")
    public void negativeLoginTestsPOM(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickLogin();

        String url = "https://qa.koel.app/";
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

        //************ This is PAGE FACTORY APPROACH  ********************
    @Test
    public void positiveLoginTestPAGEFACTORY() {
        //Objects
        LoginPageFactory loginPageFactory = new LoginPageFactory(driver);
        HomePage homePage = new HomePage(driver);
        //Steps
        //SMOOTH FLOW CALLING ONLY ONE TIME loginPageFactory
        loginPageFactory.provideEmail("montano-patino@testpro.io")
                .providePassword("testPro123")
                .clickLogin();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
        }


}

