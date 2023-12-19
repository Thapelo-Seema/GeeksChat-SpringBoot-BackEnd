package com.geeks4L.chat_server.abstract_classes;

import com.geeks4L.chat_server.models.enums.Status;
import lombok.Data;

@Data
public abstract class AbstractResponseDto {
    public Status status;
    public String message;
}
