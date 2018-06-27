package pr.repositories;

import pr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByAge(int age);
    Optional<User> findOneByLogin(String login);
    User findById(Long userId);
    
    @Query(nativeQuery = true, value = "SELECT * from service_user su WHERE su.age > ?1")
    List<User> findAllWhereAgeMoreThan(int age);


}