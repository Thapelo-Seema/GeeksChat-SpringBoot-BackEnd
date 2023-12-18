package com.geeks4L.chat_server.models.messaging;

import com.geeks4L.chat_server.models.users.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    @JoinColumn(name = "sender_id")
    private UserEntity sender;
    @OneToMany
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;
    private String txtContent;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
