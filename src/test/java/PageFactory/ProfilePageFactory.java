package PageFactory;

import Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePageFactory extends BasePage {
    public ProfilePageFactory(WebDriver GivenDriver) {
        super(GivenDriver);
    }
    @FindBy(css = "[data-testid='theme-card-violet']")
    WebElement violetTheme;

    @FindBy(css = "div[data-testid='theme-card-violet'].selected")
    WebElement selectedVioletTheme;


    public boolean isVioletSelected(){
        findElementWait(selectedVioletTheme);
        return selectedVioletTheme.isDisplayed();
    }
    public ProfilePageFactory chooseVioletTheme(){
        violetTheme.click();
        return this;


    }
}
