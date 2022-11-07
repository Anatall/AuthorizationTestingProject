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
     * ���� ���������, ��� ������������, ������� ���
     * ��������� ���� ����� � ������, ������� ��������� � �������� � ����� cookies.
     * ���� ����������� �� ������ login, email � password
     * ��� �������� �������������. � �������� ���������� ����
     * ����� � ������ �������������. ������� ����� ���� login
     * � email.
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
                "������ � ����������� ID ������ � ������ cookie (�� ������ �������� � ������ ������). \n");
    }

    /**
     * ���� ���������, ��� ������������, ������� ������� ��� �����,
     * ������� ��������������� ���������, ��� ������ �� �������.
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
                "��������� � ������� �� �������� ����������� � ����������. \n" +
                        "��������� ���������: " + ERROR_LOGIN_NAME_VALUE + " \n" +
                        "����������� ���������: " + response.asString() + ".");
    }

    /**
     * ���� ���������, ��� ������������, ������� ������� ��� ������,
     * ������� ��������������� ���������, ��� �� ���������� �����.
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
                "��������� � ������� �� �������� ����������� � ����������. \n" +
                        "��������� ���������: " + ERROR_PASSWORD_VALUE + " \n" +
                        "����������� ���������: " + response.asString() + ".");
    }

    /**
     * ���� ���������, ��� ������������, ������� ����� ��� email,
     * �� ������ ���������, ������� ��������������� ���������, ��� ������ �� �������.
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
                "��������� � ������� �� �������� ����������� � ����������. \n" +
                        "��������� ���������: " + ERROR_LOGIN_NAME_VALUE + " \n" +
                        "����������� ���������: " + response.asString() + ".");
    }
}