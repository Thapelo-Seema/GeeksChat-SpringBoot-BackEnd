package com.geeks4L.chat_server.controllers.messaging;

import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.models.messaging.MessageEntity;
import com.geeks4L.chat_server.models.messaging.MessageRequest;
import com.geeks4L.chat_server.models.messaging.MessageResponse;
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
    public MessageResponse receivePrivateMessage(@Payload MessageRequest messageRequest){
        ResponseObject<MessageResponse> messageResponseResponseObject = this.messageService.saveMessage(messageRequest);
        if(!messageResponseResponseObject.getStatus().equals(Status.SUCCESS)){
            System.out.println(messageResponseResponseObject.getMessage());
            return null;
        }
        //message will be sent to /user/users_id/private
       msgTemplate.convertAndSendToUser(messageRequest.getReceiver().toString(), "/private", messageResponseResponseObject.getData());
        return messageResponseResponseObject.getData();
    }

}
