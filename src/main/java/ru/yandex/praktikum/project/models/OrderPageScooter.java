package ru.yandex.praktikum.project.models;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class OrderPageScooter extends CommonPage{

    //конструктор класса
    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }

    //локаторы для полей на странице 1 создания заказа
    private By customerFirstName = By.cssSelector("input[placeholder='* Имя']");
    private By customerLastName = By.cssSelector("input[placeholder='* Фамилия']");
    private By customerAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private By metroStation = By.cssSelector("input[placeholder='* Станция метро']");
    private By customerPhone = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    //локатор для кнопки "Далее"
    private By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");
    //локатор для кнопки "Да" в конфирмейшене
    private By yesButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");

    //локаторы для полей на странице 2 создания заказа
    private By date = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    private By rentalPeriod = By.xpath(".//div[text()='* Срок аренды']");
    private By comment = By.cssSelector("input[placeholder='Комментарий для курьера']");
    //локатор для кнопки "Заказать"
    private By finishOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");


    //методы
    public void clickOnNextButton(){
        driver.findElement(nextButton).click();
    }

    public void clickOnMetroStationField(){
            driver.findElement(metroStation).click();
            waitVisibility(By.xpath(".//div[@class='select-search__select']"));
        }

    public void clickOnDate(){
        driver.findElement(date).click();
        waitVisibility(By.className("react-datepicker__month-container"));
    }

    public void clickOnRentalPeriod(){
        driver.findElement(rentalPeriod).click();
        waitVisibility(By.xpath(".//div[@class='Dropdown-root is-open']/div[@class='Dropdown-menu']"));
    }

    public void clickOnComment(){
        driver.findElement(comment).click();
    }

    public void clickOnFinishOrderButton(){
        driver.findElement(finishOrderButton).click();
        waitVisibility(By.xpath(".//div[@class='Order_Modal__YZ-d3']"));
    }

    public void clickOnYesButton(){
        driver.findElement(yesButton).click();
    }

    public void chooseRandomMetroStation(){
        clickOnMetroStationField();
        WebElement randomMetroStation = chooseRandomElementFromList(By.xpath(".//ul[@class='select-search__options']/li[@class='select-search__row']"));
        scrollToElement(randomMetroStation);
        randomMetroStation.click();
    }

    public void chooseRandomRentalPeriod(){
        clickOnRentalPeriod();
        WebElement randomPeriod = chooseRandomElementFromList(By.xpath(".//div[@class='Dropdown-root is-open']/div[@class='Dropdown-menu']/div[@role='option']"));
        scrollToElement(randomPeriod);
        randomPeriod.click();
    }

    public void chooseRandomColor(){
        WebElement randomColor = chooseRandomElementFromList(By.xpath(".//label[@class='Checkbox_Label__3wxSf']"));
        randomColor.click();
    }

    public void chooseRandomDate(){
        clickOnDate();
        WebElement randomDate = chooseRandomElementFromList(By.xpath(".//div[@role='button']"));
        scrollToElement(randomDate);
        randomDate.click();
    }

        //Сеттеры для полей на странице 1 создания заказа
        public void setCustomerFirstName (String customerFirstName){
            driver.findElement(this.customerFirstName).sendKeys(customerFirstName);
        }
        public void setCustomerLastName (String customerLastName){
            driver.findElement(this.customerLastName).sendKeys(customerLastName);
        }
        public void setCustomerAddress (String customerAddress){
            driver.findElement(this.customerAddress).sendKeys(customerAddress);
        }
        public void setCustomerPhone (String customerPhone){
            driver.findElement(this.customerPhone).sendKeys(customerPhone);
        }
        public void setDate (String date){
        driver.findElement(this.date).sendKeys(date);
        }

        //Геттеры для полей на странице 1 создания заказа
        public By getCustomerFirstNameField () {
            return this.customerFirstName;
        }

        //Сеттеры для полей на странице 2 создания заказа
        public void setComment (String comment){
            driver.findElement(this.comment).sendKeys(comment);
        }

        //Геттеры для полей на странице 2 создания заказа
        public By getDate () {
            return this.date;
        }
        public By getNextButton () {
            return this.nextButton;
        }
    }



