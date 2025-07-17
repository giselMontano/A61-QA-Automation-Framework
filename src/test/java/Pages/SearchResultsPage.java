package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage{
    public SearchResultsPage(WebDriver GivenDriver) {
        super(GivenDriver);
    }
    //ELEMENTS
    By viewAll = By.cssSelector("button[data-test='view-all-songs-btn']");
    By clickSong = By.xpath("//tr[@class='song-item']//td[@class='title' and text()='Pluto']");
    By addToBttn = By.cssSelector("button[class='btn-add-to']");
    By notification = (By.cssSelector("div.success.show"));



    //HELPER METHODS, actions for those elements
    public void viewAllButton (){
        //THIS findElementWait is coming from BasePage that has the wait set up already
        findElementWait(viewAll).click();
    }
    public void clickFirstSong(){
        findElementWait(clickSong).click();
    }
    public void addToButton(){
        findElementWait(addToBttn).click();
    }
    public String greenNotification(){
        return  findElementWait(notification).getText();
    }

}

