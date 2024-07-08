package ru.yandex.praktikum.page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected final WebDriver webDriver;
    protected final WebDriverWait webDriverWait;

    public BasePage (WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait (webDriver, Duration.ofSeconds (3));
    }
}