package hu.flowacademy.spring.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RootResource {

    @Autowired
    private MyComponent myComponent;

    @GetMapping("/")
    public String getRoot() {
        return "Hello world!";
    }

    @GetMapping("/my-comp")
    public String getMyCompsData() {
        return myComponent.data;
    }

    @GetMapping("/echo-string")
    public String echoString(@RequestParam("q") String q) {
        return q;
    }

    @PutMapping("/echo-path/{pathvar}")
    public String getPathVariable(@PathVariable("pathvar") String pathVariable) {
        return pathVariable;
    }

    @PostMapping("/echo-json")
    public Profile getProfile(@RequestBody Profile profile) {
        return profile;
    }
}
