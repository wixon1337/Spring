package hu.flowacademy.spring.first.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @GetMapping("/registered")
    public List<User> getRegisteredUsers() {
        return authService.getRegisteredUsers();
    }

    @GetMapping("/user/{username}")
    public User findUser(@PathVariable("username") String username) {
        return authService.getUserByUsername(username);
    }

    @PostMapping("/login")
    public Token login(@RequestBody User user) {
        return authService.login(user);
    }

    @GetMapping("/inspect")
    public User getCurrentUser(@RequestParam String token) {
        return authService.inspect(token);
    }


}
