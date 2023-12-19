package com.geeks4L.chat_server.models.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String handle;
    protected LocalDateTime lastModified;

    public boolean hasNoNulls(){
        return id != null &&
                !firstName.isEmpty() &&
                !lastName.isEmpty() &&
                !email.isEmpty() &&
                !handle.isEmpty() &&
                lastModified != null;
    }

}
