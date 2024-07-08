package ru.yandex.praktikum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    // Локатор поля email
    private final By emailFieldLocator = By.xpath("//label[normalize-space()='Email']//following-sibling::*");

    // Локатор поля пароль
    private final By passwordFieldLocator = By.xpath("//*[@name='Пароль']");

    // Локатор кнопки [Войти]
    private final By logInButtonLocator = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");

    // Локатор кнопки [Зарегистрироваться]
    private final By registerButtonLocator = By.xpath("//*[@href='/register']");

    // Локатор кнопки [Восстановить пароль]
    private final By restorePasswordButtonLocator = By.xpath("//*[@href='/forgot-password']");

    public LoginPage (WebDriver webDriver) {
        super(webDriver);
    }

    @Step ("Ввести email на странице /login")
    public void enterEmail (String email) {

        WebElement emailUser = webDriver.findElement (emailFieldLocator);
        emailUser.sendKeys(email);
    }

    @Step ("Ввести пароль на странице /login")
    public void enterPassword (String password) {

        WebElement passwordlUser = webDriver.findElement (passwordFieldLocator);
        passwordlUser.sendKeys(password);
    }

    @Step ("Нажать кнопку [Войти] на странице /login")
    public void clickLogin () {

        WebElement loginButton = webDriver.findElement (logInButtonLocator);
        loginButton.click();
    }

    @Step("Проверка доступности кнопки [Войти] на главной странице")
    public boolean loginButtonIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));

        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonLocator));
        return loginButton.isDisplayed();
    }

    @Step ("Нажать кнопку [Зарегистрироваться] на странице /login")
    public void clickRegister () {

        WebElement registerButton = webDriver.findElement (registerButtonLocator);
        registerButton.click();
    }

    @Step ("Нажать кнопку [Восстановить] на странице /login")
    public void clickRestore () {

        WebElement restoreButton = webDriver.findElement (restorePasswordButtonLocator);
        restoreButton.click();
    }


}
