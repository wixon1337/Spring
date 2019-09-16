package hu.flowacademy.spring.first.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private LoginRepository loginRepository;

    User register(User user) {
        return authRepository.save(user);
    }

    List<User> getRegisteredUsers() {
        return authRepository.findAll();
    }

    User getUserByUsername(String username) {
        return authRepository.findByUsername(username);
    }

    Token login(User user) {
        User userFound = authRepository.findByUsername(user.getUsername());
        if (userFound == null) {
            throw new RuntimeException("nincs");
        } else if (!user.getPassword().equals(userFound.getPassword())) {
            throw new RuntimeException("wrong pw");
        }
        return loginRepository.save(new Token(userFound));
    }


    User inspect(String token) {
        System.out.println(token);
        System.out.println(loginRepository.findFirstByToken(token));
        return loginRepository.findFirstByToken(token).orElseThrow(() -> new RuntimeException("nincs")).getUserId();
    }
}
