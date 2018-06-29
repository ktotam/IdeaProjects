package pr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pr.forms.MessageForm;
import pr.repositories.MessageRepository;
import pr.repositories.UsersRepository;


@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void newMessage(MessageForm messageForm, Long fromId, String fromUserName) {
        String toUserName = usersRepository.findById(messageForm.getToId()).getName();
        messageRepository.newMessage(messageForm.getText(), messageForm.getToId(), fromId, toUserName, fromUserName);
    }

    @Override
    public void updateMessages(String newUserName, Long userId) {
        messageRepository.updateMessagesFromUserName(newUserName, userId);
        messageRepository.updateMessagesToUserName(newUserName, userId);
    }
}
