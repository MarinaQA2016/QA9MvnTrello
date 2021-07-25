package org.example.tests;


import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

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
    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void negativeLoginThirdDataProv(String login, String password)  {
        loginPage.loginNotAttl(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this email",
                "The error message is not correct");
    }
    @Test(dataProviderClass = DataProviders.class, dataProvider ="dataProviderSecond")
    public void positiveLogin(String login, String password, String nameButton)  {
        //loginPage.loginAsAttl(LOGIN,PASSWORD);
        loginPage.loginAsAttl(login,password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),nameButton,
        "Name of the button is not 'Boards'");

    }
    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeDifferentMessages")
    public void loginNegativeDifferentMessages(String login, String password, String message){
        loginPage.loginNotAttl(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),message,
                "The error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeRandomData")
    public void loginNegativeRandomData(String login, String password){
        loginPage.loginNotAttl(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
    }


}
