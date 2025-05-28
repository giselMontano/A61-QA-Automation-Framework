import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19 extends BaseTest {
    /*1. Create a new branch named homework-19.

2. Create a new file Homework19.java in IntelliJ IDEA.

3. Create a test case, deletePlaylist() using @Test annotation.

4. Use the helper/reusable methods we created.

5. Use @Parameters for passing baseUrl from the TestNG config file to the tests.

6. Navigate to "https://qa.koel.app/".

7. Log in with your credentials.

8. Click the playlist you want to delete.

9. We should see a red button "x PLAYLIST" on the top right part of the page, and click on it.

Note: If the red button is not displayed, let's create a new playlist and click it.

10. Verify that the confirmation notification displayed has the text, "Deleted playlist {playlist name}".

11. Commit your changes to the new branch homework-19.

12. Push your code to the remote repository.

13. Create a pull request.*/

    //*******HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS********
    @Test
    public void deletePlaylist()  {
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();
        //8
        selectPlaylist();
        //9
        deletePlst();
        //10
        String ExpectedNotification = "Deleted playlist \"rock.\"";
        Assert.assertEquals(ExpectedNotification,notification());

    }

    private void deletePlst()  {
        //WebElement DplayList = driver.findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        //HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS
        WebElement DplayList = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//button[@class='del btn-delete-playlist']")));

        DplayList.click();
        //Thread.sleep(2000);

    }

    private void selectPlaylist()  {
        //WebElement SplayList = driver.findElement(By.xpath("//a[text() = 'rock']"));
        //HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS
        WebElement SplayList = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[text() = 'rock']")));
        SplayList.click();
        //Thread.sleep(2000);
    }

    private Object notification() {
        //TO GET THE DISAPPEARING MESSAGE(TOAST MESSAGE) WE MUST STOP THE EXECUTION
        //DOM->sources->pause the execution
        //WebElement messageInGreen = driver.findElement(By.xpath("//div[@class='success show']"));
        //HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS
        WebElement messageInGreen = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='success show']")));
        return messageInGreen.getText();
    }
}
