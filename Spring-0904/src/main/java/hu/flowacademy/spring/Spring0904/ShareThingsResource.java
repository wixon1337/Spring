package hu.flowacademy.spring.Spring0904;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class ShareThingsResource {

    @Resource(name = "thingsService")
    ThingsService thingsService;

    @PostMapping("/share-thing")
    public Thing create(@RequestBody Thing thing) {
        thingsService.create(thing);
        return thing;
    }

    @GetMapping("/share-thing")
    public HashMap<String, Thing> findAll() {
        return thingsService.findAll();
    }

    @GetMapping("/share-thing/{pathvar}")
    public Thing findOne(@RequestParam int id) {
        return thingsService.findOne(id);
    }

    @PutMapping("/share-thing")
    public Thing update(@RequestBody Thing thing) {
        return thingsService.update(thing);
    }

    @DeleteMapping("/share-thing/{pathvar}")
    public Thing delete(@PathVariable int id) {
        return thingsService.delete(id);
    }
}
