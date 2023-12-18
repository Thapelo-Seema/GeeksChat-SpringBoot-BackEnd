package com.geeks4L.chat_server.controllers.user_crud;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractCreateUserDto;
import com.geeks4L.chat_server.models.users.CreateUserResponse;
import com.geeks4L.chat_server.models.users.UserRequest;
import com.geeks4L.chat_server.models.users.UserResponse;
import com.geeks4L.chat_server.models.users.CreateUserRequest;
import com.geeks4L.chat_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//For registration and CRUD of users
@RestController
@CrossOrigin
@RequestMapping("geeks-chat/api/v1")
public class UserCrudController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserRecord(@PathVariable Long id) {
        // Get user record by ID
        Optional<UserResponse> userResponse = this.userService.getUserById(id);
        // Check if the user is present and return the appropriate ResponseEntity
        return userResponse.map(user -> ResponseEntity.ok().body(userResponse.get()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public void updateUserRecord(@RequestBody UserRequest userRequest){

    }

    @GetMapping()
    public  void getUsersContacts(){

    }

    @PostMapping("/register")
    public CreateUserResponse registerUser(@RequestBody CreateUserRequest createUserRequest){
        System.out.println(createUserRequest);
        return this.userService.createUser(createUserRequest);
    }


}
