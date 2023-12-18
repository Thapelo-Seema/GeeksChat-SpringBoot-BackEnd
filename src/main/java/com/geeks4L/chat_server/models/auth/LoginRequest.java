package com.geeks4L.chat_server.models.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
