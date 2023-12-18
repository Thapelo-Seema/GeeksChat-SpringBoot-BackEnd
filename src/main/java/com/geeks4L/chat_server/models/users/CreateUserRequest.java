package com.geeks4L.chat_server.models.users;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String handle;
    private String password;
}
