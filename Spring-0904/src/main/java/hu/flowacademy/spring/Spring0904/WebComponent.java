package hu.flowacademy.spring.Spring0904;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebComponent {
    private List<String> uuids = new ArrayList<>();

    public void add(String uuid) {
        uuids.add(uuid);
    }

    public boolean hasUUID(String uuid) {
        return uuids.contains(uuid);
    }
}
