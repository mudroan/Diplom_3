import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.AuthorizationAccountPage;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.steps.UserSteps;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.config.RestConfig.HOST;

public class AccountTests {

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
    @DisplayName("Войти в личный кабинет")
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

        mainPage.enterAccount ();

        AuthorizationAccountPage authorizationAccountPage = new AuthorizationAccountPage (webDriver);

        assertTrue (authorizationAccountPage.exitButtonIsDisplayed ());

    }

    @Test
    @DisplayName("Из личного кабинета перейти в конструктор")
    public void clickToConstructorTest() {

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

        mainPage.enterAccount ();

        AuthorizationAccountPage authorizationAccountPage = new AuthorizationAccountPage (webDriver);
        authorizationAccountPage.clickConstructor ();

        assertTrue (mainPage.burgerTextIsDisplayed ());
        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @Test
    @DisplayName("Из личного кабинета нажать на логотип")
    public void clickToLogoTest() {

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

        mainPage.enterAccount ();

        AuthorizationAccountPage authorizationAccountPage = new AuthorizationAccountPage (webDriver);
        authorizationAccountPage.clickLogo ();

        assertTrue (mainPage.burgerTextIsDisplayed ());
        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @Test
    @DisplayName("Выйти из личного кабинета")
    public void exitUserAccountPageTest() {

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

        mainPage.enterAccount ();

        AuthorizationAccountPage authorizationAccountPage = new AuthorizationAccountPage (webDriver);
        authorizationAccountPage.exitButtonIsDisplayed ();
        authorizationAccountPage.clickExit ();

        assertTrue (loginPage.loginButtonIsDisplayed ());
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

