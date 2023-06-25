package ru.yandex.praktikum.project.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

public class CheckFAQ {
        WebDriver driver;
        DriverType driverType = DriverType.FIREFOX; //выбрали константу


    @Before
    public void before(){
        if(driverType == DriverType.FIREFOX){
            System.setProperty("webdriver.gecko.driver", "src\\main\\properties\\WebDriver\\bin\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get("https://qa-scooter.praktikum-services.ru");
        }
        else {
            System.setProperty("webdriver.chrome.driver", "src\\main\\properties\\WebDriver\\bin\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://qa-scooter.praktikum-services.ru");
        }

    }
        @Test
        public void checkFAQ() {
            HomePageScooter homePageScooter = new HomePageScooter(driver);
            //Ждем загрузки страницы и переходим на страницу заказа
            new WebDriverWait(driver, 5);
            homePageScooter.clickOnQuestionButton();
            String expected = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
            WebElement actual = driver.findElement(homePageScooter.getFirstAnswer());
            assertEquals("Текст ответа не совпадает со строкой: Сутки — 400 рублей. Оплата курьеру — наличными или картой.", expected, actual.getText());
        }

        @After
        public void teardown() {
            driver.quit();
        }
    }
