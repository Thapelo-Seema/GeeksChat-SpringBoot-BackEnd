package com.geeks4L.chat_server.controllers.messaging;

import com.geeks4L.chat_server.models.messaging.MessageEntity;
import com.geeks4L.chat_server.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    //Todo: need to factor out processing to a messaging service
    @Autowired
    private SimpMessagingTemplate msgTemplate;
    @Autowired
    private MessageService messageService;
    @MessageMapping("/private-message")
    public MessageEntity receivePrivateMessage(@Payload MessageEntity messageEntity){
        this.messageService.saveMessage(messageEntity);
        //message will be sent to /user/users_id/private
        msgTemplate.convertAndSendToUser(messageEntity.getReceiver().getId().toString(), "/private", messageEntity);
        return messageEntity;
    }

}
