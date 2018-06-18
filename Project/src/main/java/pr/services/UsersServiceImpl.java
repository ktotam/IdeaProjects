package pr.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import pr.dto.UserDto;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        List<User> users = usersRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserDto.builder()
                    .id(user.getId())
                    .login(user.getLogin())
                    .build());
        }
        return userDtos;
    }

    public void addUser(UserForm user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        User newUser = User.builder()
                .login(user.getLogin())
                .name(user.getName())
                .age((user.getAge()))
                .hashPassword(hashPassword)
                .build();
        usersRepository.save(newUser);
    }
}