package Pages;

import org.openqa.selenium.By;
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

    }



