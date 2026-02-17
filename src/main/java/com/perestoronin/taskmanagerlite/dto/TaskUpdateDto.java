package com.perestoronin.taskmanagerlite.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskUpdateDto {

    @NotNull(message = "Пустое имя")
    private String name;

    private String description;
}
