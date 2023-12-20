package com.geeks4L.chat_server.models.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true) //account for fields in the superclass when generating equals and hashCode methods
@Data
public class MessageResponse extends BaseMessage {
    private Long id;
    private boolean read;
    private boolean received;
    public MessageResponse(Long id, Long sender, Long receiver, String txtContent, String topic,
                           LocalDateTime createdAt, boolean read, boolean received){
        super(sender, receiver, txtContent, topic, createdAt);
        this.id = id;
        this.read = read;
        this.received = received;
    }
}
