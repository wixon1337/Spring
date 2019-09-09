package hu.flowacademy.springAngular;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestResource {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello!";
    }
}
