package com.geeks4L.chat_server.repositories;

import com.geeks4L.chat_server.models.messaging.MessageEntity;
import com.geeks4L.chat_server.models.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySenderAndReceiverOrReceiverAndSenderOrderByCreatedAt(
            UserEntity sender, UserEntity receiver, UserEntity receiver2, UserEntity sender2);
}
