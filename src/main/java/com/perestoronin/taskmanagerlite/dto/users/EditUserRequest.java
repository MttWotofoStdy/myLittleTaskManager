package com.perestoronin.taskmanagerlite.dto.users;

import com.perestoronin.taskmanagerlite.entity.Grade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EditUserRequest {

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private Grade grade;

}
