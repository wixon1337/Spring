package hu.flowacademy.spring.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RootResource {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    BasicService basicService;

    @Autowired
    private MyComponent myComponent;

    @GetMapping("/")
    public @ResponseBody String getRoot() {
        return "Hello world!";
    }

/*    @GetMapping("/my-comp")
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
    }*/

    @PostMapping("/profile")
    public Profile saveProfile(@RequestBody Profile profile) {
        return basicRepository.save(profile);
    }

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        return basicRepository.findAll();
    }

    @GetMapping("/profile/{id}")
    public Profile getProfile(@PathVariable("id") Long id) {
        return basicRepository.getOne(id);
    }

    @DeleteMapping("/profile/{id}")
    public void deleteProfile(@PathVariable("id") Long id) {
        basicService.softDeleteById(id);
    }

    @PostMapping("/generate")
    public List<Profile> generateRecords(@RequestParam("n") Long n) {
        basicService.reset();
        basicService.generate(n);
        return basicRepository.findAll();
    }

    @GetMapping("/profile/adults")
    public List<Profile> findAdults() {
        return basicRepository.findByAgeGreaterThan(18);
    }

    @GetMapping("/profile/last_update")
    public Profile findLastUpdated() {
        return basicRepository.findLastUpdated();
    }
}
