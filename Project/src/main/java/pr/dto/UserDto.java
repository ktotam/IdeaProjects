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

    public static UserDto from(User model) {
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}