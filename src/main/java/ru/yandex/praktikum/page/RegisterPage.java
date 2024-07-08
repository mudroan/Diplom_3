package ru.yandex.praktikum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    // Локатор поля "Имя"
    private final By nameFieldLocator = By.xpath("//*[normalize-space()='Имя']//following-sibling::*[@name='name']");

    // Локатор поля "Email"
    private final By emailFieldLocator = By.xpath("//*[normalize-space()='Email']//following-sibling::*[@name='name']");

    // Локатор поля пароль
    private final By passwordFieldLocator = By.xpath("//*[@name='Пароль']");

    // Локатор кнопки [Зарегистрироваться]
    private final By registerButtonLocator = By.xpath("//button[normalize-space()='Зарегистрироваться']");

    // Локатор кнопки [Войти]
    private final By logInButtonLocator = By.xpath("//*[@href='/login']");

    // Локатор текста "Некорректный пароль"
    private final By incorrectPasswordTextLocator = By.xpath("//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']");

    public RegisterPage (WebDriver webDriver) {
        super(webDriver);
    }

    @Step ("Ввод имени на странице /register")
    public void enterName (String name) {
        WebElement nameUser = webDriver.findElement (nameFieldLocator);
        nameUser.sendKeys(name);
    }

    @Step ("Ввод email на странице /register")
    public void enterEmail (String email) {
        WebElement emailUser = webDriver.findElement (emailFieldLocator);
        emailUser.sendKeys(email);
    }

    @Step ("Ввод пароля на странице /register")
    public void enterPassword (String password) {
        WebElement passwordUser = webDriver.findElement (passwordFieldLocator);
        passwordUser.sendKeys(password);
    }


    @Step ("Нажатие кнопки [Зарегистрироваться] на странице /register")
    public void clickRegister () {
        WebElement registerButton = webDriver.findElement (registerButtonLocator);
        registerButton.click();
    }

    @Step ("Нажатие кнопки [Войти] на странице /register")
    public void clickLogin () {
        WebElement logInButton = webDriver.findElement (logInButtonLocator);
        logInButton.click();
    }

    @Step("Проверка текста Некорректный пароль на странице /register")
    public boolean incorrectPasswordTextIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement incorrectPasswordText = wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordTextLocator));
        return incorrectPasswordText.isDisplayed();
    }
}
