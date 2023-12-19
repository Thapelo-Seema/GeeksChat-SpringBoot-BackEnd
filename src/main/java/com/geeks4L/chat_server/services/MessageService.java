package com.geeks4L.chat_server.services;

import com.geeks4L.chat_server.models.messaging.MessageEntity;
import com.geeks4L.chat_server.models.users.UserEntity;
import com.geeks4L.chat_server.repositories.MessageRepository;
import com.geeks4L.chat_server.repositories.UserRepository;
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

    public boolean saveMessage(MessageEntity messageEntity){
            try{
                MessageEntity msg = this.msgRepository.save(messageEntity);
                return  true;
            }catch (DataAccessException e){
                return false;
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
