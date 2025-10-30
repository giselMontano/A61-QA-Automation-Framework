package PageFactory;

import Pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePageFactory extends BasePage {
    public HomePageFactory(WebDriver GivenDriver) {
        super(GivenDriver);

    }

    @FindBy(css = ".playlist:nth-child(3)")
    WebElement doubleClickPF;
    @FindBy (css ="[name='name']")
    WebElement newNamePF;
    @FindBy (xpath = "//div[@class='success show']")
    WebElement greenMessagePF;


    //Helper Methods
    public HomePageFactory doubleClick (){
        actions.doubleClick(doubleClickPF).perform();
        return this;
    }
    public HomePageFactory newNamePlaylist(String updatedName) {
        //IN THIS CASE WE ARE NOT USING DIRECTLY findElementWait(newName);
        // BECAUSE WE NEED TO USE THE LOCATOR FOUND WITH OTHER KEYS LIKE-> SENDKEYS SO IT NEEDS TO BE STORED IN A ELEMENT TO BE USED EASY

        //it works with both this one WebElement newNamePLst or just findElementWait(newNamePF);
        // WebElement newNamePLst = wait.until(ExpectedConditions.visibilityOf(newNamePF));
              // (newNamePF));

        findElementWait(newNamePF);

        //IN THIS CASE THE .clear(); DOES NOT WORK AND WE NEED TO USE COMMANDS IN KEYWORD TO SEND KEYS


        //Keys<= K is always capital letter
        //👉 COMMAND,"A" --> "Select All" (highlights all text inside the input field)
        //BACK_SPACE  --> 👉 Deletes the entire selected text.
        newNamePF.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        newNamePF.sendKeys(updatedName);
        newNamePF.sendKeys(Keys.ENTER);
        return this;
    }
    public String getUpdatedNameSuccessMSG() {
        // WebElement messageInGreenSccss = wait.until(ExpectedConditions.visibilityOfElementLocated
        //  (messageInGreen));
        return greenMessagePF.getText();
    }


}
