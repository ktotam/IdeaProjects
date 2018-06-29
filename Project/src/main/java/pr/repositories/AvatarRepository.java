package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Avatar;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {

    Optional<Avatar> findAvatarByFileName(String fileName);

    Avatar findOneByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO users_avatars(file_name, path, type, url, user_id) VALUES (?1,?2,?3,?4,?5)")
    void saveAvatar(String fileName, String path, String type, String url, Long userId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM users_avatars WHERE user_id = ?1")
    void deleteAvatarByUserId(Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM users_avatars WHERE id != 0 ORDER BY id")
    List<Avatar> findAllAvatars();
}
