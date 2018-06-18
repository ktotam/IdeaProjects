package pr.services;

import pr.dto.PostsDto;

import java.util.List;

public interface PageService {

    List<PostsDto> getPosts(Long userId);
    void newPost(String post, Long id);

}
