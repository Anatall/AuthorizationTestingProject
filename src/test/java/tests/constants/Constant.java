package tests.constants;

public class Constant {
    public static class Urls {
        public static final String LOGIN_PAGE = "http://localhost/login";
        public static final String BASE_PAGE_API = "http://localhost:80/";
    }

    public static class Datas {
        public static final String CORRECT_LOGIN_NAME = "first_user";
        public static final String WRONG_LOGIN_NAME = "wrong_user";
        public static final String WRONG_REGISTER_EMAIL = "default_usEr@test.ci";
        public static final String CORRECT_PASSWORD = "default_password";
        public static final String WRONG_PASSWORD = "wrong_pass";
        public static final String SUCCESS_MESSAGE_VALUE = "Выполнен успешный вход";
        public static final String ERROR_LOGIN_NAME_VALUE = "Пользователь не найден.";
        public static final String ERROR_PASSWORD_VALUE = "Невозможно войти в систему.";
        public static final String SESSION_ID_COOKIE_NAME = "JSESSIONID";
    }

}
