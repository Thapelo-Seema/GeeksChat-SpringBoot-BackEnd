package com.geeks4L.chat_server.models.users;

import com.geeks4L.chat_server.abstractions.abstract_classes.AbstractCreateUserDto;
import com.geeks4L.chat_server.models.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class CreateUserResponse {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String handle;
    protected Date lastModified;

    public boolean hasNoNulls(){
        return id != null &&
                !firstName.isEmpty() &&
                !lastName.isEmpty() &&
                !email.isEmpty() &&
                !handle.isEmpty() &&
                lastModified != null;
    }

}
