package pr.services;

import pr.dto.MessageDto;
import pr.models.Message;
import org.json.simple.JSONObject;

import java.util.List;

public interface MessageService {

    void newMessage(String text, Long toId, Long fromId);

    String getMessages(Long toId, Long fromId);
}
