package com.geeks4L.chat_server.services;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractLoginDto;
import com.geeks4L.chat_server.repositories.AuthRepository;
import com.geeks4L.chat_server.repositories.UserRepository;
import com.geeks4L.chat_server.models.auth.LoginEntity;
import com.geeks4L.chat_server.models.auth.LoginRequest;
import com.geeks4L.chat_server.models.auth.LoginResponse;
import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.users.UserResponse;
import com.geeks4L.chat_server.models.users.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public UserEntity login(LoginRequest loginRequest){
        LoginEntity loginEntity = this.authRepository.findByEmail(loginRequest.getEmail());
        if(loginEntity == null) return null;
        if(loginRequest.getPassword().equals(loginEntity.getPassword())){
            return this.userRepository.findByEmail(loginRequest.getEmail());
        }
        return null;
    }

    private boolean doesAuthExist(String email){
        return this.authRepository.findByEmail(email) != null;
    }
}
