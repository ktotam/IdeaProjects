package pr.services;

import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import pr.dto.UserDto;
import pr.forms.UserForm;
import pr.models.Avatar;
import pr.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pr.repositories.UsersRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                    .postsCount(user.getPostsCount())
                    .login(user.getLogin())
                    .avatarUrl(user.getAvatarUrl())
                    .age(user.getAge())
                    .followersCount(user.getFollowersCount())
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
                .avatarUrl("images/default.png")
                .build();
        usersRepository.save(newUser);

    }

    @Override
    public void updateUser(UserForm user, Long userId) {
        usersRepository.updateUser(user.getName(), user.getAge(), userId);
    }

    @Override
    public User getOneById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    @Override
    public List<UserDto> getPopularUsers() {
        List<User> users = usersRepository.findPopularUsers();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .postsCount(user.getPostsCount())
                    .login(user.getLogin())
                    .avatarUrl(user.getAvatarUrl())
                    .age(user.getAge())
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

    @Override
    public void followUser(Long fromId, Long toId) {
        if (!checkFollow(fromId, toId)) {
            usersRepository.followUser(fromId, toId);
            usersRepository.addFollower(toId);
        }
    }

    @Override
    public void unfollowUser(Long fromId, Long toId) {
        if (checkFollow(fromId, toId)) {
            usersRepository.unfollowUser(fromId, toId);
            usersRepository.deleteFollower(toId);
        }
    }

    @Override
    public boolean checkFollow(Long fromId, Long toId) {
        return usersRepository.checkFollow(fromId, toId);
    }

    @Override
    public void createChat(Long user1, Long user2) {
        if (!usersRepository.checkChat(user1,user2))
            usersRepository.newChat(user1,user2);
    }
}