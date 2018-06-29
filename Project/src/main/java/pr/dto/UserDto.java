package pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String name;
    private Integer age;
    private int likes;
    private int postsCount;
    private String avatarUrl;

    public static UserDto from(User model) {
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .age(model.getAge())
                .login(model.getLogin())
                .likes(model.getLikes())
                .postsCount(model.getPostsCount())
                .avatarUrl(model.getAvatarUrl())
                .build();
    }
}