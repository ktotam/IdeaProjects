package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Chat;
import pr.models.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO user_messages(text, to_id, from_id, date) VALUES (?1,?2,?3,?4)")
    void newMessage(String text, Long toId, Long fromId, LocalDateTime date);

    @Query(nativeQuery = true, value = "SELECT * FROM user_messages WHERE to_id = ?1 AND from_id = ?2 OR to_id = ?2 AND from_id = ?1")
    List<Message> getMessages(Long toId, Long fromId);


}
