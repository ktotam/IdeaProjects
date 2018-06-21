package pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.Posts;
import pr.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsDto {
    private Long userId;
    private String post;
    private String headline;
    private String userName;
    private Long id;

    public static PostsDto from(Posts model) {
        return PostsDto.builder()
                .id(model.getId())
                .userName(model.getUserName())
                .headline(model.getHeadline())
                .post(model.getPost())
                .userId(model.getUserId())
                .build();
    }
}
