package ru.yandex.praktikum.project.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class HomePageScooter extends CommonPage{

    //Локатор кнопки "Заказать" вверху страницы
    private By headerOrderButton = By.className("Button_Button__ra12g");
    //Локатор кнопки "Заказать" в середине страницы
    private By middleOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локаторы списка вопросов
    private By[] questions = new By[]{
            By.xpath(".//div[@class='accordion__item'][1]/div/div"),
            By.xpath(".//div[@class='accordion__item'][2]/div/div"),
            By.xpath(".//div[@class='accordion__item'][3]/div/div"),
            By.xpath(".//div[@class='accordion__item'][4]/div/div"),
            By.xpath(".//div[@class='accordion__item'][5]/div/div"),
            By.xpath(".//div[@class='accordion__item'][6]/div/div"),
            By.xpath(".//div[@class='accordion__item'][7]/div/div"),
            By.xpath(".//div[@class='accordion__item'][8]/div/div")
    };
    //Локаторы списка ответов
    private By[] answers = new By[]{
            By.xpath(".//div[@class='accordion__item'][1]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][2]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][3]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][4]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][5]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][6]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][7]/div[@role='region']/p"),
            By.xpath(".//div[@class='accordion__item'][8]/div[@role='region']/p")
    };


    //конструктор класса
    public HomePageScooter(WebDriver driver){
        this.driver = driver;
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

    public String[] findAllAnswers(){
        String[] allAnswers = new String[8];//Здесь пробовала с массивам элементов WebElement[], но почему-то возвращалась пустая строка при запуске теста
        for (int i=0; i<8; i++) {
            scrollToElement(driver.findElement(questions[i]));
            driver.findElement(questions[i]).click();
            String answer = driver.findElement(answers[i]).getText();
            allAnswers[i]= answer;
        }
        return allAnswers;
    }

    //геттеры
    public WebElement getMiddleOrderButton() {
        return driver.findElement(middleOrderButton);
    }
}
