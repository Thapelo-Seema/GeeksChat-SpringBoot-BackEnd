package com.geeks4L.chat_server.repositories;

import com.geeks4L.chat_server.models.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);
    public UserEntity findByHandle(String handle);


}
