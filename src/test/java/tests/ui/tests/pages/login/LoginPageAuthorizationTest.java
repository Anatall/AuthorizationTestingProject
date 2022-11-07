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
     * “ест провер€ет, что пользователь, который ввЄл
     * корректно свой логин и пароль, успешно логинитс€.
     * “ест прогон€етс€ на данных login, email и password
     * трЄх тестовых пользователей. ¬ качестве параметров идут
     * логин и пароль пользователей. Ћогином может быть login
     * и email.
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
     * “ест провер€ет, что пользователь, который верно ввЄл логин,
     * но ошибс€ с паролем, получит соответствующее сообщение, что не получаетс€
     * зайти.
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
     * “ест провер€ет, что пользователь, который неверно ввЄл логин,
     * получит соответствующее сообщение, что данные не найдены.
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
     * “ест провер€ет, что пользователь, который верно ввЄл email,
     * но ошибс€ регистром, получит соответствующее сообщение, что данные не найдены.
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
