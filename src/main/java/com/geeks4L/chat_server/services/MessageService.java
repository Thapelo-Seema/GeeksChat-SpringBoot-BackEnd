package com.geeks4L.chat_server.services;

import com.geeks4L.chat_server.mappers.MessageMapper;
import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.models.messaging.MessageEntity;
import com.geeks4L.chat_server.models.messaging.MessageRequest;
import com.geeks4L.chat_server.models.messaging.MessageResponse;
import com.geeks4L.chat_server.models.users.UserEntity;
import com.geeks4L.chat_server.repositories.MessageRepository;
import com.geeks4L.chat_server.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

//Todo: Need to implement encryption, exception handling and design patterns to logic
@Service
public class MessageService {
    @Autowired
    MessageRepository msgRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MessageMapper messageMapper;

    public ResponseObject<MessageResponse> saveMessage(MessageRequest messageRequest){
        MessageEntity messageEntity = this.messageMapper.map(messageRequest);
        if(messageEntity == null)
            return new ResponseObject<MessageResponse>(Status.NOT_FOUND, "Contact or user not on system", null);
        try{
            this.msgRepository.saveAndFlush(messageEntity);
            MessageResponse messageResponse = this.messageMapper.map(messageEntity);
            return  new ResponseObject<MessageResponse>(Status.SUCCESS, "Message sent", messageResponse);
        }catch (DataAccessException e){
            return new ResponseObject<MessageResponse>(Status.UNSUCCESSFUL, "Message not sent", null);
        }
    }

    public List<MessageEntity> getMessagesBetweenUsers(Long user1Id, Long user2Id){
        UserEntity user1 = this.userRepository.findById(user1Id).orElse(null);
        UserEntity user2 = this.userRepository.findById(user2Id).orElse(null);
        if(user1 != null && user2 != null){
            return this.msgRepository.findBySenderAndReceiverOrReceiverAndSenderOrderByCreatedAt(user1, user2, user2, user1);
        }
        return null;
    }
}
