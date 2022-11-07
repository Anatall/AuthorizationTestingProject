package tests.api.pojo;

public class Authorizator {
    private String login;
    private String password;


    public Authorizator(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
