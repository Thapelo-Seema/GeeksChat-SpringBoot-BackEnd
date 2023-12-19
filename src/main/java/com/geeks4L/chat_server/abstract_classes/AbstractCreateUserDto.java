package com.geeks4L.chat_server.abstract_classes;

import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.users.UserResponse;
import lombok.Data;

@Data
public abstract class AbstractCreateUserDto {
    protected Status status;
    protected String message;
    protected UserResponse userResponse;


    public AbstractCreateUserDto(Status status, String message, UserResponse user){
        this.status = status;
        this.message = message;
        this.userResponse = user;
    }

    public AbstractCreateUserDto(){
        this.status = Status.EXISTS_ALREADY;
        this.message = "This user already exists";
        this.userResponse = null;

    }
}
