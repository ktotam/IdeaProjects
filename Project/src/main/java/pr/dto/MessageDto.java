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

    private Long userId;
    private Long chatUserId;

    private String text;

    public static MessageDto to(Message model) {
        return MessageDto.builder()
                .text(model.getText())
                .chatUserId(model.getFromId())
                .userId(model.getToId())
                .build();
    }

    public static MessageDto from(Message model) {
        return MessageDto.builder()
                .text(model.getText())
                .chatUserId(model.getToId())
                .userId(model.getFromId())
                .build();
    }
}

