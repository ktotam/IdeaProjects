package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pr.models.Posts;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users_posts WHERE user_id = ?1 ORDER BY id desc")
    List<Posts> findPostsByUserId(Long userId);

    List<Posts> findPostsByPostContainsOrderByIdDesc(String text);
}