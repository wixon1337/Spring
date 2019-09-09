package hu.flowacademy.spring.Spring0904;

import hu.flowacademy.spring.Spring0904.LoginService;
import hu.flowacademy.spring.Spring0904.Memory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

@RestController
public class LoginResource {
    // 2.1. Legyen egy GET /auth/ endpointja amely QueryParam-ként vár egy username-et és egy password-öt, majd eltárolja ezeket az injektált LoginService egy példányában.


    @Resource(name ="loginService")
    LoginService loginService;

    // public LoginService lService = new LoginService();

    @GetMapping("/auth")
    public void login(@RequestParam String username, @RequestParam String password) {
        loginService.login(username, password);
    }

    // 2.2. Készíts egy GET /auth/current-user endpointot, amely visszaadja az aktuálisan bejeletkezett felhasználót OK státusszal.
    @GetMapping("/auth/current-user")
    @ResponseStatus(HttpStatus.OK)
    public String getCurrent() {
        return loginService.getCurrent();
    }

    // 2.3. Készíts egy DELETE /auth/ endpointot amely kijelentkezteti az aktuális felhasználót.
    @DeleteMapping
    public void logOut() {
        loginService.logout();
    }
}
