package tests.api.tests.base;

public class BaseTest {
    protected static Object[][] usersData() {
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
