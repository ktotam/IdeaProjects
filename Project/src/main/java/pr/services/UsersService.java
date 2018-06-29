package pr.services;

import pr.dto.UserDto;
import pr.forms.UserForm;
import pr.models.User;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    void addUser(UserForm user);

    void updateUser(UserForm user, Long userId);

    User getOneById(Long userId);

    void likeUserPost(Long userId);

    List<UserDto> getPopularUsers();

    void addNewPost(Long userId);

    void addAvatarUrl(String url, Long userId);
}