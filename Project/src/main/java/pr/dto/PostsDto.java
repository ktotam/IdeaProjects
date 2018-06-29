package pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.Posts;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsDto {
    private Long userId;
    private String post;
    private String userName;
    private Long id;
    private int likes;

    public static PostsDto from(Posts model) {
        return PostsDto.builder()
                .id(model.getId())
                .userName(model.getUserName())
                .post(model.getPost())
                .likes(model.getLikes())
                .userId(model.getUserId())
                .build();
    }
}
