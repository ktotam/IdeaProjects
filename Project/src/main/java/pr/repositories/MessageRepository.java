package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM user_messages WHERE from_id = ?1 ORDER BY id desc")
    List<Message> findAllByFromIdOrderById(Long userId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO user_messages(text, to_id, from_id) VALUES (?1,?2,?3)")
    void newMessage(String text, Long toId, Long fromId);

    @Query(nativeQuery = true, value = "SELECT * FROM user_messages WHERE to_id = ?1 AND from_id = ?2 OR to_id = ?2 AND from_id = ?1")
    List<Message> getMessages(Long toId, Long fromId);
}
