package pr.services;

import pr.dto.PostsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PostsService {

    List<PostsDto> searchPosts(String text);

    List<PostsDto> getPostsByUserId(Long userId);

    void newPost(String post, Long userId);

    List<PostsDto> getPopularPosts();

    List<PostsDto> getFeedPosts(Long userId);

    List<Long> getUsersPostsLikes(Long userId);

    void likePost(Long userId, Long postId);

    boolean checkLike(Long userId, Long postId);

    void repost(Long userId, Long postId);


}
