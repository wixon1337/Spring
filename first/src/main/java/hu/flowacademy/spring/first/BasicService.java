package hu.flowacademy.spring.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BasicService {

    @Autowired
    BasicRepository basicRepository;

    public void reset() {
        basicRepository.deleteAll();
    }

    public void generate(Long n) {
        List<Profile> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Profile("test" + i, new Random().nextInt(2) == 0 ? "male" : "female", new Random().nextInt(100)));
        }
        basicRepository.saveAll(list);
    }

    public void softDeleteById(Long id) {
        for (Profile p: basicRepository.findAll()) {
            if (p.getId().equals(id)) p.setDeletedAt(LocalDateTime.now());
        }
    }
}
