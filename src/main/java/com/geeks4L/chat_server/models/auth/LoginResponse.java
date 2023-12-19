package com.geeks4L.chat_server.models.auth;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponse{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String handle;
    private LocalDateTime lastModified;
}
