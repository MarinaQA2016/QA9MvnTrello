package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpWindowPageHelper extends PageBase{
    @FindBy (xpath = "//span[contains(text(),'Go to your boards')]")
    WebElement goToBoardsButton;
    @FindBy (xpath = "//h1")
    WebElement header;

    public HelpWindowPageHelper (WebDriver driver){
        this.driver = driver;
    }


    public void waitUntilPageIsOpened() {
        String handleWindowFirst = driver.getWindowHandle();
        waitUntilWindowsToBe(2,15);
        String handleWindowSecond = getAnotherHandle(handleWindowFirst);
        driver.switchTo().window(handleWindowSecond);
        waitUntilElementIsClickable(goToBoardsButton,10);
    }

    public String getHeaderText(){
        return header.getText();
    }


    public void closeWindowByXAndSwitchToAnotherWindow() {
        String currentWindow = driver.getWindowHandle();
        String anotherWindow = this.getAnotherHandle(currentWindow);
        this.closeCurrentWindow();
        driver.switchTo().window(anotherWindow);

    }

    public void closeByGoToYourBoardsbutton() {
        goToBoardsButton.click();
    }
}
