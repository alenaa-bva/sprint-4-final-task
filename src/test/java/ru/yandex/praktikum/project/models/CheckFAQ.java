package ru.yandex.praktikum.project.models;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckFAQ {
        WebDriver driver;

    @Before
    public void before(){
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
    }
        @Test
        public void checkFAQ() {
            HomePageScooter homePageScooter = new HomePageScooter(driver);
            //Ждем загрузки страницы и переходим на страницу заказа
            new WebDriverWait(driver, 5);
            homePageScooter.clickOnQuestionButton();
            String expected = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
            WebElement actual = driver.findElement(homePageScooter.getFirstAnswer());
            assertThat("Текст ответа не совпадает со строкой: Сутки — 400 рублей. Оплата курьеру — наличными или картой.", expected, is(actual.getText()));
        }

        @After
        public void teardown() {
            driver.quit();
        }
    }
