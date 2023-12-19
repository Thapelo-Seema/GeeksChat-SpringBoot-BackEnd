package com.geeks4L.chat_server.repositories;

import com.geeks4L.chat_server.models.auth.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthRepository extends JpaRepository<LoginEntity, Long> {
    @Query("SELECT l FROM LoginEntity l JOIN l.user u WHERE u.email = :userEmail")
    public LoginEntity findByUserEmail(@Param("userEmail") String userEmail);
}
