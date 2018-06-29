package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Posts;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users_posts WHERE user_id = ?1 ORDER BY id desc")
    List<Posts> findPostsByUserId(Long userId);

    List<Posts> findPostsByPostContainsOrderByIdDesc(String text);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO users_posts(post, user_id, user_name) VALUES (?1,?2,?3)")
    void newPost(String text, Long userId, String userName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users_posts SET likes = likes + 1 WHERE id = ?1")
    void likePost(Long postId);

    @Query(nativeQuery = true, value = "SELECT * FROM users_posts ORDER BY likes DESC limit 3")
    List<Posts> findPopularPosts();
}