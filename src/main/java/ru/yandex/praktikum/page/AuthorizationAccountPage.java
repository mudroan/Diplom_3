package ru.yandex.praktikum.page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationAccountPage extends BasePage {

    //Локатор кнопки [Выход]
    private final By exitButtonLocator = By.xpath("//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']");

    // Локатор кнопки [Конструктор]
    private final By constructorButtonLocator = By.xpath("//li[normalize-space()='Конструктор']");

    // Локатор логотипа
    private final By logoButtonLocator = By.xpath ("//div[@class='AppHeader_header__logo__2D0X2']");

    public AuthorizationAccountPage (WebDriver webDriver) {

        super(webDriver);
    }

    @Step("Поиск и нажатие кнопки [Выход] на странице /account/profile")
    public void clickExit () {

        WebElement exitButton = webDriver.findElement (exitButtonLocator);
        exitButton.click();
    }

    @Step("Поиск и нажатие кнопки [Конструктор] на странице /account/profile")
    public void clickConstructor () {

        WebElement constructorButton = webDriver.findElement (constructorButtonLocator);
        constructorButton.click();
    }

    @Step("Поиск и нажатие на логотип на странице /account/profile")
    public void clickLogo () {

        WebElement logoButton = webDriver.findElement (logoButtonLocator);
        logoButton.click();
    }

    @Step("Проверка доступности кнопки [Выход] на странице личного кабинета")
    public boolean exitButtonIsDisplayed() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));

        WebElement exitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(exitButtonLocator));
        return exitButton.isDisplayed();
    }
}
