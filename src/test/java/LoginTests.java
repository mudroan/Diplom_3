import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.ForgotPasswordPage;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.RegisterPage;
import ru.yandex.praktikum.steps.UserSteps;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.config.RestConfig.HOST;

public class LoginTests {

    private WebDriver webDriver;

    private static final String BROWSER = "chrome";

    private UserSteps userSteps  = new UserSteps ();
    private String accessToken;

    String email = RandomStringUtils.randomAlphabetic(10)+"@ya.ru";
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getWebDriver (BROWSER);
        webDriver.get (HOST);
    }

    @Test
    @DisplayName("[Войти в аккаунт] на главной странице")
    public void loginMainPageTest() {

        accessToken = userSteps
                .createUser (email, password, name)
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        userSteps
                .getData (accessToken)
                .statusCode(200)
                .body("success", is (true));

        MainPage mainPage = new MainPage (webDriver);
        mainPage.enterLogin ();

        LoginPage loginPage = new LoginPage (webDriver);
        loginPage.enterEmail (email);
        loginPage.enterPassword (password);
        loginPage.clickLogin ();

        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @Test
    @DisplayName("Вход через кнопку [Личный кабинет]")
    public void loginAccountPageTest() {

        accessToken = userSteps
                .createUser (email, password, name)
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        userSteps
                .getData (accessToken)
                .statusCode(200)
                .body("success", is (true));

        MainPage mainPage = new MainPage (webDriver);
        mainPage.enterAccount ();

        LoginPage loginPage = new LoginPage (webDriver);
        loginPage.enterEmail (email);
        loginPage.enterPassword (password);
        loginPage.clickLogin ();

        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @Test
    @DisplayName("Вход через кнопку на форме регистрации")
    public void loginRegisterPageTest() {

        accessToken = userSteps
                .createUser (email, password, name)
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        userSteps
                .getData (accessToken)
                .statusCode(200)
                .body("success", is (true));

        MainPage mainPage = new MainPage (webDriver);
        mainPage.enterAccount ();

        LoginPage loginPage = new LoginPage (webDriver);
        loginPage.clickRegister ();

        RegisterPage registerPage = new RegisterPage (webDriver);
        registerPage.clickLogin ();

        loginPage.enterEmail (email);
        loginPage.enterPassword (password);
        loginPage.clickLogin ();

        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @Test
    @DisplayName("Вход через кнопку на форме восстановления пароля")
    public void loginForgotPasswordPageTest() {

        accessToken = userSteps
                .createUser (email, password, name)
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        userSteps
                .getData (accessToken)
                .statusCode(200)
                .body("success", is (true));

        MainPage mainPage = new MainPage (webDriver);
        mainPage.enterAccount ();

        LoginPage loginPage = new LoginPage (webDriver);
        loginPage.clickRestore ();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage (webDriver);
        forgotPasswordPage.clickLogin ();

        loginPage.enterEmail (email);
        loginPage.enterPassword (password);
        loginPage.clickLogin ();

        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @After
    public void tearDown() {

        if (accessToken != null) {
            userSteps
                    .delete (accessToken)
                    .statusCode (202)
                    .body ("message", is ("User successfully removed"));
        }
        webDriver.quit ();
    }
}
