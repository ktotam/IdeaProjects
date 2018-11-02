package pr.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;
import pr.chat.HelloMessage;
import pr.chat.Greeting;
import pr.services.AuthenticationService;
import pr.services.MessageService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class ChatController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Authentication authentication, HelloMessage message) throws Exception {
        Thread.sleep(1000);
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        messageService.newMessage(message.getText().substring(0, message.getText().indexOf('№')),
                Long.parseLong(message.getText().substring(message.getText().indexOf('№')+1, message.getText().indexOf('|'))),
                authenticationService.getUserIdByAuthentication(authentication), dateTime);
        return new Greeting(HtmlUtils.htmlEscape(message.getText() + "~" + dateTime.toString().replace('T', ' ')));
    }

}