package com.geeks4L.chat_server.services;

import com.geeks4L.chat_server.interfaces.IAuthService;
import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.repositories.AuthRepository;
import com.geeks4L.chat_server.repositories.UserRepository;
import com.geeks4L.chat_server.models.auth.LoginEntity;
import com.geeks4L.chat_server.models.auth.LoginRequest;
import com.geeks4L.chat_server.models.auth.LoginResponse;
import com.geeks4L.chat_server.models.enums.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Todo: Need to implement encryption, exception handling and design patterns to logic
@Service
public class AuthService implements IAuthService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public ResponseObject<LoginResponse> login(LoginRequest loginRequest){
        ResponseObject<LoginResponse> requestErrors = this.getRequestErrors(loginRequest);
        if(requestErrors != null) return requestErrors;
        LoginResponse loginResponse = this.modelMapper.map(
                this.userRepository.findByEmail(loginRequest.getEmail()), LoginResponse.class);
        return new ResponseObject<>(Status.SUCCESS, "Login success", loginResponse);
    }


    //---------------------------------- Helper methods -----------------------------------------------------
    private ResponseObject<LoginResponse> getRequestErrors(LoginRequest loginRequest){
        //does that username and password exist
        LoginEntity loginEntity = this.authRepository.findByUserEmail(loginRequest.getEmail());
        if(loginEntity == null || !loginEntity.getPassword().equals(loginRequest.getPassword()))
            return new ResponseObject<>(Status.NOT_FOUND,
                    "Incorrect credentials entered", null);
        if(this.userRepository.findByEmail(loginRequest.getEmail()) == null)
            return new ResponseObject<>(Status.NOT_FOUND,
                    "User with this email does not exist", null);
        return null;
    }
}
