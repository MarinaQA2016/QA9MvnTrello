package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpWindowPageTests extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa9Board;
    MenuPageHelper menuPage;
    HelpWindowPageHelper helpWindow;


    @BeforeMethod
    public void initTests(){
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qaHaifa9Board = new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        helpWindow = PageFactory.initElements(driver, HelpWindowPageHelper.class);


        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAsAttl(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openBoardsMenu();
        qaHaifa9Board.openPage();
        qaHaifa9Board.waitUntilPageIsLoaded();
        menuPage.openPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openHelpWindow();
        helpWindow.waitUntilPageIsOpened();
    }

    @Test
    public void helpWindowVerification(){
        Assert.assertEquals(helpWindow.getHeaderText(), "Get help with Trello");
    }

    @Test
    public void closeWindowByXVerification(){
        helpWindow.closeWindowByXAndSwitchToAnotherWindow();
        qaHaifa9Board.waitUntilPageIsLoaded();
        Assert.assertTrue(qaHaifa9Board.isCorrectPage());
    }

    @Test
    public void closeByGoToYourBoardsButtonVerification(){
        helpWindow.closeByGoToYourBoardsbutton();
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),"Boards",
                "Name of the button is not 'Boards'");
    }

}
