package ru.yandex.praktikum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends BasePage {

    // Локатор кнопки [Войти]
    private final By loginButtonLocator = By.xpath(".//a[@href='/login'][text()='Войти']");

    public ForgotPasswordPage (WebDriver webDriver) {
        super(webDriver);
    }

    @Step ("Нажать кнопку [Войти] на странице /forgot-password")
    public void clickLogin () {
        WebElement loginButton = webDriver.findElement (loginButtonLocator);
        loginButton.click();
    }
}
