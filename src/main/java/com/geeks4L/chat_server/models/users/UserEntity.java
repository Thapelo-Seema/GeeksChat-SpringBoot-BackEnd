package com.geeks4L.chat_server.models.users;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String handle;
    private LocalDateTime lastModified;

    public void setLastModified(){
        this.lastModified = LocalDateTime.now();
    }
}
