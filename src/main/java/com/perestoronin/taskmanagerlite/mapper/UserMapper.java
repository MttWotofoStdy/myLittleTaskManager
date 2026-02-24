package com.perestoronin.taskmanagerlite.mapper;

import com.perestoronin.taskmanagerlite.dto.users.CreateUserRequest;
import com.perestoronin.taskmanagerlite.dto.users.GetUserRequest;
import com.perestoronin.taskmanagerlite.entity.Grade;
import com.perestoronin.taskmanagerlite.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public GetUserRequest toFullUser(Users user) {
        GetUserRequest userReturn = new GetUserRequest();
        userReturn.setId(user.getId());
        userReturn.setName(user.getName());
        userReturn.setGrade(Grade.Member);
        return userReturn;
    }
    public Users toUser(CreateUserRequest cur){
        Users thisNewUser = new Users();
        thisNewUser.setName(cur.getName());
        thisNewUser.setGrade(Grade.Member);
        return thisNewUser;
    }


}
