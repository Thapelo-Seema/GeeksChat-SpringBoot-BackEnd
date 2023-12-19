package com.geeks4L.chat_server.interfaces;

import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.models.users.CreateUserRequest;
import com.geeks4L.chat_server.models.users.CreateUserResponse;
import com.geeks4L.chat_server.models.users.UserEntity;
import com.geeks4L.chat_server.models.users.UserResponse;

import java.util.List;

public interface IUserCrudService {
    List<UserEntity> getUsersContacts(Long id);
    ResponseObject<CreateUserResponse> createUser(CreateUserRequest createUserRequest);
    ResponseObject<UserResponse> getUserById(Long id);
}
