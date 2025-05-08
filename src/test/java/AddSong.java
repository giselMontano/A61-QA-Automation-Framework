import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddSong extends BaseTest {
    /*Homework M6-L17 Framework: TestNG Overview
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
Note: Please verify using the Assert.assertEquals() method to compare the actual notification message text with the expected text.
If they match, the assertion in your test will pass; otherwise, it will fail.
12. Commit your changes to the new branch homework-17
13. Push your code to the remote repository.
14. Create a pull request.*/
    @Test
   public void addSongToPlaylist() throws InterruptedException {
        //4. Navigate to "https://qa.koel.app/".
        //5. Log in with your credentials.
        navigateToSite();
        inputEmail("gisel.montano-patino@testpro.io");
        inputPassword("TestPro123");
        loginButton();

        Thread.sleep(2000);
        //6. Search for a song (choose any song of your choice).
        WebElement searchButoon = driver.findElement(By.cssSelector("input[name='q']"));
        searchButoon.sendKeys("pluto");

        Thread.sleep(2000);

        //7. Click 'View All' button to display the search results.
        WebElement viewAll = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewAll.click();

        Thread.sleep(2000);


        //8. Click the first song in the search results.
        WebElement clickSong = driver.findElement(By.xpath("//tr[@class='song-item']//td[@class='title' and text()='Pluto']"));
        clickSong.click();
        //9. Click 'ADD TO...' button.
        WebElement addToBttn = driver.findElement(By.cssSelector("button[class='btn-add-to']"));
        addToBttn.click();

         Thread.sleep(2000);


        //10. Choose the playlist to add it to, (you can create a new one with a unique name).
        WebElement playList = driver.findElement(By.cssSelector("//section[@class='existing-playlists']/ul/li[5]"));
        //section[@class='existing-playlists']//li[5]//li[contains(text(),'mix')]
        playList.click();

        Thread.sleep(3000);

        //11. Verify that a notification message appears and contains the text, "Added 1 song into [Actual Playlist Name]".
    //Note: Please verify using the Assert.assertEquals() method to compare the actual notification message text with the expected text.
        String notification = "Added 1 song into mix";
        //THIS LINE IS RETRIEVING THE NEW "NAME" FIELD AGAIN
        WebElement messageInGreen = driver.findElement(By.cssSelector("div.success.show"));
        //"value"  Text inside input elements is stored in the value attribute
        // <input type="text" id="inputProfileName" value="student">
        Assert.assertEquals(messageInGreen.getText(), notification);



    }
}
