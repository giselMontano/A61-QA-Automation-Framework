package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllSongsPage extends BasePage{
    public AllSongsPage(WebDriver GivenDriver) {
        super(GivenDriver);
    }
    //PAGE ELEMENTS
    By rightClick1SongBttn = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
    By choosePlayBttn= By.cssSelector("li.playback");

    //HELPER METHODS
    public void rightClickFirstSong() {
        //WebElement firstSong = wait.until(ExpectedConditions.visibilityOfElementLocated
                //(rightClick1SongBttn));
        //actions can be used here because is already set up in BaseTest
        //actions.contextClick(firstSong).perform();

        //EASY WAY
        actions.contextClick(findElementWait(rightClick1SongBttn)).perform();
    }
        //CLICK FIRST SONG FROM RIGHT-CLICK
    //THIS METHOD BELONG TO THIS BECAUSE IT IS PART OF THE PAGE OF ALL SONGS, IT IS A COMPLETELY NEW PAGE OPTION
    public void choosePlayOption() {
       // WebElement play1Song = wait.until(ExpectedConditions.visibilityOfElementLocated
        //play1Song.click();

        findElementWait(choosePlayBttn).click();

    }
}
