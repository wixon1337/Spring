package hu.flowacademy.spring.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByAgeGreaterThan(int age);

    @Query(value = "SELECT * FROM profile ORDER BY age desc LIMIT 1", nativeQuery = true)
    Profile findLastUpdated();
}
