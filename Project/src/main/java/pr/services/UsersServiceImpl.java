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
        List<User> users = usersRepository.findAllUsers();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .likes(user.getLikes())
                    .postsCount(user.getPostsCount())
                    .login(user.getLogin())
                    .avatarUrl(user.getAvatarUrl())
                    .age(user.getAge())
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
                .avatarUrl("http://localhost:8080/files/default.png")
                .build();
        usersRepository.save(newUser);
    }

    @Override
    public void updateUser(UserForm user, Long userId) {
        usersRepository.updateUser(user.getName(), user.getAge(), userId);
    }

    @Override
    public User getOneById(Long userId) {
        return usersRepository.findById(userId);
    }

    @Override
    public void likeUserPost(Long userId) {
        usersRepository.likeUserPost(userId);
    }

    @Override
    public List<UserDto> getPopularUsers() {
        List<User> users = usersRepository.findPopularUsers();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserDto.builder()
                    .id(user.getId())
                    .login(user.getLogin())
                    .likes(user.getLikes())
                    .name(user.getName())
                    .build());
        }
        return userDtos;
    }

    @Override
    public void addNewPost(Long userId) {
        usersRepository.addNewPost(userId);
    }

    @Override
    public void addAvatarUrl(String url, Long userId) {
        usersRepository.addAvatarUrl(url, userId);
    }
}