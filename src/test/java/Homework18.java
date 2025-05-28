import org.apache.commons.lang3.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest {
    /*

1. Create a new branch named homework-18.

2. Create a new file, Homework18.java in IntelliJ IDEA.

3. Create a test case, playSong() using @Test annotation.

4. Use the helper/reusable methods we created.

5. Create a new config XML file for the test case.

6. Add Homework18 class to the new XML file.

7. Specify the @Test method to be ran in the new XML file.
Hint: Use the ＜methods＞ & ＜include＞ tags.

8. Navigate to "https://qa.koel.app/".

9. Log in with your credentials.

10. Click «Play next song» (media player controls), then the Play button, to play a song.

11. Validate that a song is playing by verifying if the sound bar, or the pause button is displayed.

12. Commit your changes to the new branch homework-18.

13. Push your code to the remote repository.

14. Create a pull request.
 */

    //*******HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS********
    @Test
    //3. Create a test case, playSong() using @Test annotation.
    public void playSong() {
        //4. Use the helper/reusable methods we created.

        //8. Navigate to "https://qa.koel.app/".
        //9. Log in with your credentials.
        //navigateToSite();
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //10. Click «Play next song» (media player controls), then the Play button, to play a song.
        //Thread.sleep(2000);
        playNextSong();
        //Thread.sleep(2000);
        playButton();

        //THIS IS ANOTHER WAY TO ASSERT WITH A METHOD
        //Assert.assertTrue(isSongPlaying());

        //11. Validate that a song is playing by verifying if the sound bar, or the pause button is displayed.
        //WebElement soundBar = driver.findElement(By.xpath("//img[@alt='Sound bars']"));
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//img[@alt='Sound bars']")));
        Assert.assertTrue(soundBar.isDisplayed());
    }




    public void playNextSong() {

       //WebElement playNxtbttn =  driver.findElement(By.xpath("//i[@title='Play next song']"));
        WebElement playNxtbttn = wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//i[@title='Play next song']")));
        playNxtbttn.click();
        //Thread.sleep(2000);
    }
    public void playButton()  {

        //WebElement playBttn=  driver.findElement(By.xpath("//span[@title='Play or resume']/i"));
        WebElement playBttn = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//span[@title='Play or resume']/i")));
        playBttn.click();
        //Thread.sleep(2000);
    }
    public boolean isSongPlaying(){
        //WebElement soundBar = driver.findElement(By.xpath("//img[@alt='Sound bars']"));
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//img[@alt='Sound bars']")));
        return soundBar.isDisplayed();

    }

}