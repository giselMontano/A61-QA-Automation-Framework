import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeUserProfile extends BaseTest {
    //CREATE A TEST TO CHANGE USER'S NAME AND VERIFY IT'S CHANGED
    @Test
    public void userProfile() throws InterruptedException {


        navigateToSite();
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        Thread.sleep(2000);

        //click avatar icon has a method, and we just call the method
        clickAvatarIcon();

        //Profile & Preferences tab
        //Current Password
        provideCurrentPassword("TestPro123");
        //Name
        provideName("alejandra");

        Thread.sleep(2000);
        //Save
        saveButton ();

//ASSERTIONS TO SEE IF THE ACTUAL NAME HAS BEEN UPDATED FOR "student" to "alejandra" in this case LINE 26
        String expectedUpdatedName = "alejandra";
        //THIS LINE IS RETRIEVING THE NEW "NAME" FIELD AGAIN
        WebElement updatedName = driver.findElement(By.id("inputProfileName"));
        //"value"  Text inside input elements is stored in the value attribute
        // <input type="text" id="inputProfileName" value="student">
        Assert.assertEquals(updatedName.getAttribute("value"), expectedUpdatedName);
    }

    //Profile & Preferences tab methods
    //Current Password
    private void provideCurrentPassword(String currentPass) {
        WebElement profilePass = driver.findElement(By.cssSelector("input[name='current_password']"));
        profilePass.clear();
        profilePass.sendKeys(currentPass);
    }
    //Name
    public void provideName(String profileName) {
        WebElement newName = driver.findElement(By.id("inputProfileName"));
        newName.clear();
        newName.sendKeys(profileName);
    }

    public void clickAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        avatarIcon.click();

    }
    public void saveButton () {
        WebElement save = driver.findElement(By.cssSelector("button.btn-submit"));
        save.click();

    }
}