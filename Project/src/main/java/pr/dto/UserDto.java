package pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private Integer age;
    private int postsCount;
    private String avatarUrl;
    private int followersCount;
    private List chatList;

    public static UserDto from(User model) {
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .age(model.getAge())
                .login(model.getLogin())
                .postsCount(model.getPostsCount())
                .avatarUrl(model.getAvatarUrl())
                .followersCount(model.getFollowersCount())
                .chatList(model.getChatList())
                .build();
    }
}