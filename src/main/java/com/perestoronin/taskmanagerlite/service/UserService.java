package com.perestoronin.taskmanagerlite.service;

import com.perestoronin.taskmanagerlite.dto.users.CreateUserRequest;
import com.perestoronin.taskmanagerlite.dto.users.EditUserRequest;
import com.perestoronin.taskmanagerlite.dto.users.GetUserRequest;

import com.perestoronin.taskmanagerlite.entity.User;
import com.perestoronin.taskmanagerlite.exception.usersexception.UserNotFoundException;
import com.perestoronin.taskmanagerlite.mapper.UserMapper;
import com.perestoronin.taskmanagerlite.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class    UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepositoryInj, UserMapper userMapper) {
        this.userRepository = userRepositoryInj;
        this.userMapper = userMapper;
    }

    @Transactional
    public List<GetUserRequest> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toFullUser).toList();

    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public GetUserRequest getCurrentUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toFullUser(user);
    }

    @Transactional
    public GetUserRequest createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.save(userMapper.toUser(createUserRequest));
        return userMapper.toFullUser(user);
    }

    @Transactional
    public EditUserRequest editUser(Long id, EditUserRequest edUs) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       user.setName(edUs.getName());
        if (edUs.getGrade() != null) {
            user.setGrade(edUs.getGrade());
        }
        user.setName(edUs.getName());
        userRepository.save(user);
        return userMapper.toEditUserRequest(user);
    }
}
