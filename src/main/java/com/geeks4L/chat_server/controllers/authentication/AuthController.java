package com.geeks4L.chat_server.controllers.authentication;

import com.geeks4L.chat_server.interfaces.IAuthService;
import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.models.auth.LoginRequest;
import com.geeks4L.chat_server.models.auth.LoginResponse;
import com.geeks4L.chat_server.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("geeks-chat/api/v1")
@CrossOrigin
public class AuthController {
    //Todo: Need to return more descriptive response objects that have a status, message, and data
    @Autowired
    IAuthService authService;  //decoupling decision (not tied to any specific implementation)
    @PostMapping("/login")
    public ResponseObject<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return this.authService.login(loginRequest);
    }
}
