package com.geeks4L.chat_server.models.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMessage {
    protected Long sender;
    protected Long receiver;
    protected String txtContent;
    protected String topic;
    protected LocalDateTime createdAt;
}
