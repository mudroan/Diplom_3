import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.RegisterPage;
import ru.yandex.praktikum.steps.UserSteps;
import static org.hamcrest.CoreMatchers.is;
import static ru.yandex.praktikum.config.RestConfig.HOST;
import static org.junit.Assert.assertTrue;
public class RegistrationUserTests {

    private WebDriver webDriver;

    private static final String BROWSER = "chrome";

    private UserSteps userSteps  = new UserSteps ();
    private String accessToken;

    String name = RandomStringUtils.randomAlphabetic (10);
    String email = RandomStringUtils.randomAlphabetic (6) + "@ya.ru";
    String password = RandomStringUtils.randomAlphabetic (8);
    String minPassword = RandomStringUtils.randomAlphabetic (4);

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getWebDriver (BROWSER);
        webDriver.get (HOST);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registrationTest() {

        MainPage mainPage = new MainPage (webDriver);
        mainPage.enterAccount ();

        LoginPage loginPage = new LoginPage (webDriver);
        loginPage.clickRegister ();

        RegisterPage registerPage = new RegisterPage (webDriver);
        registerPage.enterName (name);
        registerPage.enterEmail (email);
        registerPage.enterPassword (password);
        registerPage.clickRegister ();

        loginPage.enterEmail (email);
        loginPage.enterPassword (password);
        loginPage.clickLogin ();

        assertTrue (loginPage.loginButtonIsDisplayed ());

        accessToken = userSteps
                .loginUser (email, password)
                .statusCode(200)
                .extract().jsonPath().getString("accessToken");

        userSteps
                .getData (accessToken)
                .statusCode(200)
                .body("success", is (true));

    }

    @Test
    @DisplayName("Ошибка при регистрация пользователя с паролем менее 6 символов")
    public void errorRegistrationUserTest() {

        MainPage mainPage = new MainPage (webDriver);
        mainPage.enterAccount ();

        LoginPage loginPage = new LoginPage (webDriver);
        loginPage.clickRegister ();

        RegisterPage registerPage = new RegisterPage (webDriver);

        registerPage.enterName (name);
        registerPage.enterEmail (email);
        registerPage.enterPassword (minPassword);
        registerPage.clickRegister ();

        assertTrue (registerPage.incorrectPasswordTextIsDisplayed ());
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
