package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaylistPage extends BasePage{

    public PlaylistPage(WebDriver GivenDriver) {
        super(GivenDriver);
    }
    //Elements
   By playList = By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'mix')]");

    //Helper methods, actions for those elements
    public void choosePlaylist(){
        findElementWait(playList).click();
    }
}
