package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

//All the methods and elements that belong to homepage will be stored here
public class HomePage extends BasePage{

    public HomePage(WebDriver GivenDriver) {
        super(GivenDriver);
    }
   //Elements
    By userAvatarIcon = By.cssSelector("img[class='avatar']");
    By search = By.cssSelector("input[name='q']");
    By playNxtbttn = By.xpath("//i[@title='Play next song']");
    By playBttn =  By.xpath("//span[@title='Play or resume']/i");
    By allSongs =By.cssSelector("li a.songs");
    //String updatedName = "HEllO";

    By doubleClick = By.cssSelector(".playlist:nth-child(3)");
    //(By.xpath("//a[contains(text(),'TEST')]")));

    By newName = By.cssSelector("[name='name']");

    By messageInGreen = By.xpath("//div[@class='success show']");



    //Helper methods
    public WebElement getUserAvatar(){
    return  findElementWait(userAvatarIcon);
}
    public void searchBar(String searchName) {
        findElementWait(search).sendKeys(searchName);
    }
    public void playNextSongButton(){
        findElementWait(playNxtbttn).click();
    }
    public void playButton(){
        findElementWait(playBttn).click();
    }

    public void clickAllSongs() {
       findElementWait(allSongs).click();

    }
    public void doubleClickPlaylist() {
       // WebElement doubleClickPL = wait.until(ExpectedConditions.visibilityOfElementLocated
                //(doubleClick));

        //actions comes from BASE TEST class
       // actions.doubleClick(doubleClickPL).perform();

        //EASY WAY
        actions.doubleClick(findElementWait(doubleClick)).perform();
    }

    public void newNamePlaylist(String updatedName) {
        //IN THIS CASE WE ARE NOT USING DIRECTLY findElementWait(newName);
        // BECAUSE WE NEED TO USE THE LOCATOR FOUND WITH OTHER KEYS LIKE-> SENDKEYS SO IT NEEDS TO BE STORED IN A ELEMENT TO BE USED EASY
        WebElement newNamePL = wait.until(ExpectedConditions.visibilityOfElementLocated
                (newName));

        //IN THIS CASE THE .clear(); DOES NOT WORK AND WE NEED TO USE COMMANDS IN KEYWORD TO SEND KEYS
        //Keys<= K is always capital letter
        //👉 COMMAND,"A" --> "Select All" (highlights all text inside the input field)
        //BACK_SPACE  --> 👉 Deletes the entire selected text.
        newNamePL.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));
        newNamePL.sendKeys(updatedName);
        newNamePL.sendKeys(Keys.ENTER);
    }
    public String getUpdatedNameSuccessMSG() {
       // WebElement messageInGreenSccss = wait.until(ExpectedConditions.visibilityOfElementLocated
              //  (messageInGreen));
        return findElementWait(messageInGreen).getText();
    }



}



