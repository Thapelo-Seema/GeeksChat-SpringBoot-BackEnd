package com.geeks4L.chat_server.controllers.messaging;

import com.geeks4L.chat_server.models.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate msgTemplate;
    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){
        //message will be sent to /user/users_name/private
        msgTemplate.convertAndSendToUser(message.getReceiver(), "/private", message);
        return message;
    }

}
