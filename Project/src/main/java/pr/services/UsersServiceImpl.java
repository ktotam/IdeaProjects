package pr.services;

import pr.forms.UserForm;
import pr.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pr.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<String> getAllNames() {
        List<User> users = usersRepository.findAll();

        List<String> names = new ArrayList<String>();
        for (User user : users) {
            names.add(user.getName());
        }
        return names;
    }

    public void addUser(UserForm user) {
        User newUser = User.builder()
                .login(user.getLogin())
                .name(user.getName())
                .build();

        usersRepository.save(newUser);
    }
}