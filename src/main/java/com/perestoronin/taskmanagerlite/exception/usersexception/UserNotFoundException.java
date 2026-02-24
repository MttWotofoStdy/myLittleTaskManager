package com.perestoronin.taskmanagerlite.exception.usersexception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Пользователь с id " + id  + " не найдён");
    }
}
