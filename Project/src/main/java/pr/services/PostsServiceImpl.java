package pr.services;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pr.dto.PostsDto;
import pr.models.Posts;
import pr.repositories.PostsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                    .id(tempPost.getId())
                    .userName(tempPost.getUserName())
                    .post(tempPost.getPost())
                    .likes(tempPost.getLikes())
                    .userId(tempPost.getUserId())
                    .avatarUrl(tempPost.getAvatarUrl())
                    .usersLikes(tempPost.getUsersLikes())
                    .date(tempPost.getDate())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public List<PostsDto> getPostsByUserId(Long userId) {
        List<Posts> posts = postsRepository.findReposts(userId);

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .post(tempPost.getPost())
                    .likes(tempPost.getLikes())
                    .userName(tempPost.getUserName())
                    .userId(tempPost.getUserId())
                    .avatarUrl(tempPost.getAvatarUrl())
                    .usersLikes(tempPost.getUsersLikes())
                    .date(tempPost.getDate())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public void newPost(String post, Long userId) {
        postsRepository.newPost(post, userId, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }

 /*   @Override
    public void likePost(Long postId) {
        postsRepository.likePost(postId);
    }
  */
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

    @Override
    public List<PostsDto> getFeedPosts(Long userId) {
        List<Posts> posts = postsRepository.getFeedPosts(userId);

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .post(tempPost.getPost())
                    .likes(tempPost.getLikes())
                    .userName(tempPost.getUserName())
                    .userId(tempPost.getUserId())
                    .avatarUrl(tempPost.getAvatarUrl())
                    .usersLikes(tempPost.getUsersLikes())
                    .date(tempPost.getDate())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public List<Long> getUsersPostsLikes(Long userId) {
        return postsRepository.findLikesPostIdByUserId(userId);
    }

    @Override
    public void likePost(Long userId, Long postId) {
        if (checkLike(userId, postId)) {
            postsRepository.deletePostLike(userId, postId);
            postsRepository.dislikePost(postId);
        } else {
            postsRepository.addPostLike(userId, postId);
            postsRepository.likePost(postId);
        }
    }

    @Override
    public boolean checkLike(Long userId, Long postId) {
        return postsRepository.checkLike(userId, postId);
    }

    @Override
    public void repost(Long userId, Long postId) {
        if (!postsRepository.checkRepost(userId, postId)) {
            postsRepository.newRepost(userId, postId);
        }
    }


}
