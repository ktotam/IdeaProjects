package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pr.dto.MessageDto;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.models.*;
import pr.repositories.*;
import pr.security.details.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AvatarRepository avatarRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<PostsDto> getUserPostsByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<Posts> reposts = postsRepository.findReposts(userDetails.getId());


       List<PostsDto> postsDtos = new ArrayList<>();

        for (Posts tempPost : reposts) {
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .post(tempPost.getPost())
                    .userId(tempPost.getUserId())
                    .likes(tempPost.getLikes())
                    .date(tempPost.getDate())
                    .avatarUrl(tempPost.getAvatarUrl())
                    .userName(tempPost.getUserName())
                    .userId(tempPost.getUserId())
                    .build());
        }
        return postsDtos;
    }

    @Override
    public Long getUserIdByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails.getId();
    }

    @Override
    public String getLoginByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails.getUsername();
    }

    @Override
    public String getNameByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails.getName();
    }

    @Override
    public UserDto getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = usersRepository.findById(userDetails.getId()).orElse(null);
        return UserDto.from(user);
    }

    @Override
    public Avatar getAvatarByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Avatar avatar = avatarRepository.findOneByUserId(userDetails.getId());
        if (avatar == null) {
            return avatarRepository.findOneByUserId(0L);
        }
        return avatar;
    }

    @Override
    public List<User> getChatListByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<User> chatList = usersRepository.getChatListByUserId(userDetails.getId());
        return chatList;
    }


}
