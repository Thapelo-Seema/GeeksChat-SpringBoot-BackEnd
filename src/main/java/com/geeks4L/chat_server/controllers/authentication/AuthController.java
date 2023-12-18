package com.geeks4L.chat_server.controllers.authentication;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractLoginDto;
import com.geeks4L.chat_server.models.auth.LoginRequest;
import com.geeks4L.chat_server.models.auth.LoginResponse;
import com.geeks4L.chat_server.models.users.UserEntity;
import com.geeks4L.chat_server.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("geeks-chat/api/v1")
@CrossOrigin
public class AuthController {
    @Autowired
    AuthService authService;
    @PostMapping("/login")
    public UserEntity login(@RequestBody LoginRequest loginRequest){
        return this.authService.login(loginRequest);
    }
}
