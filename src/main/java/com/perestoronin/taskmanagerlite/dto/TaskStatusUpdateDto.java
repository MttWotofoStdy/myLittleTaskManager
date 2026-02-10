package com.perestoronin.taskmanagerlite.dto;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskStatusUpdateDto {
    @Valid
    @NotNull
    private TaskStatus status;
}
