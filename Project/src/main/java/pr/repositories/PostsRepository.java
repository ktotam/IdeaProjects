package pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pr.models.Posts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users_posts WHERE user_id = ?1 ORDER BY id desc")
    List<Posts> findPostsByUserId(Long userId);

    List<Posts> findPostsByPostContainsOrderByIdDesc(String text);

    @Query(nativeQuery = true, value = "SELECT post_id FROM posts_likes WHERE user_id = ?1")
    List<Long> findLikesPostIdByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO users_posts(post, user_id, date, likes) VALUES (?1,?2,?3,0)")
    void newPost(String text, Long userId, LocalDateTime date);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users_posts SET likes = likes + 1 WHERE id = ?1")
    void likePost(Long postId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users_posts SET likes = likes - 1 WHERE id = ?1")
    void dislikePost(Long postId);

    @Query(nativeQuery = true, value = "SELECT * FROM users_posts ORDER BY likes DESC limit 3")
    List<Posts> findPopularPosts();

    
    @Query(nativeQuery = true, value = "SELECT * FROM users_posts p JOIN follow_users f ON p.user_id = f.to_id AND f.from_id = ?1 ORDER BY id desc")
    List<Posts> getFeedPosts(Long userId);


    @Query(nativeQuery = true, value = "SELECT * FROM users_posts p WHERE p.user_id = ?1 OR p.id IN (SELECT post_id FROM reposts r WHERE r.user_id = ?1) ORDER BY p.id DESC")
    List<Posts> findReposts(Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO posts_likes VALUES (?2,?1)")
    void addPostLike(Long userId, Long postId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM posts_likes WHERE user_id = ?1 AND post_id = ?2 ")
    void deletePostLike(Long userId, Long postId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO reposts VALUES (?2,?1)")
    void newRepost(Long userId, Long postId);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM posts_likes WHERE user_id = ?1 AND post_id = ?2)")
    boolean checkLike(Long userId, Long postId);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT * FROM reposts WHERE user_id = ?1 AND post_id = ?2)")
    boolean checkRepost(Long userId, Long postId);
}