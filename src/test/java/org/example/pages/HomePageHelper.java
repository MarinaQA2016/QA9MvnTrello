package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper extends PageBase{
    @FindBy(css = ".text-primary")
    WebElement logInIcon;

    public HomePageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(logInIcon,40);
    }

    public boolean isCorrectPage() {
        return logInIcon.getText().equals("Log in");
    }




}
