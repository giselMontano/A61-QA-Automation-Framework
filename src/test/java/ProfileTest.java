import PageFactory.HomePageFactory;
import PageFactory.LoginPageFactory;
import PageFactory.ProfilePageFactory;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {
    //CREATE A TEST TO CHANGE USER'S NAME AND VERIFY IT'S CHANGED

    //*******HOMEWORK 20 CHANGING ALL Thread.sleep(2000); TO EXPLICIT WAITS********
    //@Test
    public void userProfile(){


        //navigateToSite();
        provideEmail("gisel.montano-patino@testpro.io");
        providePassword("TestPro123");
        loginButton();

        //Thread.sleep(2000);

        //click avatar icon has a method, and we just call the method
        navigateToProfilePage();

        //Profile & Preferences tab
        //Current Password
        provideCurrentPassword("TestPro123");
        //Name
        provideName("alejandra");

        //Thread.sleep(2000);
        //Save
        saveButton ();

//ASSERTIONS TO SEE IF THE ACTUAL NAME HAS BEEN UPDATED FOR "Gisel" to "alejandra" in this case  provideName("Gisel");

        String expectedUpdatedName = "Gisel";
        //THIS LINE IS RETRIEVING THE NEW "NAME" FIELD AGAIN
        //WebElement updatedName = driver.findElement(By.id("inputProfileName"));
        WebElement updatedName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("inputProfileName")));
        //"value"  Text inside input elements is stored in the value attribute
        // <input type="text" id="inputProfileName" value="student">
        Assert.assertEquals(updatedName.getAttribute("value"), expectedUpdatedName);
    }

    //Profile & Preferences tab methods
    //Current Password
    private void provideCurrentPassword(String currentPass) {
        //WebElement profilePass = driver.findElement(By.cssSelector("input[name='current_password']"));
        WebElement profilePass = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[name='current_password']")));
        profilePass.clear();
        profilePass.sendKeys(currentPass);
    }
    //Name
    public void provideName(String profileName) {
        //WebElement newName = driver.findElement(By.id("inputProfileName"));
        WebElement newName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("inputProfileName")));
        newName.clear();
        newName.sendKeys(profileName);
    }

    public void navigateToProfilePage() {
        //WebElement profileName = driver.findElement(By.cssSelector("img[class='avatar']"));
        WebElement profileName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("img[class='avatar']")));
        profileName.click();

    }
    public void saveButton () {
        //WebElement save = driver.findElement(By.cssSelector("button.btn-submit"));
        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button.btn-submit")));
        save.click();

    }
    @Test
    public void changeCurrentTheme() throws InterruptedException {
        LoginPageFactory loginPage1 = new LoginPageFactory(driver);
        HomePageFactory homePage1 = new HomePageFactory(driver);
        ProfilePageFactory profilePage1 = new ProfilePageFactory(driver);
        //login
        //select user
        //===>click on user icon
        //change color
        //verify color

        loginPage1.loginSetUpCredentialsPF();

        //Thread.sleep(2000);
        homePage1.getUserAvatar().click();
        Thread.sleep(5000);
        profilePage1.chooseVioletTheme();
        Thread.sleep(2000);
        Assert.assertTrue(profilePage1.isVioletSelected());

    }
}