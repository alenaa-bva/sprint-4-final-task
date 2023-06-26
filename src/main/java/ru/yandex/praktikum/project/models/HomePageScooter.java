package ru.yandex.praktikum.project.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageScooter extends CommonPage{

    //Локатор кнопки "Заказать" вверху страницы
    private By headerOrderButton = By.className("Button_Button__ra12g");
    //Локатор кнопки "Заказать" в середине страницы
    private By middleOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор 1-го элемента списка вопросов
    private By firstQuestion = By.xpath(".//div[@class='accordion__item'][1]/div/div");    //Локатор 1-го элемента списка ответов
    private By firstAnswer = By.xpath(".//div[@role='region']/p[1]");

    //конструктор класса
    public HomePageScooter(WebDriver driver){
        this.driver = driver;
    }

    //Клик на 1 вопрос из списка
    public void clickOnQuestionButton(){
        scrollToElement(driver.findElement(By.className("Home_FAQ__3uVm4")));
        driver.findElement(firstQuestion).click();
    }

    //Клик на кнопку заказать вверху страницы
    public void clickOnHeaderOrderButton(){
        driver.findElement(headerOrderButton).click();
        new WebDriverWait(driver, 5);
    }

    //Клик на кнопку заказать в середине страницы
    public void clickOnMiddleOrderButton(){
        scrollToElement(driver.findElement(middleOrderButton));
        driver.findElement(middleOrderButton).click();
        new WebDriverWait(driver, 5);
    }

    //геттеры
    public By getFirstAnswer() {
        return this.firstAnswer;
    }
    public By getMiddleOrderButton() {
        return this.middleOrderButton;
    }
}
