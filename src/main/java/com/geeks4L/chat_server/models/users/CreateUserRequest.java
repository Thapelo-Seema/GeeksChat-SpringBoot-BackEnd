package com.geeks4L.chat_server.models.users;

import com.geeks4L.chat_server.interfaces.ExistingEntityCheck;
import com.geeks4L.chat_server.interfaces.HasNoNulls;
import com.geeks4L.chat_server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest implements HasNoNulls{
    private String firstName;
    private String lastName;
    private String email;
    private String handle;
    private String password;

    public boolean hasNoNulls(){
        return !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !handle.isEmpty() && !password.isEmpty();
    }
}
