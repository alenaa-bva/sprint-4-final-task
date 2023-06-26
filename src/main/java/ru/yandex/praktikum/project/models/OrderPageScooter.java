package ru.yandex.praktikum.project.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPageScooter extends CommonPage{

    //конструктор класса
    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }

    //локаторы для полей на странице 1 создания заказа
    private By customerFirstName = By.xpath(".//div[@class='Order_Form__17u6u']/div[@class='Input_InputContainer__3NykH'][1]/input");
    private By customerLastName = By.xpath(".//div[@class='Order_Form__17u6u']/div[@class='Input_InputContainer__3NykH'][2]/input");
    private By customerAddress = By.xpath(".//div[@class='Order_Form__17u6u']/div[@class='Input_InputContainer__3NykH'][3]/input");
    private By metroStation = By.xpath(".//div[@class='select-search__value']/input[@class='select-search__input']");
    private By customerPhone = By.xpath(".//div[@class='Order_Form__17u6u']/div[@class='Input_InputContainer__3NykH'][4]/input");
    //локатор для кнопки "Далее"
    private By nextButton = By.xpath(".//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //локаторы для полей на странице 2 создания заказа
    private By date = By.cssSelector(".react-datepicker__input-container .Input_Input__1iN_Z");
    private By rentalPeriod = By.cssSelector(".Dropdown-control .Dropdown-placeholder");
    private By comment = By.xpath(".//div[@class='Order_Form__17u6u']/div[@class='Input_InputContainer__3NykH']/input");
    //локатор для кнопки "Заказать"
    private By finishOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[2]");
    //локатор для кнопки "Да" в конфирмейшене
    private By yesButton = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[2]");

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



