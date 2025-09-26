package PageFactory;
import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageFactory extends BasePage {

    public LoginPageFactory(WebDriver GivenDriver) {
        super(GivenDriver);
    }

    //Page Factory Elements
    @FindBy( css = "input[type='email']")
    WebElement emailField;

    @FindBy (css = "input[type='password']")
    WebElement passwordField;

    @FindBy (css ="button[type='submit']")
    WebElement submitButton;

    @FindBy (linkText = "Registration")
    WebElement registration;


//Helper Methods
    //Change void for object with the same name (LoginPageFactory) to make a smooth flow and add return this;
    //this way will help to use all of these methods by calling LoginPageFactory only once and adding the rest to it
    public LoginPageFactory provideEmail (String email){
        emailField.sendKeys(email);
        return this;
    }
    public LoginPageFactory providePassword (String password){
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPageFactory clickLogin (){
        submitButton.click();
        return this;
    }
    public LoginPageFactory loginSetUpCredentials (){
        provideEmail ("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        clickLogin();
        return this;

    }

    //REGISTRATION
    public LoginPageFactory registration (){
        registration.click();
        return this;
    }

}
