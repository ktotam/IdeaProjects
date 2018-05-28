package pr.services;

import pr.forms.UserForm;

import java.util.List;

public interface UsersService {
    List<String> getAllNames();

    void addUser(UserForm user);
}