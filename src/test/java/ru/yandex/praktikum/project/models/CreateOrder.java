package ru.yandex.praktikum.project.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateOrder {

    WebDriver driver;
    DriverType driverType = DriverType.FIREFOX; //выбрали константу
    private final String checkName;
    private final String checkLastName;
    private final String checkAddress;
    private final String checkPhone;
    private final String checkComment;

    public CreateOrder(String checkName, String checkLastName, String checkAddress, String checkPhone, String checkComment) {
        this.checkName = checkName;
        this.checkLastName = checkLastName;
        this.checkAddress = checkAddress;
        this.checkPhone = checkPhone;
        this.checkComment = checkComment;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return new Object[][] {
                {"Алена", "Ибрагимова", "Москва", "+7911111111", "Как можно быстрее"}, //валидные данные
                {"ав", "ав", "Лесная 35", "80000000000", ""}, //валидные данные
                {"ав", "ав", "Лесная 35", "+79111111111", "comment"}, //валидные данные
                {"АЛЕНА", "ИБРАГИМОВА", "Лесная 35", "1111111111", ""}, //невалидные данные checkPhone
                {"алена ибрагимова", "алена ибрагимова", "Lesnaya", "", ""},//невалидные данные checkName checkLastName checkAddress checkPhone
                {"тесттесттесттесттесттесттесттесттесттесттесттесттесттесттесттест", "https://qa-scooter.praktikum-services.ru", "12345", "текст", ""},//невалидные данные checkName checkLastName checkAddress checkPhone
        };

    }
    @Before
    public void before(){
            if (driverType == DriverType.FIREFOX) {
                System.setProperty("webdriver.gecko.driver", "src\\main\\properties\\WebDriver\\bin\\geckodriver.exe");
                driver = new FirefoxDriver();
                driver.get("https://qa-scooter.praktikum-services.ru");
            } else {
                System.setProperty("webdriver.chrome.driver", "src\\main\\properties\\WebDriver\\bin\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.get("https://qa-scooter.praktikum-services.ru");
            }
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
        orderPageScooter.setCustomerAddress(checkAddress);
        orderPageScooter.setCustomerPhone(checkPhone);
        //Выбор станции метро
        orderPageScooter.chooseRandomMetroStation();


        //Переходим на 2 страницу создания заказа
        orderPageScooter.clickOnNextButton();
        orderPageScooter.waitVisibility(orderPageScooter.getOrderPageHeader());

        //Заполнение поля "Когда привезти самокат"
        orderPageScooter.chooseRandomDate();

        //Заполнение поля "Срок аренды"
        orderPageScooter.chooseRandomRentalPeriod();

        //Выбор цвета самоката
        orderPageScooter.chooseRandomColor();

        //Заполнение поля "Комментарий для курьера"
        orderPageScooter.clickOnComment();
        orderPageScooter.setComment(checkComment);

        //Подтверждение заказа
        orderPageScooter.clickOnFinishOrderButton();
        orderPageScooter.clickOnYesButton();

        //Проверяем, появился ли элемент, который содержит номер заказа
        WebElement actual = orderPageScooter.getOrderNumber();
        assertTrue("Произошла ошибка при оформлении заказа", actual.isDisplayed());
    }

    @Test
    public void checkCreateOrderWithMiddleButton() {
        HomePageScooter homePageScooter = new HomePageScooter(driver);

        //Скроллим до кнопки "Заказать"
        homePageScooter.scrollToElement(homePageScooter.getMiddleOrderButton());
        homePageScooter.clickOnMiddleOrderButton();

        OrderPageScooter orderPageScooter = new OrderPageScooter(driver);

        orderPageScooter.waitVisibility(orderPageScooter.getCustomerFirstNameField());

        //Заполнение текстовых полей
        orderPageScooter.setCustomerFirstName(checkName);
        orderPageScooter.setCustomerLastName(checkLastName);
        orderPageScooter.setCustomerAddress(checkAddress);
        orderPageScooter.setCustomerPhone(checkPhone);

        //Выбор станции метро
        orderPageScooter.chooseRandomMetroStation();

        //Переходим на 2 страницу создания заказа
        orderPageScooter.clickOnNextButton();
        orderPageScooter.waitVisibility(orderPageScooter.getOrderPageHeader());

        //Заполнение поля "Когда привезти самокат"
        orderPageScooter.chooseRandomDate();

        //Заполнение поля "Срок аренды"
        orderPageScooter.chooseRandomRentalPeriod();

        //Выбор цвета самоката
        orderPageScooter.chooseRandomColor();

        //Заполнение поля "Комментарий для курьера"
        orderPageScooter.clickOnComment();
        orderPageScooter.setComment(checkComment);

        //Подтверждение заказа
        orderPageScooter.clickOnFinishOrderButton();
        orderPageScooter.clickOnYesButton();

        //Проверяем, появился ли элемент, который содержит номер заказа
        WebElement actual = orderPageScooter.getOrderNumber();
        assertTrue("Произошла ошибка при оформлении заказа", actual.isDisplayed());
    }

    @After
    public void teardown(){
        driver.quit();
    }
}
