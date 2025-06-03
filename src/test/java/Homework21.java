import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
//THIS IS ALREADY IN THE HomeTest CLASS 
public class Homework21 extends BaseTest {
    //****************DOUBLE CLICK*********************
    //we need to declare the updated name here in order to use it in the code
    String updatedName = "";

    //AFTER ONE RUN IT ALWAYS FAIL BECAUSE THE NAME HAS BEEN CHANGED AND THE NOTIFICATION DOES NOT SHOW ANYMORE
    @Test
    public void renamePlaylist() {
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
        updatedName = "HELLO";
        newNamePlaylist();
        // Thread.sleep(2000);

        //Assert new name has been updated
        String expectedNewName = "Updated playlist \"HELLO.\"";
        Assert.assertEquals(getUpdatedNameSuccessMSG(), expectedNewName);

    }

    //****************DOUBLE CLICK*********************
    public void doubleClickPlaylist() {
        WebElement doubleClick = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector(".playlist:nth-child(3)")));
        //(By.xpath("//a[contains(text(),'TEST')]")));

        //actions comes from BASE TEST class
        actions.doubleClick(doubleClick).perform();
    }

    public void newNamePlaylist() {
        WebElement newName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[name='name']")));
        //IN THIS CASE THE .clear(); DOES NOT WORK AND WE NEED TO USE COMANDS IN KEYWORD TO SEND KEYS


        //Keys<= K is always capital letter
        //👉 COMMAND,"A" --> "Select All" (highlights all text inside the input field)
        //BACK_SPACE  --> 👉 Deletes the entire selected text.
        newName.sendKeys(Keys.chord(Keys.COMMAND, "A", Keys.BACK_SPACE));

        newName.sendKeys(updatedName);
        newName.sendKeys(Keys.ENTER);
    }

    public String getUpdatedNameSuccessMSG() {
        WebElement messageInGreen = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='success show']")));
        return messageInGreen.getText();
    }

}
