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
    private int followers;

    public static UserDto from(User model) {
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .age(model.getAge())
                .login(model.getLogin())
                .followers(model.getFollowers())
                .build();
    }
}