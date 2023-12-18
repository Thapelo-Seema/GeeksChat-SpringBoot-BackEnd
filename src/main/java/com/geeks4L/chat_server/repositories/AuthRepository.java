package com.geeks4L.chat_server.repositories;

import com.geeks4L.chat_server.models.auth.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<LoginEntity, Long> {
    public LoginEntity findByEmail(String email);
}
