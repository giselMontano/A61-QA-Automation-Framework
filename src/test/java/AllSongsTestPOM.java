import Pages.AllSongsPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllSongsTestPOM extends BaseTest {
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
        //it will not work because the method isSongPlaying() has been moved to basePage from BaseTest
        //Assert.assertTrue(isSongPlaying());

    }


    public void clickAllSongs() {
        WebElement chooseAllSongs = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li a.songs")));
        chooseAllSongs.click();
    }
//RIGHT-CLICK ON THE FIRST SONG FROM /All Songs tab
    public void rightClickFirstSong() {
        WebElement firstSong = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        //actions can be used here because is already set up in BaseTest
        actions.contextClick(firstSong).perform();

    }
//CLICK PLAY ON THE FIRST SONG FROM RIGHT CLICK /All Songs tab
    public void choosePlayOption() {
        WebElement play1Song = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("li.playback")));
        play1Song.click();

    }
    //***************************** PLAY SONG  [POM] *************************
    @Test
    public void playSongWithContextClickPOM() {
        LoginPage loginP = new LoginPage(driver);
        HomePage homeP = new HomePage(driver);
        AllSongsPage allSongs= new AllSongsPage(driver);
        loginP.loginSetUpCredentials();
        //choose all songs(it is in the homepage of the website)
        homeP.clickAllSongs();
        //RIGHT CLICK/it belongs to the new tab opened All songs
        allSongs.rightClickFirstSong();
        //All songs tab/PLAY
        allSongs.choosePlayOption();

        //* verify that song is playing//THIS METHOD IS ALREADY CREATED IN HOME PAGE BECAUSE
        // YOU CAN VERIFY IN THE FIRST TAB AND CAN BE SEEN NO MATTER HOW MANY TABS YOU OPEN
        Assert.assertTrue(homeP.isSongPlaying());

    }
}
