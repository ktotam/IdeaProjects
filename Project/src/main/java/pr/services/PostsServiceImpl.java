package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pr.dto.PostsDto;
import pr.models.Posts;
import pr.repositories.PostsRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Override
    public List<PostsDto> searchPosts(String text) {
        List<Posts> posts = postsRepository.findPostsByPostContainsOrderByIdDesc(text);

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            postsDtos.add(PostsDto.builder()
                    .userName(tempPost.getUserName())
                    .headline(tempPost.getHeadline())
                    .post(tempPost.getPost())
                    .userId(tempPost.getUserId())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public List<PostsDto> getPosts(Long userId) {
        List<Posts> posts = postsRepository.findPostsByUserId(userId);

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .headline(tempPost.getHeadline())
                    .post(tempPost.getPost())
                    .userId(tempPost.getUserId())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public void newPost(String post, Long id) {
        Posts newPost = Posts.builder()
                .post(post)
                //.
                .build();
        postsRepository.save(newPost);
                
    }
}
