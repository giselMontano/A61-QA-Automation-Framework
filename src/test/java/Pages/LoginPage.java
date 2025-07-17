package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver GivenDriver) {
        super(GivenDriver);
    }
    //Elements, using By class from Selenium
    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitButton = By.cssSelector("button[type='submit']");
    //REGISTRATION
    By registration = By.partialLinkText("Registration");



    //Helper methods, actions for those elements
    public void provideEmail (String email){
        //THIS findElementWait is coming from BasePage that has the wait set up already
        findElementWait(emailField).sendKeys(email);
    }
    public void providePassword (String password){
        findElementWait(passwordField).sendKeys(password);
    }
    public void clickLogin (){
        findElementWait(submitButton).click();
    }
    public void loginSetUpCredentials (){
        provideEmail ("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        clickLogin();

    }

    //REGISTRATION
    public void registration (){
        findElementWait(registration).click();
    }
}
