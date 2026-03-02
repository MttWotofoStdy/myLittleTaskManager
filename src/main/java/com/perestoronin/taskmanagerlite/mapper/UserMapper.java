package com.perestoronin.taskmanagerlite.mapper;

import com.perestoronin.taskmanagerlite.dto.users.CreateUserRequest;
import com.perestoronin.taskmanagerlite.dto.users.EditUserRequest;
import com.perestoronin.taskmanagerlite.dto.users.GetUserRequest;
import com.perestoronin.taskmanagerlite.entity.Grade;
import com.perestoronin.taskmanagerlite.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public GetUserRequest toFullUser(User user) {
        GetUserRequest userReturn = new GetUserRequest();
        userReturn.setId(user.getId());
        userReturn.setName(user.getName());
        userReturn.setGrade(user.getGrade());
        return userReturn;
    }

    public User toUser(CreateUserRequest cur) {
        User thisNewUser = new User();
        thisNewUser.setName(cur.getName());
        thisNewUser.setGrade(Grade.Member);
        return thisNewUser;
    }

    public EditUserRequest toEditUserRequest(User user) {
        EditUserRequest curGUR = new EditUserRequest();
        curGUR.setName(user.getName());
        curGUR.setGrade(user.getGrade());
        return curGUR;
    }

}
