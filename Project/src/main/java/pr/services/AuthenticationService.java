package pr.services;

import org.springframework.security.core.Authentication;
import pr.dto.MessageDto;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.models.Avatar;

import java.util.List;
import java.util.Map;

public interface AuthenticationService {
    List<PostsDto> getUserPostsByAuthentication(Authentication authentication);

    Long getUserIdByAuthentication(Authentication authentication);

    String getLoginByAuthentication(Authentication authentication);

    UserDto getUserByAuthentication(Authentication authentication);

    List getChatListByAuthentication(Authentication authentication);

}
