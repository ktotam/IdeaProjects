package pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.Posts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String avatarUrl;
    private ArrayList<Long> usersLikes;
    private LocalDateTime date;

    public static PostsDto from(Posts model) {
        return PostsDto.builder()
                .id(model.getId())
                .userName(model.getUserName())
                .post(model.getPost())
                .likes(model.getLikes())
                .userId(model.getUserId())
                .avatarUrl(model.getAvatarUrl())
                .usersLikes(model.getUsersLikes())
                .date(model.getDate())
                .build();
    }
}
