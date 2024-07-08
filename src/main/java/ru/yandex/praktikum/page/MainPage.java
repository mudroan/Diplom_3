package ru.yandex.praktikum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage extends BasePage {

    //Локатор кнопки [Личный кабинет]
    private final By accountButtonLocator = By.xpath(".//a[@href='/account']");

    //Локатор кнопки [Войти в аккаунт]
    private final By logInButtonLocator = By.xpath("//*[text()='Войти в аккаунт']");

    // Локатор кнопки [Булки]
    private final By bunsButtonLocator = By.xpath("//*[contains(@class, 'tab')][normalize-space()='Булки']");

    // Локатор кнопки [Соусы]
    private final By saucesButtonLocator = By.xpath("//*[contains(@class, 'tab')][normalize-space()='Соусы']");

    // Локатор кнопки [Начинки]
    private final By fillingsButtonLocator = By.xpath("//*[contains(@class, 'tab')][normalize-space()='Начинки']");

    //Локатор текста "Соберите бургер"
    public final By burgerTextLocator = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");

    // Локатор кнопки [Оформить заказ]
    private final By createOrderButtonLocator = By.xpath ("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");

    public MainPage (WebDriver webDriver) {
        super (webDriver);
    }

    @Step ("Нажатие кнопки [Войти в аккаунт] на главной странице")
    public void enterLogin () {

        WebElement loginButton = webDriver.findElement (logInButtonLocator);
        loginButton.click();
    }

    @Step ("Нажатие кнопки [Личный кабинет] на главной странице")
    public void enterAccount () {

        WebElement accountButton = webDriver.findElement (accountButtonLocator);
        accountButton.click();
    }

    @Step("Проверка доступности кнопки [Оформить заказ] на главной странице")
    public boolean orderButtonIsDisplayed() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonLocator));
        return orderButton.isDisplayed();
    }

    @Step("Проверка доступности кнопки [Войти в аккаунт] на главной странице")
    public boolean loginButtonIsDisplayed() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(logInButtonLocator));
        return loginButton.isDisplayed();
    }

    @Step("Проверка доступности текста <Соберите бургер> на главной странице")
    public boolean burgerTextIsDisplayed() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        WebElement burgerText = wait.until(ExpectedConditions.visibilityOfElementLocated(burgerTextLocator));
        return burgerText.isDisplayed();
    }

    @Step ("Нажатие кнопки [Булки] на главной странице")
    public void enterBunsButton () {

        WebElement bunsButton = webDriver.findElement (bunsButtonLocator);
        bunsButton.click();
    }

    @Step ("Нажатие кнопки [Соусы] на главной странице")
    public void enterSaucesButton () {

        WebElement saucesButton = webDriver.findElement (saucesButtonLocator);
        saucesButton.click();
    }

    @Step ("Нажатие кнопки [Начинки] на главной странице")
    public void enterFillingsButton () {

        WebElement fillingsButton = webDriver.findElement (fillingsButtonLocator);
        fillingsButton.click();
    }
}
