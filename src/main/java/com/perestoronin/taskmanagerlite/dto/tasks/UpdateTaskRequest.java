package com.perestoronin.taskmanagerlite.dto.tasks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateTaskRequest {

    @NotNull(message = "нельзя сохранить запись без имени")
    @NotBlank
    private String name;

    private String description;
}
