package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pr.dto.MessageDto;
import pr.dto.PostsDto;
import pr.models.Message;
import pr.models.Posts;
import pr.repositories.MessageRepository;
import pr.repositories.UsersRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void newMessage(String text, Long toId, Long fromId, LocalDateTime date) {
        messageRepository.newMessage(text, toId, fromId, date);
    }

    @Override
    public String getMessages(Long toId, Long fromId) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonMessageList = new JSONObject();



        List<Message> list = messageRepository.getMessages(toId, fromId);

        for (Message temp : list) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("text", temp.getText());
            jsonObject.put("from_id", temp.getFromId());
            jsonObject.put("to_id", temp.getToId());
            jsonObject.put("date_time", temp.getStringDate());
            jsonArray.add(jsonObject);
        }

        jsonMessageList.put("messages", jsonArray);
        return jsonMessageList.toJSONString();
    }
}
