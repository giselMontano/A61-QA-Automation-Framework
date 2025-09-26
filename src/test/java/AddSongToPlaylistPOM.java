import Pages.HomePage;
import Pages.LoginPage;
import Pages.PlaylistPage;
import Pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddSongToPlaylistPOM extends BaseTest {
    @Test
    public void addSongToPlaylist() {
        /*HOMEWORK M6-L17 Framework: TestNG Overview
1. Create a new branch named homework-17.

2. Create a new file, Homework17.java in IntelliJ IDEA.

3. Create a test case, addSongToPlaylist() using @Test annotation and the helper/reusable methods we created.

4. Navigate to "https://qa.koel.app/".

5. Log in with your credentials.

6. Search for a song (choose any song of your choice).

7. Click 'View All' button to display the search results.

8. Click the first song in the search results.

9. Click 'ADD TO...' button.

10. Choose the playlist to add it to, (you can create a new one with a unique name).

11. Verify that a notification message appears and contains the text, "Added 1 song into [Actual Playlist Name]".

Note: Please verify using the Assert.assertEquals() method to compare the actual notification message text with the expected text. If they match, the assertion in your test will pass; otherwise, it will fail.

12. Commit your changes to the new branch homework-17

13. Push your code to the remote repository.

14. Create a pull request.
 */
        //*******HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS********

        //FOR THIS EXCERSICE ONLY ONE TIME IS ALLOWED TO ADD SONG TO A PLAYLIST
        // IF YOU RUN IT TWICE IT WON'T WORK BECAUSE THE SONG IS ALREADY ADDED

        //4. Navigate to "https://qa.koel.app/".
        //5. Log in with your credentials.
        //navigateToSite();
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //Thread.sleep(2000);
        //6. Search for a song (choose any song of your choice).
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
        Assert.assertEquals(getAddToPlaylistSuccessMsg(), notification);


    }

    //6. Search for a song (choose any song of your choice).
    //we create a method that will send different song names into the search bar is interactive and not only set in stone
    public void searchSong(String name) {
        //WebElement searchButton = driver.findElement(By.cssSelector("input[name='q']"));
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[name='q']")));

        searchButton.sendKeys(name);
        //Thread.sleep(2000);
    }

    //7. Click 'View All' button to display the search results.
    public void viewAllBtn() {
        //WebElement viewAll = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        WebElement viewAll = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button[data-test='view-all-songs-btn']")));
        viewAll.click();
        ///Thread.sleep(2000);
    }

    //8. Click the first song in the search results.
    public void clickFirstSongResult() {
        //WebElement clickSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//tr[@class='song-item'][1]"));
        //this is how it shows in the Dom however this is the way the code runs  //'song-item-selected'
        WebElement clickSong = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='songResultsWrapper']//tr[@class='song-item'][1]")));
        clickSong.click();
        //Thread.sleep(2000);
    }

    //9. Click 'ADD TO...' button.
    public void clickAddToBttn() {
        //WebElement addToBttn = driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        WebElement addToBttn = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button[class='btn-add-to']")));
        addToBttn.click();
        //Thread.sleep(2000);
    }

    //10. Choose the playlist to add it to, (you can create a new one with a unique name).
    public  void choosePlayList() {
        //WebElement playList = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'mix')]"));
        WebElement playList = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'mix')]")));
        playList.click();
        //Thread.sleep(2000);
    }

    //11. Verify that a notification message appears and contains the text, "Added 1 song into [Actual Playlist Name]".
    //Note: Please verify using the Assert.assertEquals() method to compare the actual notification message text with the expected text.
    public Object getAddToPlaylistSuccessMsg() {
        //TO GET THE DISAPPEARING MESSAGE(TOAST MESSAGE) WE MUST STOP THE EXECUTION
        //DOM->sources->pause the execution
        //WebElement messageInGreen = driver.findElement(By.cssSelector("div.success.show"));
        WebElement messageInGreen = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return messageInGreen.getText();
    }

    //***************************** ADD SONG TO PLAYLIST [POM] *************************
    //FOR THIS EXCERSICE ONLY ONE TIME IS ALLOWED TO ADD SONG TO A PLAYLIST
    // IF YOU RUN IT TWICE IT WON'T WORK BECAUSE THE SONG IS ALREADY ADDED/ always check if the song has been added to the playlist
    @Test
    //remember to use lower case for the test case
    public void addSongToPlaylistPOM(){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        PlaylistPage playListPage = new PlaylistPage(driver);

        SearchResultsPage searchResultPage = new SearchResultsPage(driver);
        // 4/5
        loginPage.loginSetUpCredentials();
        // 6
        homePage.searchBar("pluto");
        //7
        searchResultPage.viewAllButton();
        //8
        searchResultPage.clickFirstSong();
        //9
        searchResultPage.addToButton();
        //10
        playListPage.choosePlaylist();
        //11
        String notification = "Added 1 song into \"mix.\"";
        Assert.assertEquals(searchResultPage.greenNotification(),notification);


    }
}