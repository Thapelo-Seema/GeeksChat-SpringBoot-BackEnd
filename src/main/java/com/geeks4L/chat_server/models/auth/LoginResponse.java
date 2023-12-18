package com.geeks4L.chat_server.models.auth;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractLoginDto;
import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.users.UserResponse;
import lombok.Data;

@Data
public class LoginResponse extends AbstractLoginDto {
    public LoginResponse(){
        super();
    }
    public LoginResponse(Status status, String message, UserResponse user){
        super(status, message, user);
    }


}
