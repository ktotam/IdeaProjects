package pr.services;

import pr.dto.UserDto;
import pr.forms.UserForm;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    void addUser(UserForm user);
}