package com.perestoronin.taskmanagerlite.dto.tasks;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskUpdateDto {


    private String name;

    private String description;
}
