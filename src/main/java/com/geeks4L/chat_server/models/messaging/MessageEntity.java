package com.geeks4L.chat_server.models.messaging;

import com.geeks4L.chat_server.models.users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;
    private String txtContent;
    private String topic;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private boolean read;
    private boolean received;
}
