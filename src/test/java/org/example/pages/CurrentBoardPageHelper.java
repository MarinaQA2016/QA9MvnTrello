package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase {
    @FindBy(css = ".placeholder")
    WebElement addListButton;
    @FindBy(css = ".js-list-content")
    List<WebElement> collumnsList;
    @FindBy(css = "input[name='name']")
    WebElement nameListField;
    @FindBy(css = ".js-save-edit")
    WebElement saveListButton;
    @FindBy(css = ".js-cancel-edit")
    WebElement xCancelEditList;
    @FindBy(css = ".card-composer-container")
    WebElement addCardButton;
    @FindBy(css =".js-card-title")
    WebElement cardTitleField;
    @FindBy(css = ".js-add-card")
    WebElement submitCardButton;
    @FindBy(css = ".js-cancel")
    WebElement xCancelButton;
    @FindBy(css = ".list-header-extras-menu")
    WebElement listMenuButton;
    @FindBy(css = ".js-copy-list")
    WebElement copyMenu;
    @FindBy(css = ".js-autofocus")
    WebElement copyTitleListField;
    @FindBy(css = ".js-submit")
    WebElement submitCopyListButton;
    @FindBy(css = ".list-header-extras-menu")
    List<WebElement> collumnsMenuList;
    @FindBy(css = ".js-close-list")
    WebElement archiveMenuOption;
    @FindBy (css = ".js-board-editing-target")
    WebElement boardNameButton;

    String boardName;
    public CurrentBoardPageHelper(WebDriver driver, String boardName){
        this.driver = driver;
        this.boardName = boardName;
        PageFactory.initElements(driver,this);
    }

    public CurrentBoardPageHelper openPage() {
        waitUntilElementIsClickable(getLocatorBoardButton(),10);
        // open 'QA Haifa9' board
        WebElement qaHaifa9Board = driver.findElement(getLocatorBoardButton());
        qaHaifa9Board.click();
        return this;
    }

    public By getLocatorBoardButton(){
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='"+boardName+"']]");
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addListButton,10);

        if (addListButton.getText().equals("Add another list")) {
            //waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"),5);
            waitUntilAllElementsAreVisible(collumnsList,10);
        }
    }
    public int getListsQuantity() {
        //List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        return collumnsList.size();
    }
    public int getCardsQuantity() {
        List<WebElement> collumnsList = driver.findElements(By.cssSelector(".list-card-title"));
        return collumnsList.size();
    }

    public void addNewList(String name) {
        int beginListsQuantity = this.getListsQuantity();
        addListButton.click();
        editField(nameListField, name);
        saveListButton.click();
        // click 'x' button to cancel new list creating
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
        System.out.println("After adding: " + this.getListsQuantity());
        waitUntilElementIsClickable(xCancelEditList,5);
        //WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
        xCancelEditList.click();
        waitUntilElementIsClickable(addListButton,5);
    }

    public void addNewCardToFirstList(String name) {
        int beginCards = this.getCardsQuantity();
        addCardButton.click();
        // fill in card title
        editField(cardTitleField, "card title");
        submitCardButton.click();
        waitUntilElementsBecome(By.cssSelector(".list-card-title"),beginCards+1,10);
       // --- cancel new list creation ---
        waitUntilElementIsClickable(xCancelEditList,5);
        xCancelButton.click();
    }

    public void archiveFirstList() {
       this.archiveList(0);
    }

    public void copyFirstList(String name) {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(listMenuButton,5);
        listMenuButton.click();

        // -- click on "Copy menu"
        waitUntilElementIsClickable(copyMenu,10);
        copyMenu.click();

        //-- fill in title---
        waitUntilElementIsClickable(copyTitleListField,5);
        copyTitleListField.sendKeys(name);

        //--- submit copy list opetion ---
        waitUntilElementIsClickable(submitCopyListButton,10);
        submitCopyListButton.click();

        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,5);
    }

    public int getNumberOfElementWithName(String nameList) {
        int number = -1;
        int counter = 0;
        for(WebElement element: driver.findElements(By.cssSelector(".list-header-name"))){
            if(element.getText().equals(nameList)){
                number = counter;
            }
            counter++;
        }
        return number;
    }

    public void archiveList(int number) {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(collumnsMenuList.get(number),5);
        collumnsMenuList.get(number).click();

        // -- click on "Archive menu"
        waitUntilElementIsClickable(archiveMenuOption,10);
        archiveMenuOption.click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);
    }

    public boolean isCorrectPage() {
        return boardNameButton.getText().equals(boardName);
    }

    public String getNameBoard() {
        return boardName;
    }
}
