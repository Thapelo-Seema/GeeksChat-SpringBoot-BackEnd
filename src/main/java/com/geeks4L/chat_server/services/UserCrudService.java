package com.geeks4L.chat_server.services;

import com.geeks4L.chat_server.interfaces.IUserCrudService;
import com.geeks4L.chat_server.models.enums.Status;
import com.geeks4L.chat_server.models.generics.ResponseObject;
import com.geeks4L.chat_server.models.users.*;
import com.geeks4L.chat_server.repositories.AuthRepository;
import com.geeks4L.chat_server.repositories.UserRepository;
import com.geeks4L.chat_server.models.auth.LoginEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Todo: Need to implement encryption, exception handling and design patterns to logic
@Service
public class UserCrudService implements IUserCrudService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    ModelMapper modelMapper;

    public ResponseObject<UserResponse> getUserById(Long id){
        UserEntity userEntity = this.userRepository.findById(id).orElse(null);
        if(userEntity == null)
            return new ResponseObject<UserResponse>(Status.NOT_FOUND, "User with this id is not found",  null);
        UserResponse userResponse = (this.modelMapper.map(userEntity, UserResponse.class));
        return new ResponseObject<UserResponse>(Status.SUCCESS, "User found", userResponse);
    }

    public ResponseObject<CreateUserResponse> createUser(CreateUserRequest createUserRequest){
        ResponseObject<CreateUserResponse> responseObject = getRequestErrors(createUserRequest);
        if(responseObject != null) return responseObject;
        try{
            CreateUserResponse createUserResponse = this.createAndSaveUserEntity(createUserRequest);
            boolean loginCreated = this.createAndSaveLoginEntity(createUserRequest, createUserResponse);
            if(!loginCreated)
                throw new DataAccessResourceFailureException("No user entity found before creating login entity");
            return new ResponseObject<>(Status.SUCCESS, "Registration Successful", createUserResponse);
        }catch (DataAccessException e){
            return new ResponseObject<>(Status.UNSUCCESSFUL, "Couldn't save user", null);
        }
    }

    //Todo: implement method properly and make it to return ResponseObject
    public UserResponse updateUser(UserRequest userRequest){
        if(this.userRepository.findById(userRequest.getId()).isPresent() && userRequest.hasNoNulls()){
            UserEntity userEntity = this.modelMapper.map(userRequest, UserEntity.class);
            LoginEntity loginEntity = new LoginEntity();
            try{
                this.userRepository.saveAndFlush(userEntity);
                return this.modelMapper.map(userEntity, UserResponse.class);
            }catch (DataAccessException e){
               return  null;
            }
        }
        return null;
    }

    //Todo: implement method properly and make it to return ResponseObject
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

    //Todo: implement method properly and make it to return ResponseObject
    public List<UserEntity> getUsersContacts(Long id){
        return this.userRepository.findAll();
    }


    /*------------------------ Private Helper methods -------------------------*/


    private CreateUserResponse createAndSaveUserEntity(CreateUserRequest userRequest){
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        this.userRepository.saveAndFlush(userEntity);
        return this.modelMapper.map(userEntity, CreateUserResponse.class);
    }


    private boolean createAndSaveLoginEntity(CreateUserRequest createUserRequest, CreateUserResponse createUserResponse){
        UserEntity userEntity = this.userRepository.findById(createUserResponse.getId()).orElse(null);
        if(userEntity == null) return false; //make sure that UserEntity exists before creating LoginEntity
        LoginEntity loginEntity = new LoginEntity(null, userEntity, createUserRequest.getPassword(), LocalDateTime.now());
        this.authRepository.saveAndFlush(loginEntity);
        return  true;
    }

    private ResponseObject<CreateUserResponse> getRequestErrors(CreateUserRequest createUserRequest){
        if(!createUserRequest.hasNoNulls())
            return new ResponseObject<>(Status.INVALID_DATA, "Incomplete fields", null);
        if(this.userRepository.findByEmail(createUserRequest.getEmail()) != null)
            return new ResponseObject<>(Status.EXISTS_ALREADY, "This user already exists", null);
        return  null;
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
