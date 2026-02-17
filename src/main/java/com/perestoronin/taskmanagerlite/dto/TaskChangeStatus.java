package com.perestoronin.taskmanagerlite.dto;

import com.perestoronin.taskmanagerlite.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskChangeStatus {

    @NotNull
    private TaskStatus status;
}
