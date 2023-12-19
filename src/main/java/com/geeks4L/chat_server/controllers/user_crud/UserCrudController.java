package com.geeks4L.chat_server.controllers.user_crud;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractCreateUserDto;
import com.geeks4L.chat_server.models.users.*;
import com.geeks4L.chat_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//For registration and CRUD of users
@RestController
@CrossOrigin
@RequestMapping("geeks-chat/api/v1")
public class UserCrudController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
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

    @GetMapping("/users/{currentUserId}")
    public List<UserEntity> getUsersContacts(@PathVariable Long currentUserId){
        return this.userService.getUsersContacts(currentUserId);
    }

    @PostMapping("/register")
    public CreateUserResponse registerUser(@RequestBody CreateUserRequest createUserRequest){
        return this.userService.createUser(createUserRequest);
    }


}
