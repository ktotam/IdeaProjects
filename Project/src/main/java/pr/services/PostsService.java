package pr.services;

import pr.dto.PostsDto;

import java.util.List;

public interface PostsService {

    List<PostsDto> searchPosts(String text);
    List<PostsDto> getPosts(Long userId);
    void newPost(String post, Long id);

}
