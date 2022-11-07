package tests.ui.tests.base;


import tests.ui.pages.base.BasePage;
import tests.ui.pages.login.LoginPage;


public class BaseTest {
    protected BasePage basePage = new BasePage();
    protected LoginPage loginPage = new LoginPage();

    public static Object[][] users_data() {
        return new Object[][]{
                {"first_user", "default_password"},
                {"second_user", "default_password"},
                {"third_user", "default_password"},
                {"default_user@test.ci", "default_password"},
                {"Default_user@test.ci", "default_password"},
                {"third_user@test.ci", "default_password"}
        };
    }
}
