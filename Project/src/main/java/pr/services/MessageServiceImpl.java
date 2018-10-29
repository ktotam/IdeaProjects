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

import java.util.ArrayList;
import java.util.List;


@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void newMessage(String text, Long toId, Long fromId) {
        messageRepository.newMessage(text, toId, fromId);
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
            jsonArray.add(jsonObject);
        }

        jsonMessageList.put("messages", jsonArray);

        return jsonMessageList.toJSONString();
    }
}
