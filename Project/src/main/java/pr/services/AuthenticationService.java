package pr.services;

import org.springframework.security.core.Authentication;
import pr.dto.MessageDto;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.models.Avatar;

import java.util.List;

public interface AuthenticationService {
    List<PostsDto> getUserPostsByAuthentication(Authentication authentication);

    Long getUserIdByAuthentication(Authentication authentication);

    String getLoginByAuthentication(Authentication authentication);

    String getNameByAuthentication(Authentication authentication);

    UserDto getUserByAuthentication(Authentication authentication);

    Avatar getAvatarByAuthentication(Authentication authentication);

    List<MessageDto> getInboxMessagesByAuthentication(Authentication authentication);

    List<MessageDto> getSentMessagesByAuthentication(Authentication authentication);
}
