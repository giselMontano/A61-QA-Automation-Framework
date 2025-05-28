import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17GoodOne extends BaseTest {
    //*******HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS********
    @Test
    public void addSongToPlaylist()  {
        //4. Navigate to "https://qa.koel.app/".
        //5. Log in with your credentials.
        //navigateToSite();
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //Thread.sleep(2000);
        //6
        searchSong("pluto");
        //7
        viewAllBtn();
        //8
        clickFirstSongResult();
        //9
        clickAddToBttn();
        //10
        choosePlayList();
        //11-->this will only run and have a notification when the song is added for the first time, second run will not have a vanish notification
        String notification = "Added 1 song into \"mix.\"";
        Assert.assertEquals(getAddToPlaylistSuccessMsg(),notification);


    }

    //6. Search for a song (choose any song of your choice).
    //we create a method that will send different song names into the search bar is interactive and not only set in stone
    public void searchSong(String name)  {
        //WebElement searchButton = driver.findElement(By.cssSelector("input[name='q']"));
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[name='q']")));

        searchButton.sendKeys(name);
        //Thread.sleep(2000);
    }

    //7. Click 'View All' button to display the search results.
    public void viewAllBtn()  {
        //WebElement viewAll = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        WebElement viewAll = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button[data-test='view-all-songs-btn']")));
        viewAll.click();
        ///Thread.sleep(2000);
    }

    //8. Click the first song in the search results.
    public void clickFirstSongResult()  {
        //WebElement clickSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//tr[@class='song-item'][1]"));
                                       //this is how it shows in the Dom however this is the way the code runs  //'song-item-selected'
        WebElement clickSong = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//section[@id='songResultsWrapper']//tr[@class='song-item'][1]")));
        clickSong.click();
        //Thread.sleep(2000);
    }

    //9. Click 'ADD TO...' button.
    public void clickAddToBttn()  {
        //WebElement addToBttn = driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        WebElement addToBttn = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button[class='btn-add-to']")));
        addToBttn.click();
        //Thread.sleep(2000);
    }

    //10. Choose the playlist to add it to, (you can create a new one with a unique name).
    private void choosePlayList() {
        //WebElement playList = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'mix')]"));
        WebElement playList = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'mix')]")));
        playList.click();
        //Thread.sleep(2000);
    }

    //11. Verify that a notification message appears and contains the text, "Added 1 song into [Actual Playlist Name]".
    //Note: Please verify using the Assert.assertEquals() method to compare the actual notification message text with the expected text.
    private Object getAddToPlaylistSuccessMsg() {
        //TO GET THE DISAPPEARING MESSAGE(TOAST MESSAGE) WE MUST STOP THE EXECUTION
        //DOM->sources->pause the execution
        //WebElement messageInGreen = driver.findElement(By.cssSelector("div.success.show"));
        WebElement messageInGreen = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return messageInGreen.getText();
    }
}