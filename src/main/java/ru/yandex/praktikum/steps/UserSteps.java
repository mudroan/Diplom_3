package ru.yandex.praktikum.steps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.dto.CreateUserRequest;
import ru.yandex.praktikum.dto.LoginUserRequest;
import static ru.yandex.praktikum.config.RestConfig.HOST;
import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.config.ApiURI.*;

public class UserSteps {

    @Step("Создание пользователя")
    public ValidatableResponse createUser (String email, String password, String name) {

        CreateUserRequest createUserRequest = new CreateUserRequest ();

        createUserRequest.setEmail (email);
        createUserRequest.setPassword (password);
        createUserRequest.setName (name);

        return given ()
                .contentType (ContentType.JSON)
                .baseUri (HOST)
                .body (createUserRequest)
                .when ()
                .post (CREATE_USER)
                .then ();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser (String email, String password) {

        CreateUserRequest createUserRequest = new CreateUserRequest ();
        LoginUserRequest loginUserRequest = new LoginUserRequest ();

        createUserRequest.setEmail (email);
        createUserRequest.setPassword (password);

        loginUserRequest.setEmail (email);
        loginUserRequest.setPassword (password);

        return given ()
                .contentType (ContentType.JSON)
                .baseUri (HOST)
                .body (createUserRequest)
                .when ()
                .post (LOGIN_USER)
                .then ();
    }

    @Step("Получение данных пользователя")
    public ValidatableResponse getData (String accessToken) {

        return given()
                .contentType (ContentType.JSON)
                .baseUri (HOST)
                .header("Authorization", accessToken)
                .when()
                .get(CHANGE_DATA_USER)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete (String accessToken) {
        return given()
                .contentType (ContentType.JSON)
                .baseUri (HOST)
                .header("Authorization", accessToken)
                .when()
                .delete (DELETE_USER)
                .then();
    }
}