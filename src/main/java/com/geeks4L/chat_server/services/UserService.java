package com.geeks4L.chat_server.services;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractCreateUserDto;
import com.geeks4L.chat_server.models.users.*;
import com.geeks4L.chat_server.repositories.AuthRepository;
import com.geeks4L.chat_server.repositories.UserRepository;
import com.geeks4L.chat_server.models.auth.LoginEntity;
import com.geeks4L.chat_server.models.enums.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    ModelMapper modelMapper;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        //find better way to return a meaningful result
        if(this.doesUserExist(createUserRequest)) return null;
        this.createAndSaveLoginEntity(createUserRequest);
        return createAndSaveUserEntity(createUserRequest);
    }

    //Todo: Remember to use Abstraction
    public Optional<UserResponse> getUserById(Long id){
        Optional<UserEntity> chatUserEntity = this.userRepository.findById(id);
        return Optional.of(this.modelMapper.map(chatUserEntity, UserResponse.class));
    }

    public UserResponse updateUser(UserRequest userRequest){
        if(this.userRepository.findById(userRequest.getId()).isPresent() && userRequest.hasNoNulls()){
            UserEntity userEntity = this.modelMapper.map(userRequest, UserEntity.class);
            try{
                this.userRepository.saveAndFlush(userEntity);
                return this.modelMapper.map(userEntity, UserResponse.class);
            }catch (DataAccessException e){
               return  null;
            }
        }
        return null;
    }

    public boolean deleteUser(Long id){
        if(this.userRepository.findById(id).isPresent()){
           try{
               this.userRepository.deleteById(id);
               return true;
           }catch(DataAccessException e){
               return  false;
           }
        }
        return false;
    }



    /*------------------------ Private Helper methods -------------------------*/


    private CreateUserResponse createAndSaveUserEntity(CreateUserRequest userRequest){
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        this.userRepository.saveAndFlush(userEntity);
        return this.modelMapper.map(userEntity, CreateUserResponse.class);
    }


    private void createAndSaveLoginEntity(CreateUserRequest userRequest){
        LoginEntity userLogin = modelMapper.map(userRequest, LoginEntity.class);
        this.authRepository.saveAndFlush(userLogin);
    }

    //Single Responsibility on a method-level  and polymorphism (static)
    private boolean doesUserExist(CreateUserRequest createUserRequest){
        return userRepository.findByEmail(createUserRequest.getEmail()) != null;
    }
    private boolean doesUserExist(String email){
        return userRepository.findByEmail(email) != null;
    }
    private boolean doesUserExist(Long id){
        return  this.userRepository.findById(id).isPresent();
    }
}
