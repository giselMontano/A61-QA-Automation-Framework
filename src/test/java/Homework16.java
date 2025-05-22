import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class Homework16 {
    @Test
    public void registrationNavigation() {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Navigate to Koel QA site
        String Url="https://qa.koel.app/";
        driver.get(Url);

        // Click on the 'Register' link
        //WebElement registerLink = driver.findElement(By.cssSelector("a[href='registration'])"));
        WebElement registerLink =driver.findElement(By.partialLinkText("Registration"));
        registerLink.click();

        // Verify the redirected URL is the registration page
        String expectedUrl = "https://qa.koel.app/registration";
        String actualUrl = driver.getCurrentUrl();

        //Assert URL
        Assert.assertEquals(actualUrl, expectedUrl);

        // Close browser
        driver.quit();
    }
}