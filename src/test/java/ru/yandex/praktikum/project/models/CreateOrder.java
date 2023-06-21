package ru.yandex.praktikum.project.models;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class CreateOrder {

    WebDriver driver;
    private final String checkName;
    private final String checkLastName;

    public CreateOrder(String checkName, String lastName) {
        this.checkName = checkName;
        this.checkLastName = lastName;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][] {
                {"Алена", "Ибрагимова"},
                {"а", "а"},
                {" ", "g"},
                {"тесттесттесттесттесттесттесттесттесттесттесттесттесттесттесттест", "https://qa-scooter.praktikum-services.ru"},
        };

    }
    @Before
    public void before(){
        System.setProperty("webdriver.gecko.driver", "C:\\WebDriver\\bin\\geckodriver-v0.33.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void checkCreateOrderWithHeaderButton(){
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        //Принимаем куки, чтобы они нам не перекрывали контент и переходим на страницу заказа
        homePageScooter.applyCookie();
        homePageScooter.clickOnHeaderOrderButton();

        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);

        orderPageScooter.waitVisibility(orderPageScooter.getCustomerFirstNameField());

        //Заполнение текстовых полей
        orderPageScooter.setCustomerFirstName(checkName);
        orderPageScooter.setCustomerLastName(checkLastName);
        orderPageScooter.setCustomerAddress("Москва");
        orderPageScooter.setCustomerPhone("+79018038959");
        //Выбор станции метро
        orderPageScooter.chooseRandomMetroStation();


        //Переходим на 2 страницу создания заказа
        orderPageScooter.clickOnNextButton();
        WebElement header = driver.findElement(By.xpath(".//div[@class='Order_Header__BZXOb']"));
        assertNotEquals("Произошла ошибка на странице - Для кого самокат", header.getText(), "Для кого самокат");

        //Заполнение поля "Когда привезти самокат"
        orderPageScooter.chooseRandomDate();

        //Заполнение поля "Срок аренды"
        orderPageScooter.chooseRandomRentalPeriod();

        //Выбор цвета самоката
        orderPageScooter.chooseRandomColor();

        //Заполнение поля "Комментарий для курьера"
        orderPageScooter.clickOnComment();
        orderPageScooter.setComment("Как можно быстрее");

        //Подтверждение заказа
        orderPageScooter.clickOnFinishOrderButton();
        orderPageScooter.clickOnYesButton();

        //Ждем пока появится текст "Заказ оформлен"
        orderPageScooter.waitVisibility(By.xpath(".//div[text()='Заказ оформлен']"));
    }

    @Test
    public void checkCreateOrderWithMiddleButton() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        //Принимаем куки, чтобы они нам не перекрывали контент и переходим на страницу заказа
        homePageScooter.applyCookie();

        //Скроллим до кнопки "Заказать"
        homePageScooter.scrollToElement(driver.findElement(homePageScooter.getMiddleOrderButton()));
        homePageScooter.clickOnMiddleOrderButton();

        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);

        orderPageScooter.waitVisibility(orderPageScooter.getCustomerFirstNameField());

        //Заполнение текстовых полей
        orderPageScooter.setCustomerFirstName(checkName);
        orderPageScooter.setCustomerLastName(checkLastName);
        orderPageScooter.setCustomerAddress("Москва");
        orderPageScooter.setCustomerPhone("+79018038959");

        //Выбор станции метро
        orderPageScooter.chooseRandomMetroStation();

        //Переходим на 2 страницу создания заказа
        orderPageScooter.clickOnNextButton();
        WebElement header = driver.findElement(By.xpath(".//div[@class='Order_Header__BZXOb']"));
        assertNotEquals("Произошла ошибка на странице - Для кого самокат", header.getText(), "Для кого самокат");

        //Заполнение поля "Когда привезти самокат"
        orderPageScooter.chooseRandomDate();

        //Заполнение поля "Срок аренды"
        orderPageScooter.chooseRandomRentalPeriod();

        //Выбор цвета самоката
        orderPageScooter.chooseRandomColor();

        //Заполнение поля "Комментарий для курьера"
        orderPageScooter.clickOnComment();
        orderPageScooter.setComment("Как можно быстрее");

        //Подтверждение заказа
        orderPageScooter.clickOnFinishOrderButton();
        orderPageScooter.clickOnYesButton();

        //Ждем пока появится текст "Заказ оформлен"
        orderPageScooter.waitVisibility(By.xpath(".//div[text()='Заказ оформлен']"));
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
