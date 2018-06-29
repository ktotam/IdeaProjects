package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByToIdOrderByIdDesc(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM user_messages WHERE from_id = ?1 ORDER BY id desc")
    List<Message> findAllByFromIdOrderById(Long userId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO user_messages(text, to_id, from_id, to_user, from_user) VALUES (?1,?2,?3,?4,?5)")
    void newMessage(String text, Long toId, Long fromId, String toUserName, String fromUserName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE user_messages SET from_user = ?1 WHERE from_id = ?2")
    void updateMessagesFromUserName(String newUserName, Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE user_messages SET to_user = ?1 WHERE to_id = ?2")
    void updateMessagesToUserName(String newUserName, Long userId);


}
