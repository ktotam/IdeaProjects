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
                    .post(tempPost.getPost())
                    .likes(tempPost.getLikes())
                    .userId(tempPost.getUserId())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public List<PostsDto> getPostsByUserId(Long userId) {
        List<Posts> posts = postsRepository.findPostsByUserId(userId);

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .post(tempPost.getPost())
                    .likes(tempPost.getLikes())
                    .userName(tempPost.getUserName())
                    .userId(tempPost.getUserId())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public void newPost(String post, Long userId, String userName) {
        postsRepository.newPost(post, userId, userName);
                
    }

    @Override
    public void likePost(Long postId) {
        postsRepository.likePost(postId);
    }

    @Override
    public List<PostsDto> getPopularPosts() {
        List<Posts> posts = postsRepository.findPopularPosts();

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            if (tempPost.getPost().length() > 25) {
                tempPost.setPost(tempPost.getPost().substring(0,25) + "...");
            }
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .post(tempPost.getPost())
                    .likes(tempPost.getLikes())
                    .userId(tempPost.getUserId())
                    .build());
        }
        return postsDtos;
    }
}
