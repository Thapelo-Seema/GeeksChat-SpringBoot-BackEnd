package com.geeks4L.chat_server.abstractions.abstract_classes;

import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.users.UserResponse;

public abstract class AbstractLoginDto {
    protected Status status;
    protected String message;
    protected UserResponse userResponse;
    public AbstractLoginDto(){
        this.status = Status.NOT_FOUND;
        this.message = "User credentials incorrect";
        this.userResponse = null;
    }

    public AbstractLoginDto(Status status, String message, UserResponse user){
        this.status = status;
        this.userResponse = user;
        this.message = message;
    }
}
