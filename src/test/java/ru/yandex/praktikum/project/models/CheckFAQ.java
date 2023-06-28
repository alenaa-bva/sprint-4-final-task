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

        private final String[] expected = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        };

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
            new WebDriverWait(driver, 5);
            String[] actualResults = homePageScooter.findAllAnswers();
            for (int i=0; i<8; i++) {
                assertEquals("Текст ответа не соответствует воспросу", expected[i], actualResults[i]);
            }
        }

        @After
        public void teardown() {
            driver.quit();
        }
    }
