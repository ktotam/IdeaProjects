package pr.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import pr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByAge(int age);
    Optional<User> findOneByLogin(String login);
    User findById(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE service_user SET avatar_url = ?1 WHERE id = ?2")
    void addAvatarUrl(String url, Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE service_user SET name = ?1, age = ?2 WHERE id = ?3")
    void updateUser(String newName, int newAge, Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE service_user SET likes = likes + 1 WHERE id = ?1")
    void likeUserPost(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE service_user SET posts_Count = posts_Count + 1 WHERE id = ?1")
    void addNewPost(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM service_user WHERE id != 0 ORDER BY id")
    List<User> findAllUsers();

    @Query(nativeQuery = true, value = "SELECT * FROM service_user WHERE id != 0 ORDER BY likes DESC limit 3 ")
    List<User> findPopularUsers();
}