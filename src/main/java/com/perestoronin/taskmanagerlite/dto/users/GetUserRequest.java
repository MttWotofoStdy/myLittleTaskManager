package com.perestoronin.taskmanagerlite.dto.users;

import com.perestoronin.taskmanagerlite.entity.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRequest {
    private Long id;
    private String name;
    private Grade grade;
}
