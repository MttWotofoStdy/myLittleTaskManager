package com.perestoronin.taskmanagerlite.controller;

import com.perestoronin.taskmanagerlite.dto.users.CreateUserRequest;
import com.perestoronin.taskmanagerlite.dto.users.GetUserRequest;
import com.perestoronin.taskmanagerlite.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping
    public ResponseEntity<List<GetUserRequest>> getAllUsers() {
        return ResponseEntity.ok(userservice.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Valid @Min(1) Long id){
        userservice.deleteUser(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping
   public ResponseEntity<GetUserRequest> createUser(@Valid @RequestBody CreateUserRequest cur){
        return ResponseEntity.ok(userservice.createUser(cur));
    }

}
