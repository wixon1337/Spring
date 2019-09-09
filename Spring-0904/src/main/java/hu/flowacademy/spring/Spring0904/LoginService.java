package hu.flowacademy.spring.Spring0904;

public class LoginService {

    private String username;
    private String password;

    public void login(String username,String password) {
        this.username = username;
        this.password = password;
    }

    public String getCurrent() {
        return this.username;
    }

    public void logout() {
        this.username = null;
        this.password = null;
    }
}
