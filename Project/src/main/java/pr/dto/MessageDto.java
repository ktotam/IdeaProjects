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
    private String toUserName;
    private String fromUserName;
    private Long id;

    public static MessageDto from(Message model) {
        return MessageDto.builder()
                .id(model.getId())
                .toUserName(model.getUserName())
                .fromUserName(model.getFromUserName())
                .text(model.getText())
                .fromId(model.getFromId())
                .toId(model.getToId())
                .build();
    }
}

