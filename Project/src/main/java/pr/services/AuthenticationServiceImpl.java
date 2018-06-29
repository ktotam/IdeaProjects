package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pr.dto.MessageDto;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.models.Avatar;
import pr.models.Message;
import pr.models.Posts;
import pr.models.User;
import pr.repositories.AvatarRepository;
import pr.repositories.MessageRepository;
import pr.repositories.PostsRepository;
import pr.repositories.UsersRepository;
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

        List<Posts> posts = postsRepository.findPostsByUserId(userDetails.getId());

        List<PostsDto> postsDtos = new ArrayList<>();
        for (Posts tempPost : posts) {
            postsDtos.add(PostsDto.builder()
                    .id(tempPost.getId())
                    .post(tempPost.getPost())
                    .userId(tempPost.getUserId())
                    .likes(tempPost.getLikes())
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
        User user = usersRepository.findById(userDetails.getId());
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
    public List<MessageDto> getInboxMessagesByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<Message> messages = messageRepository.findAllByToIdOrderByIdDesc(userDetails.getId());

        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message tempMessage : messages) {
            messageDtos.add(MessageDto.builder()
                    .id(tempMessage.getId())
                    .text(tempMessage.getText())
                    .fromId(tempMessage.getFromId())
                    .toId(tempMessage.getToId())
                    .toUserName(tempMessage.getToUserName())
                    .fromUserName(tempMessage.getFromUserName())
                    .build());
        }
        return messageDtos;
    }

    @Override
    public List<MessageDto> getSentMessagesByAuthentication(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<Message> messages = messageRepository.findAllByFromIdOrderById(userDetails.getId());

        List<MessageDto> messageDtos = new ArrayList<>();
        for (Message tempMessage : messages) {
            messageDtos.add(MessageDto.builder()
                    .id(tempMessage.getId())
                    .text(tempMessage.getText())
                    .fromId(tempMessage.getFromId())
                    .toId(tempMessage.getToId())
                    .toUserName(tempMessage.getToUserName())
                    .fromUserName(tempMessage.getFromUserName())
                    .build());
        }
        return messageDtos;
    }


}
