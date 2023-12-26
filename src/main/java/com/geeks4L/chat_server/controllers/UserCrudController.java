package com.geeks4L.chat_server.controllers;

import com.geeks4L.chat_server.interfaces.IUserCrudService;
import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.models.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//For registration and CRUD of users
//Todo: Need to return more descriptive response objects that have a status, message, and data
@RestController
@CrossOrigin
@RequestMapping("geeks-chat/api/v1")
public class UserCrudController {
    @Autowired
    IUserCrudService userService;

    @GetMapping("/user/{id}")
    public ResponseObject<UserResponse> getUserRecord(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    //Todo: implement method and make it to return ResponseObject
    @PostMapping("/users")
    public void updateUserRecord(@RequestBody UserRequest userRequest){

    }

    //Todo: create implementation that returns ResponseObject<List<UserResponse>>
    @GetMapping("/users/{currentUserId}")
    public List<UserEntity> getUsersContacts(@PathVariable Long currentUserId){
        return this.userService.getUsersContacts(currentUserId);
    }

    @PostMapping("/register")
    public ResponseObject<CreateUserResponse> registerUser(@RequestBody CreateUserRequest createUserRequest){
        return this.userService.createUser(createUserRequest);
    }


}
