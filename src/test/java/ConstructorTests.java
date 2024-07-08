import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.steps.UserSteps;
import static org.hamcrest.CoreMatchers.is;
import static ru.yandex.praktikum.config.RestConfig.HOST;
import static org.junit.Assert.assertTrue;

public class ConstructorTests {

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
    @DisplayName("Переключение между разделами главной страницы: Булки, Соусы, Начинки с авторизацией пользователя")
    public void switchConstructorSectionTest() {

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

        mainPage.burgerTextIsDisplayed ();

        mainPage.enterSaucesButton ();
        mainPage.enterFillingsButton ();
        mainPage.enterBunsButton ();

        assertTrue (mainPage.orderButtonIsDisplayed ());
    }

    @Test
    @DisplayName("Переключение между разделами главной страницы: Булки, Соусы, Начинки без авторизацией пользователя")
    public void switchConstructorSectionUnauthorizedUserTest() {

        MainPage mainPage = new MainPage (webDriver);

        mainPage.burgerTextIsDisplayed ();

        mainPage.enterSaucesButton ();
        mainPage.enterFillingsButton ();
        mainPage.enterBunsButton ();

        assertTrue (mainPage.loginButtonIsDisplayed ());
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
