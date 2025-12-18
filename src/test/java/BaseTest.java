import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    //NOTE: USUALLY AFTER ONE RUN MOST OF THIS TEST CASES FAIL
    // BECAUSE THE ASSERTION IS LOOKING FOR THE GREEN NOTIFICATION AND IT
    //DOES NOT APPEAR ANYMORE AFTER THE FIRST TIME
    public static WebDriver driver;

    //WE DO NOT NEED THIS ONE ANYMORE BECAUSE IT IS CREATED IN
    // public void launchBrowser(String baseURL) {}
    //WE COULD DELETE THIS ONE OR ENABLE IT AS WELL SINCE IT IS ALREADY DECLARED OPTIONS INSIDE THE
    //public void launchBrowser(String baseURL) { BLOCK OF CODE
    ChromeOptions options = new ChromeOptions();
    WebDriverWait wait;

    //Fluent wait
    //Wait fluentWait;

    Actions actions;


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
        //-----CHROME SETUP------
        WebDriverManager.chromedriver().setup();

        //-----FIREFOX SETUP------
        //WebDriverManager.firefoxdriver().setup();

        //-----SAFARI SETUP------
        //WebDriverManager.safaridriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})//WE COPY THE SAME NAME AS THE XML FILE"TestNG"-> parameter
    //      Added ChromeOptions argument below to fix websocket error
    public void launchBrowser(String baseURL) throws MalformedURLException {
        //Initiate the Chrome browser to open the browser
        //ChromeOptions options = new ChromeOptions();--> we do not need this line since is static up in this class

        //-----CHROME SETUP------FOR THIS BROWSER WE ALWAYS NEED ("--remote-allow-origins=*");
        //options.addArguments("--remote-allow-origins=*");
        //driver = new ChromeDriver(options);

        //-----FIREFOX SETUP------we must comment the line for chrome before setting up firefox
        //driver= new FirefoxDriver();

        //-----SAFARI SETUP------
        //driver= new SafariDriver();

        //--------------------CROSS BROWSING SET UP-------------
        //this "browser" is the same NAME in build.gradle test(){
        driver = pickBrowser(System.getProperty("browser"));

        //***We can get rid of this implicit wait because we have an explicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //EXPLICIT WAIT
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //JUST AN EXAMPLE OF FLUENT WAIT
       /* fluentWait = new FluentWait( driver)
                //maximum time to wait before throwing an exception
                .withTimeout(Duration.ofSeconds(10))
                //Every 2 second will check if the element is visible or not
                .pollingEvery(Duration.ofSeconds(2));*/

        //this actions is declared in the beginner of this class
        actions = new Actions(driver);


        navigateToSite(baseURL);//THIS IS FROM LINE 81// CALLING THE METHOD "navigateToSite"
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

        //WebElement email = driver.findElement(By.cssSelector("input[type='email']"));
        //***We change the wait, so it will wait until the element is visible
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[type='email']")));
        email.clear();
        email.sendKeys(Email);


    }

    public void providePassword(String Password) {
        //STEP-3

        //WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        //***We change the wait so it will wait until the element is visible
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[type='password']")));
        password.clear();
        password.sendKeys(Password);
    }

    public void loginButton() {
        //STEP-4
        //WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        //***We change the wait so it will wait until the element is visible
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button[type='submit']")));
        loginButton.click();
    }


   /*            THIS METHODS HAVE BEEN MOVED TO BasePage CONSTANTLY USE

   //---------HOVER OVER ACTION CLASS-----------
    //it needs to be WebElement because it returns a WEB ELEMENT
    public WebElement hoverOver() {

        WebElement playResume = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        //moveToElement--> hover over the element
        actions.moveToElement(playResume).perform();
        return wait.until(ExpectedConditions.visibilityOf(playResume));
    }*/

   /* //VERIFICATION IS THE SONG IS PLAYING
    public boolean isSongPlaying() {
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("[data-testid= 'sound-bar-play']")));
        return soundBar.isDisplayed();


    }*/

    //----------------CROSS BROWSING SET UP------------
    //Selenium GRID//TO RUN TESTS in terminal in the same InteliJ you need to add these commands to run test in different browser
    //----> % gradle clean test -Dbrowser=Firefox
    //----> % gradle clean test -Dbrowser=Safari
    //----> % gradle clean test -Dbrowser=MicrosoftEdge(THIS DOES NOT WORK IN MY MAC)
    // /======> % gradle clean test -Dbrowser  -->IN THIS CASE NOT SPECIFY BROWSER IT WILL RUN DEFAULT WHICH IS CHROME

    public static WebDriver pickBrowser(String browserName) throws MalformedURLException {
        DesiredCapabilities caps =new DesiredCapabilities();
        String gridURL ="http://192.168.86.45:4444";
        switch (browserName){
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "Safari":
                WebDriverManager.safaridriver().setup();
                return driver= new SafariDriver();

                //FOR SOME REASON MicrosoftEdge BROWSER DOES NOT WORK IN MY MAC
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
                
                //----------Grid related Browsers/ This is a remote browser---------
            case"grid-firefox":
                caps.setCapability("browserName","firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case"grid-safari":
                caps.setCapability("browserName","safari");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case"grid-edge":
                caps.setCapability("browserName","MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);
            case"grid-chrome":
                caps.setCapability("browserName","chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(),caps);

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions ChromeOptions = new ChromeOptions();
                ChromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(ChromeOptions);
        }

    }
}
