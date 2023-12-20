package com.geeks4L.chat_server.mappers;

import com.geeks4L.chat_server.models.messaging.MessageEntity;
import com.geeks4L.chat_server.models.messaging.MessageRequest;
import com.geeks4L.chat_server.models.messaging.MessageResponse;
import com.geeks4L.chat_server.models.users.UserEntity;
import com.geeks4L.chat_server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageMapper {
    @Autowired
    UserRepository userRepository;
    public MessageEntity map(MessageRequest messageRequest){
        UserEntity sender = userRepository.findById(messageRequest.getSender()).orElse(null);
        if(sender == null) return  null;
        UserEntity receiver = userRepository.findById(messageRequest.getReceiver()).orElse(null);
        if(receiver == null) return  null;
        return new MessageEntity(null, sender, receiver, messageRequest.getTxtContent(),
                messageRequest.getTopic(), LocalDateTime.now(), false, false);
    }

    public MessageResponse map(MessageEntity messageEntity){
        return new MessageResponse(
                messageEntity.getId(),
                messageEntity.getSender().getId(),
                messageEntity.getReceiver().getId(),
                messageEntity.getTxtContent(),
                messageEntity.getTopic(),
                messageEntity.getCreatedAt(),
                messageEntity.isRead(),
                messageEntity.isReceived()
        );
    }
}
