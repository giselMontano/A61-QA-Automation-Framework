import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    @DataProvider(name = "LoginNegativeTestData")
    public Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {"invalidEmail@gmail.com", "Ivalid"},
                {"demo@class.com", "invalid"},
                {"", ""},
                {"invalid@class.com", "test$tudent"}
        };
    }


    @BeforeSuite
    //This method will open the browser and should be inhered by other classes
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})//WE COPY THE SAME NAME AS THE XML FILE"TestNG"-> parameter
    //      Added ChromeOptions argument below to fix websocket error
    public void launchBrowser(String baseURL) {
    //Initiate the Chrome browser to open the browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        navigateToSite(baseURL);//THIS IS FROM LINE 50// CALLING THE METHOD "navigateToSite"
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public void navigateToSite(String url) {
        //STEP-1
        //String url = "https://qa.koel.app/";
        driver.get(url);

    }

    public void provideEmail(String Email) {
        //STEP-2
        WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
        email.clear();
        email.sendKeys(Email);

    }


    public void providePassword(String Password) {
        //STEP-3
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.clear();
        password.sendKeys(Password);
    }

    public void loginButton() {
        //STEP-4
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }
}