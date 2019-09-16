package hu.flowacademy.spring.first.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Token, Long> {

    Optional<Token> findFirstByToken(String token);

}
