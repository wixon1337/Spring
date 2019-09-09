package hu.flowacademy.spring.Spring0904;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Memory {
    public static List<OwnClass> list = new ArrayList<>();
    public static Map<String, String> loginCredentials = new HashMap<>();

}
