import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllSongsTest extends BaseTest {
    /*
     *go to koel app
     * login
     * click all songs
     * right-click on the first song (ACTIONS CLASS)
     * click play (ACTIONS CLASS)
     * verify that song is playing
     */
    //******ACTIONS CLASS****
    @Test
    public void playSong() {
        //* login
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        // * click all songs
        clickAllSongs();

        // * right-click on the first song (ACTIONS CLASS)
        //we first find the element then we set an action
        rightClickFirstSong();
        //this action is for right click
        actions.contextClick();

        //* click play (ACTIONS CLASS)
        choosePlayOption();

        //* verify that song is playing
        Assert.assertTrue(isSongPlaying());

    }


    public void clickAllSongs() {
        WebElement chooseAllSongs = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li a.songs")));
        chooseAllSongs.click();
    }

    public void rightClickFirstSong() {
        WebElement firstSong = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        //actions can be used here because is already set up in BaseTest
        actions.contextClick(firstSong).perform();

    }
//CHICK FIRST SONG FROM RIGHT CLICK
    public void choosePlayOption() {
        WebElement play1Song = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li.playback")));
        play1Song.click();

    }


}
