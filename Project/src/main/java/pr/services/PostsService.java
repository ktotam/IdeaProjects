package pr.services;

import pr.dto.PostsDto;

import java.util.List;

public interface PostsService {

    List<PostsDto> searchPosts(String text);
    List<PostsDto> getPostsByUserId(Long userId);
    void newPost(String post, Long userId, String userName);
    void likePost(Long postId);

    List<PostsDto> getPopularPosts();
}
