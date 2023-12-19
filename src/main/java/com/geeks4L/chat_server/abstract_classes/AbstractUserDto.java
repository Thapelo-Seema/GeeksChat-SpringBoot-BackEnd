package com.geeks4L.chat_server.abstract_classes;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractUserDto {
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
