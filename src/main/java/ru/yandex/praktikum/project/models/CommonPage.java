package ru.yandex.praktikum.project.models;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public abstract class CommonPage {

    protected WebDriver driver;

    //Локатор кнопки "Да все привыкли"
    private By applyCookie = By.className("App_CookieButton__3cvqF");

    //Принять куки
    public void applyCookie(){
        waitVisibility(applyCookie);
        driver.findElement(applyCookie).click();
    }

    //ожидание появления элемента в поле зрения
    public void waitVisibility(By element){
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    //Скролл до элемента
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Вернули рандомный элемент из списка элементов, который при вызове метода положим в переменную типа WebElement
    public WebElement chooseRandomElementFromList(By elementsList){
        List<WebElement> allElements = driver.findElements(elementsList); //нашли и положили список элементов в переменную
        return allElements.get(new Random().nextInt(allElements.size())); //вернули раномный элемент из списка элементов
    }

    //Геттеры
    public By getApplyCookie() {
        return applyCookie;
    }
}
