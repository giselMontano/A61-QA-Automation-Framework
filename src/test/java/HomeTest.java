import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class HomeTest extends BaseTest{
    //---------HOVER OVER -----------
    @Test
    public void hoverOverPlaying() throws InterruptedException {
        /*
         * login
         * verify Play or Resume button is visible with mouse hover
         * */
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //without this wait it does not work
        Thread.sleep(2000);

//VERIFICATION IS THE SONG IS PLAYING
        Assert.assertTrue(hoverOver().isDisplayed());

    }



    //************WEB ELEMENTS LIST****************

    @Test
    public void countSongsInPlaylist() throws InterruptedException {
        //login
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();
        Thread.sleep(2000);
        //choose playlist by name 
        choosePlaylistByName("romance");
        Thread.sleep(2000);
        //display all songs
        displayAllSongs();
        //Assertion-contains the # of songs in the playlist info section
        // romance
//6 songs • 25:07 • Download All

        System.out.println("TEXT INFORMATION ON THE TOP OF PLAYLIST "+ getPlaylistDetails());
                                              //THIS PART IS TURNING THE INT INTO STRING TO BE COMPARE TO getPlaylistDetails();
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
        
    }


    //****************DOUBLE CLICK*********************
    //we need to declare the updated name here in order to use it in the code
    String updatedName= "";
    //AFTER ONE RUN IT ALWAYS FAIL BECAUSE THE NAME HAS BEEN CHANGED AND THE NOTIFICATION DOES NOT SHOW ANYMORE
    @Test
    public void renamePlaylist()  {
        //Login
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();
        //We only use Thread.sleep in case we need to see how the app is working in a slower pace
       // Thread.sleep(2000);

        //Choose-playlist and double click
        doubleClickPlaylist();
       // Thread.sleep(2000);

        //Change name
        updatedName ="HELLO";
        newNamePlaylist();
       // Thread.sleep(2000);

        //Assert new name has been updated
        String expectedNewName = "Updated playlist \"HELLO.\"";
        Assert.assertEquals(getUpdatedNameSuccessMSG(),expectedNewName);



    }
    //****************DOUBLE CLICK*********************
    public void doubleClickPlaylist() {
        WebElement doubleClick = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.cssSelector(".playlist:nth-child(3)")));
                //(By.xpath("//a[contains(text(),'TEST')]")));
        //actions comes from BASE TEST class
        actions.doubleClick(doubleClick).perform();
    }
    public void newNamePlaylist()  {
        WebElement newName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[name='name']")));
        //IN THIS CASE THE .clear(); DOES NOT WORK AND WE NEED TO USE COMANDS IN KEYWORD TO SEND KEYS


        //Keys<= K is always capital letter
        //👉 COMMAND,"A" --> "Select All" (highlights all text inside the input field)
        //BACK_SPACE  --> 👉 Deletes the entire selected text.
        newName.sendKeys(Keys.chord(Keys.COMMAND,"A", Keys.BACK_SPACE));

        newName.sendKeys(updatedName);
        newName.sendKeys(Keys.ENTER);
    }
    public String getUpdatedNameSuccessMSG(){
        WebElement messageInGreen = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='success show']")));
        return messageInGreen.getText();
    }





    //************WEB ELEMENTS LIST****************
    //with this method we can change the name of the playlist
    public void choosePlaylistByName(String playlist) {

        WebElement playlistName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[contains(text(),'"+playlist+"')]")));
        playlistName.click();
    }
    //THIS METHOD IS COUNTING THE SIZE/NUMBER OF ELEMENTS/SONGS  PRESENT IN THE TABLE/PLAYLIST
    public int countSongs(){

        return driver.findElements(By.xpath("//section[@id='playlistWrapper']//table/tr")).size();

    }
///THIS METHOD IS RETRIEVING THE INFORMATION ON THE TOP OF PLAYLIST WHICH SAYS
// romance
//6 songs • 25:07 • Download All
    public String getPlaylistDetails() {
        WebElement playListText = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@class='meta']")));
        return playListText.getText();
        //return driver.findElement(By.xpath("//span[@class='meta']")).getText();
    }
//THIS METHOD IS GETTING THE TEXT/STRING OF EACH SONG
//INSTEAD OF ONLY THE VALUE/SIZE OR NUMBER OF SONGS PRESENT IN THE PLAYLIST
    public void displayAllSongs() {
        List<WebElement> SongList = driver.findElements
                (By.xpath("//section[@id='playlistWrapper']//table/tr"));
        System.out.println("Number of songs found: " + countSongs());
        for (WebElement e : SongList) {
            System.out.println("LIST WEB-ELEMENTS "+ e.getText());
        }
    }
}
