package tests.api.tests.pages.login;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.api.specifications.Specifications;
import tests.api.tests.base.BaseTest;

import static io.restassured.RestAssured.given;
import static tests.constants.Constant.Datas.*;


public class LoginPageAuthorizationAPITest extends BaseTest {
    private final static String URL = "http://localhost:80/";

    /**
     * Тест проверяет, что пользователь, который ввёл
     * корректно свой логин и пароль, успешно логинится и получает в ответ cookies.
     * Тест прогоняется на данных login, email и password
     * трёх тестовых пользователей. В качестве параметров идут
     * логин и пароль пользователей. Логином может быть login
     * и email.
     * @param login
     * @param password
     */
    @Tag("TestAPI")
    @Tag("TestLoginPage")
    @ParameterizedTest
    @MethodSource("usersData")
    public void successAuthorizationTest(String login, String password) {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(302));
        String params = "/?login=" + login + "&password=" + password;
        Cookies cookies = given()
                .when()
                .post("/signin" + params)
                .then()
                .extract()
                .response()
                .getDetailedCookies();
        //System.out.println(cookies.hasCookieWithName(SESSION_ID_COOKIE_NAME));
        Assertions.assertTrue(cookies.hasCookieWithName(SESSION_ID_COOKIE_NAME),
                "Ошибка в определении ID сессии в файлах cookie (не найден параметр с данным именем). \n");
    }

    /**
     * Тест проверяет, что пользователь, который неверно ввёл логин,
     * получит соответствующее сообщение, что данные не найдены.
     */
    @Test
    @Tag("TestAPI")
    @Tag("TestLoginPage")
    public void unsuccessAuthorizationWithWrongLoginTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(404));
        String params = "/?login=" + WRONG_LOGIN_NAME + "&password=" + CORRECT_PASSWORD;
        Response response = given()
                .when()
                .post("/signin" + params)
                .then().log().all()
                .extract().response();
        Assertions.assertTrue(response.asString().contains(ERROR_LOGIN_NAME_VALUE),
                "Сообщение с ошибкой не содержит объявленное в константах. \n" +
                        "Ожидаемый результат: " + ERROR_LOGIN_NAME_VALUE + " \n" +
                        "Фактический результат: " + response.asString() + ".");
    }

    /**
     * Тест проверяет, что пользователь, который неверно ввёл пароль,
     * получит соответствующее сообщение, что не получается войти.
     */
    @Test
    @Tag("TestAPI")
    @Tag("TestLoginPage")
    public void unsuccessAuthorizationWithWrongPasswordTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(401));
        String params = "/?login=" + CORRECT_LOGIN_NAME + "&password=" + WRONG_PASSWORD;
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .post("/signin" + params)
                .then().log().all()
                .extract().response();
        Assertions.assertTrue(response.asString().contains(ERROR_PASSWORD_VALUE),
                "Сообщение с ошибкой не содержит объявленное в константах. \n" +
                        "Ожидаемый результат: " + ERROR_PASSWORD_VALUE + " \n" +
                        "Фактический результат: " + response.asString() + ".");
    }

    /**
     * Тест проверяет, что пользователь, который верно ввёл email,
     * но ошибся регистром, получит соответствующее сообщение, что данные не найдены.
     */
    @Test
    @Tag("TestAPI")
    @Tag("TestLoginPage")
    public void unsuccessAuthorizationWithWrongRegisterEmailTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(404));
        String params = "/?login=" + WRONG_REGISTER_EMAIL + "&password=" + CORRECT_PASSWORD;
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .post("/signin" + params)
                .then().log().all()
                .extract().response();
        Assertions.assertTrue(response.asString().contains(ERROR_LOGIN_NAME_VALUE),
                "Сообщение с ошибкой не содержит объявленное в константах. \n" +
                        "Ожидаемый результат: " + ERROR_LOGIN_NAME_VALUE + " \n" +
                        "Фактический результат: " + response.asString() + ".");
    }
}