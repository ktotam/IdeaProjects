package pr.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Chat;
import pr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByAge(int age);
    Optional<User> findOneByLogin(String login);
    Optional<User> findById(Long userId);

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
    @Query(nativeQuery = true, value = "UPDATE service_user SET posts_Count = posts_Count + 1 WHERE id = ?1")
    void addNewPost(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM service_user WHERE id != 0 ORDER BY id")
    List<User> findAllUsers();

    @Query(nativeQuery = true, value = "SELECT * FROM service_user WHERE id != 0 ORDER BY followers_count DESC limit 3 ")
    List<User> findPopularUsers();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO follow_users VALUES (?1,?2)")
    void followUser(Long fromId, Long toId);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM follow_users WHERE from_id = ?1 AND to_id = ?2)")
    boolean checkFollow(Long fromId, Long toId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM follow_users WHERE from_id = ?1 AND to_id = ?2")
    void unfollowUser(Long fromId, Long toId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE service_user SET followers_count = followers_count + 1 WHERE id = ?1")
    void addFollower(Long toId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE service_user SET followers_count = followers_count - 1 WHERE id = ?1")
    void deleteFollower(Long toId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO chat_list(user1, user2) VALUES (?1,?2)")
    void newChat(Long user1, Long user2);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM chat_list WHERE user1 = ?1 AND user2 = ?2 OR user1 = ?2 AND user2 = ?1)")
    boolean checkChat(Long user1, Long user2);

    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM service_user s JOIN chat_list c ON c.user1 = s.id OR c.user2 = s.id WHERE (user1 = ?1 OR user2 = ?1) AND s.id != ?1")
    List<User> getChatListByUserId(Long userId);


}