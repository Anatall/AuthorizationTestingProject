package tests.ui.tests.pages.login;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.ui.tests.base.BaseTest;

import static com.codeborne.selenide.Selenide.open;
import static tests.constants.Constant.Urls.LOGIN_PAGE;

public class LoginPageAuthorizationTest extends BaseTest {

    /**
     * ���� ���������, ��� ������������, ������� ���
     * ��������� ���� ����� � ������, ������� ���������.
     * ���� ����������� �� ������ login, email � password
     * ��� �������� �������������. � �������� ���������� ����
     * ����� � ������ �������������. ������� ����� ���� login
     * � email.
     * @param login
     * @param password
     */
    @Tag("TestUI")
    @Tag("TestLoginPage")
    @ParameterizedTest
    @MethodSource("users_data")
    public void guestCanLogIn(String login, String password) {
        open(LOGIN_PAGE);
        loginPage
                .inputLoginNameOrEmail(login)
                .inputPassword(password)
                .clickEntryButton()
                .shouldBeMessageSuccess();
    }

    /**
     * ���� ���������, ��� ������������, ������� ����� ��� �����,
     * �� ������ � �������, ������� ��������������� ���������, ��� �� ����������
     * �����.
     */
    @Tag("TestUI")
    @Tag("TestLoginPage")
    @Test
    public void guestCantLogInWithWrongPassword() {
        open(LOGIN_PAGE);
        loginPage
                .inputLoginName()
                .inputWrongPassword()
                .clickEntryButton()
                .shouldBeMessageImpossibleLogin();
    }

    /**
     * ���� ���������, ��� ������������, ������� ������� ��� �����,
     * ������� ��������������� ���������, ��� ������ �� �������.
     */
    @Tag("TestUI")
    @Tag("TestLoginPage")
    @Test
    public void guestCantLogInWithWrongLoginName() {
        open(LOGIN_PAGE);
        loginPage.
                inputWrongLoginName()
                .inputPassword()
                .clickEntryButton()
                .shouldBeMessageUserNotFound();
    }

    /**
     * ���� ���������, ��� ������������, ������� ����� ��� email,
     * �� ������ ���������, ������� ��������������� ���������, ��� ������ �� �������.
     */
    @Tag("TestUI")
    @Tag("TestLoginPage")
    @Test
    public void guestCantLogInWithWrongEmailRegister() {
        open(LOGIN_PAGE);
        loginPage.
                inputWrongRegisterLoginName()
                .inputPassword()
                .clickEntryButton()
                .shouldBeMessageUserNotFound();
    }
}
