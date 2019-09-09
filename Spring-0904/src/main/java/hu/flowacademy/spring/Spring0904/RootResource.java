package hu.flowacademy.spring.Spring0904;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Random;

@RestController
public class RootResource {

    @Autowired
    public Memory memory;

    @Autowired
    public WebComponent webComponent;

    @GetMapping("/generate-random")
    public int generateRandom(@RequestParam("max") int max) {
        return new Random().nextInt(max);
    }

    @PostMapping("/save-to-list")
    public List<OwnClass> postThatShit(@RequestBody OwnClass ownClass) {
        memory.list.add(ownClass);
        return memory.list;
    }

    @GetMapping("/get-ownclass-by-id/{pathvar}")
    public OwnClass getOwnClassById(@PathVariable("pathvar") int id) {
        for (OwnClass o: memory.list) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    @PutMapping("/put-ownclass")
    public List<OwnClass> putThatShit(@RequestBody OwnClass ownClass) {
        if (ownClass.getId() == null) {
            ownClass.setId(getNextId());
            memory.list.add(ownClass);
        } else {
            for (OwnClass o: memory.list) {
                if (o.getId() == ownClass.getId()) {
                    o.setName(ownClass.getName());
                    o.setValue(ownClass.getValue());
                }
            }
        }

        return memory.list;
    }

    public Integer getNextId() {

        return memory.list.stream()
                .map(o -> o.getId())
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    @DeleteMapping("/delete-ownclass/{pathvar}")
    public List<OwnClass> deleteThatShit(@PathVariable("pathvar") int id) {
        memory.list.removeIf(ownClass -> ownClass.getId() == id);
        return memory.list;
    }
}
