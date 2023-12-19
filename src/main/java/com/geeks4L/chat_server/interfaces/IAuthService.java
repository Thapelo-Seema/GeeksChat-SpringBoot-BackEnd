package com.geeks4L.chat_server.interfaces;

import com.geeks4L.chat_server.models.auth.LoginRequest;
import com.geeks4L.chat_server.models.auth.LoginResponse;
import com.geeks4L.chat_server.models.generics.ResponseObject;

public interface IAuthService {
    public ResponseObject<LoginResponse> login(LoginRequest loginRequest);
}
