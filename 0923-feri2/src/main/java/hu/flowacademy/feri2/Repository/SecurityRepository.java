package hu.flowacademy.feri2.Repository;

import hu.flowacademy.feri2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
