package org.example.tests;


import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    //HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests()  {
       // homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
    }


    @Test
    public void negativeLogin()  {
        loginPage.loginNotAttl("ttttmmm","pppppppp");
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
    }
    @Test
    public void positiveLogin()  {
        loginPage.loginAsAttl(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),"Boards",
        "Name of the button is not 'Boards'");

    }



}
