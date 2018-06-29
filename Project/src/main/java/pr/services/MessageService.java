package pr.services;

import pr.forms.MessageForm;

public interface MessageService {

    void newMessage(MessageForm messageForm, Long fromId, String fromUserName);

    void updateMessages(String newUserName, Long userId);
}
