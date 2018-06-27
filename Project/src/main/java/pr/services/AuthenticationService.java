package pr.services;

import org.springframework.security.core.Authentication;
import pr.dto.PostsDto;
import pr.dto.UserDto;
import pr.models.Avatar;
import pr.models.User;

import java.util.List;

public interface AuthenticationService {
    List<PostsDto> getUserPostsByAuthentication(Authentication authentication);

    Long getUserIdByAuthentication(Authentication authentication);

    String getLoginByAuthentication(Authentication authentication);

    String getNameByAuthentication(Authentication authentication);

    UserDto getUserByAuthentication(Authentication authentication);

    String getAvatarByAutentication(Authentication authentication);
}
