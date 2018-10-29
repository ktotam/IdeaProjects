package pr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pr.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    private Long toId;
    private Long fromId;
    private String text;
    private String avatarUrl;
    private Long id;

    public static MessageDto from(Message model) {
        return MessageDto.builder()
                .id(model.getId())
                .text(model.getText())
                .toId(model.getToId())
                .fromId(model.getFromId())
                .avatarUrl(model.getAvatarUrl())
                .build();
    }
}

