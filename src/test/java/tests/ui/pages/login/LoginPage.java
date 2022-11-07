package tests.ui.pages.login;

import com.codeborne.selenide.Condition;
import tests.ui.pages.base.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static tests.constants.Constant.Datas.*;
import static tests.ui.locators.Locators.LoginPageLocators.*;

public class LoginPage extends BasePage {
    public LoginPage inputLoginName() {
        $x(INPUT_LOGIN_LOCATOR)
                .setValue(CORRECT_LOGIN_NAME)
                .shouldBe(Condition.value(CORRECT_LOGIN_NAME));
        return this;
    }

    public LoginPage inputLoginNameOrEmail(String login) {
        $x(INPUT_LOGIN_LOCATOR)
                .setValue(login)
                .shouldBe(Condition.value(login));
        return this;
    }

    public LoginPage inputWrongLoginName() {
        $x(INPUT_LOGIN_LOCATOR)
                .setValue(WRONG_LOGIN_NAME)
                .shouldBe(Condition.value(WRONG_LOGIN_NAME));
        return this;
    }

    public LoginPage inputWrongRegisterLoginName() {
        $x(INPUT_LOGIN_LOCATOR)
                .setValue(WRONG_REGISTER_EMAIL)
                .shouldBe(Condition.value(WRONG_REGISTER_EMAIL));
        return this;
    }

    public LoginPage inputPassword() {
        $x(INPUT_PASSWORD_LOCATOR)
                .setValue(CORRECT_PASSWORD)
                .shouldBe(Condition.value(CORRECT_PASSWORD));
        return this;
    }

    public LoginPage inputPassword(String password) {
        $x(INPUT_PASSWORD_LOCATOR)
                .setValue(password)
                .shouldBe(Condition.value(password));
        return this;
    }

    public LoginPage inputWrongPassword() {
        $x(INPUT_PASSWORD_LOCATOR)
                .setValue(WRONG_PASSWORD)
                .shouldBe(Condition.value(WRONG_PASSWORD));
        return this;
    }

    ;

    public LoginPage clickEntryButton() {
        $x(ENTRY_BUTTON_LOCATOR)
                .shouldBe(Condition.visible)
                .click();
        return this;
    }

    public LoginPage shouldBeMessageSuccess() {
        $x(SUCCESS_MESSAGE_LOCATOR)
                .shouldBe(Condition.matchText(SUCCESS_MESSAGE_VALUE)/* value(SUCCESS_MESSAGE_VALUE)*/, Duration.ofSeconds(10));
        return this;
    }

    public LoginPage shouldBeMessageImpossibleLogin() {
        $x(ERROR_MESSAGE_LOCATOR)
                .shouldBe(Condition.ownText(ERROR_PASSWORD_VALUE));//value("Invalid email or password"));
        return this;
    }

    public LoginPage shouldBeMessageUserNotFound() {
        $x(ERROR_MESSAGE_LOCATOR)
                .shouldBe(Condition.ownText(ERROR_LOGIN_NAME_VALUE));
        return this;
    }
}
